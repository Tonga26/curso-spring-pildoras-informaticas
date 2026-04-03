package es.pildoras.spring.gestionaop.aspectos_afterreturning;

import es.pildoras.spring.gestionaop.Configuracion;
import es.pildoras.spring.gestionaop.aspectos_afterreturning.dao_after.ClienteDAOAfter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClasePrincipalAfter {

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
        // Le pedimos a Spring que nos entregue la instancia (Bean) de 'ClienteDAOAfter'.
        // Nota: Como usamos @Component en ClienteDAOAfter, el id por defecto es el nombre de la clase con la primera letra en minúscula.
        ClienteDAOAfter elClienteNormal = contexto.getBean("clienteDAOAfter", ClienteDAOAfter.class);

        /* ==========================================================================
           PASO 3: EJECUTAR EL MÉTODO (Y DESENCADENAR LOS ASPECTOS)
           ========================================================================== */
        // Llamamos al método encuentraClientes().
        // IMPORTANTE: Al llamar a este método:
        // 1. Spring detecta el llamado y ejecuta los aspectos @Before (login, perfil, BBDD).
        // 2. Se ejecuta el código interno de encuentraClientes().
        // 3. Spring detecta que el método terminó y retornó una lista, ejecutando el aspecto @AfterReturning.
        elClienteNormal.encuentraClientes();

        // Imprimimos un mensaje final para verificar que el hilo principal del programa continúa y finaliza correctamente.
        System.out.println("Aquí continuaría la ejecución del programa...");

        /* ==========================================================================
           PASO 4: CERRAR RECURSOS
           ========================================================================== */
        // Cerramos el contexto de Spring para liberar recursos de memoria.
        contexto.close();
    }
}