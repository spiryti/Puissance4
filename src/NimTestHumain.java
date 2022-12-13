import java.util.Scanner;

public class NimTestHumain {
    private static Integer state;
    private static Nim game;
    private static Search<Integer, Integer> search;
    static String algoName;

    public static void main(String[] args) {
        int depth = 0;

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object


        System.out.println("Enter number match at the start :");
        int matchNumber = scanner.nextInt();  // Read user input
        if(matchNumber <= 0) {
            return ;
        }
        game = new Nim(matchNumber, 10);

        System.out.println("Against what algorithme do you want to play ?");
        System.out.println("1 for Minimax.\n2 for Minimax with depth.\n3 for AlphaBeta.\n4 for AlphaBeta with depth.\n5 for AlphaBeta with memory.");
        int algo = scanner.nextInt();
        switch (algo) {
            case 1:
                algoName = "Minimax";
                search = MinimaxSearch.createFor(game);
                break;
            case 2:
                algoName = "Minimax with depth";
                System.out.println("What is the maximum depth ?");
                depth = scanner.nextInt();
                search = MinimaxLimitedDepth.createFor(game,depth);
                break;
            case 3:
                algoName = "AlphaBeta";
                search = AlphaBeta.createFor(game);
                break;
            case 4:
                algoName = "AlphaBeta with depth";
                System.out.println("What is the maximum depth ?");
                depth = scanner.nextInt();
                search = AlphaBetaLimitedDepth.createFor(game, depth);
                break;
            case 5:
                algoName = "AlphaBeta with memory";
                algoName = "AlphaBeta with depth";
                System.out.println("What is the maximum depth ?");
                depth = scanner.nextInt();
                search = AlphaBetaMemory.createFor(game, depth);
                break;
            default:
                System.out.println("Please enter an integer from 1 to 5");
                return;
        }
        if(depth < 0) {
            System.out.println("Inapropriate depth.");
            return;
        }


        System.out.println("Computer playing first ? true or false");
        boolean computerTurn = scanner.nextBoolean();

        state = game.getInitialState();
        if(computerTurn) {
            System.out.println("Game starting with " + matchNumber + " matches, computer playing first");

        }
        else {
            System.out.println("Game starting with " + matchNumber + " matches, human playing first");
        }

        while(!game.isTerminal(state)) {
            if (computerTurn) {
                computerPlay();
            }
            else {
                playerPlay();
            }
            computerTurn = !computerTurn;
        }
        if(computerTurn) {
            System.out.println("\nVous avez gagné !");
        }
        else {
            System.out.println("\nVous avez perdu !");
        }
    }

    static void playerPlay() {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        int matchesmin = 0;

        while (matchesmin < 1 || matchesmin > 3) {
            System.out.println("\nThere is " + state + " matches left.");
            System.out.println("Choose between 1 to 3 matches to pick up");
            matchesmin = scanner.nextInt();
            if(matchesmin >= state) {
                matchesmin = 0;
            }
        }

        state = game.getResult(state, matchesmin);
    }

    static void computerPlay() {
        int action = -1;
        System.out.println(algoName + ", what is your action?");
        //
        action = search.makeDecision(state);
        System.out.println("Chosen action is " + action);
        System.out.println("Metrics for " + algoName + " : " + search.getMetrics());

        state = game.getResult(state, action);
    }
}
