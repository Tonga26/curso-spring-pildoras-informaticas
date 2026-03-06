package es.pildoras.spring.hibernate.conexion;

import javax.persistence.*;

/**
 * SECCIÓN: MAPEO ORM (OBJECT RELATIONAL MAPPING)
 * Esta clase es una "Entidad". Su propósito es ser el espejo exacto de una tabla
 * en la base de datos MySQL, pero en el mundo de los objetos Java.
 * * COMPARACIÓN CON JAVA EE (JDBC PURO):
 * En Java antiguo, debías escribir consultas SQL a mano como:
 * "INSERT INTO clientes (nombre, apellido) VALUES (?, ?)" y luego mapear los
 * resultados manualmente recorriendo un objeto 'ResultSet'.
 * Con Hibernate, simplemente guardamos o recuperamos este objeto 'Clientes' y el
 * framework genera el código SQL automáticamente por debajo.
 * * NOTA SOBRE IMPORTACIONES:
 * Usamos 'javax.persistence.*' (el estándar JPA) en lugar de 'org.hibernate.*'.
 * Esto es una buena práctica: programamos orientados al estándar de Java, de modo
 * que si mañana cambiamos Hibernate por otro motor ORM, no hay que reescribir la clase.
 */

/*
 * @Entity: Le indica a Hibernate que esta clase no es un POJO común, sino que
 * debe ser gestionada y mapeada a una base de datos.
 */
@Entity
/*
 * @Table: Especifica el nombre exacto de la tabla física en MySQL.
 * Si la clase se llamara igual que la tabla (ej: clase Clientes y tabla clientes),
 * esta anotación sería opcional, pero es buena práctica ponerla siempre para evitar errores.
 */
@Table(name="clientes")
public class Clientes {

    // =========================================================================
    // SECCIÓN: ATRIBUTOS Y MAPEO DE COLUMNAS
    // =========================================================================

    /*
     * @Id: Le indica a Hibernate que este atributo es la Clave Primaria (Primary Key).
     * @Column: Vincula este atributo de Java con el nombre de la columna en la BD.
     */
    @Id
    @Column(name="id")
    /*
     * @GeneratedValue: Le delega a la base de datos la responsabilidad de generar el ID.
     * strategy = GenerationType.IDENTITY: Es la estrategia específica para bases de
     * datos que soportan campos autoincrementables (como MySQL).
     * Le dice a Hibernate: "No calcules el ID tú. Inserta el registro y confía en
     * el AUTO_INCREMENT de MySQL, luego recupera el ID generado".
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;

    @Column(name="direccion")
    private String direccion;

    // =========================================================================
    // SECCIÓN: CONSTRUCTORES
    // =========================================================================

    /**
     * CONSTRUCTOR POR DEFECTO (VACÍO)
     * Es ESTRICTAMENTE OBLIGATORIO para Hibernate.
     * Cuando Hibernate recupera un registro de la base de datos, primero crea un
     * objeto vacío usando este constructor, y luego usa los "Setters" o Reflection
     * para rellenar los datos. Si lo borras, la aplicación fallará.
     */
    public Clientes() {
    }

    /**
     * CONSTRUCTOR PARAMETRIZADO
     * Lo usamos nosotros para instanciar nuevos clientes antes de guardarlos.
     * Fíjate que NO incluimos el 'id'. Esto es porque el 'id' suele ser generado
     * automáticamente por la base de datos (Auto Increment) al momento de guardar.
     */
    public Clientes(String nombre, String apellido, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }

    // =========================================================================
    // SECCIÓN: GETTERS Y SETTERS
    // Son obligatorios para que el framework pueda leer y escribir en los atributos
    // privados de la clase.
    // =========================================================================

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    // =========================================================================
    // SECCIÓN: MÉTODOS AUXILIARES
    // =========================================================================

    /**
     * @Override de toString()
     * Facilita la depuración (debugging). Cuando pidamos a Hibernate que nos traiga
     * un cliente y lo imprimamos por consola (System.out.println(miCliente)),
     * veremos sus datos reales en lugar de su referencia a su espacio de memoria.
     */
    @Override
    public String toString() {
        return "Clientes{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}