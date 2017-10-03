package com.bittle.java2smali.util.files;

public class JavaFile extends File implements CodeFile {

    private String packageName = "";
    private boolean packageChecked = false;

    public JavaFile(String path) {
        super(path);
    }

    public String getPackageName() {
        if (packageChecked) {
            return packageName;
        } else {
            String all = getText();

            if (all.contains("package")) {
                int pInd = all.indexOf("package");
                String p = all.substring(pInd);
                p = p.substring(0, p.indexOf(";"));
                p = p.replace("package", "").trim();

                packageChecked = true;
                packageName = p;

                return p;
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
