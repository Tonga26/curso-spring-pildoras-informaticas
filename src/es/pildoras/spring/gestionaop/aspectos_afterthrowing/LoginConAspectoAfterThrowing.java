package es.pildoras.spring.gestionaop.aspectos_afterthrowing;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * =========================================================================================
 * SECCIÓN 1: DECLARACIÓN DEL ASPECTO
 * =========================================================================================
 * Esta clase verifica el usuario, contiene el @Pointcut principal y procesa datos devueltos.
 * Ahora está configurada para apuntar exclusivamente al paquete afterthrowing.
 */
// @Aspect: Le indica a Spring que esta no es una clase normal, sino un Aspecto (contiene Pointcuts y Advices).
@Aspect
// @Component: Registra esta clase en el contenedor de Spring (Inyección de Dependencias) para que la reconozca.
@Component
public class LoginConAspectoAfterThrowing {

    /* =========================================================================================
       SECCIÓN 2: POINTCUT CENTRALIZADO
       ========================================================================================= */
    /**
     * Pointcut centralizado que define el patrón de búsqueda para los métodos a interceptar.
     * Al estar vacío y solo tener la anotación, actúa como una "variable" que almacena
     * la ruta de ejecución para ser reutilizada en múltiples Advices (como @Before).
     */
    // Se aplicará a CUALQUIER método (*), de CUALQUIER clase (*), dentro del paquete dao_after de afterthrowing, con cualquier cantidad de parámetros (..).
    @Pointcut("execution(* es.pildoras.spring.gestionaop.aspectos_afterthrowing.dao_after.*.*(..))")
    public void paraClientes(){};

    /* =========================================================================================
       SECCIÓN 3: ADVICE @Before (INTERCEPCIÓN ANTES DE LA EJECUCIÓN)
       ========================================================================================= */
    /**
     * Advice del tipo @Before que se ejecuta inmediatamente ANTES que el método objetivo.
     * Realiza comprobaciones de seguridad (simulación de login y verificación de perfil)
     * e inspecciona los argumentos que el método interceptado está a punto de recibir.
     *
     * @param miJoin Objeto JoinPoint inyectado por Spring que contiene los metadatos
     * y el contexto del método interceptado (incluyendo sus argumentos).
     */
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

            // Comprobamos si el argumento actual es una instancia de nuestra nueva clase ClienteAfterThrowing.
            if (temp instanceof ClienteAfterThrowing){

                // Si es así, hacemos un "Cast" para transformarlo de Object a ClienteAfterThrowing.
                ClienteAfterThrowing elCliente = (ClienteAfterThrowing) temp;

                // Auditoría: Imprimimos los datos interceptados.
                System.out.println("Nombre del cliente a insertar: " + elCliente.getNombre());
                System.out.println("Tipo de cliente a insertar: " + elCliente.getTipo());
            }
        }
    }

    /* =========================================================================================
       SECCIÓN 4: ADVICE @AfterReturning (INTERCEPCIÓN DESPUÉS DE RETORNAR)
       ========================================================================================= */
    /**
     * Advice del tipo @AfterReturning que se ejecuta EXCLUSIVAMENTE después de que el método
     * interceptado (encuentraClientes) finaliza correctamente y retorna un valor.
     * <p>
     * Este método actúa como un "filtro de salida". Intercepta la lista original devuelta
     * por la Base de Datos, envía esa lista a procesar/modificar al vuelo, y luego recorre
     * los datos ya modificados para buscar e imprimir en consola aquellos clientes que sean VIP.
     *
     * @param listaDeClientes La colección de clientes interceptada directamente del 'return'
     * del método encuentraClientes(). Es crucial que el nombre de este
     * parámetro coincida exactamente con el atributo 'returning' de la anotación.
     */
    // @AfterReturning: Se ejecuta SOLO si el método finaliza correctamente (sin excepciones).
    // returning = "listaDeClientes": Le decimos a Spring CÓMO se va a llamar la variable donde queremos que guarde lo que retornó el método.
    @AfterReturning(
            pointcut = "execution(* es.pildoras.spring.gestionaop.aspectos_afterthrowing.dao_after.ClienteDAOAfterThrowing.encuentraClientes(..))",
            returning = "listaDeClientes"
    )
    public void tareaTrasEncontrarClientes(List<ClienteAfterThrowing> listaDeClientes){

        // Ejecutamos el método que manipula los datos UNA SOLA VEZ, antes de recorrer la lista para la impresión.
        procesadoDatosAfterReturning(listaDeClientes);

        // Iteramos la lista de clientes (ahora ya procesada y modificada).
        for (ClienteAfterThrowing cl : listaDeClientes){
            // Verificamos si el cliente tiene la etiqueta "VIP".
            if (cl.getTipo().equals("VIP")){
                // Si es VIP, imprimimos en consola.
                System.out.println("Existen clientes VIP en el listado. Nombre: " + cl.getNombre());
            }
        }
    }

    /* =========================================================================================
       SECCIÓN 5: ADVICE @AfterThrowing (NUEVO: INTERCEPCIÓN DE EXCEPCIONES)
       ========================================================================================= */
    /**
     * Advice del tipo @AfterThrowing que se ejecuta ÚNICAMENTE si el método interceptado
     * lanza una excepción (falla) durante su ejecución.
     * <p>
     * Es ideal para tareas de auditoría de errores, envío de notificaciones de alerta al
     * equipo de soporte, o registro de logs, sin afectar el flujo del error original.
     *
     * @param LaExcepcion Objeto de tipo Throwable que captura la excepción real lanzada por
     * el método interceptado. CORRECCIÓN: El nombre de este parámetro debe coincidir
     * exactamente con el string definido en el atributo 'throwing' de la anotación.
     */
    @AfterThrowing(
            pointcut = "execution(* es.pildoras.spring.gestionaop.aspectos_afterthrowing.dao_after.ClienteDAOAfterThrowing.encuentraClientes(..))",
            throwing = "LaExcepcion" // Este string es el nexo de unión con el parámetro de abajo.
    )
    public void procesandoDatosAfterExceptionEncuentraClientes(Throwable LaExcepcion){

        // Aquí se programarían todas las tareas automáticas que queremos que se realicen tras lanzar la excepción.
        // Por ejemplo: Enviar un email al administrador, escribir en un archivo de log, etc.
        System.out.println("Aquí se estarían ejecutando de forma automática las tareas tras la excepción.");
    }

    /* =========================================================================================
       SECCIÓN 6: PROCESAMIENTO DE DATOS (MÉTODOS AUXILIARES)
       ========================================================================================= */
    /**
     * Este método privado se encarga de "manipular" o formatear los datos de la lista
     * interceptada ANTES de que lleguen de vuelta al flujo normal del programa principal.
     *
     * @param listaDeClientes La lista original devuelta por el método encuentraClientes().
     */
    private void procesadoDatosAfterReturning(List<ClienteAfterThrowing> listaDeClientes) {

        // Iteramos sobre cada cliente dentro de la lista que recibimos.
        for (ClienteAfterThrowing cl : listaDeClientes){

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