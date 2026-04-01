package es.pildoras.spring.gestionaop.aspectos_ordenacion;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * =========================================================================================
 * SECCIÓN: ASPECTO DE LOGIN Y ALMACÉN DE POINTCUTS (Prioridad Baja)
 * Esta clase verifica el usuario, pero además sirve como "Librería" donde
 * guardamos nuestro @Pointcut principal para que otros aspectos lo usen.
 * =========================================================================================
 */
// @Aspect: Le indica a Spring que esta no es una clase normal, sino un Aspecto (contiene Pointcuts y Advices).
@Aspect
// @Component: Registra esta clase en el contenedor de Spring (Inyección de Dependencias) para que la reconozca.
@Component
// @Order(3): Tiene la prioridad más baja de nuestro ecosistema actual. Se ejecutará al final.
@Order(3)
public class LoginConAspectoOrdenacion {

    // =========================================================================================
    // POINTCUT CENTRALIZADO
    // =========================================================================================
    // CAMBIO CRUCIAL: Modificador de acceso 'public'.
    // Al ser público, permite que clases externas (como RequisitosCliente y RequisitosTabla)
    // puedan invocar este filtro de búsqueda.
    @Pointcut("execution(* es.pildoras.spring.gestionaop.aspectos_ordenacion.dao_ordenacion.*.*(..))")
    public void paraClientes(){};

    // =========================================================================================
    // ADVICE (Acción)
    // =========================================================================================
    // Como estamos en la misma clase donde "vive" el Pointcut, no necesitamos la ruta absoluta,
    // basta con llamar directamente al método paraClientes().
    @Before("paraClientes()")
    public void antesInsertarCliente(){
        System.out.println("El usuario está logueado.");
        System.out.println("El perfil para insertar clientes es correcto.");
    }
}