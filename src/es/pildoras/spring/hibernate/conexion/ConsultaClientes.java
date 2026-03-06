package es.pildoras.spring.hibernate.conexion;

// =========================================================================
// IMPORTS DEL FRAMEWORK Y JAVA
// =========================================================================
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// Importamos la interfaz List del framework de Colecciones de Java.
import java.util.List;

/**
 * SECCIÓN: CONSULTAS CON HQL (HIBERNATE QUERY LANGUAGE) Y REFACTORIZACIÓN
 * Esta clase demuestra cómo realizar búsquedas a la base de datos utilizando
 * el lenguaje orientado a objetos de Hibernate (HQL). Además, implementa
 * buenas prácticas de refactorización para evitar código duplicado.
 */
public class ConsultaClientes {

    /**
     * Método main: Punto de entrada de la aplicación.
     * @param args Argumentos de línea de comandos (no utilizados aquí).
     */
    public static void main (String[] args){

        // =====================================================================
        // PASO 1: CREAR EL SESSION FACTORY Y LA SESIÓN
        // =====================================================================
        // Configuramos la fábrica leyendo el XML y vinculamos la clase Entidad (ahora 'Cliente')
        SessionFactory miFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Cliente.class)
                .buildSessionFactory();

        // Abrimos el túnel de comunicación con la base de datos.
        Session miSession = miFactory.openSession();

        try {
            // =================================================================
            // PASO 2: INICIAR LA TRANSACCIÓN
            // =================================================================
            // Iniciamos la sesión de transacciones. Obligatorio en Hibernate
            // incluso para operaciones de solo lectura (SELECT).
            miSession.beginTransaction();

            // =================================================================
            // EJEMPLO 1: CONSULTA SIMPLE SIN CRITERIOS
            // =================================================================
            // Consultamos todos los clientes
            // HQL: "from Cliente" busca en la clase Java, no en la tabla MySQL.
            List<Cliente> losClientes = miSession.createQuery("from Cliente").getResultList();

            // Mostramos los clientes llamando al método refactorizado
            System.out.println("\n--- MOSTRANDO TODOS LOS CLIENTES ---");
            recorreClientesConsultados(losClientes);

            // =================================================================
            // EJEMPLO 2: CONSULTA CON UN CRITERIO (EJ: APELLIDO)
            // =================================================================
            // Consultamos con criterio específico (ej: apellido)
            // HQL: Asignamos el alias 'cl' a la clase Cliente y filtramos por su atributo.
            losClientes = miSession.createQuery("from Cliente cl where cl.apellido='Diaz'").getResultList();

            System.out.println("\n--- MOSTRANDO CLIENTES DE APELLIDO 'DIAZ' ---");
            recorreClientesConsultados(losClientes);

            // =================================================================
            // EJEMPLO 3: CONSULTA CON VARIOS CRITERIOS (EJ: APELLIDO O DIRECCION)
            // =================================================================
            // Consultamos con varios criterios (ej: apellido y/o direccion)
            // HQL: Uso de operadores lógicos ('or') para ampliar la búsqueda.
            losClientes = miSession.createQuery("from Cliente cl where cl.apellido='Delgado' or cl.direccion='Gran vía'").getResultList();

            System.out.println("\n--- MOSTRANDO CLIENTES 'DELGADO' O DE 'GRAN VÍA' ---");
            recorreClientesConsultados(losClientes);

            // =================================================================
            // PASO 3: CONFIRMAR Y CERRAR
            // =================================================================
            // Confirmamos la transacción de lectura para liberar la base de datos.
            miSession.getTransaction().commit();

        } finally {
            // Cerramos la fábrica pesada al terminar el programa
            miFactory.close();
        }
    }

    // =========================================================================
    // SECCIÓN: MÉTODOS AUXILIARES (REFACTORIZACIÓN)
    // =========================================================================

    /**
     * Método: recorreClientesConsultados
     * Propósito: Evita la repetición de código (Principio DRY) al momento de
     * imprimir en consola los resultados de las consultas HQL.
     * * Modificadores:
     * - private: Solo puede ser usado dentro de esta clase 'ConsultaClientes'.
     * - static: Necesario para poder ser llamado desde el método 'main' que también es static.
     * - void: No devuelve ningún valor, solo ejecuta una acción (imprimir).
     * * @param losClientes Un objeto de tipo List que contiene instancias de la clase Cliente.
     */
    private static void recorreClientesConsultados(List<Cliente> losClientes) {
        // Bucle for-each: Por cada objeto 'cliente' dentro de la lista 'losClientes'...
        for (Cliente cliente : losClientes){
            // ...imprimimos el objeto (esto llama automáticamente al método toString() de Cliente)
            System.out.println(cliente);
        }
    }
}