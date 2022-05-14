package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.CFG;

import java.io.IOException;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */

public class FXControllerCFG {

    private CFG cfg;

    public FXControllerCFG() {
        cfg = new CFG();
    }

    //----------------------------------------------------------------------------------------------------------------

    // Create.fxml file attributes

    @FXML
    private TextField stringTextField;

    @FXML
    private TextArea txtGrammar;

    @FXML
    private Button btnNext;


    //----------------------------------------------------------------------------------------------------------------

    //********************************************** GUI METHODS ***************************************************************+

    @FXML
    public void onCreateGrammar(ActionEvent event) throws IOException {
        if(!txtGrammar.getText().equals("")){
            sendGrammar();
            if(!cfg.getChomskey()){
                cfg.resetGrammar();
                alertError("No ingresaste una Gramatica FNC");
                cfg = new CFG();
            } else {
                cfg.test();
                nextScreen();
            }
        } else {
            alertError("Gramatica vacía");
        }
    }

    public void sendGrammar(){
        cfg.getGrammarM(txtGrammar.getText());
        cfg.splitGrammarT();
    }

    public void alertError(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void nextScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GUI/Testing.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage newstage = (Stage) this.btnNext.getScene().getWindow();
        newstage.close();
    }
}
