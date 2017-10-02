package com.bittle.java2smali.util;

public class File extends java.io.File {

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

    public boolean extensionIs(String ex){
        return getExtension().equals(ex);
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
    // file = File.txt
    // para extension = smali
    // return File.smali
    public File changeExtension(String extension){
        String ex = getExtension();
        if(!ex.isEmpty()){
            int ind = getAbsolutePath().lastIndexOf(ex);
            String path = getAbsolutePath().substring(0, ind);
            return new File(path+extension);
        }
        else{
            return this;
        }
    }
}
