package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
            System.out.println("No es una forma normal de Chomskey");
            cfg = new CFG();
        }
    }

    public void sendGrammar(){
        cfg.getGrammarM(txtGrammar.getText());
        cfg.splitGrammarT();
    }
}
