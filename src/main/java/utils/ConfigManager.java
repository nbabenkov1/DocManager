package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class for using properties from config.properties file
 */
public class ConfigManager {
    private static final String configFilePath = "/config.properties";
    private static Properties props = new Properties();

    public static String getProperty(String propName) {
        String propValue;

        try (InputStream inputStream = ConfigManager.class.getResourceAsStream(configFilePath)){
            props.load(inputStream);
            propValue = props.getProperty(propName);
            return propValue;
        } catch (IOException e) {
            System.err.println("Config file not find!");
        }
        return "";
    }
}
