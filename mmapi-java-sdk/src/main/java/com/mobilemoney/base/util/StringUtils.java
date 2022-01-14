package com.mobilemoney.base.util;

/***
 * Class StringUtils
 */
public class StringUtils {

    /***
     *
     * @param stringValue
     * @return
     */
    public static boolean isNullOrEmpty(final String stringValue) {
        return stringValue == null || stringValue.trim().isEmpty();
    }
}
