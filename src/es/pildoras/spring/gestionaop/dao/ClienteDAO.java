package es.pildoras.spring.gestionaop.dao;

import org.springframework.stereotype.Component;
import es.pildoras.spring.gestionaop.Cliente;

@Component
public class ClienteDAO {

    public void insertaCliente(Cliente elCliente, String tipo){

        System.out.println("Trabajo realizado ok. Cliente NORMAL insertado con éxito.");

    }


}
