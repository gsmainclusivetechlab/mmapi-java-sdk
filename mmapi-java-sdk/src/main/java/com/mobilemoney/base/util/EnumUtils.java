package com.mobilemoney.base.util;

/***
 * Enum utility class
 */
public class EnumUtils {

    /***
     * Checking enum contains a given value
     * @param enumClass
     * @param value
     * @param <E>
     * @return
     */
    public static <E extends Enum <E>> boolean isInEnum(Class <E> enumClass, String value) {
        for (E e: enumClass.getEnumConstants()) {
            if (e.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
