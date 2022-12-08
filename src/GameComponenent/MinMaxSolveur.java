package GameComponenent;


import java.util.ArrayList;
import java.util.HashMap;

public abstract class MinMaxSolveur<Puissance4State,Puissance4Action> implements Solveur<Puissance4State,Puissance4Action> {

    public Game game;
    private final int depth;

    MinMaxSolveur(Game game) {
        this.game = game;
        this.depth = 3;
    }

    @Override
    public Puissance4Action makeDecision(Puissance4State state) {
        Puissance4Action bestaction = null;
        int max = -999999;
        HashMap<Integer, Puissance4Action> bestAction = new HashMap<>();
        for (Puissance4Action action : state.getMoves()) {
            Puissance4State state2 = new Puissance4State(state);
            int score = (int) miniMax(state2,depth - 1, false, bestAction).get(1);
            if (score > max) {
                bestaction = new Puissance4Action(action.getColonne());
                max = score;
            }
        }
        return bestaction;
    }

    public ArrayList miniMax(Puissance4State state, int depth, boolean maximize,HashMap<Integer, Puissance4Action> bestAction) {
        if (depth == 0) {
            ArrayList array = new ArrayList<>();
            array.add(null);
            array.add(state.getResult());
            return array;
        }
        if (maximize) {
            ArrayList max = new ArrayList<>();
            max.add(null);
            max.add(-999999);

            for (Puissance4Action action : state.getMoves()) {
                Puissance4State state2 = new Puissance4State(state);
                int score = (int) miniMax(state, depth - 1, false,bestAction).get(1);
                if (score > (int) max.get(1)) {
                    max.set(0, state);
                    max.set(1, score);
                }
            }
            return max;
        } else {
            ArrayList min = new ArrayList<>();
            min.add(null);
            min.add(999999);
            for (Puissance4Action action : state.getMoves) {
                Puissance4State state2 = new Puissance4State(state);
                int score = (int) miniMax(state2, depth - 1, true,bestAction).get(1);
                if (score < (int) min.get(1))
                    min.set(0, action);
                min.set(1, score);
            }
            return min;
        }
    }
}