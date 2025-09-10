package fr.axopen.template-java.dto;

import org.springframework.data.domain.Page;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record PageDTO<T>(List<T> items, int page, int size, long totalElements,
                         int totalPages) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static <T> PageDTO<T> fromPage(Page<T> page) {
        return new PageDTO<>(page.toList(), page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
