package es.pildoras.spring.gestioncrud.controller;

import es.pildoras.spring.gestioncrud.dao.ClienteDAO;
import es.pildoras.spring.gestioncrud.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class Controlador {

    @Autowired
    private ClienteDAO clienteDAO;

    @RequestMapping("/lista")
    public String vistaClientes(Model elModelo){

        // 1. Obtener los clientes desde el DAO
        List<Cliente> losClientes = clienteDAO.getClientes();

        // 2. Agregar los clientes al modelo
        elModelo.addAttribute("clientes", losClientes);

        return "lista-clientes";
    }
}
