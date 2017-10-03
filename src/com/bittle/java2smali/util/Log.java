package com.bittle.java2smali.util;

// All things you want to show user, send here
public class Log {

    public static void error(Object msg){
        String err = msg.toString();
        System.out.println("ERROR = "+err);
    }

    public static void warning(Object msg) {
        String err = msg.toString();
        System.out.println("WARNING = " + err);
    }

    public static void normal(Object msg){
        String err = msg.toString();
        System.out.println("NORMAL = "+err);
    }
}
