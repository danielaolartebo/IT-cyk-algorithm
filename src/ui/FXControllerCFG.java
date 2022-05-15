package ui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.CFG;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */

public class FXControllerCFG {

    //Atributes

    private CFG cfg;

    //Constructor

    public FXControllerCFG() {
        cfg = new CFG();
    }

    //----------------------------------------------------------------------------------------------------------------

    // Create.fxml file attributes

    @FXML
    private TextArea txtGrammar;

    @FXML
    private Button btnNext;

    // Testing.fxml file attributes

    @FXML
    private BorderPane bpTest;

    @FXML
    private TextField stringTextField;

    //********************************************** GUI METHODS ***************************************************************+

    /** Checks if string a w belong to the grammar
     */

    @FXML
    private void testIfStringBelongsToGrammar(ActionEvent event) {
        String string = stringTextField.getText();
        if (check(string)) {
            cfg.initCYKTable(string);
            if (cfg.cykAlgorithm(string)) {
                alert("La cadena pertenece a la gramatica", true);
            }
        }  else {
            alert("Formato incorrecto. Volver a introducir la cadena", false);
        }
    }

    /** Checks if string is correct and has correct format
     * @param string a string to be checked
     * @return true if the string matches the grammar symbols and is not empty
     */
    private boolean check(String string) {
        String symbols = Arrays.toString(cfg.getSymbols().toArray());
        System.out.println(symbols);
        return string.matches("[" + symbols + "]+") && !string.isEmpty();
    }

    /**
     * Checks if the grammar is in FNC
     * @param event
     */

    @FXML
    public void onCreateGrammar(ActionEvent event) throws IOException {
        if(!txtGrammar.getText().equals("")){
            sendGrammar();
            if(!cfg.getChomskey()){
                cfg.resetGrammar();
                alert("No ingresaste una Gramatica FNC", false);
                cfg = new CFG();
            } else {
                cfg.test();
                nextScreen();
            }
        } else {
            alert("Gramatica vacía", false);
        }
    }

    /**
     * Sends the grammar to be checked
     */

    public void sendGrammar(){
        cfg.getGrammarM(txtGrammar.getText());
        cfg.splitGrammarT();
    }

    /**
     * Error alert
     * @param msg given to the user
     */

    public void alert(String msg, boolean type){
        Alert alert;
        if (type) {
            alert = new Alert(Alert.AlertType.INFORMATION);
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
        }
        alert.setHeaderText(null);
        alert.setTitle("Alerta");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Next screen button
     */

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
