/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view;

/**
 * Enumeration containing window types and descriptions
 *
 * @author USER
 */
public enum WindowType {

    SERVERPEER("Make instance server or peer", null),
    ADD_FRIEND("Add New Friend", null),
    REMOVE_FRIEND("Remove Friend", null),
    LOGIN("Sign in as", null),
    MAIN("P2PChat", null),
    REGISTER("Register New User", null),
    SETTINGS("Connection Settings", null),
    ERROR("P2PChat", null);
    private final String name;
    private final String description;

    /**
     * Constructor
     * @param name name as string
     * @param description window description
     */
    private WindowType(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * To string value
     * @return value as string
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Get name as string
     * @return window name as string
     */
    public String getName() {
        return name;
    }

    /**
     * Get description
     * @return window description
     */
    public String getDescription() {
        return description;
    }
}