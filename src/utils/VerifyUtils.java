/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 * Class used to hold utils to verify various message fields (passwords, server, port, etc.)
 * @author USER
 */
public class VerifyUtils {
    
    /**
     * Verify if server address is in correct format
     * @param server server address
     * @return verification result
     */
    public static boolean verifyServer(String server) {
        return (server.matches("^.[0-9]{1,3}/..[0-9]{1,3}/..[0-9]{1,3}/..[0-9]{1,3}") || server.matches("localhost"));
    }

    /**
     * Verify if port number is in correct format
     * @param port port number
     * @return verification result
     */
    public static boolean verifyPort(int port) {
        return (port < 65535);
    }
}
