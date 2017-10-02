package com.bittle.java2smali.util;

// All things you want to show user, send here
public class Log {

    public static void error(Object a){
        String err = a.toString();
        System.out.println("ERROR = "+err);
    }

    public static void warning(Object a) {
        String err = a.toString();
        System.out.println("WARNING = " + err);
    }

    public static void normal(Object a){
        String err = a.toString();
        System.out.println("NORMAL = "+err);
    }
}
