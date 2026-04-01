package es.pildoras.spring.gestionaop.aspectos_ordenacion;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// @Aspect: Le indica a Spring que esta no es una clase normal, sino un Aspecto (contiene Pointcuts y Advices).
@Aspect
// @Component: Registra esta clase en el contenedor de Spring (Inyección de Dependencias) para que la reconozca.
@Component
public class LoginConAspectoOrdenacion {

    @Pointcut("execution(* es.pildoras.spring.gestionaop.dao.*.*(..))")
    private void paraClientes(){};

    @Before("paqueteExceptoGetterSetter()")
    public void antesInsertarCliente(){
        System.out.println("El usuario está logueado.");
        System.out.println("El perfil para insertar clientes es correcto.");
    }

    // Nota: Estos métodos están comentados en su anotación para que no se ejecuten por ahora,
    // pero si les pones @Before("paqueteExceptoGetterSetter()"), también respetarían el filtro.

    //@Before("paraClientes()")
    public void requisitosCliente(){
        System.out.println("El cliente cumple los requisitos para ser insertado en la BBDD.");
    }

    //@Before("paraClientes()")
    public void requisitosTabla(){
        System.out.println("Hay menos de 3000 registros en la tabla, puedes insertar el nuevo cliente.");
    }
}