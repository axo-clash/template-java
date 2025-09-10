package fr.axopen.template-java.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Locale;
import java.util.Objects;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
public class FonctionalException extends RuntimeException {

    private final String code;
    private final String details;
    private final HttpStatus httpStatus;

    @SuppressWarnings("java:S1699") // Allow to use method initCause inside constructor
    public FonctionalException(Exception e, String message, String code, HttpStatus httpStatus, String details) {
        super(message);
        this.code = code;
        this.details = details;
        this.httpStatus = Objects.requireNonNullElse(httpStatus, INTERNAL_SERVER_ERROR);

        if (e != null) {
            this.initCause(e);
        }
    }

    public FonctionalException(ErrorEnum errorEnum) {
        this(
                null,
                errorEnum.getDescription(),
                errorEnum.name(),
                errorEnum.getStatus(),
                ""
        );
    }

    public FonctionalException(ErrorEnum errorEnum, String sup) {
        this(
                null,
                errorEnum.getDescription() + sup,
                errorEnum.name(),
                errorEnum.getStatus(),
                ""
        );
    }

    public FonctionalException(ErrorEnum errorEnum, String sup, String details) {
        this(
                null,
                errorEnum.getDescription() + sup,
                errorEnum.name(),
                errorEnum.getStatus(),
                details
        );
    }

    public FonctionalException(ErrorEnum errorEnum, Exception e) {
        this(
                e,
                errorEnum.getDescription(),
                errorEnum.name(),
                errorEnum.getStatus(),
                ""
        );
    }

    public FonctionalException(ErrorEnum errorEnum, String sup, String details, Exception e) {
        this(
                e,
                errorEnum.getDescription() + sup,
                errorEnum.name(),
                errorEnum.getStatus(),
                details
        );
    }

    public FonctionalException(ErrorEnum errorEnum, String sup, Exception e) {
        this(
                e,
                errorEnum.getDescription() + sup,
                errorEnum.name(),
                errorEnum.getStatus(),
                ""
        );
    }

    public static FonctionalException format(ErrorEnum errorEnum, Object... sup) {
        return new FonctionalException(
                null,
                String.format(Locale.FRANCE, errorEnum.getDescription(), sup),
                errorEnum.name(),
                errorEnum.getStatus(),
                ""
        );
    }

    public static FonctionalException format(Exception e, ErrorEnum errorEnum, Object... sup) {
        return new FonctionalException(
                e,
                String.format(Locale.FRANCE, errorEnum.getDescription(), sup),
                errorEnum.name(),
                errorEnum.getStatus(),
                ""
        );
    }

}
