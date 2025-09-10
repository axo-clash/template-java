package fr.axopen.template-java.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorEnum {

    // 404
    ENTITY_NOT_FOUND_ID(HttpStatus.NOT_FOUND, "Entity %s with id %d not found"),
    ENTITY_NOT_FOUND_COLUMN(HttpStatus.NOT_FOUND, "Entity %s with column %s as %s is not found"),
    PATH_TRANSVERSAL_DETECTED(HttpStatus.BAD_REQUEST, "Path transversal detected"),
    ERROR_DURING_READ_FILE(HttpStatus.INTERNAL_SERVER_ERROR, "Error during reading file : %s"),
    ID_NOT_MATCH(HttpStatus.BAD_REQUEST, "Ids %s and %s don't match"),
    ;

    private final HttpStatus status;
    private final String description;

}
