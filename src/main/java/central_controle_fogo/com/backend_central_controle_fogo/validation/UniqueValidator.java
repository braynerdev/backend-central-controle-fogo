package central_controle_fogo.com.backend_central_controle_fogo.validation;

import central_controle_fogo.com.backend_central_controle_fogo.validation.annotation.Unique;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @Autowired
    private ApplicationContext applicationContext;

    private String field;
    private Class<?> repositoryClass;
    private Object repositoryInstance;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.repositoryClass = constraintAnnotation.repository();
        this.repositoryInstance = applicationContext.getBean(repositoryClass);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;

        try {
            String methodName = "findBy" + capitalize(field);
            Method method = repositoryClass.getMethod(methodName, value.getClass());
            Object result = method.invoke(repositoryInstance, value);

            if (result == null) return true;

            if (result instanceof java.util.Optional<?> optional) {
                return optional.isEmpty();
            }

            if (result instanceof java.util.List<?> list) {
                return list.isEmpty();
            }

            return false;

        } catch (NoSuchMethodException e) {
            throw new RuntimeException("O repositório " + repositoryClass.getSimpleName()
                    + " não possui o método findBy" + capitalize(field) + "(...)");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao validar unicidade: " + e.getMessage(), e);
        }
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
