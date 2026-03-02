package es.pildoras.spring.mvc.formulario.validacionespersonalizadas;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * SECCIÓN: LA LÓGICA DE VALIDACIÓN (EL MOTOR DE LA ETIQUETA)
 * Esta clase contiene la lógica real que se ejecuta cuando Spring lee la etiqueta @CPostalMendoza.
 * Para que Spring la reconozca como un validador válido, DEBE implementar la interfaz ConstraintValidator.
 * * ConstraintValidator recibe 2 tipos de datos genéricos (Diamante <>):
 * 1. CPostalMendoza: El nombre de la anotación que va a procesar.
 * 2. String: El tipo de dato de la variable que va a validar (el código postal del HTML).
 */
public class CPostalMendozaValidacion implements
        ConstraintValidator<CPostalMendoza, String> {

    // Atributo que guardará el prefijo que queremos exigir (en nuestro caso, "55")
    private String prefijoCodigoMendoza;

    /**
     * MÉTODO 1: INICIALIZACIÓN (PREPARAR LA HERRAMIENTA)
     * Este método se ejecuta automáticamente una sola vez al cargar la aplicación.
     * Su objetivo es leer la configuración de la etiqueta y guardarla en memoria.
     * @param elCodigo Es el objeto que representa a nuestra etiqueta @CPostalMendoza.
     */
    @Override
    public void initialize(CPostalMendoza elCodigo) {
        // Extraemos el valor configurado en la etiqueta (ej: "55") y lo guardamos
        // en nuestro atributo privado para usarlo más adelante en el método isValid.
        prefijoCodigoMendoza = elCodigo.value();
    }

    /**
     * MÉTODO 2: LA LÓGICA DE NEGOCIO (EL TRIBUNAL)
     * Este método se ejecuta CADA VEZ que el usuario presiona "Enviar" en el formulario HTML.
     * @param s El texto exacto que el usuario escribió en la caja de texto (ej: "5500" o "1234").
     * @param constraintValidatorContext Un objeto auxiliar de Spring que permite personalizar
     * aún más los mensajes de error en casos avanzados.
     * @return true si el dato es correcto (pasa al controlador). false si es incorrecto (vuelve al formulario).
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        boolean validacionCodigo;

        // IMPORTANTE: Primero comprobamos que 's' no sea nulo.
        // Si no hacemos esto y el usuario no envía nada, al intentar ejecutar
        // s.startsWith() el programa explotaría con un NullPointerException.
        if (s != null) {
            // .startsWith() es un método nativo de Java que devuelve true si el
            // String 's' comienza con los caracteres guardados en 'prefijoCodigoMendoza' ("55").
            validacionCodigo = s.startsWith(prefijoCodigoMendoza);
        } else {
            // Si el campo es nulo (el usuario no lo llenó), aquí decidimos darlo por válido (true)
            // porque la responsabilidad de exigir que el campo no esté vacío recae sobre
            // la anotación @NotNull o @NotBlank en la clase Alumno, no sobre este validador específico.
            return validacionCodigo = true;
        }

        return validacionCodigo;
    }
}