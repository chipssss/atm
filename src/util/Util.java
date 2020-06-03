package util;

/**
 * @author: chips
 * @date: 2020-06-03
 * @description:
 **/
public class Util {
    public static int parseNumber(String number) {
        int num = 0;
        if (strNullOrEmpty(number))
            return num;
        try {
           num = Integer.parseInt(number);
        } catch (Exception e) {
            // number format exception
        }
        return num;
    }

    public static boolean strNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
