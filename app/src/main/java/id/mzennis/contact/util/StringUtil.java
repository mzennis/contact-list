package id.mzennis.contact.util;

/**
 * Created by mzennis on 7/26/17.
 */

public class StringUtil {

    public static boolean isAlphabet(String s){
        String pattern= "^[a-zA-Z]*$";
        return s.matches(pattern);
    }
}
