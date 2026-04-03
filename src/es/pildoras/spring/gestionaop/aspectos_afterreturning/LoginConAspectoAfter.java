package es.pildoras.spring.gestionaop.aspectos_afterreturning;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * =========================================================================================
 * SECCIÓN 1: DECLARACIÓN DEL ASPECTO
 * =========================================================================================
 * Esta clase verifica el usuario, contiene el @Pointcut principal y procesa datos devueltos.
 */
// @Aspect: Le indica a Spring que esta no es una clase normal, sino un Aspecto (contiene Pointcuts y Advices).
@Aspect
// @Component: Registra esta clase en el contenedor de Spring (Inyección de Dependencias) para que la reconozca.
@Component
public class LoginConAspectoAfter {

    /* =========================================================================================
       SECCIÓN 2: POINTCUT CENTRALIZADO
       ========================================================================================= */
    // Expresión: Se aplicará a CUALQUIER método (*), de CUALQUIER clase (*), dentro del paquete dao_after, con cualquier cantidad de parámetros (..).
    @Pointcut("execution(* es.pildoras.spring.gestionaop.aspectos_afterreturning.dao_after.*.*(..))")
    public void paraClientes(){};

    /* =========================================================================================
       SECCIÓN 3: ADVICE @Before (INTERCEPCIÓN ANTES DE LA EJECUCIÓN)
       ========================================================================================= */
    // @Before indica que esto se ejecuta ANTES del método objetivo. Usa nuestro pointcut 'paraClientes()'.
    @Before("paraClientes()")
    // JoinPoint crea un punto de intersección para obtener los parámetros del método
    public void antesInsertarCliente(JoinPoint miJoin){

        System.out.println("El usuario está logueado.");
        System.out.println("El perfil para insertar clientes es correcto.");

        /* --- LECTURA DE ARGUMENTOS --- */
        // getArgs() devuelve un array de tipo Object con los argumentos que recibe el método interceptado.
        Object[] argumentos = miJoin.getArgs();

        // Iteramos sobre todos los argumentos atrapados.
        for (Object temp : argumentos){

            // Comprobamos si el argumento actual es una instancia de nuestra clase ClienteAfter.
            if (temp instanceof ClienteAfter){

                // Si es así, hacemos un "Cast" para transformarlo de Object a ClienteAfter.
                ClienteAfter elCliente = (ClienteAfter) temp;

                // Auditoría: Imprimimos los datos interceptados.
                System.out.println("Nombre del cliente a insertar: " + elCliente.getNombre());
                System.out.println("Tipo de cliente a insertar: " + elCliente.getTipo());
            }
        }
    }

    /* =========================================================================================
       SECCIÓN 4: ADVICE @AfterReturning (INTERCEPCIÓN DESPUÉS DE RETORNAR)
       ========================================================================================= */
    // @AfterReturning: Se ejecuta SOLO si el método finaliza correctamente (sin excepciones) y retorna un valor.
    // pointcut = "...": Especificamos exactamente a qué método queremos escuchar (encuentraClientes).
    // returning = "listaDeClientes": Le decimos a Spring CÓMO se va a llamar la variable donde queremos que guarde lo que retornó el método.
    @AfterReturning(
            pointcut = "execution(* es.pildoras.spring.gestionaop.aspectos_afterreturning.dao_after.ClienteDAOAfter.encuentraClientes(..))",
            returning = "listaDeClientes"
    )
    // El parámetro List<ClienteAfter> listaDeClientes recibe la información gracias a que coincide con el nombre en 'returning'.
    public void tareaTrasEncontrarClientes(List<ClienteAfter> listaDeClientes){

        // Iteramos la lista de clientes que nos devolvió el método original (encuentraClientes).
        for (ClienteAfter cl : listaDeClientes){
            // Verificamos si el cliente tiene la etiqueta "VIP".
            if (cl.getTipo().equals("VIP")){
                // Si es VIP, lo procesamos de forma especial (en este caso, un mensaje en consola).
                System.out.println("Existen clientes VIP en el listado. Nombre: " + cl.getNombre());
            }
        }
    }
}