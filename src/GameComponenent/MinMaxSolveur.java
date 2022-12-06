package GameComponenent;

import com.sun.source.tree.BreakTree;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class MinMaxSolveur implements Solveur {

    public Game game;
    private final int depth;
    private static HashMap<Integer, Action> bestAction;

    MinMaxSolveur(Game game) {
        this.game = game;
        this.depth = 3;
        bestAction = new HashMap<>();
    }

    @Override
    public Action makeDecision(State state) {
        Action bestaction = null;
        int max = -999999;
        for (Action action : state.getMoves()) {
            State state2 = state.clone();
            int score = (int) miniMax(state2,depth - 1, false).get(1);
            if (score > max) {
                bestaction = new Puissance4Action(action.getIndex());
                max = score;
            }
        }
        return bestaction;
    }

    public static ArrayList miniMax(State state, int depth, boolean maximize) {
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

            for (Action action : state.getMoves()) {
                State state2 = state.clone();
                int score = (int) miniMax(state, depth - 1, false).get(1);
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
            for (Action action : state.getMoves) {
                State state2 = state.clone();
                int score = (int) miniMax(state2, depth - 1, true).get(1);
                if (score < (int) min.get(1))
                    min.set(0, action);
                min.set(1, score);
            }
            return min;
        }
    }
}