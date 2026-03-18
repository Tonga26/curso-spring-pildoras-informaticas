package es.pildoras.spring.hibernate.relaciones;

import javax.persistence.*;

/**
 * =========================================================================
 * SECCIÓN: ENTIDAD SECUNDARIA (DUEÑA DE LA RELACIÓN)
 * =========================================================================
 * Representa la entidad 'DetallesCliente' mapeada a la tabla 'detalles_cliente'.
 * Al contener la anotación @JoinColumn, esta entidad actúa como la dueña
 * de la relación bidireccional, gestionando físicamente la Clave Foránea en la BD.
 */
@Entity
@Table(name="detalles_cliente")
public class DetallesCliente {

    // =========================================================================
    // SECCIÓN: ATRIBUTOS DE CLASE Y MAPEO BÁSICO
    // =========================================================================

    /**
     * Identificador único de los detalles.
     * Mapeado a la columna 'id'. Generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    /** Página web del cliente. Mapeado a la columna 'web'. */
    @Column(name="web")
    private String web;

    /** Número de teléfono del cliente. Mapeado a la columna 'telefono'. */
    @Column(name="telefono")
    private String telefono;

    /** Notas o comentarios adicionales. Mapeado a la columna 'comentarios'. */
    @Column(name="comentarios")
    private String comentarios;

    // =========================================================================
    // SECCIÓN: RELACIONES ORM
    // =========================================================================

    /**
     * Relación Uno a Uno con la entidad Cliente.
     * La cascada omite explícitamente CascadeType.REMOVE para permitir la
     * eliminación de estos detalles sin destruir la entidad Cliente principal.
     */
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    /**
     * Define la columna física en la tabla 'detalles_cliente' que actúa
     * como Clave Foránea apuntando a la tabla 'cliente'.
     */
    @JoinColumn(name = "cliente_id")
    private Cliente elCliente;

    // =========================================================================
    // SECCIÓN: CONSTRUCTORES
    // =========================================================================

    /**
     * Constructor por defecto.
     * Requerido por el estándar JPA/Hibernate para la instanciación interna.
     */
    public DetallesCliente() {}

    /**
     * Constructor parametrizado para instanciar DetallesCliente sin ID.
     * * @param web         Página web del cliente.
     * @param telefono    Número de contacto.
     * @param comentarios Notas adicionales.
     */
    public DetallesCliente(String web, String telefono, String comentarios) {
        this.web = web;
        this.telefono = telefono;
        this.comentarios = comentarios;
    }

    // =========================================================================
    // SECCIÓN: MÉTODOS ACCESORES (GETTERS Y SETTERS)
    // =========================================================================

    /** @return El identificador del detalle. */
    public int getId() { return id; }

    /** @param id El nuevo identificador del detalle. */
    public void setId(int id) { this.id = id; }

    /** @return La página web asociada. */
    public String getWeb() { return web; }

    /** @param web La nueva página web. */
    public void setWeb(String web) { this.web = web; }

    /** @return El número de teléfono asociado. */
    public String getTelefono() { return telefono; }

    /** @param telefono El nuevo número de teléfono. */
    public void setTelefono(String telefono) { this.telefono = telefono; }

    /** @return Los comentarios asociados. */
    public String getComentarios() { return comentarios; }

    /** @param comentarios Los nuevos comentarios. */
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }

    /** @return La entidad Cliente principal asociada a estos detalles. */
    public Cliente getElCliente() { return elCliente; }

    /** @param elCliente La entidad Cliente a enlazar con estos detalles. */
    public void setElCliente(Cliente elCliente) { this.elCliente = elCliente; }

    // =========================================================================
    // SECCIÓN: MÉTODOS AUXILIARES
    // =========================================================================

    /**
     * Representación en cadena del estado del objeto DetallesCliente.
     * Se omite el atributo elCliente para evitar el desbordamiento de pila por recursión.
     * * @return String con los datos del detalle.
     */
    @Override
    public String toString() {
        return "DetallesCliente{id=" + id + ", " +
                "web='" + web + "', " +
                "telefono='" + telefono + "', " +
                "comentarios='" + comentarios + "'}";
    }
}