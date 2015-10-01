/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import p2pchat.connection.parser.InputParser;
import p2pchat.connection.parser.OutputParser;
import p2pchat.model.Message;

/**
 *
 * @author USER
 */
public abstract class AbstractConnection extends Thread {

    //Temporary variable for socket
    protected Socket socket;
    //Output stream variables
    protected OutputStream output;
    protected OutputParser outParser;
    //Input stream variables
    protected InputStream input;
    protected InputParser inParser;
    //Other variables
    protected boolean connected;

    /**
     * Connect
     *
     * @param server server address
     * @param port port number
     * @throws IOException io exception
     */
    public void connect(String server, int port) throws IOException {
    }

    ;

    /**
     * Send message arguments
     * @param args message arguments
     */
    public abstract void send(String[] args);

    /**
     * Receive message arguments
     *
     * @param args message arguments
     */
    public void receive(String[] args) {
    };

    /**
     * Get connection address
     *
     * @return address
     */
    public String getAddress() {
        return null;
    };

    /**
     * Get connection port
     *
     * @return port
     */
    public int getPort() {
        return -1;
    };

    /**
     * Get connection status
     *
     * @return connection status
     */
    public boolean isConnected() {
        return connected;
    }

    ;

    /**
     * Get input parser
     * @return input parser
     */
    public InputParser getInputParser() {
        return inParser;
    }

    /**
     * Get output parser
     *
     * @return output parser
     */
    public OutputParser getOutputParser() {
        return outParser;
    }

    /**
     * Get input stream
     *
     * @return input stream
     */
    public InputStream getInputStream() {
        return input;
    }

    /**
     * Get output stream
     *
     * @return output stream
     */
    public OutputStream getOutputStream() {
        return output;
    }

    /**
     * Sets socket
     *
     * @param socket socket
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * Sets input parser
     *
     * @param input input
     */
    public void setInputParser(InputParser input) {
        this.inParser = input;
    }

    /**
     * Sets output parser
     *
     * @param output output parser
     */
    public void setOutputParser(OutputParser output) {
        this.outParser = output;
    }

    /**
     * Sets input stream
     * @param input input stream
     */
    public void setInputStream(InputStream input) {
        this.input = input;
    }

    /**
     * Sets output stream
     * @param output output stream
     */
    public void setOutputStream(OutputStream output) {
        this.output = output;
    }

    /**
     * Closes every connected socket
     */
    public abstract void close();
}
