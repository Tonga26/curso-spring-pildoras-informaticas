package es.pildoras.spring.gestionaop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * =========================================================================================
 * SECCIÓN: CONFIGURACIÓN MAESTRA DE SPRING (El Enrutador)
 * Esta clase actúa como el "radar" de Spring. Aquí le decimos qué funcionalidades
 * habilitar y en qué carpetas (paquetes) debe buscar nuestras anotaciones.
 * =========================================================================================
 */
@Configuration
// Habilita el uso de la Programación Orientada a Aspectos (AOP) en el proyecto.
@EnableAspectJAutoProxy

// =========================================================================================
// 🔄 INTERRUPTOR DE ISLAS DE PRUEBA
// IMPORTANTE: Para evitar conflictos de nombres (Beans repetidos), Spring solo debe
// escanear un paquete a la vez. Comenta y descomenta las rutas según lo que quieras probar.
// =========================================================================================

// ---> ACTIVA PARA PROBAR: Conceptos Básicos
// @ComponentScan("es.pildoras.spring.gestionaop.aspectos_basicos")

// ---> ACTIVA PARA PROBAR: Ordenación de Aspectos
@ComponentScan("es.pildoras.spring.gestionaop.aspectos_afterreturning")
public class Configuracion {
    // Clase vacía, Spring solo lee sus anotaciones.
}