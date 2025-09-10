package fr.axopen.template-java.controller;

import fr.axopen.template-java.enums.RoleEnum;
import fr.axopen.template-java.security.WithCustomMockUser;
import fr.axopen.template-java.utils.WebTestsUtils;
import fr.axopen.template-java.utils.controller.AdminControllerTestsUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("AdminController")
public class AdminControllerTests extends WebTestsUtils {



    @Test
    @Order(2)
    @DisplayName("Clear all cache when auth with user should return 403")
    @WithCustomMockUser(authorities = RoleEnum.ROLE_USER)
    public void clearAllCache_whenAuthWithUser_shouldReturn403() throws Exception {
        AdminControllerTestsUtils.clearCache(this)
                .andExpect(status().isForbidden());
    }

    @Test
    @Order(3)
    @DisplayName("Clear all cache when auth with admin should return 204")
    @WithCustomMockUser(authorities = RoleEnum.ROLE_ADMIN)
    public void clearAllCache_whenAuthWithAdmin_shouldReturn204() throws Exception {
        AdminControllerTestsUtils.clearCache(this)
                .andExpect(status().isNoContent());
    }

}
