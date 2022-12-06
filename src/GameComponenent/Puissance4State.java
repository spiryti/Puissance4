package GameComponenent;

/**
 * Un plateau de jeu elle permet d'accéder à l'ensemble des éléments d'un plateau
 */
public class Puissance4State implements State{

    public Jeton plateau[][];
    
    public Puissance4State(){
        plateau=new Jeton[6][7];
    }
    
    public Puissance4State(Puissance4State copie){

    }
	public Jeton[][] getPlateau() {
		return plateau;
	}
	public void setPlateau(Jeton[][] plateau) {
		this.plateau = plateau;
	}

    
}
