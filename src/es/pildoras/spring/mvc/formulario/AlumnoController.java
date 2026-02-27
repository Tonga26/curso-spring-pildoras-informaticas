package es.pildoras.spring.mvc.formulario;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * PASO 2: RECIBIR Y PROCESAR (DATA BINDING)
     * * @ModelAttribute("elAlumno") hace un trabajo gigantesco por detrás:
     * 1. Intercepta la petición POST/GET del formulario.
     * 2. Busca los campos HTML (nombre, apellido) que coincidan con la clase Alumno.
     * 3. Llama a los métodos Setters (setNombre, setApellido) y les inyecta el texto del usuario.
     * 4. Nos entrega el objeto 'elAlumno' completamente lleno y listo para guardar en una base de datos.
     * 5. Vuelve a guardar automáticamente este objeto rellenado en el 'Model' para que viaje a la vista final.
     * * @param elAlumno El objeto POJO ya instanciado y rellenado por Spring.
     * @return String con la ruta relativa hacia la vista de confirmación.
     */
    @RequestMapping("/procesarFormulario")
    public String procesarFormulario(@ModelAttribute("elAlumno") Alumno elAlumno){

        /*
         * COMPARACIÓN CON JAVA EE (SERVLETS PUROS):
         * En un Servlet tradicional, tendrías que haber hecho todo esto a mano, línea por línea:
         * * String nom = request.getParameter("nombre");
         * String ape = request.getParameter("apellido");
         * Alumno elAlumno = new Alumno();
         * elAlumno.setNombre(nom);
         * elAlumno.setApellido(ape);
         * request.setAttribute("elAlumno", elAlumno);
         * * ¡Spring hace esas 6 líneas de código aburrido automáticamente solo con la anotación @ModelAttribute!
         */

        // Como Spring ya guardó automáticamente 'elAlumno' en el Model,
        // no hace falta hacer un modelo.addAttribute(...) de nuevo aquí.
        // Solo nos queda derivar a la vista de éxito.
        return "formulario/confirmacionRegistroAlumno";
    }
}