package es.pildoras.spring.mvc.formulario;

/**
 * SECCIÓN: EL MODELO DE DATOS (POJO)
 * En la arquitectura MVC, esto es la "M". Representa una entidad del mundo real.
 * Requisito indispensable: Debe tener un constructor vacío (Java lo pone por defecto si no creas otro)
 * y métodos Getters y Setters estándar.
 * * * COMPARACIÓN CON JAVA EE (SERVLETS PUROS):
 * En Java EE, tú eras el único responsable de instanciar esta clase usando 'new Alumno()'
 * dentro del Servlet, y luego llenarla manualmente dato por dato extrayéndolos del 'request'.
 * En Spring MVC, el framework usa una característica avanzada de Java llamada "Reflexión" (Reflection).
 * Spring crea el objeto por ti en la memoria (usando el constructor vacío) y busca automáticamente
 * los métodos 'set' que coincidan con los nombres de los campos de tu formulario HTML para inyectar los datos.
 */
public class Alumno {

    private String nombre;
    private String apellido;

    // Cuando el formulario se carga, Spring llama a estos Getters para ver
    // si hay información pre-cargada que mostrar en los <input>.
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }

    // Cuando el usuario hace clic en "Enviar", Spring llama automáticamente
    // a estos Setters para inyectar lo que el usuario escribió en el HTML.
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
}