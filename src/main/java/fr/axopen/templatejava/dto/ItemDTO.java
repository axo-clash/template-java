package fr.axopen.templatejava.dto;

import fr.axopen.templatejava.enums.ItemType;

public record ItemDTO(
        ItemType type,
        BotDTO owner) {
}