package co.edu.unilibre.Config.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties props = new Properties();

    static {
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream(".properties")) {
            if (in == null) {
                System.out.println("Archivo config.properties no encontrado");
            } else {
                props.load(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(String key) {
        return props.getProperty(key);
    }
}
