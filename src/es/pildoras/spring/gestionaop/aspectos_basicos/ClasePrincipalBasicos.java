package es.pildoras.spring.gestionaop.aspectos_basicos;

import es.pildoras.spring.gestionaop.Configuracion;
import es.pildoras.spring.gestionaop.dao.ClienteDAO;
import es.pildoras.spring.gestionaop.dao.ClienteVipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClasePrincipalBasicos {
    
    public static void main(String[] args){

        // 1. Leemos la configracion de spring
        AnnotationConfigApplicationContext contexto = new AnnotationConfigApplicationContext(Configuracion.class);

        // 2. Obtenemos el bean del contenedor de spring
        ClienteDAO elClienteNormal = contexto.getBean("clienteDAO", ClienteDAO.class);

        ClienteVipDAO elClienteVip = contexto.getBean("clienteVipDAO", ClienteVipDAO.class);

        ClienteBasicos cliente1 = new ClienteBasicos();

        // 3. Llamamos al metodo
        elClienteNormal.insertaCliente(cliente1, "Normal"); // este es el nombre del método que debe coincidir con la anotacion @before

        elClienteVip.insertaClienteVip(); // este es el nombre del método que debe coincidir con la anotacion @before


        // Pruebas combinaciones de pointcuts (getters y setters)
        elClienteNormal.setCodigoClienteNormal("2349625789");
        elClienteNormal.setValoracionClienteNormal("Positiva");

        String codigoCl = elClienteNormal.getCodigoClienteNormal();
        String valoracionCl = elClienteNormal.getValoracionClienteNormal();

        // 4. Cerrar el contexto
        contexto.close();
    }
}
