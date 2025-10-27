package central_controle_fogo.com.backend_central_controle_fogo.validation;

import central_controle_fogo.com.backend_central_controle_fogo.validation.annotation.ExistsInDatabase;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;

@Component
public class ExistsInDatabaseValidator implements ConstraintValidator<ExistsInDatabase, Long> {

    @Autowired
    private ApplicationContext applicationContext;

    private Class<?> repositoryClass;
    private Object repositoryInstance;

    @Override
    public void initialize(ExistsInDatabase constraintAnnotation) {
        this.repositoryClass = constraintAnnotation.repository();
        this.repositoryInstance = applicationContext.getBean(repositoryClass);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) return true;

        try {
            Method method = repositoryClass.getMethod("findById", Object.class);
            Object result = method.invoke(repositoryInstance, value);

            if (result instanceof Optional<?> optional) {
                return optional.isPresent();
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }
}

