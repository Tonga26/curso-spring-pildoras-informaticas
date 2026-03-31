package es.pildoras.spring.gestionaop.dao;

import org.springframework.stereotype.Component;

@Component
public class ClienteDAO {

    public void insertaCliente(){
        System.out.println("Trabajo realizado ok. Cliente insertado con éxito.");
    }
}
