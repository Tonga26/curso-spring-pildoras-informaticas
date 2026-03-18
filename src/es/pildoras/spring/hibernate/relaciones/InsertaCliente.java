package es.pildoras.spring.hibernate.relaciones;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase ejecutable para probar la persistencia en cascada (CREATE).
 * Demuestra la funcionalidad de CascadeType.ALL al guardar múltiples
 * entidades relacionadas mediante una única instrucción de guardado en la sesión.
 */
public class InsertaCliente {

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
            // Instanciación de entidades transitorias (no persistidas aún)
            Cliente cliente1 = new Cliente("Paco", "Gómez", "San Martín");
            DetallesCliente detallesCliente1 = new DetallesCliente("www.pildorasinformaticas.es", "1189765572", "Segundo Cliente");

            // Establece el enlace bidireccional en memoria
            cliente1.setDetallesCliente(detallesCliente1);

            miSession.beginTransaction();

            // Guarda la entidad principal. La cascada insertará automáticamente los detalles.
            miSession.save(cliente1);

            miSession.getTransaction().commit();
            System.out.println("Registro insertado correctamente en ambas tablas de la base de datos.");

        } finally {
            miSession.close();
            miFactory.close();
        }
    }
}