package br.com.andeson.fileanalyzer.utils;

import br.com.andeson.fileanalyzer.exceptions.ConvertStringToArrayException;

public class ArraysUtil {

    public ArraysUtil() {
    }

    public static boolean isValid(String[] array) {
        return array.length == 4;
    }

    public static String[] stringToArray(String str, String token) throws ConvertStringToArrayException {
        var array = str.split(token);
        if (isValid(array)) {
            return array;
        } else {
            throw new ConvertStringToArrayException("Array length is invalid");
        }
    }

}
