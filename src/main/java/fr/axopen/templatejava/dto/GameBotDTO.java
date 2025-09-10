package fr.axopen.templatejava.dto;

import java.io.Serializable;

public record GameBotDTO(
        GameDTO game,
        BotDTO bot
) implements Serializable {
}
