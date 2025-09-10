package fr.axopen.templatejava.dto;

import fr.axopen.templatejava.enums.CaseContent;
import java.util.List;

public record CaseDTO(
        CoordDTO coordinates,
        CaseContent content,
        List<ItemDTO> items) {
}