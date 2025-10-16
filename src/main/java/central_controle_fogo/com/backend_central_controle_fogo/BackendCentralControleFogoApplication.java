package central_controle_fogo.com.backend_central_controle_fogo;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;


import java.util.TimeZone;


@EnableAsync
@SpringBootApplication
public class BackendCentralControleFogoApplication {

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {

		SpringApplication.run(BackendCentralControleFogoApplication.class, args);
	}

}
