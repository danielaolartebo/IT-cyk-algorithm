package model;
import java.util.ArrayList;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */

public class Transition {

    //Atributes

    private String initSymbol;
    private ArrayList<String> productions;

    //Constructor

    public Transition(String initSymbol){
        this.initSymbol = initSymbol;
        productions = new ArrayList<>();
    }

    /**
     * Gets initial symbol input
     * @return initSymbol string
     */

    public String getInitSymbol() {
        return initSymbol;
    }

    /**
     * Sets initial symbol
     * @param initSymbol string
     */

    public void setInitSymbol(String initSymbol) {
        this.initSymbol = initSymbol;
    }

    /**
     * Gets productions
     * @return ArrayList of strings
     */

    public ArrayList<String> getProductions() {
        return productions;
    }

    /**
     * Sets productions
     * @param ArrayList of strings of productions
     */

    public void setProductions(ArrayList<String> productions) {
        this.productions = productions;
    }
}
