package fr.axopen.template-java.utils.controller;

import fr.axopen.template-java.utils.WebTestsUtils;
import org.springframework.test.web.servlet.ResultActions;

import static fr.axopen.template-java.utils.constants.ApiPathConstants.ADMIN_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class AdminControllerTestsUtils {

    private AdminControllerTestsUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static ResultActions clearCache(WebTestsUtils webTestsUtils) throws Exception {
        return webTestsUtils.getMockMvc().perform(post(ADMIN_PATH + "/clear-cache"));
    }

}
