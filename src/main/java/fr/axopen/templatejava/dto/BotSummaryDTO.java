package fr.axopen.templatejava.dto;

import fr.axopen.templatejava.enums.BotTypeEnum;

import java.io.Serializable;

public record BotSummaryDTO(
        BotTypeEnum botType,
        String name,
        String version,
        Integer life
) implements Serializable {
}
