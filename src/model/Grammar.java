package model;
import java.util.ArrayList;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */

public class Grammar {

    //Atributes

    private ArrayList<Transition> transitions;

    //Constructor

    public Grammar(){
        transitions = new ArrayList<>();
    }

    /**
     * Gets transitions
     * @return ArrayList of transitions
     */

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    /**
     * Sets transitions
     * @param ArrayList of transitions
     */

    public void setTransitions(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }
}
