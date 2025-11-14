package co.edu.uco.HumanSolution.config;

import co.edu.uco.HumanSolution.business.facade.EvaluacionDesempenoFacade;
import co.edu.uco.HumanSolution.business.facade.impl.EvaluacionDesempenoFacadeImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FacadeConfig {

    @Bean
    public EvaluacionDesempenoFacade evaluacionDesempenoFacade() {
        return new EvaluacionDesempenoFacadeImpl();
    }
}
