package fr.axopen.template-java.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {

    ROLE_ADMIN("ROLE_ADMIN", 1L),
    ROLE_USER("ROLE_USER", 2L);

    private final String code;
    private final Long id;

}
