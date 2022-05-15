package model;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */

public class CFG {

    //Atributes

    private Grammar gm;
    private String grammarM;
    private boolean chomskey = true;
    private final ArrayList<Character> symbols;
    String[][] cykTable;

    //Constructor

    public CFG() {
        this.gm = new Grammar();
        this.symbols = new ArrayList<>();
    }

    public Grammar getGrammar() {
        return gm;
    }

    /**
     * Gets the array of symbols
     * @return the symbols in a character array
     */

    public ArrayList<Character> getSymbols() {
        return symbols;
    }

    /**
     * Sets the grammar input
     * @param grammar string
     */

    public void getGrammarM(String grammar) {
        grammarM = grammar;
    }

    /**
     * Checks if it is an FNC
     * @return true if it is in FNC
     */

    public boolean getChomskey() {
        return chomskey;
    }

    /**
     * Resets grammar
     */

    public void resetGrammar() {
        gm = new Grammar();
    }

    /**
     * Split the grammar with a line break
     */

    public void splitGrammarT() {
        String[] splitGrammarT = grammarM.split("\n");
        splitSymbolInit(splitGrammarT);
    }

    /**
     * Splits the lines according to the initial symbol
     * @param splitGrammarT as a string array
     */

    public void splitSymbolInit(String[] splitGrammarT) {
        Transition transition;
        boolean out = true;
        String[] productions = new String[splitGrammarT.length];
        for (int i = 0; i < splitGrammarT.length; i++) {
            String[] init = splitGrammarT[i].split("->");
            init[0] = init[0].replaceAll(" ", "");
            if (init[0].length() == 1) {
                if ((int) init[0].charAt(0) >= 65 && (int) init[0].charAt(0) <= 90 && validateNotRepeat(init[0])) {
                    transition = new Transition(init[0]);
                    productions[i] = init[1];
                    gm.getTransitions().add(transition);
                } else {
                    chomskey = false;
                    out = false;
                }
            } else {
                out = false;
                chomskey = false;
            }
        }
        if (out) {
            splitProducctions(productions);
        }
    }

    /**
     * Validates if there is not a repeated symbol in the transitions
     * @param symbol string
     * @return in a boolean if there is or not
     */

    public boolean validateNotRepeat(String symbol) {
        if (gm.getTransitions().isEmpty()) {
            return true;
        } else {
            for (int i = 0; i < gm.getTransitions().size(); i++) {
                if (gm.getTransitions().get(i).getInitSymbol().equals(symbol)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Splits productions with a "|" in order to be checked
     * @param productions saved
     */

    public void splitProducctions(String[] productions) {
        int actualT = 0;
        for (String s : productions) {
            String[] production = s.split("\\|");
            deleteSpace(production);
            validateTerminals(production);
            validateBinary(production);
            addTransitions(actualT, production);
            actualT++;
        }
    }

    /**
     * Deletes spaces in between production
     * @param production saved
     */

    public void deleteSpace(String[] production) {
        for (int i = 0; i < production.length; i++) {
            production[i] = production[i].replaceAll(" ", "");
        }
    }

    /**
     * Validates if there is just one terminal per production
     * @param production checked
     */

    public void validateTerminals(String[] production) {
        for (String s : production) {
            if (s.length() == 1) {
                if (!((int) s.charAt(0) >= 97 && (int) s.charAt(0) <= 122 || (int) s.charAt(0) == 42)) {
                    chomskey = false;
                } else {
                    symbols.add(s.charAt(0));
                }
            }
        }
    }

    /**
     * Validates if there are two variables in a binary production
     * @param production checked
     */

    public void validateBinary(String[] production) {
        boolean out = true;
        for (int i = 0; i < production.length && out; i++) {
            if (production[i].length() > 2) {
                chomskey = false;
                out = false;
            } else if (production[i].length() == 2) {
                if (!validateInInit(production[i].charAt(0) + "")) {
                    chomskey = false;
                    out = false;
                }
                if (!validateInInit(production[i].charAt(1) + "")) {
                    chomskey = false;
                    out = false;
                }
            }
        }
    }

    /**
     * Validates initial symbol is valid
     * @param symbol string
     * @return boolean if valid
     */

    public boolean validateInInit(String symbol) {
        boolean out = false;
        for (int i = 0; i < gm.getTransitions().size(); i++) {
            if (gm.getTransitions().get(i).getInitSymbol().equals(symbol)) {
                out = true;
                break;
            }
        }
        return out;
    }

    /**
     * Adds transitions to production if it is in FNC
     * @param production array of productions
     * @param act used to check
     */

    public void addTransitions(int act, String[] production) {
        if (chomskey) {
            for (String s : production) {
                gm.getTransitions().get(act).getProductions().add(s);
            }
        }
    }


    /**
     * Gets the initial symbol for the CYK
     */

    public void initCYKTable(String w){
        cykTable = new String[w.length()][w.length()];
        for (int i = 0; i < w.length(); i++) {
            cykTable[i][0] = w.charAt(i)+"";
        }
    }

    /**
     * Searches initial symbol
     * @param w that saves symbols found
     * @return arrays of symbols found by w
     */

    public ArrayList<String> searchStringInit(String w){
        ArrayList<String> symbols = new ArrayList<>();
        for (int i = 0; i < gm.getTransitions().size(); i++) {
            for (int j = 0; j < gm.getTransitions().get(i).getProductions().size(); j++) {
                if(gm.getTransitions().get(i).getProductions().get(j).equals(w)){
                    symbols.add(gm.getTransitions().get(i).getInitSymbol());
                }
            }
        }
        return symbols;
    }


    /**
     * Checks if a string is achieved by the grammar
     * @return true if the string is generated by the grammar, false otherwise
     */


    public boolean cykAlgorithm() {
        String temp = "";
        int n = cykTable.length;
        for (int i = 0; i < n; i++) {
            cykTable[i][0] = String.join(",", searchStringInit(cykTable[i][0]+""));
        }
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < (n-j); i++) {
                for (int k = 0; k < j; k++) {
                    String string = cykTable[i][k] + cykTable[i+k+1][j-k-1];
                    if(string.length() > 2) {
                        string = splitCadena(cykTable[i][k], cykTable[i+k+1][j-k-1]);
                    }
                    temp += searchProduction(string);
                    if(temp.length() > 0) {
                        if((int)temp.charAt(temp.length()-1) == 44){
                            temp = temp.substring(0, temp.length()-1);
                        }
                    }
                    cykTable[i][j] =  fixString(temp);
                }
                temp = "";
            }
            temp = "";
        }
        return !Objects.equals(cykTable[0][cykTable.length - 1], "");
    }

    /**
     * Searches production for the CYK
     * @param production of each transition
     * @return value found
     */

    public String searchProduction(String production){
        String msg = "";
        if(production != null){
            if(production.length() == 2){
                for (int i = 0; i < gm.getTransitions().size(); i++) {
                    if(gm.getTransitions().get(i).getProductions().contains(production)){
                        msg += gm.getTransitions().get(i).getInitSymbol()+",";
                    }
                }
            } else {
                String[] productions = production.split(" ");
                for (String s : productions) {
                    for (int j = 0; j < gm.getTransitions().size(); j++) {
                        if (gm.getTransitions().get(j).getProductions().contains(s)) {
                            msg += gm.getTransitions().get(j).getInitSymbol() + ",";
                        }
                    }
                }
            }
        }
        if(!msg.equals("")) {
            return msg;
        } else {
            return "";
        }
    }

    /**
     * Splits the string typed by the user
     * @param w1, w2 to evaluate
     * @return msg if found
     */

    public String splitCadena(String w1, String w2){
        if(w1 != null && w2 != null){
            String[] firstV = w1.split(",");
            String[] secondV = w2.split(",");
            String msg = "";
            for (String s : firstV) {
                for (String value : secondV) {
                    msg += s + value + " ";
                }
            }
            return msg;
        } else {
            return "";
        }
    }

    /**
     * Fixes the string with a coma
     * @param s to evaluate
     * @return msg if corrected
     */

    public String fixString(String s){
        if(s != null){
            String msg = "";
            String[] st = s.split(",");
            String sep = "";

            for (int i = 0; i < st.length; i++) {
                if(st[i].length()>1){
                    st[i] = st[i].replaceAll("", ",");
                    st[i] = st[i].replaceFirst(",", "");
                    st[i] = st[i].substring(0, st[i].length()-1);
                }
                String[] sti = st[i].split(",");
                for (String value : sti) {
                    msg += sep + value;
                    sep = ",";
                }
            }
            return msg;
        }
        return null;
    }

    public String showMatrix(){
        String msg = "";
        for (String[] strings : cykTable) {
            for (String string : strings) {
                msg += "["+string+"]" + " ";
            }
            msg += "\n";
        }
        return msg;
    }
}
