package h2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {
/**
 * check field "device" to correct
 * @param str
 * @return
 */
    public boolean checkDevice(String str) {
        int l = str.length();
        if (l == 0 || l > 30)
            return false;
        else
            return true;
    }

    /**
     * check field "WiFI" to correct
     * @param str
     * @return
     */
    public boolean checkWifi(String str) {
        Pattern p = Pattern.compile("^YES|NO$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * check field "date" to correct
     * @param str
     * @return
     */
    public boolean checkDate(String str) {
        Pattern p = Pattern.compile("^(19[8-9]\\d|200\\d|201[0-4])\\-([0]\\d|[1][0-2])\\-([012]\\d|[3][0-1])$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * check on the type integer
     * @param str
     * @return
     */
    public boolean checkInt(String str) {
        Pattern p = Pattern.compile("^[1-9]|[1]\\d|20$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
    
    /**
     * check input menu to correct
     * @param str
     * @return
     */
    public boolean checkInput(String str){
        Pattern p = Pattern.compile("^[0-5]$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
    
}
