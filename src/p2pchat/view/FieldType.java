/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view;

/**
 * Enumeration containing field names and descriptions
 *
 * @author USER
 */
public enum FieldType {

    USERNAME("Username", null),
    PASSWORD("Password", null),
    RE_PASSWORD("Re-enter Password", null),
    SERVER("Server", null),
    PORT("Port", null),
    FRIENDNAME("Friend Name", null);
    private final String text;
    private final String description;

    /**
     * Constructor
     * @param text name as text
     * @param description field description
     */
    private FieldType(final String text, final String description) {
        this.text = text;
        this.description = description;
    }

    /**
     * To string value
     * @return string value
     */
    @Override
    public String toString() {
        return text;
    }

    /**
     * Get field name as string
     * @return field name
     */
    public String getName() {
        return text;
    }

    /**
     * Get field description
     * @return field description
     */
    public String getDescription() {
        return description;
    }
}