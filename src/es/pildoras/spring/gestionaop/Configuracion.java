package es.pildoras.spring.gestionaop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * =========================================================================================
 * SECCIÓN 1: CONFIGURACIÓN MAESTRA DE SPRING (El Enrutador)
 * =========================================================================================
 * Esta clase actúa como el "radar" de Spring. Aquí le decimos qué funcionalidades
 * habilitar y en qué carpetas (paquetes) debe buscar nuestras anotaciones.
 */
// @Configuration: Indica que esta clase contiene definiciones de Beans y directivas de configuración para el contexto de Spring.
@Configuration

// @EnableAspectJAutoProxy: Habilita el soporte para manejar componentes marcados con @Aspect (Programación Orientada a Aspectos).
@EnableAspectJAutoProxy

/* =========================================================================================
   SECCIÓN 2: 🔄 INTERRUPTOR DE ISLAS DE PRUEBA
   =========================================================================================
   IMPORTANTE: Para evitar conflictos de nombres (Beans repetidos con el mismo ID), Spring
   solo debe escanear un paquete a la vez. Comenta y descomenta las rutas según la lección.
*/

// ---> 1. ACTIVA PARA PROBAR: Conceptos Básicos (Lecciones iniciales)
// @ComponentScan("es.pildoras.spring.gestionaop.aspectos_basicos")

// ---> 2. ACTIVA PARA PROBAR: Ordenación de Aspectos (@Order)
// @ComponentScan("es.pildoras.spring.gestionaop.aspectos_ordenacion")

// ---> 3. ACTIVA PARA PROBAR: Aspectos After Returning
// @ComponentScan("es.pildoras.spring.gestionaop.aspectos_afterreturning")

// ---> 4. ACTIVA PARA PROBAR: Aspectos After Throwing (LECCIÓN ACTUAL)
// Al estar sin comentar, Spring solo inyectará los Beans que vivan dentro de este paquete.
@ComponentScan("es.pildoras.spring.gestionaop.aspectos_afterthrowing")

public class Configuracion {
    // El cuerpo de la clase se mantiene vacío.
    // Spring solo necesita leer las anotaciones de la cabecera para configurar el entorno.
}