/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat;

/**
 *
 * @author USER
 */
public enum P2PChatType {
    s("s"),
    p("p")
    ;
    
    //Enum text
    private final String text;
    
    /**
     * Constructor
     * @param text
     */
    private P2PChatType(String text) {
        this.text = text;
    }

    /**
     * To string value
     * @return string
     */
    @Override
    public String toString() {
        return text;
    }
}
