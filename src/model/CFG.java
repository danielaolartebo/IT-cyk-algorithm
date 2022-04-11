package model;

public class CFG {

    private char[] variables;
    private char[] symbols;
    // productions será una lista???
    private char initialSymbol;

    public CFG(String inputVariables, String inputSymbols, char inputInitialSymbol) {
        variables = inputVariables.toCharArray();
        symbols = inputSymbols.toCharArray();
        initialSymbol = inputInitialSymbol;
    }
}
