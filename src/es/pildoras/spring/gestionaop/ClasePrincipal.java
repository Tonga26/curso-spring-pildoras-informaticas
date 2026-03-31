package es.pildoras.spring.gestionaop;

import es.pildoras.spring.gestionaop.dao.ClienteDAO;
import es.pildoras.spring.gestionaop.dao.ClienteVipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClasePrincipal {
    
    public static void main(String[] args){

        // 1. Leemos la configracion de spring
        AnnotationConfigApplicationContext contexto = new AnnotationConfigApplicationContext(Configuracion.class);

        // 2. Obtenemos el bean del contenedor de spring
        ClienteDAO elClienteNormal = contexto.getBean("clienteDAO", ClienteDAO.class);

        ClienteVipDAO elClienteVip = contexto.getBean("clienteVipDAO", ClienteVipDAO.class);

        Cliente cliente1 = new Cliente();

        // 3. Llamamos al metodo
        elClienteNormal.insertaCliente(cliente1, "Normal"); // este es el nombre del método que debe coincidir con la anotacion @before

        elClienteVip.insertaClienteVip(); // este es el nombre del método que debe coincidir con la anotacion @before

        // 4. Cerrar el contexto
        contexto.close();
    }
}
