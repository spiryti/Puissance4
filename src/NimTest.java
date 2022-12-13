public class NimTest {

    public static void main(String[] args) {
        int depth = 3;

        ///MINIMAX

        System.out.println("MiniMax without depth max : ");

        Nim game = new Nim(10, 10);
        MinimaxSearch<Integer, Integer> minimaxSearch = MinimaxSearch.createFor(game);

        int state = game.getInitialState();
        int action = -1;
        System.out.println("Machine player, what is your action?");
        //
        action = minimaxSearch.makeDecision(state);
        System.out.println("Metrics for Minimax : " + minimaxSearch.getMetrics());
        System.out.println("Chosen action is " + action);

        ///MINIMAX WITH DEPTH

        System.out.println("\n\n");

        System.out.println("MiniMax with depth max : ");
        MinimaxLimitedDepth<Integer, Integer> minimaxSearchDepth = MinimaxLimitedDepth.createFor(game, depth);

        int state2 = game.getInitialState();
        int action2 = -1;
        System.out.println("Machine player, what is your action?");
        //
        action = minimaxSearchDepth.makeDecision(state);
        System.out.println("Metrics for Minimax with depth : " + minimaxSearchDepth.getMetrics());
        System.out.println("Chosen action is " + action);

        ///ALPHA BETA

        System.out.println("\n\n");
        System.out.println("Alphabeta without depth max : ");

        AlphaBeta<Integer, Integer> alphabetasearch = AlphaBeta.createFor(game);

        state = game.getInitialState();
        action = -1;
        System.out.println("Machine player, what is your action?");
        //
        action = alphabetasearch.makeDecision(state);
        System.out.println("Metrics for alpha beta : " + alphabetasearch.getMetrics());
        System.out.println("Chosen action is " + action);

        ///ALPHA BETA WITH DEPTH

        System.out.println("\n\n");
        System.out.println("Alphabeta with depth max : ");

        AlphaBetaLimitedDepth<Integer, Integer> alphabetasearchLimitedDepth = AlphaBetaLimitedDepth.createFor(game, depth);

        state = game.getInitialState();
        action = -1;
        System.out.println("Machine player, what is your action?");
        //
        action = alphabetasearchLimitedDepth.makeDecision(state);
        System.out.println("Metrics for alpha beta with depth : " + alphabetasearchLimitedDepth.getMetrics());
        System.out.println("Chosen action is " + action);

        ///ALPHA BETA WITH MEMORY

        System.out.println("\n\n");
        System.out.println("Alphabeta with memory : ");

        AlphaBetaMemory<Integer, Integer> alphabetaMemory = AlphaBetaMemory.createFor(game, depth);

        state = game.getInitialState();
        action = -1;
        System.out.println("Machine player, what is your action?");
        //
        action = alphabetaMemory.makeDecision(state);
        System.out.println("Metrics for alpha beta with memory : " + alphabetaMemory.getMetrics());
        System.out.println("Chosen action is " + action);


    }
}
