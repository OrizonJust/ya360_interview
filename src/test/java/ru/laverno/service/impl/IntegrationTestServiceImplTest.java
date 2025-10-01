package ru.laverno.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.laverno.service.IntegrationTestService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class IntegrationTestServiceImplTest {

    private final Integrations integrations = Mockito.mock(Integrations.class);
    private final IntegrationTestService integrationTestService = new IntegrationTestServiceImpl(integrations);

    private final ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

    @Test
    void doSend() {
        doNothing().when(integrations).doSomething(anyString());
        integrationTestService.doSend("test");

        verify(integrations).doSomething(stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getValue())
                .isNotNull()
                .isNotEmpty()
                .isEqualTo("Request: test");
    }
}
