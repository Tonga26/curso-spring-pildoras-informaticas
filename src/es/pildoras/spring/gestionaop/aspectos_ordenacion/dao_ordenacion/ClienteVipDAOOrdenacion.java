package es.pildoras.spring.gestionaop.aspectos_ordenacion.dao_ordenacion;

import org.springframework.stereotype.Component;

@Component
public class ClienteVipDAOOrdenacion {

    public void insertaClienteVip(){
        System.out.println("Trabajo realizado ok. Cliente VIP insertado con éxito.");
    }
}
