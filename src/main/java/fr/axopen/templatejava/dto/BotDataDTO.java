package fr.axopen.templatejava.dto;

import fr.axopen.templatejava.enums.BotState;

public record BotDataDTO(
        Integer life,
        Integer forbiddenActions,
        BotState state,
        Long lastActionTimeMs) {
}