package es.pildoras.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HolaAlumnosControlador {

    /**
     * MOSTRAR EL FORMULARIO VACÍO
     * Este método se ejecuta cuando el usuario hace clic en el enlace de paginaEjemplo.
     * Solo sirve para entregar el HTML del formulario al cliente.
     */
    @RequestMapping ("/muestraFormulario")
    public String muestraFormulario(){

        // Busca el archivo /WEB-INF/vista/HolaAlumnosFormulario.jsp
        return "HolaAlumnosFormulario";
    }

    /**
     * PROCESAR LOS DATOS ENVIADOS
     * Este método es el "action" del formulario. Se ejecuta cuando el usuario hace clic
     * en el botón "Submit". Por ahora, solo redirige a otra vista, pero aquí es donde
     * en el futuro guardarás datos en la base de datos (MySQL).
     */
    @RequestMapping("/procesaFormulario")
    public String procesaFormulario(){

        // Busca el archivo /WEB-INF/vista/HolaAlumnosSpring.jsp
        return "HolaAlumnosSpring";
    }

    /**
     * SECCIÓN: PROCESAMIENTO AVANZADO CON REQUEST Y MODELO
     * Este método recibe los datos del formulario, los manipula en el backend
     * y luego envía información nueva hacia la vista usando el objeto Model.
     */
    @RequestMapping("/procesaFormulario2")
    public String otroProcesoFormulario(HttpServletRequest request, Model modelo){

        /*
         * HttpServletRequest request:
         * Es el objeto que contiene TODA la información de la petición que hizo el navegador.
         * (IP del usuario, tipo de navegador, cookies, y por supuesto, los datos del formulario).
         * * request.getParameter("nombreAlumno"):
         * Busca en la URL (o en el cuerpo del POST) una variable llamada "nombreAlumno"
         * (que viene del atributo 'name' de tu <input> HTML) y extrae su valor (ej: "Gaston").
         */
        String nombre = request.getParameter("nombreAlumno");

        /*
         * Aquí simplemente manipulamos el dato puro de Java.
         * Le concatenamos texto al nombre que escribió el usuario.
         */
        nombre += " es el mejor alumno.";

        /*
         * Model modelo:
         * Es como una "caja" o un "maletín" que Spring nos da para meter datos
         * que queremos que viajen desde este Controlador hacia la Vista (el archivo JSP).
         * * addAttribute("nombreDeLaVariable", valorQueContiene):
         * Empaquetamos nuestro 'mensajeFinal' bajo la etiqueta "mensajeClaro".
         * Así, la vista JSP podrá llamarlo usando ${mensajeClaro}.
         */
        String mensajeFinal = "¿Quien es el mejor alumno?" + nombre;
        // Agregar info al modelo
        modelo.addAttribute("mensajeClaro", mensajeFinal);

        /*
         * Devolvemos el nombre lógico de la vista.
         * Spring tomará la "caja" (Model) y se la entregará a este JSP para que la renderice.
         */
        return "HolaAlumnosSpring";
    }
}