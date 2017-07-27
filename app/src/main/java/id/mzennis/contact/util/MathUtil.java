package id.mzennis.contact.util;

import java.util.Random;

/**
 * Created by mzennis on 7/26/17.
 */

public class MathUtil {

    public static int random(int minimum, int maximum) {
        Random rn = new Random();
        int range = maximum - minimum + 1;
        return rn.nextInt(range) + minimum;
    }
}
