package es.pildoras.spring.gestionaop.aspectos_basicos.dao_basicos;

import es.pildoras.spring.gestionaop.aspectos_basicos.ClienteBasicos;
import org.springframework.stereotype.Component;

/**
 * =========================================================================================
 * SECCIÓN: CLASE DAO (Data Access Object)
 * Esta clase simula el acceso a la base de datos. Sus métodos son los "objetivos" (Targets)
 * que nuestro Aspecto de seguridad está vigilando.
 * =========================================================================================
 */
// @Component: Registra la clase en Spring. Al estar dentro del paquete 'dao_basicos',
// Spring sabe que debe envolverla en un Proxy para aplicarle los Pointcuts.
@Component
public class ClienteDAOBasicos {

    private String valoracionClienteNormal;
    private String codigoClienteNormal;

    // Método de lógica de negocio principal. Al llamarlo, el Aspecto SÍ lo interceptará
    // porque cumple con la condición del Pointcut combinado.
    public void insertaCliente(ClienteBasicos elCliente, String tipo){
        System.out.println("Trabajo realizado ok. Cliente NORMAL insertado con éxito.");
    }

    // --- SECCIÓN DE GETTERS Y SETTERS ---
    // Gracias a nuestra combinación de Pointcuts ( && ! ), estos métodos NO activarán
    // la lógica de seguridad del Aspecto, manteniendo la consola y el rendimiento limpios.

    public String getValoracionClienteNormal() {
        System.out.println("GETTER: Obteniendo valoración del cliente.");
        return valoracionClienteNormal;
    }

    public void setValoracionClienteNormal(String valoracionClienteNormal) {
        System.out.println("SETTER: Estableciendo valoración del cliente.");
        this.valoracionClienteNormal = valoracionClienteNormal;
    }

    public String getCodigoClienteNormal() {
        System.out.println("GETTER: Obteniendo código del cliente.");
        return codigoClienteNormal;
    }

    public void setCodigoClienteNormal(String codigoClienteNormal) {
        System.out.println("SETTER: Estableciendo código del cliente.");
        this.codigoClienteNormal = codigoClienteNormal;
    }
}