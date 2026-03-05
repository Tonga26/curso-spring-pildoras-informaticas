package es.pildoras.spring.hibernate.conexion;

// =========================================================================
// IMPORTS: Estrictamente del ecosistema org.hibernate
// =========================================================================
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * SECCIÓN: CLASE PRINCIPAL DE EJECUCIÓN HIBERNATE
 * Esta clase levanta el entorno de Hibernate, abre la conexión,
 * ejecuta la inserción del objeto y cierra los recursos.
 */
public class GuardaClientePrueba {

    public static void main (String[] args){

        // =====================================================================
        // PASO 1: CREAR EL SESSION FACTORY (LA FÁBRICA DE CONEXIONES)
        // =====================================================================
        /*
         * COMPARACIÓN CON JAVA EE:
         * En el desarrollo tradicional, esto equivaldría a configurar un "Connection Pool"
         * (un DataSource) pesado. El SessionFactory lee el archivo XML que creaste,
         * descubre dónde está la base de datos MySQL, con qué credenciales entrar,
         * y lee las anotaciones @Entity de tu clase Clientes.
         * NOTA: Este objeto es muy pesado. En una app web real, se crea UNA SOLA VEZ
         * al arrancar el servidor Tomcat.
         */
        SessionFactory miFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Clientes.class)
                .buildSessionFactory();

        // =====================================================================
        // PASO 2: CREAR LA SESIÓN (EL TÚNEL DE COMUNICACIÓN)
        // =====================================================================
        /*
         * La 'Session' equivale a la variable 'Connection' de JDBC que usamos
         * en la prueba anterior. Es un objeto ligero y de corta vida.
         * Se abre uno nuevo cada vez que queremos hacer una consulta a la BD.
         */
        Session miSession = miFactory.openSession();

        try {
            // =================================================================
            // PASO 3: CREAR EL OBJETO JAVA (LA ENTIDAD)
            // =================================================================
            // Instanciamos el objeto usando el constructor parametrizado.
            // Recuerda que el 'id' no se pasa porque MySQL lo autogenerará.
            Clientes cliente1 = new Clientes("Juan", "Diaz", "Gran Vía");

            // =================================================================
            // PASO 4: INICIAR LA TRANSACCIÓN
            // =================================================================
            /*
             * COMPARACIÓN CON JAVA EE:
             * En JDBC puro esto equivalía a 'conexion.setAutoCommit(false)'.
             * Le decimos a la base de datos: "Prepárate, voy a hacer operaciones,
             * pero no guardes nada definitivamente hasta que yo te avise".
             */
            miSession.beginTransaction();

            // =================================================================
            // PASO 5: GUARDAR EL OBJETO (LA MAGIA DEL ORM)
            // =================================================================
            /*
             * Aquí ocurre la magia. Hibernate toma nuestro objeto Java 'cliente1',
             * lee sus atributos, y en milisegundos genera el código SQL:
             * "INSERT INTO clientes (nombre, apellido, direccion) VALUES ('Juan', ...)"
             */
            miSession.save(cliente1);

            // =================================================================
            // PASO 6: CONFIRMAR LA TRANSACCIÓN (COMMIT)
            // =================================================================
            // Este es el aviso final a la base de datos para que los cambios sean permanentes.
            miSession.getTransaction().commit();

            System.out.println("Registro insertado correctamente en la base de datos.");

            // Cerramos la sesión actual (liberamos el túnel JDBC)
            miSession.close();

        } finally {
            // Cerramos la fábrica pesada al terminar el programa
            miFactory.close();
        }
    }
}