/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.connection;

import p2pchat.connection.parser.InputParser;
import p2pchat.connection.parser.OutputParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import p2pchat.model.Message;

/**
 *
 * @author USER
 */
public class ServerSession extends Thread implements IConnection {

    private boolean occupied = false;
    private Socket socket;
    private OutputStream output;
    private InputStream input;
    private InputParser inParser;
    private OutputParser outParser;
    private ConnectionManager connectionManager;

    public ServerSession() {
        connectionManager = ConnectionManager.getInstance();
    }

    public void init(Socket socket) {
        try {
            setSocket(socket);
            setOccupied(true);
            setOutputParser(new OutputParser(socket.getOutputStream(), this));
            setInputParser(new InputParser(socket.getInputStream(), this));
        } catch (IOException ex) {
            Logger.getLogger(ServerSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        System.out.println("Server started");
        occupied = true;

        while (true) {

            try {
                
                //Initialize parsers
                inParser.parse();

            } catch (ParserConfigurationException ex) {
                Logger.getLogger(ServerSession.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(ServerSession.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {

                occupied = false;
                System.out.println("Error: Connection error");

            }

        }

    }

    public void send(Message message) {
        getOutputParser().send(message);
    }

    public void setOccupied(boolean occupied) {
    }

    public boolean getOccupied() {
        if (socket == null) {
            return false;
        }
        return socket.isBound();
    }

    public InputParser getInputParser() {
        return inParser;
    }

    public OutputParser getOutputParser() {
        return outParser;
    }

    public InputStream getInputStream() {
        return input;
    }

    public OutputStream getOutputStream() {
        return output;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setInputParser(InputParser input) {
        this.inParser = input;
    }

    public void setOutputParser(OutputParser output) {
        this.outParser = output;
    }

    public void setInputStream(InputStream input) {
        this.input = input;
    }

    public void setOutputStream(OutputStream output) {
        this.output = output;
    }

    @Override
    public void connect(String server, int port) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void receive(Message message) {
        connectionManager.receive(message);
    }

    @Override
    public boolean isConnected() {
        return occupied;
    }

    @Override
    public void setConnected(boolean value) {
        occupied = value;
    }
}
