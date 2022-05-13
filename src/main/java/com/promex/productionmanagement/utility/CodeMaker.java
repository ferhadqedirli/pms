package com.promex.productionmanagement.utility;

import java.util.Arrays;

public class CodeMaker {

    public static String make(Integer value) {
        char [] arr = new char[10];
        String val = String.valueOf(value);
        Arrays.fill(arr, '0');

        int count = arr.length - 1;

        for (int i = val.length() - 1; i >= 0; i --) {
            arr[count] = val.charAt(i);
            count --;
        }
        return new String(arr);
    }

}
