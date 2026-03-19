package es.pildoras.spring.hibernate.relaciones;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * =========================================================================
 * SECCIÓN: CLASE DE PRUEBA - LECTURA DE PEDIDOS (READ - RELACIÓN 1:N)
 * =========================================================================
 * Clase ejecutable que demuestra la recuperación de un {@link Cliente} y
 * la navegación por su colección de {@link Pedido} asociados mediante
 * la relación {@code @OneToMany} configurada en la entidad {@link Cliente}.
 *
 * <p>Flujo de ejecución:</p>
 * <ol>
 *   <li>Recupera un {@link Cliente} existente por su ID desde la base de datos.</li>
 *   <li>Accede a su lista de pedidos a través del getter {@code getPedidos()}.
 *       Hibernate ejecuta automáticamente un SELECT con JOIN sobre la tabla
 *       {@code pedido} filtrando por {@code cliente_id}.</li>
 *   <li>Imprime tanto los datos del cliente como los de todos sus pedidos.</li>
 * </ol>
 *
 * <p><b>Nota sobre Lazy Loading:</b> Por defecto, {@code @OneToMany} usa carga
 * diferida (LAZY), lo que significa que la consulta de pedidos se ejecuta
 * en el momento en que se accede a la lista, no al cargar el cliente.</p>
 */
public class ObtenerPedidosCliente {

    /**
     * Método principal de ejecución.
     * Configura la {@link SessionFactory} registrando todas las entidades
     * mapeadas del modelo relacional antes de abrir la sesión.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
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

            // Recupera el cliente existente desde la base de datos por su ID
            Cliente elCliente = miSession.get(Cliente.class, 2);

            // Navega la relación bidireccional para obtener todos sus pedidos
            System.out.println("Cliente: " + elCliente);
            System.out.println("Pedidos: " + elCliente.getPedidos());

            miSession.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            miSession.close();
            miFactory.close();
        }
    }
}