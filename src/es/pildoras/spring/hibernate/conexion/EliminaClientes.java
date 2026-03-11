package es.pildoras.spring.hibernate.conexion;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * SECCIÓN: ELIMINACIÓN DE REGISTROS (DELETE)
 * Esta clase cierra el ciclo CRUD demostrando cómo borrar información
 * de la base de datos usando Hibernate Query Language (HQL).
 * * * COMPARACIÓN CON JAVA EE (JDBC PURO):
 * - En JDBC: Tendrías que escribir el String "DELETE FROM clientes WHERE direccion = ?",
 * crear la conexión, inyectar el parámetro, ejecutar el PreparedStatement y gestionar
 * múltiples bloques try/catch por si falla la conexión.
 * - En Hibernate: En una sola línea concisa e independiente del dialecto SQL,
 * le decimos a la sesión que elimine los objetos filtrando por sus atributos Java.
 */
public class EliminaClientes {

    public static void main (String[] args){

        // =====================================================================
        // PASO 1: CREAR EL SESSION FACTORY Y LA SESIÓN
        // =====================================================================
        SessionFactory miFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Cliente.class)
                .buildSessionFactory();

        Session miSession = miFactory.openSession();

        try {
            // =================================================================
            // PASO 2: INICIAR LA TRANSACCIÓN
            // =================================================================
            // Toda operación que modifique la base de datos debe ir encapsulada
            // en una transacción para mantener la consistencia (ACID).
            miSession.beginTransaction();

            // =================================================================
            // PASO 3: EJECUTAR LA OPERACIÓN DELETE (HQL)
            // =================================================================
            /*
             * NOTA IMPORTANTE: HQL NO USA LA PALABRA 'FROM' EN LOS DELETES.
             * No se escribe "DELETE FROM Cliente", sino simplemente "DELETE Cliente".
             * Además, filtramos por 'direccion' (el atributo de la clase Java).
             * El método .executeUpdate() sirve tanto para hacer UPDATE como DELETE,
             * ya que ambas son operaciones de escritura en bloque.
             */
            miSession.createQuery("DELETE Cliente WHERE direccion = 'Goya'").executeUpdate();

            /*
             * OPCIÓN ALTERNATIVA (Borrando un objeto individual específico):
             * Si ya tuvieras el objeto Cliente cargado en memoria, podrías borrarlo así:
             * Cliente miCliente = miSession.get(Cliente.class, 1);
             * miSession.delete(miCliente);
             */

            // =================================================================
            // PASO 4: CONFIRMAR LOS CAMBIOS
            // =================================================================
            miSession.getTransaction().commit();

            System.out.println("Registro(s) eliminado(s) correctamente en la base de datos.");

        } finally {
            // =================================================================
            // SECCIÓN: CIERRE SEGURO DE RECURSOS
            // =================================================================
            miSession.close();
            miFactory.close();
        }
    }
}