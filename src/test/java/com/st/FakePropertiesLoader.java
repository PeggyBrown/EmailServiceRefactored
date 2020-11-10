package com.st;

import java.util.Properties;

public class FakePropertiesLoader implements PropertiesLoader {

    private Properties properties;

    @Override
    public Properties loadProperties() {
        properties = new Properties();
        return properties;
    }

    @Override
    public String getPassword() {
        return "password";
    }

    @Override
    public String getUsername() {
        return "username";
    }

    @Override
    public String getFrom() {
        return "from@email.com";
    }
}
