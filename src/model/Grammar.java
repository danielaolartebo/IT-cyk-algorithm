package model;

import java.util.ArrayList;

public class Grammar {

    private ArrayList<Transition> transitions;

    public Grammar(){
        transitions = new ArrayList<>();
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }
}
