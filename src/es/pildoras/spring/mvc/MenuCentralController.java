package es.pildoras.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SECCIÓN: CONTROLADOR FRONTAL DE LA INTERFAZ (DASHBOARD)
 * Este será el ÚNICO controlador en toda la aplicación que tenga la ruta raíz ("/")
 * para la interfaz gráfica. Su única misión es mostrar el menú principal.
 */
@Controller
public class MenuCentralController {

    /**
     * SECCIÓN: MAPEO DE LA RAÍZ
     * * COMPARACIÓN CON JAVA EE (SERVLETS):
     * En Java EE clásico, si querías una página de inicio, simplemente ponías un
     * archivo 'index.jsp' suelto en la carpeta 'webapp' y el servidor lo leía solo.
     * En Spring MVC, por razones de seguridad y control de flujo, incluso la página
     * de inicio debe pasar por un Controlador.
     */
    @RequestMapping("/")
    public String mostrarMenuPrincipal() {
        // Devuelve el nombre de la vista que contendrá nuestro menú.
        // Como no tiene prefijo, el ViewResolver buscará en /WEB-INF/vista/menuPrincipal.jsp
        return "menuPrincipal";
    }
}
