public class AlphaBetaLimitedDepth<STATE,ACTION> implements Search<STATE,ACTION> {

        private Game<STATE, ACTION> game;
        private int expandedNodes;
        private int _depth;

        /** Creates a new search object for a given game. */
        public static <STATE, ACTION> AlphaBetaLimitedDepth<STATE, ACTION>
        createFor(Game<STATE, ACTION> game, int depth) {
            return new AlphaBetaLimitedDepth<STATE, ACTION>(game, depth);
        }

        public AlphaBetaLimitedDepth(Game<STATE, ACTION> game, int depth)
        {
            this.game = game;
            _depth = depth;
        }

        public ACTION makeDecision(STATE state) {
            expandedNodes = 0;
            ACTION result = null ;
            double resultValue = Double.NEGATIVE_INFINITY;
            int depth = _depth;
            boolean p = true;
            //max(state, p, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
            for (ACTION action : game.getActions(state)) {
                double value = Math.max(resultValue, min(game.getResult(state, action),depth, !p, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
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
            if(game.isTerminal(s) ||depth == 0) {
                return game.getUtility(s,player);
            }
            double v = Double.NEGATIVE_INFINITY;
            for(ACTION a : game.getActions(s)) {
                v = Math.max(v, min(game.getResult(s,a),depth - 1, !player, alpha, beta));
                if(v > beta) {
                    return v;
                }
                alpha = Math.max(alpha, v);
            }

            return v;
        }


        private double min(STATE s,int depth, boolean player, double alpha, double beta) {
            assert(!player);
            expandedNodes++;
            if(game.isTerminal(s) || depth == 0) {
                return game.getUtility(s,player);
            }
            double v = Double.POSITIVE_INFINITY;
            for(ACTION a : game.getActions(s)) {
                v = Math.min(v, max(game.getResult(s,a),depth - 1, !player, alpha, beta));
                if(v < alpha) {
                    return v;
                }
                beta = Math.min(beta, v);
            }

            return v;
        }


        public int getMetrics() {
            return expandedNodes;
        }


}
