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
}