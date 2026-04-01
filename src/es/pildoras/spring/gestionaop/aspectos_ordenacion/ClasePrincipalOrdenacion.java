package es.pildoras.spring.gestionaop.aspectos_ordenacion;

import es.pildoras.spring.gestionaop.Configuracion;
import es.pildoras.spring.gestionaop.aspectos_ordenacion.daoordenacion.ClienteDAOOrdenacion;
import es.pildoras.spring.gestionaop.aspectos_ordenacion.daoordenacion.ClienteVipDAOOrdenacion;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClasePrincipalOrdenacion {
    
    public static void main(String[] args){

        // 1. Leemos la configracion de spring
        AnnotationConfigApplicationContext contexto = new AnnotationConfigApplicationContext(Configuracion.class);

        // 2. Obtenemos el bean del contenedor de spring
        ClienteDAOOrdenacion elClienteNormal = contexto.getBean("clienteDAO", ClienteDAOOrdenacion.class);

        ClienteVipDAOOrdenacion elClienteVip = contexto.getBean("clienteVipDAO", ClienteVipDAOOrdenacion.class);

        ClienteOrdenacion cliente1 = new ClienteOrdenacion();

        // 3. Llamamos al metodo
        elClienteNormal.insertaCliente(cliente1, "Normal"); // este es el nombre del método que debe coincidir con la anotacion @before

        elClienteVip.insertaClienteVip(); // este es el nombre del método que debe coincidir con la anotacion @before


        // 4. Cerrar el contexto
        contexto.close();
    }
}
