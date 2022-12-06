package GameComponenent;

import java.util.ArrayList;

public abstract class MinMaxSolveur implements Solveur{

    public Game game;

    MinMaxSolveur(Game game) {
        this.game = game;
    }

    @Override
    public Action makeDecision(State state) {
        return null;
    }
}