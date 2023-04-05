package nl.maikel.mu_bank.exception.handler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import nl.maikel.mu_bank.exception.Error;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        var error = new Error(400, "Bad Request", String.join(", ", errors.values()));
        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
        var error = new Error(400, "Bad Request", ex.getMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
        var error = new Error(400, "Bad Request", ex.getMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { EntityNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
        var error = new Error(404, "Not found", ex.getMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ InvalidDataAccessApiUsageException.class })
    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        var error = new Error(409, "Conflict", ex.getMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        var error = new Error(500, "Internal Server Error", ex.getMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
