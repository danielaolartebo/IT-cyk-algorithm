package model;

import netscape.javascript.JSUtil;

import java.util.ArrayList;

public class CFG {

    private final Grammar gm;
    private String grammarM;
    private boolean chomskey = true;

    public CFG() {
        this.gm = new Grammar();
    }

    public Grammar getGrammar() {
        return gm;
    }

    public void getGrammarM(String grammar) {
        grammarM = grammar;
    }

    public boolean getChomskey() {
        return chomskey;
    }

    public void splitGrammarT() {
        String[] splitGrammarT = grammarM.split("\n");
        splitSymbolInit(splitGrammarT);
    }

    public void splitSymbolInit(String[] splitGrammarT) {
        Transition transition;
        boolean out = true;
        String[] productions = new String[splitGrammarT.length];
        for (int i = 0; i < splitGrammarT.length; i++) {
            String[] init = splitGrammarT[i].split("->");
            init[0] = init[0].replaceAll(" ", "");
            if (init[0].length() == 1) {
                transition = new Transition(init[0]);
                productions[i] = init[1];
                gm.getTransitions().add(transition);
            } else {
                out = false;
                chomskey = false;
            }
        }
        if (out) {
            splitProducctions(productions);
        }
    }

    public void splitProducctions(String[] productions) {
        int actualT = 0;
        for (int i = 0; i < productions.length; i++) {
            String[] production = productions[i].split("\\|");
            deleteSpace(production);
            validateTerminals(production);
        }
    }

    public void deleteSpace(String[] production){
        for (int i = 0; i < production.length; i++) {
            production[i] = production[i].replaceAll(" ", "");
        }
    }

    public void validateTerminals(String[] production){
        for (int i = 0; i < production.length; i++) {
            if(production[i].length() == 1){
                if(!((int)production[i].charAt(0) >= 97 & (int)production[i].charAt(0) <= 122) ||
                        (int)production[i].charAt(0) == 42){
                    chomskey = false;
                }
            }
        }
    }
}
