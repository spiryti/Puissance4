package GameComponenent;

/**
 * Un plateau de jeu elle permet d'accéder à l'ensemble des éléments d'un plateau
 */
public class Puissance4State {

    private Jeton plateau[][];
    private boolean joueur;

    public Puissance4State() {
        joueur = true; // Jeton jaune = true et jeton Rouge = false
        plateau = new Jeton[6][7];
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                this.plateau[i][j] = Jeton.Vide;
            }
        }
    }

    /*public Puissance4State(Puissance4State copie) {
        this.joueur = copie.joueur; // Jeton jaune = true et jeton Rouge = false
        this.plateau = new Jeton[6][7];
        for (int i = 0; i < copie.plateau.length; i++) {
            System.arraycopy(copie.plateau[i], 0, this.plateau[i], 0, copie.plateau[i].length);
        }
    }*/

    public <State> Puissance4State(Puissance4State copie) {
        this.joueur = copie.joueur; // Jeton jaune = true et jeton Rouge = false
        this.plateau = new Jeton[6][7];
        for (int i = 0; i < copie.plateau.length; i++) {
            System.arraycopy(copie.plateau[i], 0, this.plateau[i], 0, copie.plateau[i].length);
        }
    }

    public boolean isJoueur() {
        return joueur;
    }

    public void setJoueur(boolean joueur) {
        this.joueur = joueur;
    }

    public Jeton[][] getPlateau() {
        return plateau;
    }

    public void setPlateau(Jeton[][] plateau) {
        this.plateau = plateau;
    }


    public void stateToString() {
        for (int i = plateau.length - 1; i > -1; i--) {
            for (int j = 0; j < 7; j++) {
                if (plateau[i][j] == Jeton.Vide) {
                    System.out.print("|" + plateau[i][j] + " |");
                } else {
                    System.out.print("|" + plateau[i][j] + "|");
                }
            }
            System.out.println();
        }
        for (int i = 0; i < 7; i++) {
            int colonne = i + 1;
            System.out.print("|" + colonne + "|    ");
        }

    }
}
