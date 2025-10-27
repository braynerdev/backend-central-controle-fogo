package central_controle_fogo.com.backend_central_controle_fogo.validation.annotation;

import central_controle_fogo.com.backend_central_controle_fogo.validation.UniqueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {

    String message() default "Valor já cadastrado no sistema!";

    String field();

    Class<?> repository();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
