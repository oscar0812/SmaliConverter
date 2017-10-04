package com.bittle.java2smali;

import com.bittle.java2smali.util.Converter;
import com.bittle.java2smali.util.files.File;
import com.bittle.java2smali.util.files.JavaClassFile;
import com.bittle.java2smali.util.files.JavaFile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// TODO: Drago, make the GUI pretty
// TODO: Bittle, tweak around and make .apk -> .smali
// TODO: OTHERS, ask
// TODO: EVERYONE, don't make a class for every conversion please, simplify it
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();



        String f1 = "/Users/oscartorres/AndroidStudioProjects/Virtuous/app/build/intermediates/classes/release/com/bittle/virtuous/dialogs/AddPlaylistDialog.class";
        String f2 = "/Users/oscartorres/AndroidStudioProjects/ColorPicker/app/build/intermediates/classes/release/com/getbase/floatingactionbutton/AddFloatingActionButton\\$1.class";
        String f3 = "/Users/oscartorres/IdeaProjects/test/src/File.java";
        String f4 = "/Users/oscartorres/IdeaProjects/test/out/production/test/MainClass.class";

        File file = Converter.javaToSmali(f3);
        if (file != null)
            System.out.println(file.getText());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
