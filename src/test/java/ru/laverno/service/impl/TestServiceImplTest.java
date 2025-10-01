package ru.laverno.service.impl;

import org.junit.jupiter.api.Test;
import ru.laverno.service.TestService;

import static org.assertj.core.api.Assertions.assertThat;

public class TestServiceImplTest {

    private final TestService testService = new TestServiceImpl();

    @Test
    void greetings() {
        var actual = testService.greetings("Max");
        assertThat(actual)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo("Hello, Max");
    }
}
