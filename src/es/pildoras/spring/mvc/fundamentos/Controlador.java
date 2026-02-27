package es.pildoras.spring.mvc.fundamentos;

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
     * SECCIÓN: MAPEO DE RUTAS (ACTUALIZADO PARA EL DASHBOARD)
     * @RequestMapping
     * // BUENA PRÁCTICA: Siempre definir la ruta explícita.
     * // Antes esta era la ruta raíz ("/"), pero como ahora tenemos un Menú Central
     * // que administra la entrada principal, le asignamos una ruta específica
     * // a este ejercicio ("inicioFundamentos") para evitar el error de ambigüedad.
     * * * COMPARACIÓN CON JAVA EE:
     * En Servlets puros, para cambiar la URL de un controlador tendrías que haber
     * ido al pesado archivo 'web.xml', buscar la etiqueta <servlet-mapping> y cambiar
     * el <url-pattern>. En Spring MVC, gracias a las anotaciones, solo cambiamos
     * este String y el framework reconfigura todo automáticamente.
     */
    @RequestMapping ("/inicioFundamentos")
    public String muestraPagina(){

        // Retorna el nombre lógico de la vista.
        // El ViewResolver le agregará el prefijo y sufijo: /WEB-INF/vista/fundamentos/paginaEjemplo.jsp
        return "fundamentos/paginaEjemplo";
    }
}