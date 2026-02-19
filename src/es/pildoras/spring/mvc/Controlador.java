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
     * @RequestMapping
     * // BUENA PRÁCTICA: Siempre definir la ruta raíz de forma explícita ("/")
     * // para evitar errores 404 en servidores de producción (Tomcat standalone).
     */
    @RequestMapping ("/")
    public String muestraPagina(){

        // Retorna el nombre lógico de la vista.
        // El ViewResolver le agregará el prefijo y sufijo: /WEB-INF/vista/paginaEjemplo.jsp
        return "paginaEjemplo";
    }
}