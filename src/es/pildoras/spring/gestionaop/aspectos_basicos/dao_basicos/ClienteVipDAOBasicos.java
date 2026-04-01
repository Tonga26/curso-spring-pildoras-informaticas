package es.pildoras.spring.gestionaop.aspectos_basicos.dao_basicos;

import org.springframework.stereotype.Component;

@Component
public class ClienteVipDAOBasicos {

    public void insertaClienteVip(){
        System.out.println("Trabajo realizado ok. Cliente VIP insertado con éxito.");
    }
}
