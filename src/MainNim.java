import java.util.Scanner;

import GameComponenent.*;

public class MainNim {
    public static void main(String[] args) {
        Nim nim =  new Nim();
        Scanner scanner = new Scanner(System.in);
        Integer nimState = nim.getDepth();
        nim.getActions(nimState);
        boolean joueur=true;
        System.out.println(nimState);
        //MinMaxSolveur<Integer, Integer> solveur = new MinMaxSolveur(nim);
        AlphaBetaSolveur<Integer, Integer> solveur = new AlphaBetaSolveur(nim);

        while(!nim.isTerminal(nimState)) {
            if(joueur) {
                System.out.println();
                System.out.println("Entrez le nombre de batons à enlever (entre 1 et 3)");
                int colonne = scanner.nextInt();
                for (Integer action : nim.getActions(nimState)) {
                    if (colonne == action) {
                        Integer nimAction = colonne;
                        nimState = nim.getResult(nimState, nimAction, joueur);
                        joueur = false;
                        System.out.println();
                        System.out.println(nimState);
                    }
                }
            }else {
                Integer nimAction = solveur.makeDecision(nimState);
                nimState = nim.getResult(nimState, nimAction, joueur);
                joueur = true;
            }
                    System.out.println(nimState);
        }
        if(!joueur && nimState>0){
            System.out.println("Le joueur a gagné");
        }else{
            System.out.println("L'ordi a gagné");
        }
    }
}