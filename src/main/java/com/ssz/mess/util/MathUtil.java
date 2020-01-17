package com.ssz.mess.util;

import java.util.regex.Pattern;

public class MathUtil {
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
