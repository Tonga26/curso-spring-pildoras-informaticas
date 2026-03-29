package es.pildoras.spring.gestioncrud.controller;

import es.pildoras.spring.gestioncrud.dao.ClienteDAO;
import es.pildoras.spring.gestioncrud.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// ANOTACIÓN @Controller: Indica a Spring que esta clase actuará como un Controlador MVC.
@Controller
// ANOTACIÓN @RequestMapping: Define la ruta base para todos los métodos de esta clase.
@RequestMapping("/cliente")
public class Controlador {

    // ATRIBUTO clienteDAO: Es la interfaz que nos permite comunicarnos con la base de datos.
    // ANOTACIÓN @Autowired: Inyección de dependencias. Spring buscará una clase que implemente
    // ClienteDAO (en este caso ClienteDAOImpl) y nos la proporcionará automáticamente aquí.
    @Autowired
    private ClienteDAO clienteDAO;

    // MÉTODO vistaClientes: Se ejecuta cuando el usuario ingresa a "/cliente/lista".
    // Parámetro 'elModelo': Interfaz de Spring usada para pasar datos del backend a la vista JSP.
    @RequestMapping("/lista")
    public String vistaClientes(Model elModelo){
        // 1. Solicitamos al DAO la lista completa de clientes.
        List<Cliente> losClientes = clienteDAO.getClientes();
        // 2. Empaquetamos esa lista en el modelo bajo el alias "clientes".
        elModelo.addAttribute("clientes", losClientes);
        // 3. Retornamos el nombre del archivo JSP (sin la extensión) que Spring debe renderizar.
        return "lista-clientes";
    }

    // MÉTODO muestraFormularioAgregar: Prepara el terreno para insertar un nuevo cliente.
    @RequestMapping("/muestraFormularioAgregar")
    public String muestraFormularioAgregar (Model elModelo){
        // 1. Instanciamos un objeto Cliente completamente vacío en la memoria.
        Cliente elCliente = new Cliente();
        // 2. Enviamos este objeto vacío al JSP bajo el alias "cliente".
        // Esto es fundamental para que el <form:form modelAttribute="cliente"> tenga algo que llenar.
        elModelo.addAttribute("cliente", elCliente);

        return "formulario-cliente";
    }

    // MÉTODO guardarCliente: Atrapa los datos enviados por el formulario al hacer "Submit".
    // ANOTACIÓN @PostMapping: Variante de @RequestMapping que SOLO acepta peticiones de tipo POST.
    // Parámetro @ModelAttribute("cliente") Cliente elCliente:
    //    Aquí ocurre la magia del "Data Binding". Spring toma los datos del formulario HTTP,
    //    crea un objeto Cliente, ejecuta los métodos set, y nos entrega el objeto 'elCliente' hidratado.
    @PostMapping("/guardarCliente")
    public String guardarCliente(@ModelAttribute("cliente") Cliente elCliente){

        // 1. Delegamos al DAO la responsabilidad física de persistir en la base de datos.
        // Ahora este método es capaz de hacer INSERT (si el ID es 0/null) o UPDATE (si el ID existe).
        clienteDAO.guardarCliente(elCliente);

        // 2. PATRÓN REDIRECT: En lugar de retornar un archivo JSP, retornamos una redirección.
        // La palabra reservada "redirect:" obliga al navegador del usuario a hacer una NUEVA petición
        // hacia la URL "/crud/cliente/lista". Esto evita que si el usuario presiona F5 (Actualizar)
        // se vuelva a enviar el formulario por accidente (evitando registros duplicados).
        return "redirect:/crud/cliente/lista";
    }

    // MÉTODO muestraFormularioActualizar: Atrapa el clic del botón "Editar" en la lista.
    // ANOTACIÓN @GetMapping: Solo acepta peticiones que viajan por la URL (GET).
    @GetMapping("/muestraFormularioActualizar")
    public String muestraFormularioActualizar(@RequestParam("clienteId") int id, Model elModelo) {

        // 1. Vamos a la BD y traemos el cliente específico usando el ID de la URL.
        Cliente elCliente = clienteDAO.getClienteById(id);

        // 2. Pasamos el cliente lleno de datos al modelo para que el formulario se autorellene.
        elModelo.addAttribute("cliente", elCliente);

        // 3. Reutilizamos exactamente el mismo archivo JSP que usamos para agregar.
        return "formulario-cliente";
    }
}