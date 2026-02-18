package es.pildoras.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Controlador {

    @RequestMapping// Con esta anotación "RequestMapping" decimos que este método es el encargado
    // de mapear cuál es la vista que queremos ver
    public String muestraPagina(){
        return "paginaEjemplo";
    }
}
