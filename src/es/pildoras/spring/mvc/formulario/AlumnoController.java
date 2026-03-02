package es.pildoras.spring.mvc.formulario;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * SECCIÓN: DECLARACIÓN DEL CONTROLADOR Y NAMESPACE
 * @Controller indica que esta clase es un componente web manejado por Spring.
 * @RequestMapping("/alumno") crea un "espacio de nombres" o prefijo de ruta.
 * Todas las rutas dentro de esta clase requerirán "/alumno/..." para ser accedidas.
 * Esto es vital arquitectónicamente para evitar que colisionen con las rutas
 * de tu otro proyecto ("fundamentos").
 */
@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    public AlumnoController() {
    }

    /**
     * PASO 1: PREPARAR EL ESCENARIO (EL FORMULARIO VACÍO)
     * Antes de mostrar el formulario en pantalla, Spring necesita enviarle un objeto
     * "en blanco" para poder conectarlo con las etiquetas de Spring (<form:input>) en el JSP.
     * * @param modelo Es la "caja de transporte" de Spring para pasar datos desde
     * el backend hacia la vista (el HTML).
     * @return String con la ruta relativa hacia el archivo JSP.
     */
    @RequestMapping("/muestraFormulario")
    public String muestraFormulario (Model modelo){

        // Creamos el objeto a enviar (está vacío por ahora, sus atributos son null)
        Alumno elAlumno = new Alumno();

        // Con addAttribute empaquetamos dentro del modelo el objeto Alumno.
        // El String "elAlumno" es la ETIQUETA EXACTA que usaremos en el JSP
        // dentro de la propiedad modelAttribute="..." del formulario.
        // * COMPARACIÓN CON JAVA EE: Esto es el equivalente moderno y limpio a hacer:
        // request.setAttribute("elAlumno", elAlumno);
        modelo.addAttribute("elAlumno", elAlumno);

        // Retornamos el formulario de registro para que el usuario complete los datos.
        // El ViewResolver le sumará el prefijo y sufijo: /WEB-INF/vista/formulario/alumnoRegistroFormulario.jsp
        return "formulario/alumnoRegistroFormulario";
    }

    /**
     * PASO 2: RECIBIR, VALIDAR Y PROCESAR (DATA BINDING)
     * * * @ModelAttribute("elAlumno") hace un trabajo gigantesco por detrás:
     * 1. Intercepta la petición POST/GET del formulario.
     * 2. Busca los campos HTML (nombre, apellido) que coincidan con la clase Alumno.
     * 3. Llama a los métodos Setters (setNombre, setApellido) y les inyecta el texto del usuario.
     * 4. Nos entrega el objeto 'elAlumno' completamente lleno y listo para guardar en una base de datos.
     * 5. Vuelve a guardar automáticamente este objeto rellenado en el 'Model' para que viaje a la vista final.
     *
     * * * @Valid: Le dice a Spring "¡Alto! Antes de inyectar los datos en 'elAlumno',
     * revisa sus anotaciones (@NotNull, @Min, @Email) y valida todo".
     * * * BindingResult: Es la "libreta de reportes". Aquí Spring anota si la validación falló.
     * IMPORTANTE: BindingResult DEBE ir inmediatamente después del parámetro @Valid.
     */
    @RequestMapping("/procesarFormulario")
    public String procesarFormulario(@Valid @ModelAttribute("elAlumno") Alumno elAlumno, BindingResult resultadovalidacion){

        // hasErrors() revisa la libreta de reportes. Si hay al menos un error...
        if (resultadovalidacion.hasErrors()){

            // ...cortamos el flujo y devolvemos al usuario al formulario.
            // Spring se encargará de mostrar los mensajes definidos en la clase Alumno.
            return "formulario/alumnoRegistroFormulario";
        } else {
            /*
             * COMPARACIÓN CON JAVA EE (SERVLETS PUROS):
             * En un Servlet tradicional, tendríamos que haber hecho todo esto a mano, línea por línea:
             * * String nom = request.getParameter("nombre");
             * String ape = request.getParameter("apellido");
             * Alumno elAlumno = new Alumno();
             * elAlumno.setNombre(nom);
             * elAlumno.setApellido(ape);
             * request.setAttribute("elAlumno", elAlumno);
             * * ¡Spring hace esas 6 líneas de código automáticamente solo con la anotación @ModelAttribute!
             */

            // Si la validación pasó limpia, vamos a la pantalla de éxito.
            return "formulario/confirmacionRegistroAlumno";
        }
    }

    /**
     * SECCIÓN: PRE-PROCESAMIENTO DE PETICIONES (@InitBinder)
     * Este método se ejecuta SIEMPRE antes de que el controlador procese cualquier petición web.
     * Actúa como un filtro que limpia o transforma los datos entrantes antes del Data Binding.
     *
     * * COMPARACIÓN CON JAVA EE (SERVLETS):
     * En Java EE tendrías que haber llamado manualmente a la función .trim() por cada variable:
     * String nombre = request.getParameter("nombre").trim();
     * if (nombre.isEmpty()) nombre = null;
     */
    @InitBinder
    public void miBinder(WebDataBinder binder){

        // StringTrimmerEditor es una clase nativa de Spring.
        // Su función es eliminar los espacios en blanco iniciales y finales de un String.
        // El parámetro 'true' es la clave: le dice a Spring que si después de recortar
        // los espacios el String queda completamente vacío (""), debe convertirlo a 'null'.
        StringTrimmerEditor recortaEspaciosBlanco = new StringTrimmerEditor(true);

        // Aquí registramos la regla en el "binder" (el motor que une el HTML con Java).
        // Le indicamos: "Para CUALQUIER dato de tipo String.class que llegue del formulario,
        // aplícale la regla 'recortaEspaciosBlanco' antes de inyectarlo en mi objeto Alumno".
        binder.registerCustomEditor(String.class, recortaEspaciosBlanco);
    }
}