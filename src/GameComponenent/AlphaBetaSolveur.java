package GameComponenent;


public class AlphaBetaSolveur<State,Action> implements Solveur<State,Action> {

    public Game<State,Action> game;
    private final int depth;
    private int nombreActions;

    public AlphaBetaSolveur(Game game) {
        this.game = game;
        this.depth = 20;
        this.nombreActions=0;
    }

    @Override
    public Action makeDecision(State state) {
        Action bestaction = null;
        double alpha = Double.NEGATIVE_INFINITY;
        double beta = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (Action action : game.getActions(state)) {
            nombreActions++;
            State state2 = state;
            state2 = game.getResult(state2, action,false);
            double score = alphaBeta(state2, depth - 1, false, alpha, beta);
            if (score >= max) {
                bestaction = action;
                max = score;
            }
        }
        return bestaction;
    }

    public double alphaBeta(State state, int depth, boolean maximize,double alpha, double beta){
        nombreActions++;
        if(game.isTerminal(state)||depth==0){
            return game.getUtility(state, false);
        }
        if (maximize) {
            double score = Double.NEGATIVE_INFINITY;
            for (Action action : game.getActions(state)) {
                State state2 = state;
                state2 = game.getResult(state2,action,false);
                score = Math.max(score,alphaBeta(state2, depth-1, false,alpha,beta));
                if(score >= beta){
                    return score;
                }
                alpha = Math.max(score,alpha);
            }
            return score;
        } else {
            double score = Double.POSITIVE_INFINITY;
            for (Action action : game.getActions(state)) {
                State state2 = state;
                state2 = game.getResult(state2, action,true);
                score = Math.min(score,alphaBeta(state2, depth - 1, true,alpha,beta));
                if(score <= alpha){
                    return score;
                }
                beta = Math.min(score,beta);
            }
            return score;
        }
    }
}

