package es.pildoras.spring.gestionaop.aspectos_basicos;

/**
 * =========================================================================================
 * SECCIÓN: CLASE ENTIDAD / POJO (Plain Old Java Object)
 * Esta clase representa los datos de negocio. No tiene anotaciones de Spring (@Component)
 * porque no queremos que Spring la instancie automáticamente; la creamos con 'new'
 * cuando la necesitamos para transportar datos.
 * =========================================================================================
 */
public class ClienteBasicos {

    // Atributos privados de la clase (Encapsulamiento)
    private String nombre;
    private String tipo;

    // Métodos Getters y Setters tradicionales para acceder y modificar los atributos.
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}