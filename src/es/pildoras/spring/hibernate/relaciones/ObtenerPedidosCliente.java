package es.pildoras.spring.hibernate.relaciones;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Clase ejecutable que demuestra la solución al problema de LAZY loading
 * tras el cierre de sesión, usando HQL con {@code JOIN FETCH}.
 *
 * <p><b>Contexto del problema:</b> Hibernate ofrece dos estrategias de carga
 * para las relaciones entre entidades:</p>
 * <ul>
 *   <li><b>EAGER:</b> carga la entidad y todas sus relaciones en una sola consulta.
 *       No produce errores al acceder a los datos fuera de sesión, pero es ineficiente
 *       porque siempre trae todos los datos aunque no se necesiten.</li>
 *   <li><b>LAZY (por defecto en @OneToMany):</b> carga solo la entidad principal.
 *       Las relaciones se consultan a la BD únicamente cuando se accede a ellas.
 *       Es eficiente, pero lanza {@code LazyInitializationException} si se intenta
 *       acceder a los datos relacionados después de cerrar la sesión.</li>
 * </ul>
 *
 * <p><b>¿Cuándo ocurre el error?</b> En aplicaciones reales con capas separadas
 * (controlador, servicio, repositorio), es muy común cerrar la sesión en la capa
 * de datos y luego intentar acceder a relaciones LAZY en la capa de presentación.
 * Ese es el escenario exacto que reproduce esta clase.</p>
 *
 * <p><b>Solución:</b> usar HQL con {@code JOIN FETCH}, que obliga a Hibernate a
 * traer el cliente y sus pedidos juntos en una única consulta SQL, dejando todo
 * en memoria antes de cerrar la sesión. Así la relación queda definida como LAZY
 * por defecto (eficiente), pero en las consultas que sí necesitan los datos
 * relacionados se usa {@code JOIN FETCH} para traerlos explícitamente.</p>
 *
 * <p><b>Patrón mental para reconocer el problema:</b></p>
 * <pre>
 *   EAGER         → siempre trae todo → sin errores, pero ineficiente
 *   LAZY          → trae solo lo necesario → eficiente, pero requiere sesión abierta
 *   JOIN FETCH    → trae todo en consultas específicas → lo mejor de ambos mundos
 * </pre>
 *
 * <p>En Spring Boot este problema se gestiona con {@code @Transactional} o el
 * patrón <i>Open Session in View</i>, que mantienen la sesión abierta durante
 * todo el ciclo de vida de una petición HTTP.</p>
 */
public class ObtenerPedidosCliente {

    public static void main(String[] args) {

        SessionFactory miFactory = new Configuration()
                .configure("hibernate-relaciones.cfg.xml")
                .addAnnotatedClass(Cliente.class)
                .addAnnotatedClass(DetallesCliente.class)
                .addAnnotatedClass(Pedido.class)
                .buildSessionFactory();

        Session miSession = miFactory.openSession();

        try {
            miSession.beginTransaction();

            // ─────────────────────────────────────────────────────────────────
            // SOLUCIÓN AL PROBLEMA LAZY: HQL con JOIN FETCH
            //
            // En lugar de miSession.get(Cliente.class, 1), que con FetchType.LAZY
            // solo cargaría el cliente y dejaría los pedidos sin cargar, usamos
            // una consulta HQL con JOIN FETCH.
            //
            // HQL (Hibernate Query Language) es el lenguaje de consultas propio
            // de Hibernate. Es similar a SQL pero trabaja con nombres de clases
            // y atributos Java en lugar de nombres de tablas y columnas.
            //
            // El JOIN FETCH le indica a Hibernate que, aunque la relación sea LAZY,
            // en esta consulta puntual debe traer también los pedidos del cliente.
            // Hibernate traduce esto internamente al siguiente SQL:
            //   SELECT * FROM cliente cl
            //   INNER JOIN pedido p ON cl.id = p.cliente_id
            //   WHERE cl.id = 1
            // ─────────────────────────────────────────────────────────────────
            Query<Cliente> consulta = miSession.createQuery(
                    "SELECT cl FROM Cliente cl JOIN FETCH cl.pedidos WHERE cl.id = :elClienteId",
                    Cliente.class);

            // :elClienteId es un parámetro nombrado en la consulta HQL.
            // setParameter() lo reemplaza por el valor real de forma segura,
            // previniendo inyección SQL.
            consulta.setParameter("elClienteId", 1);

            Cliente elCliente = consulta.getSingleResult();

            miSession.getTransaction().commit();

            // ─────────────────────────────────────────────────────────────────
            // Se cierra la sesión deliberadamente ANTES de acceder a los pedidos.
            // Esto simula el escenario real donde la sesión se cierra en la capa
            // de datos y los datos se usan después en la capa de presentación.
            //
            // Sin JOIN FETCH esto lanzaría LazyInitializationException aquí.
            // Con JOIN FETCH funciona porque los pedidos ya están en memoria.
            // ─────────────────────────────────────────────────────────────────
            miSession.close();

            System.out.println("Cliente: " + elCliente);
            System.out.println("Pedidos: " + elCliente.getPedidos());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Solo se cierra la factory aquí. La sesión ya fue cerrada
            // manualmente dentro del try para simular el escenario del problema.
            miFactory.close();
        }
    }
}