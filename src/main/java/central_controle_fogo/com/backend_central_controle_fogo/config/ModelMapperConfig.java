package central_controle_fogo.com.backend_central_controle_fogo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModel(){
        return new ModelMapper();
    }
}
