package fr.axopen.template-java.utils;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(profiles = {"dev", "local", "test"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DefaultTestsUtils {
}
