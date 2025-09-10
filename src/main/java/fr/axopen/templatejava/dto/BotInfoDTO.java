package fr.axopen.templatejava.dto;

import java.io.Serializable;

public record BotInfoDTO(
        String name,
        String version
) implements Serializable {
}
