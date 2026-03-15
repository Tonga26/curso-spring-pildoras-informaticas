package es.pildoras.spring.hibernate.relaciones;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * SECCIÓN: ELIMINACIÓN EN CASCADA (DELETE)
 * Esta clase demuestra cómo la propiedad 'CascadeType.ALL' de la relación
 * @OneToOne nos permite eliminar registros en múltiples tablas encadenadas
 * a partir de un solo objeto Java.
 */
public class EliminaCliente {

    /**
     * Método principal que arranca la aplicación.
     * @param args Argumentos de consola (no utilizados en este script).
     */
    public static void main (String[] args){

        // =====================================================================
        // PASO 1: CONFIGURAR LA FÁBRICA Y ABRIR LA SESIÓN
        // =====================================================================
        // Instanciamos el Configuration, le pasamos el XML de relaciones y
        // registramos ambas clases. El método buildSessionFactory() construye la fábrica.
        SessionFactory miFactory = new Configuration()
                .configure("hibernate-relaciones.cfg.xml")
                .addAnnotatedClass(Cliente.class)
                .addAnnotatedClass(DetallesCliente.class)
                .buildSessionFactory();

        // openSession() abre un nuevo "túnel" de comunicación con MySQL.
        Session miSession = miFactory.openSession();

        try {

            // =================================================================
            // PASO 2: INICIAR LA TRANSACCIÓN
            // =================================================================
            // beginTransaction() prepara a Hibernate para ejecutar operaciones DML
            // (Data Manipulation Language) de forma segura.
            miSession.beginTransaction();

            // =================================================================
            // PASO 3: RECUPERAR EL OBJETO A ELIMINAR (READ)
            // =================================================================
            /*
             * Método get(Class, Serializable id):
             * Busca en la base de datos el registro con id = 3 de la tabla 'cliente'.
             * Si lo encuentra, lo transforma en un objeto Java. Si no, devuelve 'null'.
             */
            Cliente elCliente = miSession.get(Cliente.class, 3);

            // =================================================================
            // PASO 4: VALIDACIÓN Y ELIMINACIÓN (DELETE EN CASCADA)
            // =================================================================
            // Buena práctica: Siempre verificar que el objeto exista (no sea null)
            // antes de intentar borrarlo, de lo contrario Java lanzará un NullPointerException.
            if (elCliente != null){
                System.out.println("Voy a eliminar al cliente: " + elCliente.getNombre());

                /*
                 * Método delete(Object object):
                 * Marca el objeto para ser borrado.
                 * Gracias al '@OneToOne(cascade = CascadeType.ALL)',
                 * Hibernate detecta que este Cliente tiene un DetallesCliente asociado.
                 * Automáticamente generará DOS sentencias DELETE en SQL, respetando
                 * el orden de las restricciones (Foreign Keys) de MySQL.
                 */
                miSession.delete(elCliente);

                System.out.println("Registro eliminado correctamente en ambas tablas de la base de datos.");
            } else {
                // Si el ID 3 no existe, informamos al usuario sin romper el programa.
                System.out.println("No se ha encontrado el cliente en la base de datos.");
            }

            // =================================================================
            // PASO 5: CONFIRMAR LOS CAMBIOS
            // =================================================================
            // commit() ejecuta las sentencias SQL reales en MySQL de forma definitiva.
            miSession.getTransaction().commit();

        } finally {
            miSession.close();
            miFactory.close();
        }
    }
}