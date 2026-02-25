package es.pildoras.spring.mvc.fundamentos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// import javax.servlet.http.HttpServletRequest;

/**
 * SECCIÓN: ENRUTAMIENTO JERÁRQUICO A NIVEL DE CLASE
 * Para evitar problemas de ambigüedad en las rutas (por ejemplo, que dos controladores
 * distintos tengan un método mapeado a "/muestraFormulario"), debemos agregar una
 * ruta relativa (llamada por ejemplo "principal") a nivel de clase.
 * Esta va a ser la ruta "padre" o prefijo de TODAS las rutas contenidas en esta clase.
 */
@Controller
@RequestMapping ("/principal")
public class HolaAlumnosControlador {

    /**
     * MOSTRAR EL FORMULARIO VACÍO
     * Este método se ejecuta cuando el usuario hace clic en el enlace.
     * Como la clase tiene el prefijo "/principal", la ruta real para llegar aquí
     * ahora es: /principal/muestraFormulario
     */
    @RequestMapping ("/muestraFormulario")
    public String muestraFormulario(){

        // Busca el archivo /WEB-INF/vista/HolaAlumnosFormulario.jsp
        return "fundamentos/HolaAlumnosFormulario";
    }

    /**
     * PROCESAR LOS DATOS ENVIADOS
     * Este método es el "action" del formulario.
     * Su ruta real ahora es: /principal/procesaFormulario
     */
    @RequestMapping("/procesaFormulario")
    public String procesaFormulario(){

        // Busca el archivo /WEB-INF/vista/HolaAlumnosSpring.jsp
        return "fundamentos/HolaAlumnosSpring";
    }

    /**
     * SECCIÓN: PROCESAMIENTO AVANZADO CON @RequestParam Y MODELO
     * Su ruta real ahora es: /principal/procesaFormulario2
     * * Este método recibe los datos del formulario, los manipula en el backend
     * y luego envía información nueva hacia la vista usando el objeto Model.
     */
    @RequestMapping("/procesaFormulario2")
    // VERSIÓN ANTIGUA (Java EE Puro con Servlets):
    // public String otroProcesoFormulario(HttpServletRequest request, Model modelo){

    // VERSIÓN MODERNA (Spring MVC):
    public String otroProcesoFormulario(@RequestParam("nombreAlumno") String nombre, Model modelo){

        /*
         * VERSIÓN ANTIGUA (COMENTADA):
         * HttpServletRequest request:
         * Es el objeto que contiene TODA la información de la petición que hizo el navegador.
         * (IP del usuario, tipo de navegador, cookies, y por supuesto, los datos del formulario).
         * Antes necesitábamos recibir el objeto gigante 'request' entero solo para
         * llamar a su método getParameter("nombreAlumno") y extraer un simple String.
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
        String mensajeFinal = "¿Quien es el mejor alumno? " + nombre;
        // Agregar info al modelo
        modelo.addAttribute("mensajeClaro", mensajeFinal);

        /*
         * Devolvemos el nombre lógico de la vista.
         * Spring tomará la "caja" (Model) y se la entregará a este JSP para que la renderice.
         */
        return "fundamentos/HolaAlumnosSpring";
    }
}