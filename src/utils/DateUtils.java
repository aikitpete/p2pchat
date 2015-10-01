/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class with various date and time utils
 * @author USER
 */
public class DateUtils {

    /**
     * Get current time in HH:mm format
     * @return 
     */
    public static String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String ret = dateFormat.format(date);
        return ret;
    }
}
