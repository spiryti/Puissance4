package GameComponenent;

import java.util.ArrayList;

public class Puissance4 implements Game<Puissance4State,Puissance4Action>{
    

    @Override
    public Puissance4Action getActions(Puissance4State state) {
        //@todo renvoie la liste actions possible après un coup
    	ArrayList<Puissance4Action> puissance4Action = new ArrayList<>();
    	
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
    public Puissance4State getResult(Puissance4State state, Puissance4Action action) {
        //@todo renvoie l'état (le plateau) après que une action ait été effectué
    	
    	Jeton[][] plateau = state.getPlateau();
    	ArrayList<Jeton> places = new ArrayList<Jeton>();
    	int colonne = action.getColonne() -1;
    	
    	for (int i = 0; i<6;i++) {
    		Jeton jeton = plateau[i][colonne];
    			places.add(jeton);
    	}
    	
    	for(int i=0; i<places.size();i++) {
    		Jeton jeton = places.get(i);
    		if ( jeton.equals(Jeton.Vide) == false && i == places.size()-1) {
    			System.out.println("plus de place sur cette colonne");	
    		}else if (jeton.equals(Jeton.Vide)){
    			jeton = Jeton.Jaune; // @todo a modifs une fois tout impl�menter
    			plateau[i][colonne] = jeton;
    			state.setPlateau(plateau);
    			break;
    		}
    	}
        return state;
    }


    @Override
    public boolean isTerminal(Puissance4State state) {
        //@todo determine si cet état correspond à la fin du jeu(égalité,victoire jeune, victoire rouge)
        return false;
    }

    @Override
    public int getUtility(Puissance4State state,boolean player) {
        //@todo Gabriel s'en occupe
        return 0;
    }

	private boolean isFull(Puissance4State state){

		for(int i=0; i < 7;i++){
			if(state.getPlateau()[i][5]==Jeton.Vide){
				return false;
			}
		}
		//state.lastUtilityCalculated=0;
		return true;
	}

	private boolean checkVertical(Puissance4State state,boolean player){
		Jeton plateau[][]=state.getPlateau();
		for(int j=0;j<6;j++){
			Jeton firstFind=Jeton.Vide;
			int numberFind=0;
			for(int i=5;i>=0;i--){
				if(firstFind!=Jeton.Vide){
					if(firstFind!=plateau[i][j]) {
						break;
					}
					else {
						numberFind+=1;
					}
				}
				else {
					if (plateau[i][j] != Jeton.Vide) {
						firstFind = plateau[i][j];
						numberFind += 1;
					}
				}
			}
			if(numberFind>=4){
				if((firstFind==Jeton.Jaune)==player){
					//state.lastUtilityCalculated =Integer.MAX_VALUE;
				}
				else{
					//state.lastUtilityCalculated=Integer.MIN_VALUE;
				}
				return true;
			}
		}
		return false;
	}

	private boolean checkHorizontal(Puissance4State state,boolean player){
		Jeton plateau[][]=state.getPlateau();
		for(int i=0;i<7;i++){
			Jeton firstFind=Jeton.Vide;
			int numberFind=0;
			for(int j=0;j<6;j++){
				if(firstFind!=Jeton.Vide){
					if(firstFind!=plateau[i][j]) {
						numberFind=0;
					}
					else {
						numberFind+=1;
					}
				}

				if (plateau[i][j] != Jeton.Vide) {
					firstFind = plateau[i][j];
					numberFind += 1;

				}
			}
			if(numberFind>=4){
				if((firstFind==Jeton.Jaune)==player){
					//state.lastUtilityCalculated =Integer.MAX_VALUE;
				}
				else{
					//state.lastUtilityCalculated=Integer.MIN_VALUE;
				}
				return true;
			}
		}
		return false;
	}

	private boolean checkDiagUp(Puissance4State state,boolean player){
		Jeton plateau[][]=state.getPlateau();
		for(int i=0;i<7;i++){
			Jeton firstFind=Jeton.Vide;
			int numberFind=0;
			for(int j=0;j<6;j++){
				if(firstFind!=Jeton.Vide){
					if(firstFind!=plateau[i][j]) {
						numberFind=0;
					}
					else {
						numberFind+=1;
					}
				}

				if (plateau[i][j] != Jeton.Vide) {
					firstFind = plateau[i][j];
					numberFind += 1;

				}
			}
			if(numberFind>=4){
				if((firstFind==Jeton.Jaune)==player){
					//state.lastUtilityCalculated =Integer.MAX_VALUE;
				}
				else{
					//state.lastUtilityCalculated=Integer.MIN_VALUE;
				}
				return true;
			}
		}
		return false;
	}

}
