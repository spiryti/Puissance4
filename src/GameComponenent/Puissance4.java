package GameComponenent;

import java.util.ArrayList;

public class Puissance4 implements Game{
    @Override
    public State getInitialState() {
        //@todo renvoie l'état apres qu'un coup ai été joué
        return null;
    }

    @Override
    public ArrayList<Action> getActions(State state) {
        //@todo renvoie la liste actions possible après un coup
        return null;
    }

    @Override
    public int getDepth() {
        //@todo renvoie la profo,nd
        return 0;
    }

    @Override
    public State getResult(State state, Action action) {
        return null;
    }

    @Override
    public boolean isTerminal(State state) {
        return false;
    }

    @Override
    public int getUtility(State state) {
        return 0;
    }
}
