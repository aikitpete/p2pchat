/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.mock;

import p2pchat.IP2PChat;

/**
 *
 * @author USER
 */
public class P2PChatMock implements IP2PChat {

    public boolean exited = false;
    
    @Override
    public void exit() {
        exited = true;
    }
    
}
