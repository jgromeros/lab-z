package lab.util;

/**
 * Clase con utilidades para las entidades
 * @author @juano
 *
 */
public class LabcaseUtils {

    /**
     * M&eacute;todo simplisimo para pasar un parametro a un mensaje
     * @param message
     * @param param
     * @return
     */
    public static String createMessage(String message, String param) {
        return message.replace("{0}", param);
    }

}
