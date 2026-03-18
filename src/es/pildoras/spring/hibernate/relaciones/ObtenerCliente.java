package es.pildoras.spring.hibernate.relaciones;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase ejecutable para probar la lectura bidireccional (READ) en Hibernate.
 * Demuestra cómo, a partir de la entidad secundaria (DetallesCliente),
 * el ORM es capaz de ejecutar un JOIN implícito para recuperar
 * la información de la entidad principal (Cliente) asociada.
 */
public class ObtenerCliente {

    /**
     * Método principal de ejecución.
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main (String[] args) {

        SessionFactory miFactory = new Configuration()
                .configure("hibernate-relaciones.cfg.xml")
                .addAnnotatedClass(Cliente.class)
                .addAnnotatedClass(DetallesCliente.class)
                .buildSessionFactory();

        Session miSession = miFactory.openSession();

        try {
            miSession.beginTransaction();

            // Recupera la entidad secundaria desde la base de datos
            DetallesCliente detallesDeCliente = miSession.get(DetallesCliente.class, 1);

            if (detallesDeCliente != null) {
                System.out.println("Detalles encontrados: " + detallesDeCliente);

                // Navegación bidireccional: Recupera al dueño de los detalles
                System.out.println("El cliente dueño de estos detalles es: " + detallesDeCliente.getElCliente());
            } else {
                System.out.println("No se encontraron detalles con ese ID.");
            }

            miSession.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            miSession.close();
            miFactory.close();
        }
    }
}