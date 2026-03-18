package es.pildoras.spring.hibernate.relaciones;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * =========================================================================
 * SECCIÓN: ENTIDAD PRINCIPAL (LADO INVERSO DE LA RELACIÓN)
 * =========================================================================
 * Representa la entidad 'Cliente' mapeada a la tabla 'cliente' de la base de datos.
 * En esta arquitectura bidireccional, actúa como el lado inverso de la relación,
 * delegando el control de la Clave Foránea a la entidad DetallesCliente.
 */
@Entity
@Table(name="cliente")
public class Cliente {

    // =========================================================================
    // SECCIÓN: ATRIBUTOS DE CLASE Y MAPEO BÁSICO
    // =========================================================================

    /**
     * Identificador único del cliente.
     * Mapeado a la columna 'id'. Generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    /** Nombre del cliente. Mapeado a la columna 'nombre'. */
    @Column(name="nombre")
    private String nombre;

    /** Apellido del cliente. Mapeado a la columna 'apellido'. */
    @Column(name="apellido")
    private String apellido;

    /** Dirección física del cliente. Mapeado a la columna 'direccion'. */
    @Column(name="direccion")
    private String direccion;

    // =========================================================================
    // SECCIÓN: RELACIONES ORM
    // =========================================================================

    /**
     * Relación Uno a Uno bidireccional con DetallesCliente.
     * La propiedad 'mappedBy' indica que el atributo 'elCliente' en la clase
     * DetallesCliente es el dueño físico de la relación (posee la Clave Foránea).
     * El 'CascadeType.ALL' asegura que las operaciones de persistencia,
     * actualización y eliminación se propaguen a los detalles asociados.
     */
    @OneToOne(mappedBy = "elCliente", cascade = CascadeType.ALL)
    private DetallesCliente detallesCliente;

    /**
     * Relación Uno a Muchos bidireccional con Pedido.
     * Un cliente puede tener múltiples pedidos asociados.
     * La propiedad 'mappedBy' indica que el atributo 'cliente' en la clase
     * Pedido es el dueño físico de la relación (posee la Clave Foránea 'cliente_id').
     * La cascada excluye CascadeType.REMOVE para que al eliminar un cliente
     * no se destruyan sus pedidos automáticamente.
     */
    @OneToMany(mappedBy = "cliente", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    private List<Pedido> pedidos;

    // =========================================================================
    // SECCIÓN: CONSTRUCTORES
    // =========================================================================

    /**
     * Constructor por defecto.
     * Requerido por el estándar JPA/Hibernate para la instanciación mediante Reflection.
     */
    public Cliente() {}

    /**
     * Constructor parametrizado para instanciar un Cliente sin ID (autogenerado).
     * * @param nombre   Nombre del cliente.
     * @param apellido Apellido del cliente.
     * @param direccion Dirección del cliente.
     */
    public Cliente(String nombre, String apellido, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }

    // =========================================================================
    // SECCIÓN: MÉTODOS ACCESORES (GETTERS Y SETTERS)
    // =========================================================================

    /** @return El identificador del cliente. */
    public int getId() { return id; }

    /** @param id El nuevo identificador del cliente. */
    public void setId(int id) { this.id = id; }

    /** @return El nombre del cliente. */
    public String getNombre() { return nombre; }

    /** @param nombre El nuevo nombre del cliente. */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /** @return El apellido del cliente. */
    public String getApellido() { return apellido; }

    /** @param apellido El nuevo apellido del cliente. */
    public void setApellido(String apellido) { this.apellido = apellido; }

    /** @return La dirección del cliente. */
    public String getDireccion() { return direccion; }

    /** @param direccion La nueva dirección del cliente. */
    public void setDireccion(String direccion) { this.direccion = direccion; }

    /** @return El objeto DetallesCliente asociado a este cliente. */
    public DetallesCliente getDetallesCliente() { return detallesCliente; }

    /**
     * Establece los detalles para este cliente y sincroniza la relación bidireccional.
     * Inyecta automáticamente la referencia de este Cliente en el objeto DetallesCliente.
     * * @param detallesCliente El objeto DetallesCliente a asociar, o null para desvincular.
     */
    public void setDetallesCliente(DetallesCliente detallesCliente) {
        this.detallesCliente = detallesCliente;
        if(detallesCliente != null) {
            detallesCliente.setElCliente(this);
        }
    }

    // =========================================================================
    // SECCIÓN: MÉTODOS AUXILIARES
    // =========================================================================

    /**
     * Representación en cadena del estado del objeto Cliente.
     * Se omite el atributo detallesCliente para evitar excepciones de recursión infinita.
     * * @return String con los datos del cliente.
     */
    @Override
    public String toString() {
        return "Cliente{id=" + id + ", " +
                "nombre='" + nombre + "', " +
                "apellido='" + apellido + "', " +
                "direccion='" + direccion + "'}";
    }

    /**
     * Método de conveniencia para agregar un pedido a la lista del cliente
     * y sincronizar automáticamente la relación bidireccional en memoria.
     * Inicializa la lista si aún no existe (lazy initialization).
     *
     * @param elPedido El pedido a asociar con este cliente.
     */
    public void agregarPedidos(Pedido elPedido) {
        if (pedidos == null) pedidos = new ArrayList<>();
        pedidos.add(elPedido);
        elPedido.setCliente(this);
    }
}