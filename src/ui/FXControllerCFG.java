package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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

    CFG cfg;
    Productions productions;

    public FXControllerCFG () {
    }

    //----------------------------------------------------------------------------------------------------------------

    // Create.fxml file attributes

    @FXML
    private TextField variablesTF;

    @FXML
    private TextField symbolsTF;

    @FXML
    private TextField initialSymbolTF;


    //----------------------------------------------------------------------------------------------------------------

    //Production.fxml file attributes

    @FXML
    private GridPane gridPane;

    //----------------------------------------------------------------------------------------------------------------

    //Testing.fxml file attributes

    @FXML
    private TextField stringTextField;

    @FXML
    void test(ActionEvent event) {

    }

    //********************************************** GUI METHODS ***************************************************************+

    private boolean hasEmptyFields () {
        String variables = variablesTF.getText();
        String symbols = symbolsTF.getText();
        String initialSymbol = initialSymbolTF.getText();

        return variables.isEmpty() || symbols.isEmpty() || initialSymbol.isEmpty();
    }

    private boolean isUppercase () {
        String variables = variablesTF.getText();
        String uppercaseVariables = variables.toUpperCase();

        return variables.equals(uppercaseVariables);
    }

    private boolean isLowercase () {
        String symbols = symbolsTF.getText();
        String lowercaseSymbols = symbols.toLowerCase();

        return symbols.equals(lowercaseSymbols);
    }

    private boolean isVariable () {
        String variables = variablesTF.getText();
        String initialSymbol = initialSymbolTF.getText();

        int length = initialSymbol.length();

        return variables.contains(initialSymbol) && length == 1;
    }

    private boolean containsSpaces () {
        String space = " ";
        boolean variables = variablesTF.getText().contains(space);
        boolean symbols = symbolsTF.getText().contains(space);

        return variables || symbols;
    }

    @FXML
    void nextPage(ActionEvent event) throws IOException {
        if (hasEmptyFields()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Fill out empty spaces");

            alert.showAndWait();
        }else if (!isUppercase()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong Format");
            alert.setContentText("Variables have to be in uppercase");

            alert.showAndWait();
        }else if (!isLowercase()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong Format");
            alert.setContentText("Symbols have to be in lowercase");

            alert.showAndWait();
        }else if (!isVariable()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Not Variable");
            alert.setContentText("Your initial symbol is not a variable");

            alert.showAndWait();
        }else if (containsSpaces()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Input contains spaces");
            alert.setContentText("The input should not contains spaces");

            alert.showAndWait();
        }else {
            String variables = variablesTF.getText();
            String symbols = symbolsTF.getText();
            char initialSymbol = initialSymbolTF.getText().charAt(0);

            cfg = new CFG(variables, symbols, initialSymbol);
            productions = new Productions(cfg);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GUI/productions.fxml"));
            fxmlLoader.setController(productions);
            Parent root = fxmlLoader.load();
    }


    }
}
