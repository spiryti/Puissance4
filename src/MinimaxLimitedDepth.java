public class MinimaxLimitedDepth<STATE, ACTION> implements Search<STATE, ACTION>{
    private Game<STATE, ACTION> game;
    private int expandedNodes;
    private int _depthMax;


    /** Creates a new search object for a given game. */
    public static <STATE, ACTION> MinimaxLimitedDepth<STATE, ACTION>
    createFor(Game<STATE, ACTION> game, int depth) {
        return new MinimaxLimitedDepth<STATE, ACTION>(game, depth);
    }

    public MinimaxLimitedDepth(Game<STATE, ACTION> game, int depthMax) {
        this.game = game;
        _depthMax = depthMax;
    }


    public ACTION makeDecision(STATE state) {
        expandedNodes = 0;
        ACTION result = null ;
        double resultValue = Double.NEGATIVE_INFINITY;
        boolean p = true;
        for (ACTION action : game.getActions(state)) {
            double value = minValue(game.getResult(state, action), !p, _depthMax);
            if (value > resultValue) {
                result = action;
                resultValue = value;
            }
        }

        return result;
    }

    public double maxValue(STATE state, boolean player, int depth) {
        // calcule une valeur d'utilité pour un noued max
        assert (player);
        expandedNodes++;
        if (game.isTerminal(state) || depth == 0) {
            return game.getUtility(state, player);
        }
        double value = Double.NEGATIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            value = Math.max(value, minValue(game.getResult(state, action),!player, depth - 1));
        }
        return value;
    }

    public double minValue(STATE state, boolean player, int depth) {
        // calcule une valeur d'utilité pour un noeud min
        assert (!(player));
        expandedNodes++;
        if (game.isTerminal(state) ||depth == 0){
            return game.getUtility(state, player);
        }
        double value = Double.POSITIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            value = Math.min(value, maxValue(game.getResult(state, action),!player, depth - 1));
        }
        return value;
    }

    public int getMetrics() {
        return expandedNodes;
    }
}
