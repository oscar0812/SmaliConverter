package com.bittle.java2smali.util.files;

import com.bittle.java2smali.util.Log;
import com.bittle.java2smali.util.StringMan;

public class JavaClassFile extends File implements CodeFile {

    private String packageName = "";
    private boolean packageChecked = false;

    public JavaClassFile(String path) {
        super(path);
    }

    // try to get package name out of the mess
    // harder since .class file isn't meant to be read
    public String getPackageName() {
        if (packageChecked) {
            return packageName;
        } else {
            String all = getText();

            if (all.contains("SourceFile")) {
                // null char
                char n = '\u0000';
                // class closes with this unknown char
                char n2 = '\u0001';
                // get line that starts with SourceFile, package is here
                String line = StringMan.getLineStartsWith(all, "SourceFile");
                //Log.normal(line);
                String javaName = line.substring(0, line.indexOf(".java"));
                javaName = StringMan.runBackwardsTilUnknown(javaName);

                line = line.substring(line.indexOf(javaName + ".java") + javaName.length());
                int index = line.indexOf(javaName + n2 + n);
                if (index == -1)
                    index = line.indexOf("$") - javaName.length();
                //Log.normal("line = "+line+"\njavaName = "+javaName+"\nindex = "+index);

                line = line.substring(0, index);
                String pkg = "";

                if (line.contains(File.separator)) {
                    pkg = StringMan.runBackwardsTilUnknown(line);
                    pkg = StringMan.removeBack(pkg, File.separator.charAt(0));
                    pkg = pkg.replaceAll(File.separator, ".");
                }

                // safety check, packages cant start with a number
                pkg = StringMan.removeFrontDigits(pkg);

                packageChecked = true;
                packageName = pkg;
                return pkg;
            } else {
                packageChecked = true;
                return packageName;
            }
        }
    }

    public String getPackagePath() {
        String p = ".";
        return separator + getPackageName().replace(p, separator) + separator;
    }
}
