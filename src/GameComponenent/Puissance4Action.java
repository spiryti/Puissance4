package GameComponenent;

public class Puissance4Action {
    /**
     * vaut true si c'est le joeur jaune 0 sinon
     */
   // public boolean player; 
    /**
     * la colonne où le jeton est joué
     */
    public int colonne;

   public Puissance4Action(/*boolean player,*/int colonne){
        //@todo Actiont
    	this.colonne = colonne;
    }

	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
    
    public void readColonne() {
    	
    }
}
