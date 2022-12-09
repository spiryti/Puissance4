import java.util.Scanner;

import GameComponenent.*;

public class MainNim {
    public static void main(String[] args) {
        Nim nim =  new Nim();
        Scanner scanner = new Scanner(System.in);
        Integer nimState = nim.getDepth();
        nim.getActions(nimState);
        boolean joueur=true;
        while(!nim.isTerminal(nimState)) {
            System.out.println(nimState);
            System.out.println("Entrez le nombre de baton à enlever (entre 1 et 3)");
            int colonne = scanner.nextInt();
            joueur = true;
            if(colonne>0 && colonne<=3) {
                Integer nimAction = colonne;
                nimState=nim.getResult(nimState, nimAction, joueur);
                System.out.println();
                System.out.println(nimState);
                joueur = false;
                if(!nim.isTerminal(nimState)){
                    MinMaxSolveur test = new MinMaxSolveur(nim);
                    nimAction = (Integer) test.makeDecision(nimState);
                    nimState = nim.getResult(nimState, nimAction, joueur);
                }
            }
        }
        if(!joueur){
            System.out.println("Le joueur a gagné");
        }else{
            System.out.println("L'ordi a gagné");
        }
    }
}
