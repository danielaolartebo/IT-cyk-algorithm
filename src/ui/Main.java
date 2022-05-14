package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */

public class Main extends Application {

    //Atributes

   FXControllerCFG fxGUI;

   //Constructor

    public Main() throws IOException {
        fxGUI = new FXControllerCFG();
    }

    /**
     * Start
     * @param primaryStage of JavaFX
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GUI/Create.fxml"));
        fxmlLoader.setController(fxGUI);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CYK Algorithm");
        primaryStage.show();
    }

    /**
     * Main
     */

    public static void main(String[] args) {
        launch(args);
    }
}

