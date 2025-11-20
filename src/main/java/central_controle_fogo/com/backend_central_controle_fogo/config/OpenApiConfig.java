package central_controle_fogo.com.backend_central_controle_fogo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Central Controle Fogo - API")
                        .version("1.0.0")
                        .description("""
                                API para gerenciamento de ocorrências do corpo de bombeiros.
                                """)
                        .contact(new Contact()
                                .name("Central Controle Fogo")
                                .email("dev.brayner@outlook.com")
                                .url("https://front-centro-controle-fogo.onrender.com"))
                        .license(new License()
                                .name("Licença não está pronta")
                                .url("")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8088")
                                .description("Servidor de Desenvolvimento backend"),
                        new Server()
                                .url("https://backend-central-controle-fogo.onrender.com")
                                .description("Servidor de Produção backend"),
                        new Server()
                                .url("http://localhost:5173")
                                .description("Servidor de Desenvolvimento frontend"),
                        new Server()
                                .url("https://front-centro-controle-fogo.onrender.com")
                                .description("Servidor de Produção frontend")
                ))
                .tags(List.of(
                        new Tag()
                                .name("Autenticação")
                                .description("Endpoints para autenticação, login, logout e gestão de usuários"),
                        new Tag()
                                .name("Permissões")
                                .description("Gerenciamento de permissões e roles do sistema"),
                        new Tag()
                                .name("Ocorrência")
                                .description("Gestão completa de ocorrências do corpo de bombeiros"),
                        new Tag()
                                .name("Batalhão")
                                .description("Gerenciamento de batalhões"),
                        new Tag()
                                .name("Veículo")
                                .description("Controle de veículos operacionais"),
                        new Tag()
                                .name("Patent")
                                .description("Gestão de patentes e hierarquia militar")
                ))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes("bearerAuth",
                        new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Insira o token JWT gerado no login. Formato: Bearer {token}")));
    }
}
