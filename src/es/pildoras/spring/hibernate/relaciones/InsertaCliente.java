package es.pildoras.spring.hibernate.relaciones;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * =========================================================================
 * SECCIÓN: CLASE DE PRUEBA - PERSISTENCIA EN CASCADA (CREATE)
 * =========================================================================
 * Clase ejecutable que demuestra la inserción de un Cliente junto con sus
 * DetallesCliente en una única operación de guardado, aprovechando la
 * propagación en cascada configurada en la relación {@code @OneToOne}.
 *
 * <p>Flujo de ejecución:</p>
 * <ol>
 *   <li>Instancia un {@link Cliente} y un {@link DetallesCliente} en estado transitorio.</li>
 *   <li>Enlaza ambas entidades bidirecccionalmente en memoria.</li>
 *   <li>Persiste únicamente el {@link Cliente}. La cascada se encarga de
 *       persistir automáticamente el {@link DetallesCliente} asociado.</li>
 * </ol>
 */
public class InsertaCliente {

    /**
     * Método principal de ejecución.
     * Configura la {@link SessionFactory} registrando todas las entidades
     * mapeadas del modelo relacional antes de abrir la sesión.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

        SessionFactory miFactory = new Configuration()
                .configure("hibernate-relaciones.cfg.xml")
                .addAnnotatedClass(Cliente.class)
                .addAnnotatedClass(DetallesCliente.class)
                .addAnnotatedClass(Pedido.class)
                .buildSessionFactory();

        Session miSession = miFactory.openSession();

        try {
            // Instanciación de entidades transitorias (no persistidas aún)
            Cliente cliente1 = new Cliente("Ana", "Marín", "Gran Vía");
            DetallesCliente detallesCliente1 = new DetallesCliente(
                    "www.pildorasinformaticas.es", "2613789034", "Tercer Cliente");

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