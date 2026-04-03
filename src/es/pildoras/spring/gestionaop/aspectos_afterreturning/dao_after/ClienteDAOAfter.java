package es.pildoras.spring.gestionaop.aspectos_afterreturning.dao_after;

import es.pildoras.spring.gestionaop.aspectos_afterreturning.ClienteAfter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// @Component: Registra la clase como un Bean en Spring para poder inyectarla luego (en ClasePrincipalAfter).
@Component
public class ClienteDAOAfter {

    /* ==========================================================================
       MÉTODO 1: INSERCIÓN (Interceptado por @Before)
       ========================================================================== */
    // Método que simula insertar un cliente en la BBDD.
    // Recibe un objeto ClienteAfter y un String con el tipo.
    public void insertaCliente(ClienteAfter elCliente, String tipo){
        System.out.println("Trabajo realizado ok. Cliente NORMAL insertado con éxito.");
    }

    /* ==========================================================================
       MÉTODO 2: LECTURA (Interceptado por @Before y @AfterReturning)
       ========================================================================== */
    // Método que simula realizar un SELECT a la base de datos para obtener todos los clientes.
    // Retorna una lista de objetos ClienteAfter.
    public List<ClienteAfter> encuentraClientes(){

        // Inicializamos una lista vacía.
        List<ClienteAfter> listaCliente = new ArrayList<>();

        // Creamos objetos simulando los registros devueltos por la base de datos.
        ClienteAfter cliente1 = new ClienteAfter("María", "Normal");
        ClienteAfter cliente2 = new ClienteAfter("Ana", "Normal");
        // Notar que este es el cliente VIP que nuestro aspecto @AfterReturning va a detectar.
        ClienteAfter cliente3 = new ClienteAfter("Sandra", "VIP");
        ClienteAfter cliente4 = new ClienteAfter("Antonio", "Normal");

        // Agregamos los objetos a la lista.
        listaCliente.add(cliente1);
        listaCliente.add(cliente2);
        listaCliente.add(cliente3);
        listaCliente.add(cliente4);

        // Mensaje de control para saber cuándo termina exactamente este método.
        System.out.println("Ejecución finalizada del método encuentraClientes()");

        // Retornamos la lista. Este es el valor que Spring captura y le pasa a nuestro @AfterReturning
        return listaCliente;
    }
}