/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.connection;

import p2pchat.model.Message;

/**
 *
 * @author USER
 */
public interface IConnection {
    public abstract void connect(String server, int port);
    public abstract void send(Message message);
    public abstract void receive(Message message);
    public abstract boolean isConnected();
    public void setConnected(boolean b);
}
