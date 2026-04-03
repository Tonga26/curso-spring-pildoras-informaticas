package es.pildoras.spring.gestionaop.aspectos_ordenacion;

import es.pildoras.spring.gestionaop.Configuracion;
import es.pildoras.spring.gestionaop.aspectos_ordenacion.dao_ordenacion.ClienteDAOOrdenacion;
import es.pildoras.spring.gestionaop.aspectos_ordenacion.dao_ordenacion.ClienteVipDAOOrdenacion;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClasePrincipalOrdenacion {

    public static void main(String[] args){

        // 1. Leemos la configracion de spring
        AnnotationConfigApplicationContext contexto = new AnnotationConfigApplicationContext(Configuracion.class);

        // 2. Obtenemos el bean del contenedor de spring
        ClienteDAOOrdenacion elClienteNormal = contexto.getBean("clienteDAOOrdenacion", ClienteDAOOrdenacion.class);
        ClienteVipDAOOrdenacion elClienteVip = contexto.getBean("clienteVipDAOOrdenacion", ClienteVipDAOOrdenacion.class);

        ClienteOrdenacion cliente1 = new ClienteOrdenacion();

        // =========================================================================================
        // PREPARACIÓN DE LOS DATOS (Llenando la mochila)
        // =========================================================================================
        // Seteamos los valores. Esta es la información que viajará por el sistema y que
        // nuestro Aspecto va a interceptar y "leer" en pleno vuelo.
        cliente1.setNombre("Juan Díaz");
        cliente1.setTipo("Normal");

        // 3. Llamamos al metodo
        // =========================================================================================
        // EL LANZAMIENTO
        // =========================================================================================
        // ATENCIÓN: Al invocar este método, Spring pausa la ejecución y llama a nuestros aspectos.
        // Los argumentos (cliente1 y "Normal") son empaquetados y enviados al proxy.
        // elClienteNormal.insertaCliente(argumento_0, argumento_1);
        elClienteNormal.insertaCliente(cliente1, cliente1.getTipo()); // este es el nombre del método que debe coincidir con la anotacion @before

        elClienteVip.insertaClienteVip(); // este es el nombre del método que debe coincidir con la anotacion @before


        // 4. Cerrar el contexto
        contexto.close();
    }
}