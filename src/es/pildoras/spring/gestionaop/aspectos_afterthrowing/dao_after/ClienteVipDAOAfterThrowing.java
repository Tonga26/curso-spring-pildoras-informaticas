package es.pildoras.spring.gestionaop.aspectos_afterthrowing.dao_after;

import org.springframework.stereotype.Component;

@Component
public class ClienteVipDAOAfterThrowing {

    public void insertaClienteVip(){
        System.out.println("Trabajo realizado ok. Cliente VIP insertado con éxito.");
    }
}
