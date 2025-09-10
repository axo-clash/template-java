package fr.axopen.templatejava.dto;

import fr.axopen.templatejava.enums.ActionEnum;

import java.io.Serializable;
import java.time.Instant;

public record ActionDTO(
        Long id,
        Long botId,
        ActionEnum action,
        Boolean forbidden,
        Long executionTimeMs,
        Instant createdDate
) implements Serializable {
}
