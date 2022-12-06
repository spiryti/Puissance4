package GameComponenent;

import java.util.ArrayList;

public class Puissance4 implements Game{
    

    @Override
    public ArrayList<Action> getActions(Puissance4State state) {
        //@todo renvoie la liste actions possible après un coup
    	ArrayList<Action> puissance4Action = new ArrayList<>();
    	
    	Jeton[][] plateau=state.getPlateau();
        for (int i=0 ; i<plateau.length;i++) {
    		for (int j=0;j<plateau[i].length;j++) {
    			if(i==plateau.length-1) { // si on est � la derni�re ligne du tableau
    				if (plateau[i][j].equals(Jeton.Vide)) {
    					Puissance4Action actionPosible = new Puissance4Action(j);
    					puissance4Action.add(actionPosible);
    					
    				}
    			}
    		}
    	}
        return puissance4Action;
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
