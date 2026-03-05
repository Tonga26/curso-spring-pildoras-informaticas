package es.pildoras.spring.hibernate.pruebas;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * SECCIÓN: CLASE DE PRUEBA DE CONEXIÓN (JDBC PURO)
 * Esta clase actúa como un programa independiente (Java SE) para verificar que
 * las credenciales de XAMPP y el conector MySQL funcionan correctamente antes
 * de delegarle el trabajo a Hibernate.
 */
public class PruebasJDBC {

    /**
     * MÉTODO PRINCIPAL
     * Punto de entrada de la aplicación. Es necesario para poder ejecutar
     * esta clase directamente desde el IDE.
     */
    public static void main(String[] args) {

        // =====================================================================
        // PREPARACIÓN DE LAS CREDENCIALES
        // =====================================================================

        // jdbcUrl: Es la cadena de conexión.
        // Parámetro useSSL=false: Desactiva advertencias de seguridad en entornos locales.
        // Parámetro serverTimezone=UTC: Es vital agregarlo para el Connector/J 8.x,
        // de lo contrario, MySQL rechazará la conexión por desajustes horarios.
        String jdbcUrl = "jdbc:mysql://localhost:3306/pruebashibernate?useSSL=false&serverTimezone=UTC";

        // Credenciales por defecto de XAMPP
        String user = "root";
        String password = "";

        // =====================================================================
        // SECCIÓN: APERTURA DE CONEXIÓN (TRY-WITH-RESOURCES)
        // =====================================================================
        /*
         * COMPARACIÓN CON JAVA EE (VIEJA ESCUELA):
         * En Java antiguo (antes de Java 7), estabas obligado a declarar la variable
         * 'Connection' fuera del try, instanciarla adentro, y luego crear un bloque
         * 'finally' gigantesco lleno de más try-catch únicamente para llamar a
         * 'conexion.close()'. Si un programador olvidaba ese paso, las conexiones
         * quedaban "abiertas" permanentemente hasta colapsar la memoria del servidor
         * (un error gravísimo llamado Connection Leak).
         * Este bloque moderno soluciona ese problema de raíz.
         */

        System.out.println("Intentando conectar con la BBDD: " + jdbcUrl);

        try (Connection conexion = DriverManager.getConnection(jdbcUrl, user, password)) {

            // Si el código llega a esta línea, significa que la conexión fue exitosa.
            System.out.println("¡Conexión exitosa y segura!");

            // La variable 'conexion' está viva y lista para usarse aquí dentro.

        } catch (Exception e) {
            // Si Apache/MySQL están apagados en XAMPP, o si hay un error en la URL,
            // el código salta aquí inmediatamente y nos imprime el rastro del error.
            System.out.println("Ocurrió un error al intentar conectar:");
            e.printStackTrace();
        }
    }
}