package com.bittle.java2smali.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;

public class StringMan {

    // return a line in the string all that starts with a
    public static String getLineStartsWith(String all, String a) {
        try {
            BufferedReader reader = new BufferedReader(new StringReader(all));
            String line;
            String output = "";
            while ((line = (reader.readLine())) != null) {
                if (line.startsWith(a)) {
                    output = line;
                    break;
                }
            }
            reader.close();
            return output;
        } catch (Exception e) {
            Log.error(e.toString() + ", trying to get starts with");
            return "";
        }
    }
    // read the string backwards until unknown char is encountered
    public static String runBackwardsTilUnknown(String all) {
        StringBuilder builder = new StringBuilder();
        for (int x = all.length() - 1; x >= 0; x--) {

            char c = all.charAt(x);
            if (!isAlpha(c) && !isDigit(c) && !isSeparator(c)) {
                break;
            }
            else {
                builder.append(c);
            }
        }

        return builder.reverse().toString();
    }

    public static String removeFrontDigits(String all){
        StringBuilder builder = new StringBuilder(all);

        for(int x= 0; x<all.length(); x++){
            if(isDigit(all.charAt(x))){
                builder.deleteCharAt(0);
            }
            else
                break;
        }

        return builder.toString();
    }
    public static String removeBack(String all, char remove){
        StringBuilder builder = new StringBuilder(all);

        for(int x = all.length()-1; x>=0; x--){
            if(all.charAt(x) == remove){
                builder.deleteCharAt(builder.length()-1);
            }
            else
                break;
        }

        return builder.toString();
    }
    private static boolean isAlpha(char a) {
        return (a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z');
    }

    private static boolean isDigit(char a) {
        return a >= '0' && a <= '9';
    }

    private static boolean isSeparator(char a){
        return (a+"").equals(File.separator);
    }
}
