package es.pildoras.spring.hibernate.relaciones;

import javax.persistence.*;
import java.util.Date;

/**
 * =========================================================================
 * SECCIÓN: ENTIDAD SECUNDARIA (DUEÑA DE LA RELACIÓN UNO A MUCHOS)
 * =========================================================================
 * Representa la entidad 'Pedido' mapeada a la tabla 'pedido' de la base de datos.
 * En la relación bidireccional con Cliente, esta entidad actúa como la dueña
 * de la relación, gestionando físicamente la Clave Foránea 'cliente_id'.
 * Un pedido pertenece a exactamente un Cliente, mientras que un Cliente
 * puede tener múltiples Pedidos (@ManyToOne desde esta perspectiva).
 */
@Entity
@Table(name = "pedido")
public class Pedido {

    // =========================================================================
    // SECCIÓN: ATRIBUTOS DE CLASE Y MAPEO BÁSICO
    // =========================================================================

    /**
     * Identificador único del pedido.
     * Mapeado a la columna 'id'. Generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Fecha en la que se realizó el pedido.
     * Mapeado a la columna 'fecha'.
     */
    @Column(name = "fecha")
    private Date fecha;

    /**
     * Forma de pago utilizada en el pedido (ej: "efectivo", "tarjeta").
     * Mapeado a la columna 'forma_pago'.
     */
    @Column(name = "forma_pago")
    private String formaPago;

    // =========================================================================
    // SECCIÓN: RELACIONES ORM
    // =========================================================================

    /**
     * Relación Muchos a Uno con la entidad Cliente.
     * Muchos pedidos pueden pertenecer a un mismo cliente.
     * La cascada excluye explícitamente CascadeType.REMOVE para evitar que
     * al eliminar un pedido se destruya accidentalmente el Cliente principal.
     * La anotación @JoinColumn define la columna 'cliente_id' en la tabla
     * 'pedido' como la Clave Foránea que referencia a la tabla 'cliente'.
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // =========================================================================
    // SECCIÓN: CONSTRUCTORES
    // =========================================================================

    /**
     * Constructor por defecto.
     * Requerido por el estándar JPA/Hibernate para la instanciación mediante Reflection.
     */
    public Pedido() {}

    /**
     * Constructor parametrizado para instanciar un Pedido con fecha.
     *
     * @param fecha La fecha en la que se realizó el pedido.
     */
    public Pedido(Date fecha) {
        this.fecha = fecha;
    }

    // =========================================================================
    // SECCIÓN: MÉTODOS ACCESORES (GETTERS Y SETTERS)
    // =========================================================================

    /** @return El identificador del pedido. */
    public int getId() { return id; }

    /** @param id El nuevo identificador del pedido. */
    public void setId(int id) { this.id = id; }

    /** @return La fecha del pedido. */
    public Date getFecha() { return fecha; }

    /** @param fecha La nueva fecha del pedido. */
    public void setFecha(Date fecha) { this.fecha = fecha; }

    /** @return La forma de pago del pedido. */
    public String getFormaPago() { return formaPago; }

    /** @param formaPago La nueva forma de pago del pedido. */
    public void setFormaPago(String formaPago) { this.formaPago = formaPago; }

    /** @return La entidad Cliente propietaria de este pedido. */
    public Cliente getCliente() { return cliente; }

    /** @param cliente La entidad Cliente a enlazar con este pedido. */
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    // =========================================================================
    // SECCIÓN: MÉTODOS AUXILIARES
    // =========================================================================

    /**
     * Representación en cadena del estado del objeto Pedido.
     * Se omite el atributo cliente para evitar excepciones de recursión infinita
     * en la relación bidireccional con Cliente.
     *
     * @return String con los datos del pedido.
     */
    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", formaPago='" + formaPago + '\'' +
                '}';
    }
}