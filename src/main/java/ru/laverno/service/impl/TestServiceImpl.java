package ru.laverno.service.impl;

import ru.laverno.service.TestService;

public class TestServiceImpl implements TestService {

    @Override
    public String greetings(String name) {
        return "Hello, " + name;
    }
}
