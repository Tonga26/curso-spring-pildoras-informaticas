package es.pildoras.spring.gestioncrud.dao;

import es.pildoras.spring.gestioncrud.entity.Cliente;
import java.util.List;

// INTERFAZ ClienteDAO: Define el contrato de las operaciones permitidas sobre la entidad Cliente.
public interface ClienteDAO {

    // MÉTODO getClientes: Retorna una colección List con todos los clientes de la BD.
    List<Cliente> getClientes();

    // MÉTODO guardarCliente: Recibe un objeto Cliente y evalúa si debe insertarlo o actualizarlo.
    // Parámetro 'elCliente': El objeto hidratado que viene desde el Controlador.
    void guardarCliente(Cliente elCliente);

    // MÉTODO getClienteById: Busca un registro específico utilizando su clave primaria.
    Cliente getClienteById(int id);
}