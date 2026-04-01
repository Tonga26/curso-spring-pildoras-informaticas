package es.pildoras.spring.gestionaop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("es.pildoras.spring.gestionaop.aspectos_basicos")
public class Configuracion {
}
