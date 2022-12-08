package GameComponenent;

public abstract class AlphaBetaSolveur<State,Action> implements Solveur<State,Action>{

    public Game game;

    AlphaBetaSolveur(Game game) {
        this.game = game;
    }
    @Override
    public Action makeDecision(State state) {
        return null;
    }

    public double alphaBeta(State state, int depth, boolean maximize,double alpha, double beta){
        if (maximize) {
            if(game.isTerminal(state)||depth==0){
                return game.getUtility(state, false);
            }
            double score;
            for (Action action : game.getActions(state)) {
                State state2 = state;
                state2 = game.getResult(state2,action);
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
                state2 = game.getResult(state2, action);
                score = alphaBeta(state2, depth - 1, true,alpha,beta);
                beta = Math.min(beta,score);
                if(beta <= alpha){
                    return beta;
                }
            }
            return beta;
        }
        return 0;
    }


}