package es.pildoras.spring.gestionaop.aspectos_ordenacion.dao_ordenacion;

import es.pildoras.spring.gestionaop.aspectos_ordenacion.ClienteOrdenacion;
import org.springframework.stereotype.Component;

@Component
public class ClienteDAOOrdenacion {

    public void insertaCliente(ClienteOrdenacion elCliente, String tipo){

        System.out.println("Trabajo realizado ok. Cliente NORMAL insertado con éxito.");

    }

}
