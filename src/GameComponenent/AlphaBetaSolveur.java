package GameComponenent;

public abstract class AlphaBetaSolveur<State,Action> implements Solveur<State,Action>{

    public Game game;
    private int alpha;
    private int beta;

    AlphaBetaSolveur(Game game, int alpha, int beta) {
        this.game = game;
        this.alpha = alpha;
        this.beta = beta;
    }
    @Override
    public Action makeDecision(State state) {
        return null;
    }
}