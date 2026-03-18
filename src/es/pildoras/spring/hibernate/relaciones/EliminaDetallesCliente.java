package es.pildoras.spring.hibernate.relaciones;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase ejecutable para probar la eliminación parcial (DELETE sin cascada total).
 * Demuestra cómo romper un enlace bidireccional en memoria para poder eliminar
 * la entidad secundaria (DetallesCliente) sin afectar a la entidad principal (Cliente).
 */
public class EliminaDetallesCliente {

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

            // Recupera la entidad secundaria que se desea aislar y eliminar
            DetallesCliente detalleDelCliente = miSession.get(DetallesCliente.class, 1);

            if (detalleDelCliente != null){

                // Paso Crítico: Desvincula la relación bidireccional para evitar
                // violaciones de restricción de Clave Foránea al momento del DELETE.
                detalleDelCliente.getElCliente().setDetallesCliente(null);

                // Elimina únicamente el detalle. El cliente principal permanece intacto.
                miSession.delete(detalleDelCliente);

                System.out.println("Registro eliminado correctamente. El cliente ha sido conservado.");
            } else {
                System.out.println("No se ha encontrado el detalle del cliente en la base de datos.");
            }

            miSession.getTransaction().commit();

        } finally {
            miSession.close();
            miFactory.close();
        }
    }
}