import java.util.HashMap;

public class AlphaBetaMemory<STATE, ACTION> implements Search<STATE, ACTION> {


    private Game<STATE, ACTION> game;
    private int expandedNodes;
    private int _depth;

    private class hashing {
        public int _depth;
        public double _alpha;
        public double _beta;

        public hashing() {
            _depth = 0;
            _alpha = Double.POSITIVE_INFINITY;
            _beta = Double.POSITIVE_INFINITY;
        }

        public hashing(int depth, double alpha, double beta) {
            _depth = depth;
            _alpha = alpha;
            _beta  = beta;
        }

        @Override
        public String toString() {
            return "|depth : " + _depth + " - a : " + _alpha + " - b : " + _beta + "|";
        }
    }

    /** Creates a new search object for a given game. */
    public static <STATE, ACTION> AlphaBetaMemory<STATE, ACTION>
    createFor(Game<STATE, ACTION> game, int depth) {
        return new AlphaBetaMemory<STATE, ACTION>(game, depth);
    }

    public AlphaBetaMemory(Game<STATE, ACTION> game, int depth) {
        this.game = game;
        _depth = depth;
        _transpositionTable = new HashMap<Integer, hashing>();
    }

    HashMap<Integer, hashing> _transpositionTable;


    @Override
    public ACTION makeDecision(STATE state) {
        expandedNodes = 0;
        double alpha = Double.NEGATIVE_INFINITY;
        double beta = Double.POSITIVE_INFINITY;
        ACTION result = null ;
        double resultValue = Double.NEGATIVE_INFINITY;
        boolean p = true;
        for (ACTION action : game.getActions(state)) {
            double value = Math.max(resultValue, min(game.getResult(state, action),0, !p, alpha, beta));
            if (value > resultValue) {
                result = action;
                resultValue = value;
            }
        }
        return result;
    }

    private double max(STATE s,int depth, boolean player, double alpha, double beta) {
        assert(player);
        expandedNodes++;
        if(game.isTerminal(s) || depth == _depth) {
            return game.getUtility(s,player);
        }
        double v = Double.NEGATIVE_INFINITY;
        double alph = alpha;
        double bet = alpha;

        if(_transpositionTable.containsKey(game.hash(s))) {
            hashing registered = _transpositionTable.get(game.hash(s));
            if(registered._depth >= depth) {
                return registered._alpha;
            }
        }
        else {
            for(ACTION a : game.getActions(s)) {
                double eval = min(game.getResult(s,a),depth + 1, !player, alpha, beta);
                v = Math.max(v, eval);
                alpha = Math.max(alpha, eval);
                if(beta <= alpha) {
                    break;
                }
            }
        }
        if(v > alph) {
            hashing newValue = new hashing(depth, v, beta);
            _transpositionTable.put(game.hash(s), newValue);
        }
        if(v < bet) {
            hashing newValue = new hashing(depth, alpha, v);
            _transpositionTable.put(game.hash(s), newValue);
        }
        return v;
    }


    private double min(STATE s,int depth, boolean player, double alpha, double beta) {
        assert(!player);
        expandedNodes++;
        if(game.isTerminal(s) || depth == _depth) {
            return game.getUtility(s,player);
        }
        double v = Double.POSITIVE_INFINITY;

        double alph = alpha;
        double bet = alpha;

        if(_transpositionTable.containsKey(game.hash(s))) {
            hashing registered = _transpositionTable.get(game.hash(s));
            if(registered._depth >= depth) {
                return registered._beta;
            }
        }
        for(ACTION a : game.getActions(s)) {
            double eval = max(game.getResult(s,a),depth + 1, !player, alpha, beta);
            v = Math.min(v, eval);
            beta = Math.min(beta, eval);
            if(beta <= alpha) {
                break;
            }
        }

        if(v > alph) {
            hashing newValue = new hashing(depth, v, beta);
            _transpositionTable.put(game.hash(s), newValue);
        }
        if(v < bet) {
            hashing newValue = new hashing(depth, alpha, v);
            _transpositionTable.put(game.hash(s), newValue);
        }
        return v;
    }


    @Override
    public int getMetrics() {
        return expandedNodes;
    }
}
