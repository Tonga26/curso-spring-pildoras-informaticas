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
    @AfterReturning(
            pointcut = "execution(* es.pildoras.spring.gestionaop.aspectos_afterreturning.dao_after.ClienteDAOAfter.encuentraClientes(..))",
            returning = "listaDeClientes"
    )
    public void tareaTrasEncontrarClientes(List<ClienteAfter> listaDeClientes){

        // Ejecutamos el método que manipula los datos UNA SOLA VEZ, antes de recorrer la lista para la impresión.
        procesadoDatosAfterReturning(listaDeClientes);

        // Iteramos la lista de clientes (ahora ya procesada y modificada).
        for (ClienteAfter cl : listaDeClientes){
            // Verificamos si el cliente tiene la etiqueta "VIP".
            if (cl.getTipo().equals("VIP")){
                // Si es VIP, imprimimos en consola.
                System.out.println("Existen clientes VIP en el listado. Nombre: " + cl.getNombre());
            }
        }
    }

    /* =========================================================================================
       SECCIÓN 5: PROCESAMIENTO DE DATOS
       ========================================================================================= */
    /**
     * Este método privado se encarga de "manipular" o formatear los datos de la lista
     * interceptada ANTES de que lleguen de vuelta al flujo normal del programa principal.
     *
     * @param listaDeClientes La lista original devuelta por el método encuentraClientes().
     */
    private void procesadoDatosAfterReturning(List<ClienteAfter> listaDeClientes) {

        // Iteramos sobre cada cliente dentro de la lista que recibimos.
        for (ClienteAfter cl : listaDeClientes){

            // 1. Tomamos el nombre actual del cliente, lo convertimos a MAYÚSCULAS usando toUpperCase(),
            // y le concatenamos el texto "V.I.P. " adelante. Guardamos el resultado en una variable.
            String datosProcesados = "V.I.P. " + cl.getNombre().toUpperCase();

            // 2. Usamos el setter para sobrescribir el nombre original del objeto Cliente con nuestro texto modificado.
            // IMPORTANTE: Como los objetos en Java se pasan por referencia, al modificar el objeto 'cl' aquí,
            // estamos modificando el objeto real en memoria. Por ende, la lista que reciba tu método main
            // ya llegará con todos estos nombres transformados.
            cl.setNombre(datosProcesados);
        }
    }
}