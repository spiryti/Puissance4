public class NimTestAlphaBeta {

    public static void main(String[] args) {
        System.out.println("Alphabeta without depth max : ");

        Nim game = new Nim(7, 10);
        AlphaBeta<Integer, Integer> alphabetasearch = AlphaBeta.createFor(game);

        int state = game.getInitialState();
        int action = -1;
        System.out.println("Machine player, what is your action?");
        //
        action = alphabetasearch.makeDecision(state);
        System.out.println("Metrics for Minimax : " + alphabetasearch.getMetrics());
        System.out.println("Chosen action is " + action);
        /*
        System.out.println("MiniMax with depth max : ");
        MinimaxLimitedDepth<Integer, Integer> minimaxSearchDepth = MinimaxLimitedDepth.createFor(game, 1);

        int state2 = game.getInitialState();
        int action2 = -1;
        System.out.println("Machine player, what is your action?");
        //
        action = minimaxSearchDepth.makeDecision(state);
        System.out.println("Metrics for Minimax depth : " + minimaxSearchDepth.getMetrics());
        System.out.println("Chosen action is " + action);
        */
    }
}
