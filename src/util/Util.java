package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @author: chips
 * @date: 2020-06-03
 * @description:
 **/
public class Util {
    public static double parseNumber(String number) {
        double num = 0;
        if (strNullOrEmpty(number))
            return num;
        try {
           num = Double.parseDouble(number);
        } catch (Exception e) {
            // number format exception
        }
        return num;
    }

    public static boolean strNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
