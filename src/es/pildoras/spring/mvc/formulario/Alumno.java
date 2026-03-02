package es.pildoras.spring.mvc.formulario;

import es.pildoras.spring.mvc.formulario.validacionespersonalizadas.CPostalMendoza;

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
     * * @NotBlank: Es superior a @NotNull para cadenas de texto. No solo impide nulos,
     * sino que recorta espacios en blanco (trim) y verifica que el usuario haya
     * escrito caracteres reales.
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
     * SECCIÓN: VALIDACIÓN CON EXPRESIONES REGULARES (RegEx)
     * (CÓDIGO COMENTADO PARA REFERENCIA)
     * @Pattern: Obliga a que el texto ingresado coincida exactamente con un patrón matemático.
     * Desglose de la fórmula "[0-9]{4}":
     * - [0-9]: Define un "rango". Solo acepta números del 0 al 9.
     * - {4}: Es un "cuantificador". Exige que haya EXACTAMENTE 4 caracteres. Ni 3, ni 5.
     * * COMPARACIÓN CON JAVA EE:
     * En Java clásico, para lograr esto debías importar 'java.util.regex.Pattern', compilar la
     * expresión, y evaluarla usando un objeto 'Matcher' dentro de un bloque if/else en tu
     * Servlet. Spring MVC lo resuelve de forma declarativa con una sola línea.
     */
    //@Pattern(regexp = "[0-9]{4}", message = "Solo se permiten 4 valores numéricos")

    /**
     * SECCIÓN: VALIDACIÓN PERSONALIZADA (CUSTOM ANNOTATION)
     * Al colocar @CPostalMendoza, Spring busca la clase CPostalMendozaValidacion.class
     * y ejecuta su método isValid().
     * Como no le pasamos parámetros, usará el valor "55" y el mensaje por defecto
     * configurados en la interfaz CPostalMendoza.
     */
    @CPostalMendoza
    public String codigoPostal;

    /**
     * SECCIÓN: VALIDACIÓN COMBINADA PARA CORREO ELECTRÓNICO
     * Aquí estamos apilando reglas. Spring Validator evaluará ambas anotaciones
     * en orden antes de dar el visto bueno para guardar el dato.
     * * @NotBlank: Asegura que el usuario no envíe la caja vacía o llena de espacios.
     * * @Email: Entra en acción solo si hay texto, verificando formato básico.
     * * @Pattern: Fuerza un dominio público estricto solucionando la carencia del @Email.
     * * Desglose de la fórmula ".*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}":
     * - .* -> Permite cualquier texto antes del arroba.
     * - @ -> Exige literalmente el símbolo arroba.
     * - [a-zA-Z0-9.-]+ -> Exige el nombre del dominio (letras, números, guiones o puntos).
     * - \\. -> Exige literalmente un punto (usamos doble barra para escapar el carácter en Java).
     * - [a-zA-Z]{2,} -> Exige 2 o más letras al final (ej: .com, .ar).
     */
    @NotBlank(message = "El email es un campo obligatorio y no puede quedar en blanco")
    @Email(message = "El formato de email ingresado es incorrecto")
    @Pattern(regexp=".*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message="El email debe contener un dominio válido (ej: .com, .ar)")
    public String getEmail() { return email; }

    public String getCodigoPostal() { return codigoPostal; }

    public String getOptativa() { return optativa; }
    public String getCiudadEstudios() { return ciudadEstudios; }
    public String getIdiomasAlumno() { return idiomasAlumno; }

    // Setters (Spring los llama automáticamente para inyectar los datos)
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setEmail(String email) { this.email = email; }
    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }
    public void setOptativa(String optativa) { this.optativa = optativa; }
    public void setCiudadEstudios(String ciudadEstudios) { this.ciudadEstudios = ciudadEstudios; }
    public void setIdiomasAlumno(String idiomasAlumno) { this.idiomasAlumno = idiomasAlumno; }
}