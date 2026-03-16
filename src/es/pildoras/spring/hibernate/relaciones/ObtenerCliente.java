package es.pildoras.spring.hibernate.relaciones;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * SECCIÓN: CONSULTA BIDIRECCIONAL (READ)
 * Esta clase demuestra cómo, a partir de un registro secundario (DetallesCliente),
 * Hibernate es capaz de navegar hacia atrás y recuperar la información de la
 * entidad principal (Cliente) usando una sola consulta (JOIN implícito).
 */
public class ObtenerCliente {

    public static void main (String[] args) {

        // 1. Configuramos la fábrica apuntando al XML de relaciones
        SessionFactory miFactory = new Configuration()
                .configure("hibernate-relaciones.cfg.xml")
                .addAnnotatedClass(Cliente.class)
                .addAnnotatedClass(DetallesCliente.class)
                .buildSessionFactory();

        Session miSession = miFactory.openSession();

        try {

            // 2. Iniciamos la transacción DML
            miSession.beginTransaction();

            // =================================================================
            // PASO 3: OBTENER EL DETALLE (EL SECUNDARIO)
            // =================================================================
            // Vamos a la base de datos y traemos el detalle con ID = 1.
            DetallesCliente detallesDeCliente = miSession.get(DetallesCliente.class, 1);

            if (detallesDeCliente != null) {
                // 4. Mostramos la información propia del detalle
                System.out.println("Detalles encontrados: " + detallesDeCliente);

                // =================================================================
                // PASO 5: RELACIÓN BIDIRECCIONAL (NAVEGACIÓN)
                // =================================================================
                // A partir del detalle, le pedimos que nos traiga al Cliente asociado.
                // Hibernate armó el puente de vuelta automáticamente.
                System.out.println("El cliente dueño de estos detalles es: " + detallesDeCliente.getElCliente());
            } else {
                System.out.println("No se encontraron detalles con ese ID.");
            }

            // 6. Confirmamos transacción
            miSession.getTransaction().commit();

        } finally {
            // 7. Liberamos recursos
            miSession.close();
            miFactory.close();
        }
    }
}