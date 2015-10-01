/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model;

/**
 * Class used to represent status type
 *
 * @author USER
 */
public enum StatusType {

    ONLINE("ONLINE"),
    OFFLINE("OFFLINE");
    private final String text;

    /**
     * Constructor
     *
     * @param text status text
     */
    private StatusType(final String text) {
        this.text = text;
    }
    

    /*
     * To string value
     */
    @Override

    public String toString() {

        return text;
    }
}
