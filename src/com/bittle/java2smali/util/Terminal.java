package com.bittle.java2smali.util;

import com.bittle.java2smali.util.files.File;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal {

    public static String compile(String input) {
        File file = new File(input);
        if(file.exists()) {
            String error = null;

            if(input.endsWith("java"))
                error = attemptCommand(new String[]{"javac", input});

            if (error != null) {
                Log.error(error);
                return null;
            }
            else{
                // get .class from compiled .java
                // Ex: Def.java -> Def.class
                String name = file.getName();
                name = name.substring(0, name.indexOf(file.getExtension()))+"class";
                return (file.getParentFile().getAbsolutePath()+File.separator+name);
            }
        }
        else{
            Log.error("files " + input + " doesn\'t exist.");
            return null;
        }
    }

    public static void runCommand(String[] cmd) {
        String bad = attemptCommand(cmd);
        if (bad != null) {
            String a = "";
            for(int x = 0; x<cmd.length; x++){
                a+=(cmd[x]+" ");
            }
            Log.error("Couldn\'t execute "+a.trim());
        }
    }

    private static String attemptCommand(String[] command) {


        try {

            Process p = Runtime.getRuntime().exec(command);
            String in = readBuffer(new BufferedReader(new InputStreamReader(p.getInputStream())));
            String bad = readBuffer(new BufferedReader(new InputStreamReader(p.getErrorStream())));
            String out = readBuffer(new BufferedReader(new InputStreamReader(p.getErrorStream())));


            if(in!=null){
                System.out.println("In = "+in);
            }
            else if(out!=null){
                System.out.println("Out = "+out);
            }
            return bad;

        } catch (Exception ex) {
            return null;
        }
    }

    private static String readBuffer(BufferedReader reader) throws IOException {
        StringBuffer buffer = null;

        String line;

        while ((line = reader.readLine()) != null) {
            if (buffer == null) {
                buffer = new StringBuffer();
            }

            buffer.append(line);
            buffer.append("\n");
        }

        if (buffer != null) {
            return buffer.toString().trim();
        }
        return null;
    }
}
