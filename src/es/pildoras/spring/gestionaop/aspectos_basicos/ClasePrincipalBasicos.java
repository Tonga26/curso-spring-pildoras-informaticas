package es.pildoras.spring.gestionaop.aspectos_basicos;

import es.pildoras.spring.gestionaop.Configuracion;
import es.pildoras.spring.gestionaop.aspectos_basicos.dao_basicos.ClienteDAOBasicos;
import es.pildoras.spring.gestionaop.aspectos_basicos.dao_basicos.ClienteVipDAOBasicos;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * =========================================================================================
 * SECCIÓN: CLASE PRINCIPAL (ENTRY POINT)
 * Esta clase es el punto de entrada de la aplicación. Aquí inicializamos el contenedor
 * de Spring y probamos que nuestros aspectos estén funcionando correctamente.
 * =========================================================================================
 */
public class ClasePrincipalBasicos {

    public static void main(String[] args){

        // 1. Leemos la configuracion de spring
        // AnnotationConfigApplicationContext crea el "contenedor" de Spring en memoria.
        // Lee la clase Configuracion.class para saber qué paquetes escanear y qué funcionalidades activar (como AOP).
        AnnotationConfigApplicationContext contexto = new AnnotationConfigApplicationContext(Configuracion.class);

        // 2. Obtenemos el bean del contenedor de spring
        // getBean() nos devuelve la instancia de la clase que Spring ha creado.
        // IMPORTANTE: Como estas clases cumplen con los Pointcuts definidos, Spring NO nos
        // devuelve el objeto real, sino un "Objeto Proxy" (el impostor) que incluye la seguridad.
        ClienteDAOBasicos elClienteNormal = contexto.getBean("clienteDAOBasicos", ClienteDAOBasicos.class);
        ClienteVipDAOBasicos elClienteVip = contexto.getBean("clienteVipDAOBasicos", ClienteVipDAOBasicos.class);

        // Instanciamos un objeto común (POJO). Este no está manejado por Spring (usamos 'new').
        ClienteBasicos cliente1 = new ClienteBasicos();

        // 3. Llamamos al método
        // Al llamar a estos métodos, la petición pasa primero por el Objeto Proxy.
        elClienteNormal.insertaCliente(cliente1, "Normal"); // este es el nombre del método que debe coincidir con la anotacion @before
        elClienteVip.insertaClienteVip(); // este es el nombre del método que debe coincidir con la anotacion @before

        // Pruebas combinaciones de pointcuts (getters y setters)
        // Al ejecutar estos métodos, verificamos que nuestro filtro booleano (&& !) funcione
        // y no ejecute el aspecto de seguridad en operaciones simples de lectura/escritura.
        elClienteNormal.setCodigoClienteNormal("2349625789");
        elClienteNormal.setValoracionClienteNormal("Positiva");

        String codigoCl = elClienteNormal.getCodigoClienteNormal();
        String valoracionCl = elClienteNormal.getValoracionClienteNormal();

        // 4. Cerrar el contexto
        // Liberamos la memoria y cerramos el contenedor de Spring.
        contexto.close();
    }
}