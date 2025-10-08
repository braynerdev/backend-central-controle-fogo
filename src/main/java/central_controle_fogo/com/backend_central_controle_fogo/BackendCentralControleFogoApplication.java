package central_controle_fogo.com.backend_central_controle_fogo;

import central_controle_fogo.com.backend_central_controle_fogo.Enum.RolesEnum;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

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
