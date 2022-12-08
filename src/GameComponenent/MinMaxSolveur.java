package GameComponenent;


import java.util.ArrayList;
import java.util.HashMap;

public abstract class MinMaxSolveur<State,Action> implements Solveur<State,Action> {

    public Game<State,Action> game;
    private final int depth;

    MinMaxSolveur(Game game) {
        this.game = game;
        this.depth = 3;
    }

    @Override
    public Action makeDecision(State state) {
        Action bestaction = null;
        int max = -999999;
        HashMap<Integer, Action> bestAction = new HashMap<>();
        for (Action action : game.getActions(state)) {
            State state2 = state;
            int score = (int) miniMax(state2,depth - 1, false, bestAction).get(1);
            if (score > max) {
                bestaction =action;
                max = score;
            }
        }
        return bestaction;
    }

    public ArrayList miniMax(State state, int depth, boolean maximize,HashMap<Integer, Action> bestAction) {
        if (depth == 0) {
            ArrayList array = new ArrayList<>();
            array.add(null);
            array.add(game.getResult(state,bestAction));
            return array;
        }
        if (maximize) {
            ArrayList max = new ArrayList<>();
            max.add(null);
            max.add(-999999);

            for (Action action : game.getActions(state)) {
                State state2 = state;
                int score = (int) miniMax(state2, depth - 1, false,bestAction).get(1);
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
            for (Action action : game.getActions(state)) {
                State state2 = state;
                int score = (int) miniMax(state2, depth - 1, true,bestAction).get(1);
                if (score < (int) min.get(1))
                    min.set(0, action);
                min.set(1, score);
            }
            return min;
        }
    }
}
/*
package jeux;


public class MinimaxSearch<STATE, ACTION> implements
        Search<STATE, ACTION> {

    private Game<STATE, ACTION> game;
    private int expandedNodes;

    /** Creates a new search object for a given game. */
    /*
    public static <STATE, ACTION> MinimaxSearch<STATE, ACTION>
    createFor(Game<STATE, ACTION> game) {
        return new MinimaxSearch<STATE, ACTION>(game);
    }

    public MinimaxSearch(Game<STATE, ACTION> game) {
        this.game = game;
    }


    public ACTION makeDecision(STATE state) {
        expandedNodes = 0;
        ACTION result = null ;
        double resultValue = Double.NEGATIVE_INFINITY;
        boolean p = true;
        for (ACTION action : game.getActions(state)) {
            double value = minValue(game.getResult(state, action), !p);
            if (value > resultValue) {
                result = action;
                resultValue = value;
            }
        }
        return result;
    }

    public double maxValue(STATE state, boolean player) {
        // calcule une valeur d'utilité pour un noued max
        assert (player);
        expandedNodes++;
        if (game.isTerminal(state))
            return game.getUtility(state, player);
        double value = Double.NEGATIVE_INFINITY;
        for (ACTION action : game.getActions(state))
            value = Math.max(value,
                    minValue(game.getResult(state, action),!player));
        return value;
    }

    public double minValue(STATE state, boolean player) {
        // calcule une valeur d'utilité pour un noeud min
        assert (!(player));
        expandedNodes++;
        if (game.isTerminal(state)){
            return game.getUtility(state, player);}
        double value = Double.POSITIVE_INFINITY;
        for (ACTION action : game.getActions(state))
            value = Math.min(value,
                    maxValue(game.getResult(state, action),!player));
        return value;
    }




    public int getMetrics() {
        return expandedNodes;
    }
}
*/