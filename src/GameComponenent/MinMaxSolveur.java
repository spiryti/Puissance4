package GameComponenent;


public class MinMaxSolveur<State,Action> implements Solveur<State,Action> {

    public Game<State,Action> game;
    private final int depth;

    public MinMaxSolveur(Game game) {
        this.game = game;
        this.depth = 5;
    }

    @Override
    public Action makeDecision(State state) {
        Action bestaction = null;
        double max = Double.NEGATIVE_INFINITY;
        for (Action action : game.getActions(state)) {
            State state2 = state;
            state2 = game.getResult(state2,action,false);
            double score = miniMax(state2,depth - 1, false);
            if (score > max) {
                bestaction = action;
                max = score;
            }
        }
        return bestaction;

    }

    public double miniMax(State state, int depth, boolean maximize) {
        if (maximize) {
            if(game.isTerminal(state)||depth==0){
                return game.getUtility(state, false);
            }
            double score = Double.NEGATIVE_INFINITY;
            for (Action action : game.getActions(state)) {
                State state2 = state;
                state2 = game.getResult(state2,action,false);
                score = Math.max(score, miniMax(state2, depth-1, false));
            }
            return score;
        } else {
            if(game.isTerminal(state)||depth==0){
                return game.getUtility(state, true);
            }
            double score = Double.POSITIVE_INFINITY;
            for (Action action : game.getActions(state)) {
                State state2 = state;
                state2 = game.getResult(state2,action,true);
                score = Math.min(score, miniMax(state2, depth-1, true));
            }
            return score;
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