package es.pildoras.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * SECCIÓN: SEGUNDO CONTROLADOR Y EVASIÓN DE AMBIGÜEDAD
 * Para evitar problemas de ambigüedad en las rutas (ya que los métodos de abajo
 * se llaman exactamente igual que en el HolaAlumnosControlador), debemos agregar
 * una ruta relativa diferente.
 * * Al poner "/secundario", creamos un espacio de nombres (namespace) totalmente
 * aislado. Tomcat ya no se confundirá al arrancar.
 */
@Controller
@RequestMapping ("/secundario")
public class ControladorPruebaRutas {

    /**
     * SECCIÓN: MOSTRAR FORMULARIO DESDE RUTA SECUNDARIA
     * Aunque el método se llama igual que en el otro archivo, su ruta absoluta es diferente.
     * Ruta completa: /secundario/muestraFormulario
     */
    @RequestMapping ("/muestraFormulario")
    public String muestraFormulario(){
        // Busca el archivo /WEB-INF/vista/HolaAlumnosFormulario.jsp
        // No hay problema en que dos controladores distintos devuelvan la misma vista.
        return "HolaAlumnosFormulario";
    }

    /**
     * SECCIÓN: PROCESAMIENTO DESDE RUTA SECUNDARIA
     * Ruta completa: /secundario/procesaFormulario2
     */
    @RequestMapping("/procesaFormulario2")
    public String otroProcesoFormulario(@RequestParam("nombreAlumno") String nombre, Model modelo){

        // Lógica diferente para demostrar que estamos en el segundo controlador
        nombre += " es el peor alumno.";
        String mensajeFinal = "¿Quien es el peor alumno? " + nombre;

        modelo.addAttribute("mensajeClaro", mensajeFinal);

        return "HolaAlumnosSpring";
    }
}