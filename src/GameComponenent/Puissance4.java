package GameComponenent;

import java.util.ArrayList;

public class Puissance4 implements Game{
    

    @Override
    public ArrayList<Action> getActions(State state) {
        //@todo renvoie la liste actions possible après un coup
    	ArrayList<Puissance4Action> puissance4Action = new ArrayList<>();
    	
    	
    	
        return null;
    }

    @Override
    public int getDepth() {
        return 100;
    }

    @Override
    public State getResult(State state, Action action) {
        //@todo renvoie l'état (le plateau) après que une action ait été effectué
        return null;
    }


    @Override
    public boolean isTerminal(State state) {
        //@todo determine si cet état correspond à la fin du jeu(égalité,victoire jeune, victoire rouge)
        return false;
    }

    @Override
    public int getUtility(State state,boolean player) {
        //@todo Gabriel s'en occupe
        return 0;
    }
}
