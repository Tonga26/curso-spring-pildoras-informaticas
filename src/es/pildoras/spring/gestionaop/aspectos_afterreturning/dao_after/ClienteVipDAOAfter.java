package es.pildoras.spring.gestionaop.aspectos_afterreturning.dao_after;

import org.springframework.stereotype.Component;

@Component
public class ClienteVipDAOAfter {

    public void insertaClienteVip(){
        System.out.println("Trabajo realizado ok. Cliente VIP insertado con éxito.");
    }
}
