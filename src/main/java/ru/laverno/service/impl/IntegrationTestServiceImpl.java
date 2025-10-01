package ru.laverno.service.impl;

import ru.laverno.service.IntegrationTestService;

public class IntegrationTestServiceImpl implements IntegrationTestService {

    private final Integrations integrations;

    public IntegrationTestServiceImpl(Integrations integrations) {
        this.integrations = integrations;
    }

    @Override
    public void doSend(String str) {
        var request = "Request: " + str;
        integrations.doSomething(request);
    }
}

class Integrations {
    void doSomething(String request) {
        System.out.println(request);
    }
}
