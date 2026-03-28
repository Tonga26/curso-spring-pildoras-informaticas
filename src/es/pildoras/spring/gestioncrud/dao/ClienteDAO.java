package es.pildoras.spring.gestioncrud.dao;

import es.pildoras.spring.gestioncrud.entity.Cliente;

import java.util.List;

public interface ClienteDAO {

    public List<Cliente> getClientes();
}
