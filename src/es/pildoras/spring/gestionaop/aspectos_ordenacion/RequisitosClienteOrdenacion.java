package es.pildoras.spring.gestionaop.aspectos_ordenacion;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * =========================================================================================
 * SECCIÓN: ASPECTO DE VALIDACIÓN DE CLIENTE (Prioridad Alta)
 * Esta clase se encarga exclusivamente de verificar que el cliente sea apto.
 * =========================================================================================
 */
@Aspect
@Component
// @Order(1): El número más bajo indica la MÁXIMA prioridad.
// Cuando el método objetivo sea llamado, este aspecto será el PRIMERO en ejecutarse.
@Order(1)
public class RequisitosClienteOrdenacion {

    // @Before: Se ejecuta antes del método objetivo.
    // IMPORTANTE: Como el @Pointcut no está definido en esta clase, debemos pasarle
    // la "Ruta Absoluta" (Paquete + Clase + Método()) para que sepa a quién vigilar.
    @Before("es.pildoras.spring.gestionaop.aspectos_ordenacion.LoginConAspectoOrdenacion.paraClientes()")
    public void requisitosCliente(){
        System.out.println("El cliente cumple los requisitos para ser insertado en la BBDD.");
    }
}