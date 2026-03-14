package es.pildoras.spring.hibernate.relaciones;

import javax.persistence.*;

/**
 * SECCIÓN: ENTIDAD SECUNDARIA (DEPENDIENTE)
 * Esta clase mapea la tabla 'detalles_cliente'. En s una relación
 * unidireccional (El Cliente conoce sus Detalles, pero los detalles
 * no saben a qué Cliente pertenecen en el código Java).
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