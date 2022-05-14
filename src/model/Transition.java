package model;

import java.util.ArrayList;

public class Transition {

    private String initSymbol;
    private ArrayList<String> productions;

    public Transition(String initSymbol){
        this.initSymbol = initSymbol;
        productions = new ArrayList<>();
    }

    public String getInitSymbol() {
        return initSymbol;
    }

    public void setInitSymbol(String initSymbol) {
        this.initSymbol = initSymbol;
    }

    public ArrayList<String> getProductions() {
        return productions;
    }

    public void setProductions(ArrayList<String> productions) {
        this.productions = productions;
    }
}
