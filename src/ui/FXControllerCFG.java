package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
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
    private TextArea txtGrammar;

    //----------------------------------------------------------------------------------------------------------------

    //********************************************** GUI METHODS ***************************************************************+

    @FXML
    public void onCreateGrammar(ActionEvent event) {
        sendGrammar();
        if(!cfg.getChomskey()){
            cfg.resetGrammar();
            cfg = new CFG();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error no ingresaste una gramatica en FNC");
            alert.showAndWait();
        }
        cfg.test();
    }

    public void sendGrammar(){
        cfg.getGrammarM(txtGrammar.getText());
        cfg.splitGrammarT();
    }
}
