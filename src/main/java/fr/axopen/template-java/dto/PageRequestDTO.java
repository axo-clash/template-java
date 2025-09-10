package fr.axopen.template-java.dto;

import jakarta.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serial;
import java.io.Serializable;

public record PageRequestDTO(
        @Min(value = 0, message = "Page can't be negative") int page,
        @Min(value = 1, message = "Size can't be inferior to one") int size) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public Pageable toPageable() {
        return PageRequest.of(page, size);
    }

}
