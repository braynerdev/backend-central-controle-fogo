package central_controle_fogo.com.backend_central_controle_fogo.validation.annotation;

import central_controle_fogo.com.backend_central_controle_fogo.validation.ExistsInDatabaseValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistsInDatabaseValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsInDatabase {

    String message() default "Valor não encontrado no banco de dados.";

    Class<?> repository();

    String repositoryMethod() default "findById";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
