package es.pildoras.spring.gestioncrud.dao;

import es.pildoras.spring.gestioncrud.entity.Cliente;
import java.util.List;

// INTERFAZ ClienteDAO: Define el contrato de las operaciones permitidas sobre la entidad Cliente.
public interface ClienteDAO {

    // MÉTODO getClientes: Retorna una colección List con todos los clientes de la BD.
    List<Cliente> getClientes();

    // MÉTODO insertarCliente: Recibe un objeto Cliente y lo persiste en la BD.
    // Parámetro 'elCliente': El objeto hidratado que viene desde el Controlador.
    void insertarCliente(Cliente elCliente);
}