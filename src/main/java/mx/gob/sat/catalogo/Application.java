package mx.gob.sat.catalogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Representa la clase principal de la aplicación.
 */
@SpringBootApplication
@ComponentScan(basePackages = "mx.gob.sat")
@EntityScan(basePackages = "mx.gob.sat")
@EnableFeignClients
public class Application {
    
    /**
     * Metodo principal de la aplicación.
     * 
     * @param args - Representa los argumentos necesarios para el inicio de la aplicación.
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
