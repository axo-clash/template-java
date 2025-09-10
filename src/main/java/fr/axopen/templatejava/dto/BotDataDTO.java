package fr.axopen.templatejava.dto;

import fr.axopen.templatejava.enums.BotStateEnum;

import java.io.Serializable;

public record BotDataDTO(
        int life,
        int forbiddenActions,
        BotStateEnum state,
        Long lastActionTimeMs
)implements Serializable {
}
