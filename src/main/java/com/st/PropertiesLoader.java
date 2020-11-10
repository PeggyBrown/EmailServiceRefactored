package com.st;

import java.util.Properties;

public interface PropertiesLoader {
    Properties loadProperties();
    String getPassword();
    String getUsername();
    String getFrom();
}
