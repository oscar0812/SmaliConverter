package com.bittle.java2smali.util.files;

import com.bittle.java2smali.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;

public class File extends java.io.File {
    private String textContent = "";
    private boolean textChanged = false;

    public File(String path){
        super(path);
    }

    // EX:
    // file.txt returns txt
    public String getExtension(){
        try {
            int ind = getName().lastIndexOf(".");
            return getName().substring(ind + 1);
        }catch (Exception ignored) {
            return "";
        }
    }

    // EX:
    // current: /Users/me/dir/
    // append("file.txt")
    // return /Users/me/dir/file.txt
    public File appendFile(String path){
        String p = getPath();
        return new File(p+separator+path);
    }

    public File appendFile(File file){
        return new File(getAbsolutePath()+separator+file.getAbsolutePath());
    }

    public File getParentFile() {
        String path = super.getParentFile().getAbsolutePath();
        return new File(path);
    }

    // EX:
    // file = files.txt
    // para extension = smali
    // return files.smali
    public File changeExtension(String extension){
        String ex = getExtension();
        if(!ex.isEmpty()){
            if(extension.charAt(0) == '.')
                return changeExtension(extension.substring(1));

            int ind = getAbsolutePath().lastIndexOf(ex);
            String path = getAbsolutePath().substring(0, ind);
            return new File(path+extension);
        }
        else{
            return this;
        }
    }

    // get all text content
    public String getText(){
        if(!textChanged && !textContent.isEmpty()){
            // no need to read file text
            return textContent;
        }
        else{
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(getAbsolutePath()));
                StringBuilder builder = new StringBuilder();

                // get all the text into builder
                String line;
                while((line = (bufferedReader.readLine())) != null){
                    builder.append(line);
                    builder.append("\n");
                }

                // remove last extra \n
                builder.delete(builder.length()-1, builder.length());

                bufferedReader.close();
                textContent = builder.toString();
                return textContent;
            }catch (Exception e){
                Log.error(e.toString()+", trying to getText");
                return textContent;
            }
        }
    }
}
