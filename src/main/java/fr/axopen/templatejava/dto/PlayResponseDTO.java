package fr.axopen.templatejava.dto;

import fr.axopen.templatejava.enums.ActionEnum;

public record PlayResponseDTO(ActionEnum action, CoordDTO coordinates) {
    public PlayResponseDTO(ActionEnum action) {
        this(action, null);
    }
}
