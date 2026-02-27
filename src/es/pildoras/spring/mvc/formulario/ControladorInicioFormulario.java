package es.pildoras.spring.mvc.formulario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SECCIÓN: CONTROLADOR DE NAVEGACIÓN (LAUNCHER)
 * Aplica el Principio de Responsabilidad Única: Solo se encarga de mostrar
 * la vista inicial de este módulo, separando la navegación de la lógica de negocio (Alumno).
 */
@Controller
public class ControladorInicioFormulario {

    /**
     * SECCIÓN: MAPEO HACIA LA VISTA DE BIENVENIDA
     * * COMPARACIÓN CON JAVA EE:
     * En Java EE, separar controladores por responsabilidad implicaba crear múltiples
     * clases Servlet pesadas y ensuciar el web.xml. En Spring, los @Controller son
     * tan livianos que podemos crear los que queramos para mantener el código ordenado.
     */
    @RequestMapping("/inicioRegistro")
    public String muestraPaginaBienvenida(){

        // Retorna la vista donde está tu enlace "Ir al formulario de registro"
        return "formulario/paginaBienvenida";
    }
}