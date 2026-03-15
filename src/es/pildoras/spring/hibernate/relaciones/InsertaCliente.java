package es.pildoras.spring.hibernate.relaciones;

// =========================================================================
// IMPORTS DEL FRAMEWORK
// =========================================================================
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * SECCIÓN: INSERCIÓN CON RELACIONES 1 A 1 (CASCADA)
 * Esta clase demuestra el inmenso poder de la propiedad 'CascadeType.ALL'.
 * Nos permite guardar información en múltiples tablas de la base de datos
 * ejecutando una sola instrucción de guardado en Java.
 */
public class InsertaCliente {

    public static void main (String[] args){

        // =====================================================================
        // PASO 1: CONFIGURAR LA FÁBRICA (MULTI-ENTIDAD)
        // =====================================================================
        // Apuntamos al archivo XML de relaciones y, registramos AMBAS clases
        // con .addAnnotatedClass().
        SessionFactory miFactory = new Configuration()
                .configure("hibernate-relaciones.cfg.xml")
                .addAnnotatedClass(Cliente.class)
                .addAnnotatedClass(DetallesCliente.class)
                .buildSessionFactory();

        Session miSession = miFactory.openSession();

        try {
            // =================================================================
            // PASO 2: CREAR LOS OBJETOS INDEPENDIENTES EN MEMORIA
            // =================================================================
            Cliente cliente1 = new Cliente("Sandra", "Delgado", "Goya");
            DetallesCliente detallesCliente1 = new DetallesCliente("www.pildorasinformaticas.es", "546456456", "Primer Cliente");

            // =================================================================
            // PASO 3: ESTABLECER LA RELACIÓN (EL PUENTE JAVA)
            // =================================================================
            // En este punto asociamos los objetos. Le decimos al cliente quién
            // es su detalle. Esta es una relación unidireccional por ahora.
            cliente1.setDetallesCliente(detallesCliente1);

            // Iniciamos la transacción para comunicarnos con MySQL
            miSession.beginTransaction();

            // =================================================================
            // PASO 4: GUARDAR EN LA BASE DE DATOS (CASCADE)
            // =================================================================
            /*
             * Solo le estamos diciendo a Hibernate: "Guarda al cliente1".
             * Pero como en la clase Cliente configuramos @OneToOne(cascade=ALL),
             * Hibernate dice: "Espera, este cliente tiene un objeto Detalles adentro.
             * Primero voy a insertar los detalles, tomaré su ID, y luego insertaré
             * al cliente enlazándolo a ese ID".
             * Todo esto ocurre con una sola línea de código en Java.
             */
            miSession.save(cliente1);

            // Confirmamos los INSERTs múltiples
            miSession.getTransaction().commit();

            System.out.println("Registro insertado correctamente en ambas tablas de la base de datos.");

        } finally {
            miSession.close();
            miFactory.close();
        }
    }
}