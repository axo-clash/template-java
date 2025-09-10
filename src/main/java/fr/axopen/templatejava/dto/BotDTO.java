package fr.axopen.templatejava.dto;

import fr.axopen.templatejava.enums.BotTypeEnum;

import java.io.Serializable;

public record BotDTO(
        Long id,
        String name,
        String version,
        String url,
        BotDataDTO data,
        BotTypeEnum botType,
        CoordDTO coordinates,
        boolean faster
) implements Serializable {
}
