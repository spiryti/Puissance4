

public class AlphaBeta<STATE, ACTION> implements Search<STATE, ACTION>{


    private Game<STATE, ACTION> game;
    private int expandedNodes;

    /** Creates a new search object for a given game. */
    public static <STATE, ACTION> AlphaBeta<STATE, ACTION>
    createFor(Game<STATE, ACTION> game) {
        return new AlphaBeta<STATE, ACTION>(game);
    }

    public AlphaBeta(Game<STATE, ACTION> game) {
        this.game = game;
    }

    public ACTION makeDecision(STATE state) {
        expandedNodes = 0;
        ACTION result = null ;
        double resultValue = Double.NEGATIVE_INFINITY;
        boolean p = true;
        //max(state, p, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        for (ACTION action : game.getActions(state)) {
            double value = Math.max(resultValue, min(game.getResult(state, action), !p, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
            if (value > resultValue) {
                result = action;
                resultValue = value;
            }
        }

        return result;
    }

    private double max(STATE s, boolean player, double alpha, double beta) {
        assert(player);
        expandedNodes++;
        if(game.isTerminal(s)) {
            return game.getUtility(s,player);
        }
        double v = Double.NEGATIVE_INFINITY;
        for(ACTION a : game.getActions(s)) {
            double eval = min(game.getResult(s,a), !player, alpha, beta);
            v = Math.max(v, eval);
            alpha = Math.max(alpha, eval);

            if(beta <= alpha) {
                break;
            }
        }

        return v;
    }


    private double min(STATE s, boolean player, double alpha, double beta) {
        assert(!player);
        expandedNodes++;
        if(game.isTerminal(s)) {
            return game.getUtility(s,player);
        }
        double v = Double.POSITIVE_INFINITY;
        for(ACTION a : game.getActions(s)) {
            double eval = max(game.getResult(s,a), !player, alpha, beta);
            v = Math.min(v, eval);
            beta = Math.min(beta, eval);
            if(beta <= alpha) {
                break;
            }
        }

        return v;
    }


    public int getMetrics() {
        return expandedNodes;
    }



}
