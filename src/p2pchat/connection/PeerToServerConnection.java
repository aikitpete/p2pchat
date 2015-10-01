package p2pchat.connection;

import p2pchat.connection.parser.InputParser;
import p2pchat.connection.parser.OutputParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import p2pchat.model.Message;

/**
 *
 */
public class PeerToServerConnection extends Thread implements IConnection {

    private OutputStream output;
    private InputStream input;
    private boolean connected = false;
    private InputParser inParser;
    private OutputParser outParser;
    private ConnectionManager manager;
    private int currentSocket = 0;

    public PeerToServerConnection(ConnectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        while (true) {
            try {
                inParser.parse();
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(PeerToServerConnection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(PeerToServerConnection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PeerToServerConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void connect(String address, int port) {

        int attempt = 0;
        
        while (!connected && attempt++ < 10) {
        
        System.out.println("Connection attempt: " + attempt + " Server port: " + (port + currentSocket));

// Create a socket with a timeout
        try {
            InetAddress addr = InetAddress.getByName(address);
            SocketAddress sockaddr = new InetSocketAddress(addr, (port + currentSocket));

            // Create an unbound socket
            Socket socket = new Socket();

            // This method will block no more than timeoutMs.
            // If the timeout occurs, SocketTimeoutException is thrown.
            int timeoutMs = 2000;   // 2 seconds

            socket.connect(sockaddr, timeoutMs);

            setOutputStream(socket.getOutputStream());
            setInputStream(socket.getInputStream());

            setConnected(true);

            //Initialize parsers
            setOutputParser(new OutputParser(getOutputStream(), this));
            setInputParser(new InputParser(getInputStream(), this));

            } catch ( IOException e ) {
                currentSocket = (currentSocket + 1) % 10; 
                System.out.println("Error: Connection error");
            }
        }
    }

    @Override
    public void send(Message message) {
        if (!isConnected()) {
            connect(manager.getServer(), manager.getPort());
        }
        getOutputParser().send(message);
    }

    public boolean isConnected() {
        return connected;
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
    public void receive(Message message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setConnected(boolean value) {
        connected = value;
    }
}
