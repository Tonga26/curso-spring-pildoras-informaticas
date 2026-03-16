package es.pildoras.spring.hibernate.relaciones;

import javax.persistence.*;

/**
 * SECCIÓN: ENTIDAD SECUNDARIA (DEPENDIENTE) - VERSIÓN BIDIRECCIONAL
 * Esta clase mapea la tabla 'detalles_cliente'.
 * Es una relación BIDIRECCIONAL. Los detalles saben exactamente a qué
 * Cliente pertenecen gracias al atributo 'elCliente'.
 */
@Entity
@Table(name="detalles_cliente")
public class DetallesCliente {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="web")
    private String web;

    @Column(name="telefono")
    private String telefono;

    @Column(name="comentarios")
    private String comentarios;

    // =========================================================================
    // MAPEO BIDIRECCIONAL (@OneToOne con mappedBy)
    // =========================================================================
    /*
     * mappedBy = "detallesCliente": Esto le dice a Hibernate:
     * "¡Oye! Yo NO soy el dueño de la Clave Foránea en la base de datos.
     * Ve a buscar las reglas de conexión (@JoinColumn) a la clase 'Cliente',
     * específicamente en su atributo llamado 'detallesCliente'".
     * cascade = CascadeType.ALL: Sigue aplicando la cascada. Si borramos este
     * Detalle, también se borrará el Cliente entero.
     */
    @OneToOne(mappedBy = "detallesCliente", cascade = CascadeType.ALL)
    private Cliente elCliente; // El puente de regreso hacia el dueño

    public DetallesCliente() {
    }

    public DetallesCliente(String web, String telefono, String comentarios) {
        this.web = web;
        this.telefono = telefono;
        this.comentarios = comentarios;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getWeb() { return web; }
    public void setWeb(String web) { this.web = web; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getComentarios() { return comentarios; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }

    public Cliente getElCliente() { return elCliente; }
    public void setElCliente(Cliente elCliente) { this.elCliente = elCliente; }

    @Override
    public String toString() {
        return "DetallesCliente{" +
                "id=" + id +
                ", web='" + web + '\'' +
                ", telefono='" + telefono + '\'' +
                ", comentarios='" + comentarios + '\'' +
                '}';
    }
}