package es.pildoras.spring.gestionaop.dao;

import org.springframework.stereotype.Component;
import es.pildoras.spring.gestionaop.Cliente;

@Component
public class ClienteDAO {

    private String valoracionClienteNormal;
    private String codigoClienteNormal;

    public void insertaCliente(Cliente elCliente, String tipo){

        System.out.println("Trabajo realizado ok. Cliente NORMAL insertado con éxito.");

    }

    public String getValoracionClienteNormal() {
        System.out.println("GETTER: Obteniendo valoración del cliente.");
        return valoracionClienteNormal;
    }

    public void setValoracionClienteNormal(String valoracionClienteNormal) {
        System.out.println("SETTER: Estableciendo valoración del cliente.");
        this.valoracionClienteNormal = valoracionClienteNormal;
    }

    public String getCodigoClienteNormal() {
        System.out.println("GETTER: Obteniendo código del cliente.");
        return codigoClienteNormal;
    }

    public void setCodigoClienteNormal(String codigoClienteNormal) {
        System.out.println("SETTER: Estableciendo código del cliente.");
        this.codigoClienteNormal = codigoClienteNormal;
    }
}
