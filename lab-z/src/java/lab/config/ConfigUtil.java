package lab.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigUtil {

    private static Logger logger = Logger.getLogger(ConfigUtil.class);
    private static Properties props = null;

    static {
        props = new Properties();
        try {
            props.load(ConfigUtil.class.getResourceAsStream("config.properties"));
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }

}
