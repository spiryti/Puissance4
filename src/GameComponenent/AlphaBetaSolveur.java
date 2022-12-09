package GameComponenent;

public abstract class AlphaBetaSolveur<State,Action> implements Solveur<State,Action>{

    public Game game;
    private int depth;

    AlphaBetaSolveur(Game game) {
        this.game = game;
        this.depth = 3;
    }
    @Override
    public Action makeDecision(State state) {
        Action bestaction = null;
        double alpha = Double.NEGATIVE_INFINITY;
        double beta = Double.POSITIVE_INFINITY;
        double score;
        for (Action action : game.getActions(state)) {
            State state2 = state;
            state2 = game.getResult(state2, action,false);
            score = alphaBeta(state2, depth - 1, false, alpha, beta);
            alpha = Math.max(alpha, score);
            if (score > alpha) {
                bestaction = action;
                alpha = score;
                if (beta <= alpha) {
                    return action;
                }
            }
        }
        return bestaction;
    }

    public double alphaBeta(State state, int depth, boolean maximize,double alpha, double beta){
        if (maximize) {
            if(game.isTerminal(state)||depth==0){
                return game.getUtility(state, false);
            }
            double score;
            for (Action action : game.getActions(state)) {
                State state2 = state;
                state2 = game.getResult(state2,action,false);
                score = alphaBeta(state2, depth-1, false,alpha,beta);
                alpha = Math.max(alpha,score);
                if(beta <= alpha){
                    return alpha;
                }
            }
            return alpha;
        } else {
            if (game.isTerminal(state) || depth == 0) {
                return game.getUtility(state, true);
            }
            double score;
            for (Action action : game.getActions(state)) {
                State state2 = state;
                state2 = game.getResult(state2, action,true);
                score = alphaBeta(state2, depth - 1, true,alpha,beta);
                beta = Math.min(beta,score);
                if(beta <= alpha){
                    return beta;
                }
            }
            return beta;
        }
    }
}