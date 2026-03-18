package es.pildoras.spring.hibernate.relaciones;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase ejecutable para probar la eliminación en cascada (DELETE total).
 * Verifica que al eliminar la entidad principal (Cliente), Hibernate
 * elimine automáticamente la entidad secundaria asociada (DetallesCliente)
 * respetando las restricciones de integridad de la base de datos.
 */
public class EliminaCliente {

    /**
     * Método principal de ejecución.
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main (String[] args){

        SessionFactory miFactory = new Configuration()
                .configure("hibernate-relaciones.cfg.xml")
                .addAnnotatedClass(Cliente.class)
                .addAnnotatedClass(DetallesCliente.class)
                .buildSessionFactory();

        Session miSession = miFactory.openSession();

        try {
            miSession.beginTransaction();

            // Búsqueda de la entidad a eliminar
            Cliente elCliente = miSession.get(Cliente.class, 3);

            // Verificación de nulidad antes de invocar la eliminación
            if (elCliente != null){
                System.out.println("Voy a eliminar al cliente: " + elCliente.getNombre());

                // Elimina el cliente. La cascada elimina sus detalles.
                miSession.delete(elCliente);
                System.out.println("Registro eliminado correctamente en ambas tablas de la base de datos.");
            } else {
                System.out.println("No se ha encontrado el cliente en la base de datos.");
            }

            miSession.getTransaction().commit();

        } finally {
            miSession.close();
            miFactory.close();
        }
    }
}