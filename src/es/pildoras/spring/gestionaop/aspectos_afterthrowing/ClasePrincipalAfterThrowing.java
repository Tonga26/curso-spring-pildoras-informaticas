package es.pildoras.spring.gestionaop.aspectos_afterthrowing;

import es.pildoras.spring.gestionaop.Configuracion;
// CORRECCIÓN: Ahora importamos el DAO correcto de la carpeta afterthrowing.
import es.pildoras.spring.gestionaop.aspectos_afterthrowing.dao_after.ClienteDAOAfterThrowing;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClasePrincipalAfterThrowing {

    public static void main(String[] args){

        /* ==========================================================================
           PASO 1: INICIALIZAR EL CONTENEDOR DE SPRING
           ========================================================================== */
        // Instanciamos el contexto de Spring.
        // Le pasamos 'Configuracion.class' para que sepa dónde buscar los componentes (@Component)
        // y que debe habilitar los proxies de AOP (@EnableAspectJAutoProxy).
        AnnotationConfigApplicationContext contexto = new AnnotationConfigApplicationContext(Configuracion.class);

        /* ==========================================================================
           PASO 2: OBTENER EL BEAN
           ========================================================================== */
        // CORRECCIÓN: Le pedimos a Spring que nos entregue la instancia de 'ClienteDAOAfterThrowing'.
        // Cambiamos tanto el id del bean como la clase a la que hacemos referencia.
        ClienteDAOAfterThrowing elClienteNormal = contexto.getBean("clienteDAOAfterThrowing", ClienteDAOAfterThrowing.class);

        // Variable booleana que usaremos para forzar el error en el DAO y ver cómo actúa @AfterThrowing.
        boolean miParam = true;

        /* ==========================================================================
           PASO 3: EJECUTAR EL MÉTODO (Y DESENCADENAR LOS ASPECTOS)
           ========================================================================== */
        // Llamamos al método encuentraClientes() pasándole nuestro parámetro booleano.
        // IMPORTANTE: Al llamar a este método con 'true':
        // 1. Spring ejecuta los aspectos @Before.
        // 2. Se ejecuta el código interno de encuentraClientes().
        // 3. ¡Boom! Se lanza una RuntimeException simulando una caída de la BBDD.
        // 4. (Aquí es donde más adelante entrará en acción el aspecto @AfterThrowing).
        try {
            elClienteNormal.encuentraClientes(miParam);
        } catch (Exception e) {
            // Un pequeño bloque try-catch provisional para que el programa no colapse
            // del todo mientras Juan te explica el @AfterThrowing en el video.
            System.out.println("Excepción capturada en el main: " + e.getMessage());
        }

        // Imprimimos un mensaje final para verificar que el hilo principal del programa continúa y finaliza correctamente.
        System.out.println("Aquí continuaría la ejecución del programa...");

        /* ==========================================================================
           PASO 4: CERRAR RECURSOS
           ========================================================================== */
        // Cerramos el contexto de Spring para liberar recursos de memoria.
        contexto.close();
    }
}