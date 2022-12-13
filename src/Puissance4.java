import java.util.*;

class State {
    public Integer[][] tableau;
    public int _player;

    public State(int player) {
        _player = player;
        tableau = new Integer[6][7];
    }
}


public class Puissance4 implements Game<State, Integer> {

    Integer[][] hashTable;
    int depth;
    int currentPlayer;
    State initial_state;
    boolean firstCall;



    Puissance4(int player, int d){
        initial_state = new State(player);
        hashTable = new Integer[6 * 7][3];
        for(int i = 0; i < hashTable.length; i++) {
            for(int j = 0; j < hashTable[i].length; j++) {
                hashTable[i][j] = ( i * hashTable[i].length + j);
            }
        }

        for (Integer[] integers : initial_state.tableau) {
            Arrays.fill(integers, -1);
        }
        depth = d;
        currentPlayer = player;
    }

    @Override
    public boolean isTerminal(State state) {
        //Si 4 éléments sont alignés
        if (alignement(state.tableau) > -1){
            return true;
        }
        //Si toutes les cases sont remplies
        for (Integer[] i : state.tableau){
            for (Integer j : i){
                if (j == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean row(Integer a, Integer b, Integer c, Integer d){
        return (a.equals(b) && b.equals(c) && c.equals(d) && a > -1);
    }

    private int alignement(Integer[][] state){

        //Test sur les colonnes
        for (int i=0; i < 7; i++){
            Integer c1 = state[0][i];
            Integer c2 = state[1][i];
            Integer c3 = state[2][i];
            Integer c4 = state[3][i];
            Integer c5 = state[4][i];
            Integer c6 = state[5][i];

            if(row(c1,c2,c3,c4) || row(c2,c3,c4,c5) || row(c3,c4,c5,c6)){
                return c3;
            }
        }


        //Test sur les lignes
        for (Integer[] i : state){
            Integer a = i[0];
            Integer b = i[1];
            Integer c = i[2];
            Integer d = i[3];
            Integer e = i[4];
            Integer f = i[5];
            Integer g = i[6];

            if (row(a,b,c,d) || row(b,c,d,e) || row(c,d,e,f) || row(d,e,f,g)){
                return d;
            }
        }

        //Test sur les diagonales

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 3; j++){
                //Diagonales gauche-droite
                Integer d1 = state[5-j][i];
                Integer d2 = state[4-j][i+1];
                Integer d3 = state[3-j][i+2];
                Integer d4 = state[2-j][i+3];
                if (row(d1,d2,d3,d4)){
                    return d1;
                }

                //Diagonales droite-gauche
                Integer d5 = state[5-j][6-i];
                Integer d6 = state[4-j][5-i];
                Integer d7 = state[3-j][4-i];
                Integer d8 = state[2-j][3-i];

                if (row(d5,d6,d7,d8)){
                    return d5;
                }
            }
        }


        return -1;
    }

    int getMaxChain(Integer[][] state) {



        return 0;
    }




    @Override
    public State getInitialState() {
        return initial_state; }

    @Override
    public int getDepth() {
        return depth;
    }

    private int max3(int a, int b, int c){
        if (a > b) {
            return Math.max(a,c);
        } else {
            return Math.max(b,c);
        }
    }

    //Fonction qui renvoie la plus grande chaîne dans un tableur à partir d'un point d'origine et d'une direction
    private int parcours(Integer[][] state, int x, int y, int directX, int directY, int player){
        int n = state.length;
        int m = state[x].length;
        int i = 0;
        int j = 0;
        int maxi = 0;
        int chain = 0;
        while (x + i < n && y + j < m && x + i > -1 && y + j > -1){
            if (state[x+i][y+j] == player){
                chain++;
            } else {
                if (maxi < chain){
                    maxi = chain;
                } chain = 0;
            }
            i+=directX;
            j+=directY;
        }
        return maxi;
    }

    @Override
    public double getUtility(State state, boolean player) {
        int playerint = player ? 0 : 1;
        int facteur = player ? 1 : -1;
        int n = state.tableau.length;
        int m = state.tableau[0].length;
        int maxTotal = 0;

        //Parcours suivant une ligne
        for (int i = 0; i < n; i++){
            int maxLigne = parcours(state.tableau, i, 0, 0, 1, playerint);
            int maxDiagGDB = parcours(state.tableau, i, 0, 1, 1, playerint);
            int maxDiagDGB = parcours(state.tableau, n-i-1, m-1, 1, -1,playerint);

            int maxLocal = max3(maxLigne, maxDiagDGB, maxDiagGDB);
            if (maxTotal < maxLocal) maxTotal = maxLocal;
        }

        //Parcours suivant une colonne
        for (int i = 0; i < m; i++){
            int maxColonne = parcours(state.tableau, 0, i, 1, 0, playerint);
            int maxDiagGDR = parcours(state.tableau, 0, i, 1, 1, playerint);
            int maxDiagDGR = parcours(state.tableau, 0, m-(i+1), 1, -1, playerint);

            int maxLocal = max3(maxColonne, maxDiagDGR, maxDiagGDR);
            if (maxTotal < maxLocal) maxTotal = maxLocal;
        }

        return facteur * Math.min(maxTotal, 4);
    }

    @Override
    public State getResult(State state, Integer column) {
        State newState = new State(state._player);

        for(int k = 0; k < state.tableau.length; k++) {
            for(int l = 0; l < state.tableau[0].length; l++) {
                newState.tableau[k][l] = state.tableau[k][l];
            }
        }
        boolean isCHanged = false;
        for (int i = 1 ; i < newState.tableau.length; i ++ ){
            if (newState.tableau[i][column] != -1){
                newState.tableau[i-1][column] = state._player;
                isCHanged = true;
                break;
            }
        }
        if(!isCHanged) {
            newState.tableau[newState.tableau.length - 1][column] = state._player;
        }
        state._player = 1 - state._player;
        return newState;
    }

    @Override
    public List<Integer> getActions(State state) {
        List<Integer> actions = new ArrayList<>();

        //On peut mettre un pion dans chaque colonne qui n'est pas pleine
        for (int k = 0; k < state.tableau[0].length; k++){
            if (state.tableau[0][k] == -1) {
                actions.add(k);
            }
        }
        return actions;
    }

    public void Print(State state) {
        for (Integer[] integers : state.tableau) {
            for (Integer integer : integers) {
                if(integer == -1) {
                    System.out.print("  " + " | ");
                }
                else {
                    System.out.print(integer%3 + "  | ");
                }
            }
            System.out.print("\n");
        }
    }

    public int hash(State state) {
        int hash = 0;
        for(int k = 0; k < state.tableau.length; k++) {
            for(int l = 0; l < state.tableau[k].length; l++) {
                int piece = state.tableau[k][l];
                int pieceValue = (piece == -1) ? 2 : piece;
                hash ^= hashTable[k][pieceValue];
            }
        }
        if(state._player == 0) {
            hash ^= 27*27 +5;
        }
        else {
            hash ^= 27*27 +6;
        }

        return hash;
    }

    @Override
    public State ChangePlayer(State s){
        firstCall = true;
        s._player = 1 - s._player;
        return s;
    }
}
