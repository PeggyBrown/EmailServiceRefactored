package com.st;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailPropertiesLoader implements PropertiesLoader{
    private Properties prop;

    public MailPropertiesLoader() {
        prop = new Properties();
    }

    public Properties loadProperties() {
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {

            prop.load(input);

            return prop;

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public String getPassword() {
        return prop.getProperty("mail.smtp.password");
    }

    public String getUsername() {
        return prop.getProperty("mail.smtp.username");
    }

    public String getFrom() {
        return prop.getProperty("from");
    }
}