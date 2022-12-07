package GameComponenent;

/**
 * Un plateau de jeu elle permet d'accéder à l'ensemble des éléments d'un plateau
 */
public class Puissance4State {

    private Jeton plateau[][];
    
    public Puissance4State(){
        plateau=new Jeton[6][7];
        for (int i=0 ; i<plateau.length;i++) {
    		for (int j=0;j<plateau[i].length;j++) {
    			this.plateau[i][j] = Jeton.Vide;
    		}
    	}
    }
    
    public Puissance4State(Puissance4State copie){

    }
    
	public Jeton[][] getPlateau() {
		return plateau;
	}
	public void setPlateau(Jeton[][] plateau) {
		this.plateau = plateau;
	}

	
	public void stateToString() {
		for (int i=plateau.length -1 ; i> -1;i--) {
    		for (int j=0;j<7;j++) {
    			System.out.print("|"+plateau[i][j]+"|");
    		}
    		System.out.println();
		}
		for (int i = 0; i <6; i++) {
			int colonne = i+1;
			System.out.print("|"+ colonne +"|     ");
		}
		
	}
	


    
}
