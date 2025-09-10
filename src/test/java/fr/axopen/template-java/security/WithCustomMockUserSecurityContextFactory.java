package fr.axopen.template-java.security;

import fr.axopen.template-java.dto.user.UserTokenDTO;
import fr.axopen.template-java.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.*;

public class WithCustomMockUserSecurityContextFactory implements WithSecurityContextFactory<WithCustomMockUser> {
    private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    WithCustomMockUserSecurityContextFactory() {
    }

    public SecurityContext createSecurityContext(WithCustomMockUser withCustomMockUser) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(withCustomMockUser.authorities().length);
        List<String> authoritiesString = new ArrayList<>(withCustomMockUser.authorities().length);

        for (RoleEnum role : withCustomMockUser.authorities()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getCode()));
            authoritiesString.add(role.getCode());
        }

        UserTokenDTO userTokenDTO = new UserTokenDTO(withCustomMockUser.id(), withCustomMockUser.username(), withCustomMockUser.firstName(), withCustomMockUser.lastName(), withCustomMockUser.email(), authoritiesString);

        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(userTokenDTO, withCustomMockUser.password(), grantedAuthorities);
        SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        return context;
    }

    @Autowired(
            required = false
    )
    void setSecurityContextHolderStrategy(SecurityContextHolderStrategy securityContextHolderStrategy) {
        this.securityContextHolderStrategy = securityContextHolderStrategy;
    }
}
