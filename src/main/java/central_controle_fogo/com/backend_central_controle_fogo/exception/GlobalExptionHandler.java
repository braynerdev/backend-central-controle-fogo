package central_controle_fogo.com.backend_central_controle_fogo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            addNestedError(errors, error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    private void addNestedError(Map<String, Object> errors, String fieldPath, String message) {
        String[] parts = fieldPath.split("\\.");

        Map<String, Object> currentLevel = errors;
        for (int i = 0; i < parts.length - 1; i++) {
            String part = parts[i];
            currentLevel = (Map<String, Object>) currentLevel.computeIfAbsent(part, k -> new HashMap<>());
        }
        currentLevel.put(parts[parts.length - 1], message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericErrors(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Ocorreu um erro interno.");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
