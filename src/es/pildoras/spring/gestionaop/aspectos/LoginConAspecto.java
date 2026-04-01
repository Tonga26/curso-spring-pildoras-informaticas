package es.pildoras.spring.gestionaop.aspectos;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginConAspecto {

    //@Pointcut("execution(public * insertaCliente*(..))")
    @Pointcut("execution(* es.pildoras.spring.gestionaop.dao.*.*(..))")
    private void paraClientes(){};

    @Before("paraClientes()")
    public void antesInsertarCliente(){
        System.out.println("El usuario está logueado.");
        System.out.println("El perfil para insertar clientes es correcto.");
    }

    @Before("paraClientes()")
    public void requisitosCliente(){
        System.out.println("El cliente cumple los requisitos para ser insertado en la BBDD.");
    }

    @Before("paraClientes()")
    public void requisitosTabla(){
        System.out.println("Hay menos de 3000 registros en la tabla, puedes insertar el nuevo cliente.");
    }
}
