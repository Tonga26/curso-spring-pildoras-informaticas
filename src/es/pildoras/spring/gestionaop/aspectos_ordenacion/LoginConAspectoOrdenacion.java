package es.pildoras.spring.gestionaop.aspectos_ordenacion;

import org.aspectj.lang.JoinPoint;
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
// @Aspect
// @Component: Registra esta clase en el contenedor de Spring (Inyección de Dependencias) para que la reconozca.
// @Component
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
    // ADVICE (Acción) CON INTERCEPCIÓN DE DATOS
    // =========================================================================================
    // Como estamos en la misma clase donde "vive" el Pointcut, no necesitamos la ruta absoluta,
    // basta con llamar directamente al método paraClientes().

    // EXPLICACIÓN DE JOINPOINT:
    // Al añadir 'JoinPoint miJoin' como parámetro, le estamos pidiendo a Spring que nos entregue
    // el "contexto" del método que acaba de ser interceptado (sus metadatos y los parámetros que trae).
    @Before("paraClientes()")
    public void antesInsertarCliente(JoinPoint miJoin){ // JoinPoint crea un punto de intersección para obtener los parámetros del método

        System.out.println("El usuario está logueado.");
        System.out.println("El perfil para insertar clientes es correcto.");

        // =========================================================================================
        // LECTURA DE ARGUMENTOS (Abriendo la mochila)
        // =========================================================================================

        // getArgs() devuelve un array de tipo Object con los argumentos que va a recibir el método que se va a ejecutar después del aspecto.
        // En nuestro caso, el método insertaCliente recibe dos parámetros: un objeto ClienteOrdenacion y un String.
        // Por lo tanto, el array contendrá: [ObjetoCliente, "Normal"]
        Object[] argumentos = miJoin.getArgs();

        // Iteramos sobre todos los argumentos que atrapamos
        for (Object temp : argumentos){

            // Como el array es de tipo genérico 'Object', no sabemos qué hay adentro exactamente.
            // Usamos 'instanceof' para preguntar: "¿Es este argumento temporal un objeto de nuestra clase ClienteOrdenacion?"
            if (temp instanceof ClienteOrdenacion){

                // Si la respuesta es SÍ, hacemos un "Cast" (conversión explícita).
                // Esto transforma el Object genérico de vuelta a un ClienteOrdenacion,
                // permitiéndonos acceder a sus métodos específicos como getNombre() y getTipo().
                ClienteOrdenacion elCliente = (ClienteOrdenacion) temp;

                // ¡Auditoría exitosa! Imprimimos los datos interceptados ANTES de que lleguen a la Base de Datos.
                System.out.println("AUDITORÍA - Nombre del cliente a insertar: " + elCliente.getNombre());
                System.out.println("AUDITORÍA - Tipo de cliente a insertar: " + elCliente.getTipo());
            }
        }
    }
}