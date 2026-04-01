package es.pildoras.spring.gestionaop.aspectos;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * =========================================================================================
 * SECCIÓN 1: DEFINICIÓN DE LA CLASE ASPECTO
 * Esta clase actúa como nuestro "Guardia de Seguridad" centralizado.
 * =========================================================================================
 */
// @Aspect: Le indica a Spring que esta no es una clase normal, sino un Aspecto (contiene Pointcuts y Advices).
@Aspect
// @Component: Registra esta clase en el contenedor de Spring (Inyección de Dependencias) para que la reconozca.
@Component
public class LoginConAspecto {

    /**
     * =========================================================================================
     * SECCIÓN 2: DECLARACIÓN DE POINTCUTS BASE (Nuestras piezas de Lego)
     * Aquí definimos las reglas de búsqueda de métodos, pero aún no ejecutamos ninguna acción.
     * =========================================================================================
     */

    // @Pointcut("execution(public * insertaCliente*(..))")
    // Aplicamos el pointcut para absolutamente todos los metodos del paquete dao (inluyendo getters y setters)
    /* EXPLICACIÓN:
     * execution(* ...): El primer asterisco indica que no importa el tipo de dato que devuelva el método.
     * .*.*(..): Significa "Cualquier clase" . "Cualquier método" ( "Con cualquier parámetro" ).
     * Este es nuestro Pointcut "Universo", atrapa todo en esa carpeta.
     */
    @Pointcut("execution(* es.pildoras.spring.gestionaop.dao.*.*(..))")
    private void paraClientes(){};

    // Aplicamos los pointcut solo a los GETTERS del paquete dao
    /* EXPLICACIÓN:
     * .get*(..): El asterisco después de 'get' obliga a que el nombre del método comience estrictamente
     * con la palabra "get" (ej: getNombre, getCodigo).
     */
    @Pointcut("execution(* es.pildoras.spring.gestionaop.dao.*.get*(..))")
    private void paraGetters(){};

    // Aplicamos los pointcut solo a los SETTERS del paquete dao
    /* EXPLICACIÓN:
     * .set*(..): Igual que el anterior, pero restringe la búsqueda a métodos que empiecen con "set".
     */
    @Pointcut("execution(* es.pildoras.spring.gestionaop.dao.*.set*(..))")
    private void paraSetters(){};


    /**
     * =========================================================================================
     * SECCIÓN 3: COMBINACIÓN LÓGICA DE POINTCUTS (El Filtro Maestro)
     * Aquí usamos operadores booleanos de Java para mezclar los Pointcuts anteriores.
     * =========================================================================================
     */

    // Combinación de pointcuts (excluyendo getters y setters)
    /* EXPLICACIÓN:
     * paraClientes() -> 1. Toma el "Universo" completo (todos los métodos del DAO).
     * &&             -> 2. Operador AND (Y además...). Fuerza a que se cumpla la siguiente condición.
     * !              -> 3. Operador NOT (Negación). Significa "Que NO sea lo que está entre paréntesis".
     * (paraGetters() || paraSetters()) -> 4. Operador OR (O). Significa "Un Getter O un Setter".
     * TRADUCCIÓN FINAL: "Atrapa todo lo del DAO, Y que NO sea (ni un Getter O un Setter)".
     */
    @Pointcut("paraClientes() && !(paraGetters() || paraSetters())")
    private void paqueteExceptoGetterSetter(){};


    /**
     * =========================================================================================
     * SECCIÓN 4: ADVICES (Las acciones a ejecutar)
     * Aquí vinculamos el código real (los System.out) con los Pointcuts que definimos arriba.
     * =========================================================================================
     */

    // @Before("paraClientes()") Aplicamos aspect para todos los métodos
    // @Before("paraGetters()") Aplicamos aspect solo para los getters
    // @Before("paraSetters()") Aplicamos aspect solo para los setters

    // @Before: Indica que este código se ejecutará ANTES del método interceptado.
    // Usamos el Pointcut combinado para mantener la consola limpia de lecturas/escrituras simples.
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