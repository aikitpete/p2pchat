/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model;

/**
 * Class representing message
 * @author USER
 */
public class Message {

    //Variables
    private MessageType type;
    private MessageSubtype subtype;
    private MessageResult result;
    private String field1;
    private String field2;
    private String field3;
    private String time;
    private boolean initialized = false;

    /**
     * Constructor taking several variables as parameters
     * @param type message type
     * @param subtype message subtype
     * @param result message result
     * @param field1 message field1
     * @param field2 message field2
     * @param field3 message field3
     * @param time message time
     */
    public Message(MessageType type, MessageSubtype subtype, MessageResult result, String field1, String field2, String field3, String time) {
        init(type, subtype, result, field1, field2, field3, time);
    }
    
    /**
     * Constructor taking one string as parameter
     * @param data string containing arguments
     */
    public Message(String data) {
        String args[] = data.split(",");
        
        MessageType messageType = MessageType.valueOf(args[0]);
        MessageSubtype messageSubtype = MessageSubtype.valueOf(args[1]);
        MessageResult messageResult = MessageResult.valueOf(args[2]);

        init(messageType, messageSubtype, messageResult, args[3], args[4], args[5], args[6]);
    }

    /**
     * Constructor taking one string array as parameter
     * @param args array of strings to be used as parameters
     */
    public Message(String[] args) {
        MessageType messageType = MessageType.valueOf(args[0]);
        MessageSubtype messageSubtype = MessageSubtype.valueOf(args[1]);
        MessageResult messageResult = MessageResult.valueOf(args[2]);

        init(messageType, messageSubtype, messageResult, args[3], args[4], args[5], args[6]);
    }

    /**
     * Initialize message
     * @param type message type
     * @param subtype message subtype
     * @param result message result
     * @param field1 message field1
     * @param field2 message field2
     * @param field3 message field3
     * @param time message time
     */
    public final void init(MessageType type, MessageSubtype subtype, MessageResult result, String field1, String field2, String field3, String time) {

        if (type == null) {
            System.err.println("Missing message type");
            return;
        }

        if (subtype == null) {
            System.err.println("Missing message subtype");
            return;
        }

        if (result == null) {
            System.err.println("Missing message result");
            return;
        }

        if (time == null) {
            System.err.println("Missing message time");
            return;
        }

        //Check message length
        if (field1.length() == 0) {
            System.err.println(ErrorType.MESSAGE_LENGTH_SHORT);
            return;
        }

        if (field1.length() > 140) {
            System.err.println(ErrorType.MESSAGE_LENGTH_LONG);
            return;
        }

        setType(type);
        setSubtype(subtype);
        setResult(result);
        setField1(field1);
        setField2(field2);
        setField3(field3);
        setTime(time);

        initialized = true;
    }
    
    /**
     * To string value
     * @return string value
     */
    @Override
    public String toString() {
        String ret = "";
        
        ret.concat(type.toString()+",");
        ret.concat(subtype.toString()+",");
        ret.concat(result.toString()+",");
        ret.concat(field1+",");
        ret.concat(field2+",");
        ret.concat(field3+",");
        ret.concat(time+",");
                
        return ret;
    }
    
    /**
     * To string array
     * @return srtring array of message arguments
     */
    public String[] toStringArray() {
        String ret[] = new String[7];
        
        ret[0]=type.toString();
        ret[1]=subtype.toString();
        ret[2]=result.toString();
        ret[3]=field1;
        ret[4]=field2;
        ret[5]=field3;
        ret[6]=time;
                
        return ret;
    }

    /**
     * Set message type
     * @param value message type
     */
    public final void setType(MessageType value) {
        this.type = value;
    }

    /**
     * Get message type
     * @return message type
     */
    public MessageType getType() {
        return type;
    }
    
    /**
     * Set message subtype
     * @param value message subtype 
     */
    public final void setSubtype(MessageSubtype value) {
        this.subtype = value;
    }

    /**
     * Get message subtype
     * @return message subtype
     */
    public MessageSubtype getSubtype() {
        return subtype;
    }
    
    /**
     * Set message result
     * @param value message result
     */
    public final void setResult(MessageResult value) {
        this.result = value;
    }

    /**
     * Get message result
     * @return message result
     */
    public MessageResult getResult() {
        return result;
    }

    /**
     * Set message field 1
     * @param value message field 1 value
     */
    public final void setField1(String value) {
        this.field1 = value;
    }

    /**
     * Set message field 2
     * @param value message field 2 value
     */
    public final void setField2(String value) {
        this.field2 = value;
    }

    /**
     * Set message field 3
     * @param value message field 3 value
     */
    public final void setField3(String value) {
        this.field3 = value;
    }

    /**
     * Get message field 1
     * @return message field 1 value 
     */
    public String getField1() {
        return field1;
    }

    /**
     * Get message field 2
     * @return message field 2 value
     */
    public String getField2() {
        return field2;
    }

    /**
     * Get message field 3
     * @return message field 3 value
     */
    public String getField3() {
        return field3;
    }

    /**
     * Set time
     * @param value message time value 
     */
    public final void setTime(String value) {
        this.time = value;
    }

    /**
     * Get time
     * @return message time value 
     */
    public String getTime() {
        return time;
    }

    /**
     * Check if message is initialized
     * @return initialization status
     */
    public boolean isInitialized() {
        return initialized;
    }

}
