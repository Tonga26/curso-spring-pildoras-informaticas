package es.pildoras.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HolaAlumnosControlador {

    // Método encargado de pedir el primer formulario al servidor (proporciona el formulario)
    @RequestMapping ("/muestraFormulario") // Le indicamos a Spring que vamos a hacer una petición de un url
    public String muestraFormulario(){
        return "HolaAlumnosFormulario";
    }

    // Método encargado de procesar la información del formulario que viaja rellenado
}
