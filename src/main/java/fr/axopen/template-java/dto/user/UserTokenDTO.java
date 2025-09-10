package fr.axopen.template-java.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1;

    private Long id;
    private String username;
    private String lastname;
    private String firstname;
    private String email;
    private Collection<String> authorities;
}
