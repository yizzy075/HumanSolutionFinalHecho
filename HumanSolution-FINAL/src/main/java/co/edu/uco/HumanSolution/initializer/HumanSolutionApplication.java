package co.edu.uco.HumanSolution.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Clase principal de la aplicación Spring Boot HumanSolution.
 * Gestiona recursos humanos con evaluaciones de desempeño, contratos y usuarios.
 */
@SpringBootApplication
@ComponentScan(basePackages = "co.edu.uco.HumanSolution")
public class HumanSolutionApplication {

    public static void main(String[] args) {
        SpringApplication.run(HumanSolutionApplication.class, args);
    }
}
