package es.pildoras.spring.mvc;

// Importaciones necesarias de Spring
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SECCIÓN: DECLARACIÓN DEL CONTROLADOR
 * @Controller convierte esta clase Java normal en un "Bean" (componente)
 * que el DispatcherServlet de Spring puede encontrar y utilizar.
 */
@Controller
public class Controlador {

    /**
     * SECCIÓN: MAPEO DE RUTAS
     * @RequestMapping sin parámetros entre paréntesis significa que este método
     * responderá a la URL raíz de tu aplicación (ej. localhost:8080/Spring_MVC_Ejemplo1/).
     */
    @RequestMapping
    public String muestraPagina(){

        // Retorna el nombre lógico de la vista.
        // El ViewResolver le agregará el prefijo y sufijo: /WEB-INF/vista/paginaEjemplo.jsp
        return "paginaEjemplo";
    }
}