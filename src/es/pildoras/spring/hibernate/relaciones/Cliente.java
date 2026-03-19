package es.pildoras.spring.hibernate.relaciones;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad JPA que representa un cliente del sistema, mapeada a la tabla 'cliente'.
 *
 * <p>Participa en dos relaciones bidireccionales:</p>
 * <ul>
 *   <li><b>Uno a Uno</b> con {@link DetallesCliente}: actúa como lado inverso
 *       (usa {@code mappedBy}). El dueño físico de la Clave Foránea es {@link DetallesCliente}.</li>
 *   <li><b>Uno a Muchos</b> con {@link Pedido}: actúa como lado inverso
 *       (usa {@code mappedBy}). El dueño físico de la Clave Foránea es {@link Pedido}.</li>
 * </ul>
 *
 * <p>En ambas relaciones se excluye {@code CascadeType.REMOVE} en el lado hijo
 * para evitar eliminaciones accidentales en cascada.</p>
 */
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "direccion")
    private String direccion;

    /**
     * Relación Uno a Uno con {@link DetallesCliente}.
     * {@code mappedBy = "elCliente"} indica que la Clave Foránea reside
     * físicamente en la tabla 'detalles_cliente'.
     * {@code CascadeType.ALL} propaga todas las operaciones, incluida la eliminación.
     */
    @OneToOne(mappedBy = "elCliente", cascade = CascadeType.ALL)
    private DetallesCliente detallesCliente;

    /**
     * Relación Uno a Muchos con {@link Pedido}.
     * {@code mappedBy = "cliente"} indica que la Clave Foránea 'cliente_id'
     * reside físicamente en la tabla 'pedido'.
     * Se omite {@code CascadeType.REMOVE} para que eliminar un cliente
     * no destruya sus pedidos en cascada.
     */
    @OneToMany(mappedBy = "cliente", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    private List<Pedido> pedidos;

    public Cliente() {}

    public Cliente(String nombre, String apellido, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public DetallesCliente getDetallesCliente() { return detallesCliente; }

    /**
     * Establece los detalles del cliente y sincroniza la relación bidireccional en memoria.
     * Al asignar el objeto, inyecta automáticamente la referencia inversa en
     * {@link DetallesCliente#setElCliente(Cliente)}, manteniendo coherencia en ambos lados.
     *
     * @param detallesCliente El objeto a asociar, o {@code null} para desvincular.
     */
    public void setDetallesCliente(DetallesCliente detallesCliente) {
        this.detallesCliente = detallesCliente;
        if (detallesCliente != null) {
            detallesCliente.setElCliente(this);
        }
    }

    public List<Pedido> getPedidos() { return pedidos; }
    public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }

    /**
     * Agrega un pedido a la lista del cliente y sincroniza la relación bidireccional en memoria.
     * Inicializa la lista si todavía es {@code null} (lazy initialization).
     * Equivale a llamar manualmente a {@code pedidos.add(p)} y {@code p.setCliente(this)}.
     *
     * @param elPedido El pedido a asociar con este cliente.
     */
    public void agregarPedidos(Pedido elPedido) {
        if (pedidos == null) pedidos = new ArrayList<>();
        pedidos.add(elPedido);
        elPedido.setCliente(this);
    }

    /**
     * Se omite {@code detallesCliente} y {@code pedidos} para evitar
     * recursión infinita por la relación bidireccional.
     */
    @Override
    public String toString() {
        return "Cliente{id=" + id +
                ", nombre='" + nombre + "'" +
                ", apellido='" + apellido + "'" +
                ", direccion='" + direccion + "'}";
    }
}