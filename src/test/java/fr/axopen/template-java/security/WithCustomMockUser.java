package fr.axopen.template-java.security;

import fr.axopen.template-java.enums.RoleEnum;
import org.springframework.core.annotation.AliasFor;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@WithSecurityContext(
        factory = WithCustomMockUserSecurityContextFactory.class
)
public @interface WithCustomMockUser {

    long id() default 1L;

    String username() default "user1";

    String email() default "user1@email.com";

    String firstName() default "Firstname1";

    String lastName() default "Lastname1";

    RoleEnum[] authorities() default {};

    String password() default "password";

    @AliasFor(
            annotation = WithSecurityContext.class
    )
    TestExecutionEvent setupBefore() default TestExecutionEvent.TEST_METHOD;
}
