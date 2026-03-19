package es.pildoras.spring.hibernate.relaciones;

import javax.persistence.*;
import java.util.GregorianCalendar;

/**
 * Entidad JPA que representa un pedido del sistema, mapeada a la tabla 'pedido'.
 *
 * <p>Es el lado dueño de la relación <b>Muchos a Uno</b> con {@link Cliente},
 * gestionando físicamente la Clave Foránea {@code cliente_id} en la tabla 'pedido'.
 * Desde la perspectiva de {@link Cliente}, la relación es {@code @OneToMany}.</p>
 *
 * <p>Se excluye {@code CascadeType.REMOVE} para que eliminar un pedido
 * no destruya accidentalmente al {@link Cliente} propietario.</p>
 *
 * <p><b>Nota:</b> {@link GregorianCalendar} indexa los meses desde 0,
 * por lo que enero = 0, febrero = 1, marzo = 2, etc.</p>
 */
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "fecha")
    private GregorianCalendar fecha;

    @Column(name = "forma_pago")
    private String formaPago;

    /**
     * Relación Muchos a Uno con {@link Cliente}.
     * {@code @JoinColumn} define la columna 'cliente_id' en esta tabla
     * como la Clave Foránea que referencia a la tabla 'cliente'.
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Pedido() {}

    public Pedido(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public GregorianCalendar getFecha() { return fecha; }
    public void setFecha(GregorianCalendar fecha) { this.fecha = fecha; }

    public String getFormaPago() { return formaPago; }
    public void setFormaPago(String formaPago) { this.formaPago = formaPago; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    /**
     * Se omite {@code cliente} para evitar recursión infinita
     * por la relación bidireccional con {@link Cliente}.
     */
    @Override
    public String toString() {
        return "Pedido{id=" + id +
                ", fecha=" + fecha +
                ", formaPago='" + formaPago + "'}";
    }
}