package com.bittle.java2smali.util;

import com.bittle.java2smali.util.files.File;
import com.bittle.java2smali.util.files.JavaFile;
import org.jf.baksmali.Baksmali;
import org.jf.baksmali.BaksmaliOptions;
import org.jf.dexlib2.DexFileFactory;
import org.jf.dexlib2.Opcodes;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;

public class Converter {

    public static File javaToSmali(String input) {
        /* Steps:
            1) .java -> .class
            2) .class -> .dex
            3) .dex -> .smali
         */
        JavaFile javaFile = new JavaFile(input);
        // shortcut
        File dexFile = javaToDex(input);
        if (dexFile != null) {
            File smaliDir = dexToSmali(dexFile.getAbsolutePath(), dexFile.getParent());
            if (smaliDir != null) {
                return findSmali(javaFile, smaliDir);
            } else {
                Log.error("Couldn\'t dex to smali");
                return null;
            }
        } else {
            return null;
        }
    }

    // we need to find the smali once its out of dex,
    // use the java packaging to find it
    private static File findSmali(JavaFile javaFile, File smaliDir) {
        // append the package to the directory
        File s = smaliDir.appendFile(javaFile.getPackagePath() +
                javaFile.changeExtension("smali").getName());

        if (s.exists())
            return s;
        else {
            Log.error("Couldn\'t find smali file: 2");
            return null;
        }
    }

    public static File javaToDex(String javaDir) {
        File classFile = javaToClass(javaDir);
        if (classFile != null) {
            File dexFile = classToDex(classFile.getAbsolutePath());
            if (dexFile != null) {
                return dexFile;
            } else {
                Log.error("Couldn\'t class to dex");
                return null;
            }
        } else {
            Log.error("Couldn\'t java to dex");
            return null;
        }
    }

    public static File javaToClass(String javaDir) {
        String output = Terminal.compile(javaDir);

        if (output == null)
            return null;
        return new File(output);
    }

    public static File classToDex(String classDir) {
        File inputFile = new File(classDir);
        File dexFile = inputFile.getParentFile().appendFile("classes.dex");

        String[] arg = new String[]{"--dex", "--output=" + dexFile.getAbsolutePath(),
                "--no-strict", inputFile.getAbsolutePath()};

        com.android.dx.command.Main.main(arg);

        return dexFile;
    }

    public static File jarToSmali(String jarDir) {
        File dex = jarToDex(jarDir);
        if (dex != null) {
            File smaliDir = dexToSmali(dex.getAbsolutePath(), dex.getParent());
            if (smaliDir != null) {
                return smaliDir;
            } else {
                Log.error("Couldn\'t dex to smali");
                return null;
            }
        } else {
            Log.error("Couldn\'t jar to dex");
            return null;
        }
    }

    public static File jarToDex(String jarDir) {
        return classToDex(jarDir);
    }

    public static File dexToSmali(String dexFile, String outputDir) {
        try {
            Opcodes opCodes = Opcodes.getDefault();
            DexBackedDexFile dexBackedDexFile = DexFileFactory.loadDexFile(dexFile, opCodes);
            BaksmaliOptions options = new BaksmaliOptions();
            options.apiLevel = opCodes.api;
            Baksmali.disassembleDexFile(dexBackedDexFile, new File(outputDir), 6, options);

            return new File(outputDir);
        } catch (Exception e) {
            Log.error(e.toString() + ", trying to convert .dex to .smali");
            return null;
        }
    }
}