package GameComponenent;

import java.util.ArrayList;

public class Puissance4 implements Game{
    @Override
    public State getInitialState() {
        //@todo renvoie l'etat initiale( plateau vide)
    	Puissance4State puissance4Init = new Puissance4State();
    	Jeton plateau[][] = null;
    	for (int i=0 ; i<6;i++) {
    		for (int j=0;i<7;i++) {
    			plateau[i][j] = Jeton.Vide;
    		}
    	}
    	puissance4Init.setPlateau(plateau);
    	//State state = puissance4Init.getPlateau();
    	
        return puissance4Init;
    }

    @Override
    public ArrayList<Action> getActions(State state) {
        //@todo renvoie la liste actions possible après un coup
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
