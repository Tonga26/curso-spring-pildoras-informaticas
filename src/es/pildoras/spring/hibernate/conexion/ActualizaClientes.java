package es.pildoras.spring.hibernate.conexion;

// =========================================================================
// IMPORTS DEL FRAMEWORK
// =========================================================================
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * SECCIÓN: ACTUALIZACIÓN DE REGISTROS (UPDATE)
 * Esta clase demuestra las dos formas principales de modificar datos
 * que ya existen en nuestra base de datos usando la magia del ORM.
 */
public class ActualizaClientes {

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
            // OPCIÓN 1: ACTUALIZACIÓN MEDIANTE SETTERS (EL MÉTODO "MÁGICO")
            // =================================================================
            /*
             * ¿Cómo funciona?
             * Al buscar un registro con .get() dentro de una transacción, el objeto
             * Java se vuelve "Persistente" (Hibernate lo está vigilando).
             * Si usamos un setter para cambiarle un valor en memoria, al hacer 'commit',
             * Hibernate detecta el cambio y genera el UPDATE SQL automáticamente.
             * ¡No hace falta usar miSession.save() ni miSession.update()!
             */

            /*
            // 1. Establecemos el id del cliente a actualizar
            int clienteId = 1;

            // 2. Iniciamos la transacción
            miSession.beginTransaction();

            // 3. Recuperamos el cliente y lo traemos a la memoria
            Cliente miCliente = miSession.get(Cliente.class, clienteId);

            // 4. Utilizamos el setter del campo correspondiente para actualizar el valor
            miCliente.setNombre("Juan");

            // 5. Confirmamos. Hibernate ve el cambio y actualiza MySQL.
            miSession.getTransaction().commit();
            */

            // =================================================================
            // OPCIÓN 2: ACTUALIZACIÓN MASIVA MEDIANTE HQL
            // =================================================================
            /*
             * ¿Por qué usar HQL aquí?
             * Si quisiéramos actualizar 1000 clientes que empiezan con la letra 'D'
             * usando la Opción 1, tendríamos que traer 1000 objetos a la memoria de
             * Java y hacer 1000 setters. ¡Muy ineficiente!
             * Con HQL, mandamos una sola orden directa a la base de datos.
             */

            // 1. Iniciamos una nueva transacción
            miSession.beginTransaction();

            // 2. Ejecutamos el UPDATE con HQL
            // CORRECCIÓN: Usamos 'Cliente' (nombre de tu clase Java) y no 'clientes'.
            // El método .executeUpdate() se usa siempre que hacemos UPDATE o DELETE.
            miSession.createQuery("UPDATE Cliente SET apellido='Dominguez' WHERE apellido LIKE 'D%'").executeUpdate();

            // 3. Confirmamos los cambios en la BD
            miSession.getTransaction().commit();

            System.out.println("Registro(s) actualizado(s) correctamente en la base de datos.");

        } finally {
            // =================================================================
            // SECCIÓN: CIERRE DE RECURSOS (PREVENCIÓN DE LEAKS)
            // =================================================================
            // El bloque 'finally' se ejecuta SIEMPRE, incluso si hay un error arriba.
            // Así evitamos el error "Connection leak detected" dejando conexiones huérfanas en MySQL.
            miSession.close();
            miFactory.close();
        }
    }
}