package com.coreLibriaries;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.String.format;

public class ConfigReader {
    public static Properties prop = readConfig();

    public static Properties readConfig(){
        Properties prop = new Properties();

        try {
            InputStream input = new FileInputStream("src/main/java/com/identity/configuration.properties");
            prop.load(input);
            System.out.println(prop.getProperty("browser"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }

    public static String getProperty(String propertyKey){

        String propValue =  prop.getProperty(propertyKey);
        if (propValue == null) throw new RuntimeException(format("Property: %s was not found or empty", propertyKey));
        return  propValue;
    }


}
