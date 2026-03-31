package es.pildoras.spring.gestionaop.aspectos;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginConAspecto {

    @Before("execution(public * insertaCliente*(es.pildoras.spring.gestionaop.Cliente))")
    public void antesInsertarCliente(){
        System.out.println("El usuario está logueado.");
        System.out.println("El perfil para insertar clientes es correcto.");
    }
}
