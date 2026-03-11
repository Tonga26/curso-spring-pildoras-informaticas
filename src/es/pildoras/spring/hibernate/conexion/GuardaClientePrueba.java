package es.pildoras.spring.hibernate.conexion;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * SECCIÓN: CLASE PRINCIPAL DE EJECUCIÓN HIBERNATE
 * Esta clase levanta el entorno de Hibernate, abre la conexión,
 * ejecuta la inserción del objeto, lee ese mismo objeto y cierra los recursos.
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
         */
        SessionFactory miFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Cliente.class)
                .buildSessionFactory();

        // =====================================================================
        // PASO 2: CREAR LA SESIÓN (EL TÚNEL DE COMUNICACIÓN)
        // =====================================================================
        Session miSession = miFactory.openSession();

        try {
            // =================================================================
            // PASO 3: CREAR EL OBJETO JAVA (LA ENTIDAD)
            // =================================================================
            Cliente cliente1 = new Cliente("Sandra", "Delgado", "Goya");

            // =================================================================
            // PASO 4: INICIAR LA TRANSACCIÓN PARA GUARDAR
            // =================================================================
            miSession.beginTransaction();

            // =================================================================
            // PASO 5: GUARDAR EL OBJETO (OPERACIÓN CREATE / INSERT)
            // =================================================================
            miSession.save(cliente1);

            // =================================================================
            // PASO 6: CONFIRMAR LA TRANSACCIÓN (COMMIT)
            // =================================================================
            // Al hacer commit, la inserción se ejecuta en MySQL.
            // Hibernate detecta el ID autogenerado en la base de datos
            // y automáticamente actualiza la variable 'cliente1' inyectándole ese ID.
            miSession.getTransaction().commit();

            System.out.println("Registro insertado correctamente en la base de datos.");

            // =================================================================
            // SECCIÓN: LECTURA DE REGISTRO (OPERACIÓN READ / SELECT)
            // =================================================================

            // 1. Iniciamos una nueva transacción.
            // Aunque solo vamos a leer, es buena práctica en Hibernate envolver
            // todas las interacciones con la base de datos en transacciones.
            miSession.beginTransaction();

            // Usamos cliente1.getId() para ver qué número le asignó MySQL automáticamente.
            System.out.println("Lectura del registro con id: " + cliente1.getId());

            // 2. Operación GET (El equivalente a: SELECT * FROM clientes WHERE id = ?)
            // miSession.get() requiere dos parámetros:
            // - La clase de la entidad que queremos mapear (Clientes.class)
            // - La clave primaria o ID que queremos buscar en la tabla.
            // Devuelve un objeto Java completamente poblado con los datos de la BD.
            Cliente clienteInsertado = miSession.get(Cliente.class, cliente1.getId());

            // 3. Imprimimos el objeto por consola.
            // Al haber sobrescrito el método toString() en la clase Clientes,
            // veremos el texto formateado con los datos y no un código de memoria.
            System.out.println("Registro recuperado: " + clienteInsertado);

            // 4. Confirmamos la transacción de lectura.
            miSession.getTransaction().commit();

            System.out.println("Terminado");

            // Cerramos la sesión actual (liberamos el túnel JDBC)
            miSession.close();

        } finally {
            // Cerramos la fábrica pesada al terminar el programa
            miFactory.close();
        }
    }
}