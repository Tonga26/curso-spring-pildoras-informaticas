package es.pildoras.spring.hibernate.relaciones;

import java.sql.Connection;
import java.sql.DriverManager;

public class PruebaConexion {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/relacioneshibernate?useSSL=false&serverTimezone=UTC";

        String user = "root";
        String password = "";

        System.out.println("Intentando conectar con la BBDD: " + jdbcUrl);

        try (Connection conexion = DriverManager.getConnection(jdbcUrl, user, password)) {

            System.out.println("¡Conexión exitosa y segura!");

        } catch (Exception e) {
            System.out.println("Ocurrió un error al intentar conectar:");
            e.printStackTrace();
        }
    }
}