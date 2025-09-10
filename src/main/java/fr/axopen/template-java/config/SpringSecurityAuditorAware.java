package fr.axopen.template-java.config;

import fr.axopen.template-java.dto.user.UserTokenDTO;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<Long> {

    public Optional<Long> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        UserTokenDTO userTokenDTO = (UserTokenDTO) authentication.getPrincipal();
        return Optional.of(userTokenDTO.getId());
    }
}
