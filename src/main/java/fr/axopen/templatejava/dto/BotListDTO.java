package fr.axopen.templatejava.dto;

import java.io.Serializable;

public record BotListDTO(
        Long id,
        String name,
        String version,
        String description,
        String url
) implements Serializable {
}
