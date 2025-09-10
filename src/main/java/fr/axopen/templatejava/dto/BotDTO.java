package fr.axopen.templatejava.dto;

import fr.axopen.templatejava.enums.BotType;

public record BotDTO(
        Long id,
        String name,
        String version,
        String url,
        BotDataDTO data,
        BotType botType,
        CoordDTO coordinates,
        Boolean faster) {
}