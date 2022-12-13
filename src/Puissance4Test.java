import java.util.List;

public class Puissance4Test {
    public static void main(String[] args) {
        int depth = 30;

        ///MINIMAX

        System.out.println("MiniMax without depth max : ");

        Puissance4 game = new Puissance4(0, 10);
        State state = game.getInitialState();
    /*
        double utility = game.getUtility(state, true);
        System.out.println("utilirt : " + utility);


        state = game.getResult(state, game.getActions(state).get(0));
        game.Print(state);
        System.out.print("\n");
        state = game.getResult(state, game.getActions(state).get(0));
        game.Print(state);
        state = game.getResult(state, game.getActions(state).get(1));
        game.Print(state);
        System.out.print("\n");
        state = game.getResult(state, game.getActions(state).get(1));
        game.Print(state);
        state = game.getResult(state, game.getActions(state).get(2));
        game.Print(state);
        System.out.print("\n");
        state = game.getResult(state, game.getActions(state).get(2));
        game.Print(state);
        utility = game.getUtility(state, true);
        System.out.println("utilirt : " + utility);

        System.out.print("\n");
        state = game.getResult(state, game.getActions(state).get(0));
        game.Print(state);
        System.out.print("\n");
        state = game.getResult(state, game.getActions(state).get(0));
        game.Print(state);
        System.out.print("\n");
        state = game.getResult(state, game.getActions(state).get(0));
        game.Print(state);
        System.out.print("\n");
        state = game.getResult(state, game.getActions(state).get(0));
        game.Print(state);
        System.out.print("\n");
        state = game.getResult(state, game.getActions(state).get(0));
        game.Print(state);

/*
        MinimaxSearch<State, Integer> minimaxSearch = MinimaxSearch.createFor(game);

        State s = game.getInitialState();

        int action = -1;
        System.out.println("Machine player, what is your action?");
        //
        action = minimaxSearch.makeDecision(s);
        System.out.println("Metrics for Minimax : " + minimaxSearch.getMetrics());
        System.out.println("Chosen action is " + action);
*/
        ///MINIMAX WITH DEPTH
        System.out.println("\n\n");

        System.out.println("MiniMax with depth max : ");
        MinimaxLimitedDepth<State, Integer> minimaxSearchDepth = MinimaxLimitedDepth.createFor(game, depth);

        state = game.getInitialState();

        int action2 = -1;
        System.out.println("Machine player, what is your action?");
        //
        Integer action = minimaxSearchDepth.makeDecision(state);
        System.out.println("Metrics for Minimax depth : " + minimaxSearchDepth.getMetrics());
        System.out.println("Chosen action is " + action);
/*
        ///ALPHA BETA

        System.out.println("\n\n");
        System.out.println("Alphabeta without depth max : ");

        AlphaBeta<Integer[][], Integer> alphabetasearch = AlphaBeta.createFor(game);

        state = game.getInitialState();
        System.out.println("Machine player, what is your action?");
        //
        Integer action = alphabetasearch.makeDecision(state);
        game.Print(state);
        System.out.println("Metrics for Minimax : " + alphabetasearch.getMetrics());
        System.out.println("Chosen action is " + action);

        ///ALPHA BETA WITH DEPTH

        System.out.println("\n\n");
        System.out.println("Alphabeta with depth max : ");

        AlphaBetaLimitedDepth<Integer[][], Integer> alphabetasearchLimitedDepth = AlphaBetaLimitedDepth.createFor(game, depth);

        state = game.getInitialState();
        System.out.println("Machine player, what is your action?");
        //
        action = alphabetasearchLimitedDepth.makeDecision(state);
        System.out.println("Metrics for Minimax : " + alphabetasearchLimitedDepth.getMetrics());
        System.out.println("Chosen action is " + action);
*/
        ///ALPHA BETA WITH MEMORY
/*
        System.out.println("\n\n");
        System.out.println("Alphabeta with depth max : ");

        AlphaBetaMemory<State, Integer> alphabetasearchMemory = AlphaBetaMemory.createFor(game, depth);

        state = game.getInitialState();
        System.out.println("Machine player, what is your action?");
        //
        int action = alphabetasearchMemory.makeDecision(state);
        System.out.println("Metrics for Minimax : " + alphabetasearchMemory.getMetrics());
        System.out.println("Chosen action is " + action);

*/

    }


}
