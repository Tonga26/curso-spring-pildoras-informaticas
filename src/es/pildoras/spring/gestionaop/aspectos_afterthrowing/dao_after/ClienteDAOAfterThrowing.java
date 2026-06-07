package es.pildoras.spring.gestionaop.aspectos_afterthrowing.dao_after;

import es.pildoras.spring.gestionaop.aspectos_afterthrowing.ClienteAfterThrowing;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// @Component: Registra la clase como un Bean en Spring para poder inyectarla luego (en ClasePrincipalAfterThrowing).
@Component
public class ClienteDAOAfterThrowing {

    /* ==========================================================================
       MÉTODO 1: INSERCIÓN (Interceptado por @Before)
       ========================================================================== */
    // Método que simula insertar un cliente en la BBDD.
    // Recibe un objeto ClienteAfter y un String con el tipo.
    public void insertaCliente(ClienteAfterThrowing elCliente, String tipo){
        System.out.println("Trabajo realizado ok. Cliente NORMAL insertado con éxito.");
    }

    /* ==========================================================================
       MÉTODO 2: LECTURA (Preparado para lanzar Excepción)
       ========================================================================== */
    /**
     * Método que simula realizar un SELECT a la base de datos para obtener todos los clientes.
     * Ahora cuenta con un parámetro de control para simular una falla en la conexión.
     * * @param miParam Booleano que actúa como "interruptor" de errores. Si es true,
     * se simula una caída de la Base de Datos lanzando una RuntimeException.
     * @return Una lista de objetos ClienteAfterThrowing si no hay errores.
     * @throws RuntimeException Si miParam es true.
     */
    public List<ClienteAfterThrowing> encuentraClientes(boolean miParam){

        // Si el parámetro es true, forzamos la excepción para practicar el @AfterThrowing.
        if (miParam) throw new RuntimeException("ERROR: No se ha podido conectar con la BBDD.");

        // Inicializamos una lista vacía.
        List<ClienteAfterThrowing> listaCliente = new ArrayList<>();

        // Creamos objetos simulando los registros devueltos por la base de datos.
        ClienteAfterThrowing cliente1 = new ClienteAfterThrowing("María", "Normal");
        ClienteAfterThrowing cliente2 = new ClienteAfterThrowing("Ana", "Normal");
        // Notar que este es el cliente VIP.
        ClienteAfterThrowing cliente3 = new ClienteAfterThrowing("Sandra", "VIP");
        ClienteAfterThrowing cliente4 = new ClienteAfterThrowing("Antonio", "Normal");

        // Agregamos los objetos a la lista.
        listaCliente.add(cliente1);
        listaCliente.add(cliente2);
        listaCliente.add(cliente3);
        listaCliente.add(cliente4);

        // Mensaje de control para saber cuándo termina exactamente este método.
        System.out.println("Ejecución finalizada del método encuentraClientes()");

        // Retornamos la lista.
        return listaCliente;
    }
}