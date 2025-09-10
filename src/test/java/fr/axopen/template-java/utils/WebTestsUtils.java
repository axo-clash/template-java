package fr.axopen.template-java.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.axopen.template-java.exception.ErrorEnum;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Getter
@AutoConfigureMockMvc
public abstract class WebTestsUtils extends DefaultTestsUtils {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    public <T> T convertToObject(MvcResult mvcResult, Class<T> clazz) throws UnsupportedEncodingException, JsonProcessingException {
        return this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(), clazz);
    }

    public <T> T convertToObject(MvcResult mvcResult, TypeReference<T> valueTypeRef) throws UnsupportedEncodingException, JsonProcessingException {
        return this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(), valueTypeRef);
    }

    public void assertIsError(MvcResult mvcResult, ErrorEnum errorEnum, Object... args) throws UnsupportedEncodingException, JsonProcessingException {
        final ProblemDetail problemDetail = this.convertToObject(mvcResult, ProblemDetail.class);
        assertThat(problemDetail).isNotNull();
        assertThat(problemDetail.getStatus()).isEqualTo(errorEnum.getStatus().value());
        assertThat(problemDetail.getTitle()).isEqualTo(errorEnum.name());
        assertThat(problemDetail.getDetail()).isEqualTo(String.format(errorEnum.getDescription(), args));
    }

    public void assertIsBadRequestValidation(MvcResult mvcResult, List<String> exceptions) throws Exception {
        final ProblemDetail problemDetail = this.convertToObject(mvcResult, ProblemDetail.class);
        assertThat(problemDetail).isNotNull();
        assertThat(problemDetail.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(problemDetail.getTitle()).isEqualTo("METHOD_ARGUMENT_NOT_VALID");

        exceptions.forEach(exception -> assertThat(problemDetail.getDetail()).contains(exception));
    }

}
