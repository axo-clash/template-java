package fr.axopen.templatejava.dto;

import fr.axopen.templatejava.enums.ActionType;
import java.time.LocalDateTime;

public record ActionDTO(
        Long id,
        Long botId,
        ActionType action,
        Boolean forbidden,
        Long executionTimeMs,
        LocalDateTime createdDate) {
}