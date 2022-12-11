package GameComponenent;


public class MinMaxSolveur<State,Action> implements Solveur<State,Action> {

    public Game<State,Action> game;
    private final int depth;
    private int nombreActions;

    public MinMaxSolveur(Game game) {
        this.game = game;
        this.depth = 25;
        this.nombreActions=0;
    }

    @Override
    public Action makeDecision(State state) {
        Action bestaction = null;
        double max = Double.NEGATIVE_INFINITY;
        for (Action action : game.getActions(state)) {
            nombreActions++;
            State state2;
            try{
                state2 = (State) new Puissance4State((Puissance4State) state);
            }catch(Exception e){
                state2 = state;
            }
            state2 = game.getResult(state2,action,false);
            double score = miniMax(state2,depth - 1, false);
            if (score >= max) {
                bestaction = action;
                max = score;
            }
        }
        return bestaction;

    }

    public double miniMax(State state, int depth, boolean maximize) {
        nombreActions++;
        if (maximize) {
            if(game.isTerminal(state)||depth==0){
                //System.out.println("Terminal    "+game.getUtility(state,false));
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
                //System.out.println("Terminal 2          "+game.getUtility(state,true));
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
