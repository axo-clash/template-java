package fr.axopen.templatejava.dto;

import fr.axopen.templatejava.enums.CaseContentEnum;

import java.io.Serializable;
import java.util.List;

public record CaseDTO(CoordDTO coordinates, CaseContentEnum content, List<ItemDTO> items) implements Serializable {
}
