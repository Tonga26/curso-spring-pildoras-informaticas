package es.pildoras.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HolaAlumnosControlador {

    /**
     * PASO 1: MOSTRAR EL FORMULARIO VACÍO
     * Este método se ejecuta cuando el usuario hace clic en el enlace de paginaEjemplo.
     * Solo sirve para entregar el HTML del formulario al cliente.
     */
    @RequestMapping ("/muestraFormulario")
    public String muestraFormulario(){

        // Busca el archivo /WEB-INF/vista/HolaAlumnosFormulario.jsp
        return "HolaAlumnosFormulario";
    }

    /**
     * PASO 2: PROCESAR LOS DATOS ENVIADOS
     * Este método es el "action" del formulario. Se ejecuta cuando el usuario hace clic
     * en el botón "Submit". Por ahora, solo redirige a otra vista, pero aquí es donde
     * en el futuro guardarás datos en la base de datos (MySQL).
     */
    @RequestMapping("/procesaFormulario")
    public String procesaFormulario(){

        // Busca el archivo /WEB-INF/vista/HolaAlumnosSpring.jsp
        return "HolaAlumnosSpring";
    }
}