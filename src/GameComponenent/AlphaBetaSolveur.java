package GameComponenent;


public class AlphaBetaSolveur<State,Action> implements Solveur<State,Action> {

    public Game<State,Action> game;
    private final int depth;
    private int nombreActions;

    public AlphaBetaSolveur(Game game) {
        this.game = game;
        this.depth = 25;
        this.nombreActions=0;
    }

    @Override
    public Action makeDecision(State state) {
        Action bestaction = null;
        double alpha = Double.NEGATIVE_INFINITY;
        double beta = Double.POSITIVE_INFINITY;
        double score = Double.NEGATIVE_INFINITY;
        for (Action action : game.getActions(state)) {
            State state2 = state;
            state2 = game.getResult(state2,action,false);
            score = Math.max(score,alphaBeta(state2, depth-1, false,alpha,beta));
            if(score >= alpha){
                bestaction = action;
                alpha = score;
            }
        }
        return bestaction;
    }

    public double alphaBeta(State state, int depth, boolean maximize,double alpha, double beta){
        nombreActions++;
        if (maximize) {
            if(game.isTerminal(state)||depth==0){
                return game.getUtility(state, false);
            }
            double score = Double.NEGATIVE_INFINITY;
            for (Action action : game.getActions(state)) {
                State state2 = state;
                state2 = game.getResult(state2,action,false);
                score = Math.max(score,alphaBeta(state2, depth-1, false,alpha,beta));
                alpha = Math.max(score,alpha);
                if(alpha >= beta){
                    return score;
                }
            }
            return score;
        } else {
            if(game.isTerminal(state)||depth==0){
                return game.getUtility(state, true);
            }
            double score = Double.POSITIVE_INFINITY;
            for (Action action : game.getActions(state)) {
                State state2 = state;
                state2 = game.getResult(state2, action,true);
                score = Math.min(score,alphaBeta(state2, depth - 1, true,alpha,beta));
                beta = Math.min(score,beta);
                if(beta <= alpha){
                    return score;
                }
            }
            return score;
        }
    }
}

