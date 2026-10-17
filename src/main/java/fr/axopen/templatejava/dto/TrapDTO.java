package fr.axopen.templatejava.dto;

import fr.axopen.templatejava.enums.BotTypeEnum;

public record TrapDTO(BotTypeEnum placedBy, int placedAtTurn, int armedFromTurn, int expiresAfterTurn) { }
