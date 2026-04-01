package es.pildoras.spring.gestionaop.aspectos_ordenacion;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * =========================================================================================
 * SECCIÓN: ASPECTO DE VALIDACIÓN DE BASE DE DATOS (Prioridad Media)
 * Esta clase se encarga exclusivamente de verificar la capacidad de la tabla.
 * =========================================================================================
 */
@Aspect
@Component
// @Order(2): Tiene prioridad media. Spring lo pondrá a la cola esperando
// a que termine el Aspecto con @Order(1).
@Order(2)
public class RequisitosTablaOrdenacion {

    // Reutilizamos el mismo Pointcut centralizado apuntando a su ruta absoluta.
    @Before("es.pildoras.spring.gestionaop.aspectos_ordenacion.LoginConAspectoOrdenacion.paraClientes()")
    public void requisitosTabla(){
        System.out.println("Hay menos de 3000 registros en la tabla, puedes insertar el nuevo cliente.");
    }
}