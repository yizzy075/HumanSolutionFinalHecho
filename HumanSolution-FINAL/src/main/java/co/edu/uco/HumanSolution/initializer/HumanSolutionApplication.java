package co.edu.uco.HumanSolution.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "co.edu.uco.HumanSolution")  // ✅ ASEGÚRATE DE TENER ESTA LÍNEA
public class HumanSolutionApplication {

    public static void main(String[] args) {
        System.out.println("=== INICIANDO HumanSolutionApplication ===");
        SpringApplication.run(HumanSolutionApplication.class, args);

        System.out.println("\n============================================================");
        System.out.println("🚀 Servidor REST API HumanSolution iniciado");
        System.out.println("📍 URL: http://localhost:8080");
        System.out.println("============================================================\n");
    }
}