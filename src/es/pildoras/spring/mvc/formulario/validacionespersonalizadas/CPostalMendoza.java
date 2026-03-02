package es.pildoras.spring.mvc.formulario.validacionespersonalizadas;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * SECCIÓN: DEFINICIÓN DE ANOTACIÓN PERSONALIZADA
 * La palabra reservada @interface le dice a Java: "No estoy creando una clase ni
 * una interfaz normal, estoy creando una nueva Etiqueta (Anotación) llamada @CPostalMendoza".
 * * * COMPARACIÓN CON JAVA EE (SERVLETS PUROS):
 * En Java EE, crear una validación de negocio específica (como verificar prefijos de
 * códigos postales) requería escribir la lógica "if(cp.startsWith("55"))" directamente
 * dentro del Servlet o en una clase auxiliar, repitiendo el código en cada formulario.
 * Spring MVC nos permite encapsular esa regla en una simple etiqueta reutilizable.
 */

/*
 * @Constraint: Es el puente de conexión.
 * Una etiqueta por sí sola no hace nada, es solo texto. Esta meta-anotación le dice a Spring:
 * "Cuando veas esta etiqueta, ve a buscar la clase 'CPostalMendozaValidacion.class',
 * allí adentro está la lógica matemática (el if/else) que decide si el dato es válido o no".
 */
@Constraint(validatedBy = CPostalMendozaValidacion.class)

/*
 * @Target: Define el "Blanco" o destino de la etiqueta. ¿Dónde se puede escribir?
 * - ElementType.METHOD: Permite que la escribas arriba de los métodos (Getters).
 * - ElementType.FIELD: Permite que la escribas arriba de las variables (private String codigoPostal).
 * Si intentaras poner esta etiqueta arriba del nombre de una clase, Java daría error.
 */
@Target({ElementType.METHOD, ElementType.FIELD})

/*
 * @Retention: Define la "Esperanza de vida" de tu anotación.
 * - RetentionPolicy.RUNTIME: Es vital para Spring. Le indica a la máquina virtual de Java (JVM)
 * que esta etiqueta debe sobrevivir al proceso de compilación y mantenerse viva mientras
 * el programa se está ejecutando. Así Spring puede leerla usando "Reflection".
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CPostalMendoza {

    // =========================================================================
    // CUERPO DE LA ANOTACIÓN (LOS 3 MÉTODOS OBLIGATORIOS DE HIBERNATE VALIDATOR)
    // =========================================================================

    /**
     * 1. EL VALOR POR DEFECTO (El prefijo a validar)
     * Define qué valor buscará la validación si el programador no especifica ninguno.
     * Como queremos validar el código de Mendoza, le asignamos "55" por defecto.
     * Uso en la clase Alumno: @CPostalMendoza (usará "55")
     * Uso alternativo: @CPostalMendoza(value="54") (sobreescribiría el valor, por ejemplo, para San Juan)
     */
    String value() default "55";

    /**
     * 2. EL MENSAJE DE ERROR POR DEFECTO
     * Es el texto que se enviará al JSP si la validación falla (si el usuario escribe "1000").
     */
    String message() default "El código postal debe comenzar con 55 (Mendoza)";

    /**
     * 3. GRUPOS DE VALIDACIÓN (Obligatorio por el estándar de Java)
     * Permite agrupar validaciones para ejecutarlas por fases.
     * Por ejemplo, ejecutar unas validaciones al crear un alumno y otras al actualizarlo.
     * Casi siempre se deja vacío por defecto (Class<?>[] default {};).
     */
    Class<?>[] groups() default {};

    /**
     * 4. PAYLOAD (Obligatorio por el estándar de Java)
     * Permite enviar información extra o metadatos junto con el error (por ejemplo,
     * niveles de severidad: INFO, WARN, ERROR). Rara vez se usa en proyectos básicos,
     * pero Spring exige que esté declarado.
     */
    Class<? extends Payload>[] payload() default {};
}