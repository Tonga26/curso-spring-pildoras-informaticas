package es.pildoras.spring.hibernate.relaciones;

import javax.persistence.*;

/**
 * SECCIÓN: ENTIDAD PRINCIPAL (DUEÑA DE LA RELACIÓN)
 * Esta clase mapea la tabla 'cliente'. En una relación 1 a 1, una de las dos
 * clases debe llevar el control, y en este diseño, Cliente es la principal.
 */
@Entity
@Table(name="cliente")
public class Cliente {

    // =========================================================================
    // MAPEO DE COLUMNAS SIMPLES
    // =========================================================================
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;

    @Column(name="direccion")
    private String direccion;

    // =========================================================================
    // MAPEO DE RELACIÓN OBJETO-RELACIONAL (@OneToOne)
    // =========================================================================
    /*
     * @OneToOne: Define la relación 1 a 1. Un Cliente -> Un Detalle.
     * cascade = CascadeType.ALL: El efecto dominó. Si guardamos, actualizamos
     * o borramos este objeto Cliente, Hibernate hará automáticamente lo mismo
     * con el objeto 'detallesCliente' asociado, afectando ambas tablas en MySQL.
     */
    @OneToOne(cascade = CascadeType.ALL)
    /*
     * @JoinColumn: Le indica a Hibernate qué columna de la tabla 'cliente'
     * se usa para unirse a la otra tabla. En este caso, comparten la columna "id".
     */
    @JoinColumn(name = "id")
    private DetallesCliente detallesCliente;

    // =========================================================================
    // CONSTRUCTORES
    // =========================================================================
    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }

    // =========================================================================
    // GETTERS Y SETTERS
    // =========================================================================
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public DetallesCliente getDetallesCliente() { return detallesCliente; }
    public void setDetallesCliente(DetallesCliente detallesCliente) { this.detallesCliente = detallesCliente; }

    // =========================================================================
    // MÉTODOS AUXILIARES
    // =========================================================================
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}