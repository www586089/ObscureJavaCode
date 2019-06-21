package com.zf.obscurecode.nw.utils;

public class StringUtil {

    /**
     * 首字母大写
     * @param variableName
     * @return
     */
    public static String getFirstCapitalLetter(String variableName) {
        return String.valueOf(variableName.charAt(0)).toUpperCase();
    }
}
