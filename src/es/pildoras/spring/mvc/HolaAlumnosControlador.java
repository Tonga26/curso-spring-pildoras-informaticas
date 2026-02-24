package es.pildoras.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HolaAlumnosControlador {

    /**
     * SECCIÓN: MOSTRAR EL FORMULARIO VACÍO
     * Este método se ejecuta cuando el usuario hace clic en el enlace de paginaEjemplo.
     * Solo sirve para entregar el HTML del formulario al cliente.
     */
    @RequestMapping ("/muestraFormulario")
    public String muestraFormulario(){
        // Busca el archivo /WEB-INF/vista/HolaAlumnosFormulario.jsp
        return "HolaAlumnosFormulario";
    }

    /**
     * SECCIÓN: PROCESAR LOS DATOS ENVIADOS (BÁSICO)
     * Este método es el "action" del formulario original.
     * Solo redirige a otra vista sin manipular datos complejos.
     */
    @RequestMapping("/procesaFormulario")
    public String procesaFormulario(){
        // Busca el archivo /WEB-INF/vista/HolaAlumnosSpring.jsp
        return "HolaAlumnosSpring";
    }

    /**
     * SECCIÓN: PROCESAMIENTO AVANZADO CON @RequestParam Y MODELO
     * Este método recibe los datos del formulario, pero ahora delegando a Spring
     * la tarea de extraer los parámetros de la URL o el cuerpo de la petición.
     * 
     * En Java Servlets puro harías: String nombre = request.getParameter("nombreAlumno");
     * En Spring MVC: Usamos @RequestParam directamente en los argumentos del método.
     */
    @RequestMapping("/procesaFormulario2")
    // VERSIÓN ANTIGUA (Java EE Puro con Servlets):
    // public String otroProcesoFormulario(HttpServletRequest request, Model modelo){

    // VERSIÓN MODERNA (Spring MVC):
    public String otroProcesoFormulario(@RequestParam("nombreAlumno") String nombre, Model modelo){

        /*
         * VERSIÓN ANTIGUA (COMENTADA):
         * Antes necesitábamos recibir el objeto gigante 'request' entero solo para
         * llamar a su método getParameter y extraer un simple String.
         */
        // String nombre = request.getParameter("nombreAlumno");

        /*
         * EXPLICACIÓN DE @RequestParam("nombreAlumno") String nombre:
         * 1. Spring intercepta la petición del usuario antes de que entre al método.
         * 2. Busca en la URL (o en el formulario POST) un parámetro llamado "nombreAlumno".
         * 3. Extrae su valor de texto.
         * 4. Se lo inyecta automáticamente a la variable Java 'nombre'.
         * ¡Ventaja!: El código es más limpio y ya no dependemos de la librería de Servlets.
         */

        /*
         * Aquí simplemente manipulamos el dato puro de Java que Spring ya nos entregó.
         * Le concatenamos texto al nombre que escribió el usuario.
         */
        nombre += " es el mejor alumno.";
        String mensajeFinal = "¿Quien es el mejor alumno? " + nombre;

        /*
         * Model modelo:
         * Es la "caja" que Spring nos da para meter datos que viajarán hacia la Vista.
         * Empaquetamos nuestro 'mensajeFinal' bajo la etiqueta "mensajeClaro".
         * Así, la vista JSP podrá imprimirlo usando ${mensajeClaro}.
         */
        modelo.addAttribute("mensajeClaro", mensajeFinal);

        /*
         * Devolvemos el nombre lógico de la vista JSP.
         * Spring tomará el Modelo y lo fusionará con esta vista para crear el HTML final.
         */
        return "HolaAlumnosSpring";
    }
}