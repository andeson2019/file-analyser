package br.com.andeson.fileanalyzer.utils;

import java.util.Arrays;

public enum DataType {

    SALESMAN("001"),
    CLIENT("002"),
    SALE("003");

    DataType(String value) {
        this.value = value;
    }
    String value;

    public static DataType getValue(String value){
        return Arrays.stream(values())
                .filter(item -> item.value.equals(value))
                .findFirst()
                .get();
    }
}
