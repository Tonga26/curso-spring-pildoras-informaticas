package es.pildoras.spring.mvc.formulario;

import javax.validation.constraints.*;

/**
 * SECCIÓN: EL MODELO DE DATOS (POJO) CON REGLAS DE VALIDACIÓN
 * En la arquitectura MVC, esto es la "M".
 * Requisito indispensable: Debe tener un constructor vacío y métodos Getters y Setters.
 *
 * COMPARACIÓN CON JAVA EE (SERVLETS PUROS):
 * En Java EE, tú eras el único responsable de instanciar esta clase y llenarla a mano.
 * Además, tenías que crear métodos gigantes llenos de "if/else" para validar cada campo.
 * En Spring MVC, delegamos la creación del objeto al framework y la validación a Hibernate Validator.
 */
public class Alumno {

    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private String optativa;
    private String ciudadEstudios;
    private String idiomasAlumno;

    /**
     * SECCIÓN: ANOTACIONES DE VALIDACIÓN EN GETTERS
     * Cuando Spring recibe los datos, lee estas anotaciones para saber si los acepta.
     * @NotNull: Impide que el valor viaje como un objeto nulo.
     * @Size: Restringe la longitud de una cadena de texto (String).
     */
    @NotBlank
    @Size(min = 2, message = "El nombre debe tener mínimo 2 letras y no debe quedar en blanco")
    public String getNombre() { return nombre; }

    @NotBlank
    @Size(min = 2, message = "El apellido debe tener mínimo 2 letras y no debe quedar en blanco")
    public String getApellido() { return apellido; }

    /**
     * @Min y @Max: Se usan específicamente para validar números (int, Integer, double, etc.).
     * Automáticamente evitan que el usuario ingrese edades absurdas.
     */
    @Min(value = 18, message = "No se permite que la edad sea menor a 18")
    @Max(value = 100, message = "No se permite que la edad sea mayor a 100")
    public int getEdad() { return edad; }

    /**
     * @Email: Verifica que la cadena contenga un formato válido (ejemplo@dominio.com).
     * NOTA: @Email acepta campos vacíos. Si quieres que el email sea OBLIGATORIO,
     * debes acompañarlo con un @NotNull o @NotBlank.
     */
    @Email(message = "Formato de email incorrecto")
    public String getEmail() { return email; }

    public String getOptativa() { return optativa; }
    public String getCiudadEstudios() { return ciudadEstudios; }
    public String getIdiomasAlumno() { return idiomasAlumno; }

    // Setters (Spring los llama automáticamente para inyectar los datos)
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setEmail(String email) { this.email = email; }
    public void setOptativa(String optativa) { this.optativa = optativa; }
    public void setCiudadEstudios(String ciudadEstudios) { this.ciudadEstudios = ciudadEstudios; }
    public void setIdiomasAlumno(String idiomasAlumno) { this.idiomasAlumno = idiomasAlumno; }
}