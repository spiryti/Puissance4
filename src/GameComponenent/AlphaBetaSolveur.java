package GameComponenent;

public abstract class AlphaBetaSolveur implements Solveur{

    public Game game;

    AlphaBetaSolveur(Game game) {
        this.game = game;
    }
    @Override
    public Action makeDecision(State state) {
        return null;
    }
}