package fr.axopen.templatejava.dto;

import fr.axopen.templatejava.enums.ItemEnum;

import java.io.Serializable;

public record ItemDTO(
        ItemEnum type,
        BotDTO owner
) implements Serializable {
}
