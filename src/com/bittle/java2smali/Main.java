package com.bittle.java2smali;

import com.bittle.java2smali.Converters.Converter;
import com.bittle.java2smali.util.File;
import javafx.application.Application;
import javafx.stage.Stage;

// TODO: Drago, make the GUI pretty
// TODO: Bittle, tweak around and make .apk -> .smali
// TODO: OTHERS, ask
// TODO: EVERYONE, don't make a class for every conversion please, simplify it
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        */

        String java_file = "/Users/oscartorres/IdeaProjects/Java2Smali/temp/Definition.java";
        File file = Converter.javaToSmali(java_file);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
