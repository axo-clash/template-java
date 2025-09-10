package fr.axopen.template-java.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
@Slf4j
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ProblemDetail> handleAllExceptions(Exception ex, WebRequest request) {
        HttpStatus status;
        String detail;
        String title = null;
        URI type = URI.create(request.getDescription(false));
        if (ex instanceof AccessDeniedException) {
            log.warn("Access denied", ex);
            status = HttpStatus.FORBIDDEN;
            detail = ex.getLocalizedMessage();
        } else if (ex instanceof FonctionalException fe) {
            log.error("Fonctional Exception", fe);
            status = fe.getHttpStatus();
            title = fe.getCode();
            detail = fe.getMessage();
        } else {
            log.error("Default Exception Handler", ex);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            detail = ex.getMessage();
        }

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        problemDetail.setType(type);
        problemDetail.setInstance(type);
        return new ResponseEntity<>(problemDetail, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Set<String> messages = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toSet());
        String message = String.join(", ", messages);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message);
        problemDetail.setType(URI.create(request.getDescription(false)));
        problemDetail.setInstance(URI.create(request.getDescription(false)));
        problemDetail.setTitle("METHOD_ARGUMENT_NOT_VALID");
        return new ResponseEntity<>(problemDetail, status);
    }

}
