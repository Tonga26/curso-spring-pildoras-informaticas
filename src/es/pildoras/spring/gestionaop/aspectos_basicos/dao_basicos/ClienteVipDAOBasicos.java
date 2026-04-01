package es.pildoras.spring.gestionaop.aspectos_basicos.dao_basicos;

import org.springframework.stereotype.Component;

/**
 * =========================================================================================
 * SECCIÓN: CLASE DAO SECUNDARIA
 * Sirve para demostrar que nuestro Pointcut afecta a MÚLTIPLES clases dentro del mismo paquete.
 * =========================================================================================
 */
// @Component: Registra el bean en el contenedor con el nombre automático "clienteVipDAOBasicos".
@Component
public class ClienteVipDAOBasicos {

    // Al pertenecer al paquete interceptado y no ser un getter ni un setter,
    // la ejecución de este método también disparará el Aspecto de seguridad.
    public void insertaClienteVip(){
        System.out.println("Trabajo realizado ok. Cliente VIP insertado con éxito.");
    }
}