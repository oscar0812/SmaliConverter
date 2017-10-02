package com.bittle.java2smali.util;

import com.bittle.java2smali.util.File;
import com.bittle.java2smali.util.Log;
import com.bittle.java2smali.util.Terminal;
import org.jf.baksmali.Baksmali;
import org.jf.baksmali.BaksmaliOptions;
import org.jf.dexlib2.DexFileFactory;
import org.jf.dexlib2.Opcodes;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;

public class Converter {

    public static File javaToSmali(String input) {
        File class_file = javaToClass(input);

        if (class_file != null) {
            File dex_file = classToDex(class_file.getAbsolutePath());

            if (dex_file != null) {
                File smali_file = dexToSmali(dex_file.getAbsolutePath(), dex_file.getParent());

                if (smali_file != null) {
                    // all successful once u reach here, so delete dex and class
                    class_file.delete();
                    dex_file.delete();

                    return smali_file;

                } else {
                    Log.error("Couldn\'t dex to smali");
                    return null;
                }
            } else {
                Log.error("Couldn\'t class to dex");
                return null;
            }
        } else {
            Log.error("Couldn\'t compile java file");
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
