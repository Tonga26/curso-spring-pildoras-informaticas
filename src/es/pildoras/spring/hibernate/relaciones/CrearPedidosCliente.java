package es.pildoras.spring.hibernate.relaciones;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.GregorianCalendar;

/**
 * =========================================================================
 * SECCIÓN: CLASE DE PRUEBA - CREACIÓN DE PEDIDOS (CREATE - RELACIÓN 1:N)
 * =========================================================================
 * Clase ejecutable que demuestra la creación y persistencia de múltiples
 * {@link Pedido} asociados a un {@link Cliente} existente en la base de datos.
 *
 * <p>Flujo de ejecución:</p>
 * <ol>
 *   <li>Recupera un {@link Cliente} existente por su ID desde la base de datos.</li>
 *   <li>Instancia tres objetos {@link Pedido} en estado transitorio con distintas fechas.</li>
 *   <li>Utiliza el método {@code agregarPedidos()} para sincronizar la relación
 *       bidireccional en memoria: el cliente conoce sus pedidos y cada pedido
 *       conoce a su cliente propietario.</li>
 *   <li>Persiste cada pedido individualmente. Hibernate escribe la Clave Foránea
 *       {@code cliente_id} en cada fila de la tabla {@code pedido}.</li>
 * </ol>
 *
 * <p><b>Nota:</b> {@link GregorianCalendar} usa meses con base 0,
 * por lo que enero = 0, febrero = 1, marzo = 2, etc.</p>
 */
public class CrearPedidosCliente {

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

            // Instanciación de pedidos en estado transitorio con sus respectivas fechas
            Pedido pedido1 = new Pedido(new GregorianCalendar(2026, 0, 4));
            Pedido pedido2 = new Pedido(new GregorianCalendar(2026, 1, 5));
            Pedido pedido3 = new Pedido(new GregorianCalendar(2026, 2, 6));

            // Sincroniza la relación bidireccional en memoria para cada pedido:
            // el cliente agrega el pedido a su lista y el pedido registra a su cliente propietario
            elCliente.agregarPedidos(pedido1);
            elCliente.agregarPedidos(pedido2);
            elCliente.agregarPedidos(pedido3);

            // Persiste cada pedido en la tabla 'pedido' de la base de datos
            miSession.save(pedido1);
            miSession.save(pedido2);
            miSession.save(pedido3);

            miSession.getTransaction().commit();
            System.out.println("Registro(s) insertado(s) correctamente en la base de datos.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            miSession.close();
            miFactory.close();
        }
    }
}