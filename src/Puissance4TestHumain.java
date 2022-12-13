import java.util.List;
import java.util.Scanner;

public class Puissance4TestHumain {
        private static State state;
        private static Game<State, Integer> game;
        private static Search<State, Integer> search;
        static String algoName;

        public static void main(String[] args) {
            int depth = 0;

            Scanner scanner = new Scanner(System.in);  // Create a Scanner object


            System.out.println("Computer playing first ? true or false");
            boolean computerTurn = scanner.nextBoolean();
            game = new Puissance4(0, 10);

            System.out.println("Against what algorithme do you want to play ?");
            System.out.println("1 for Minimax with depth.\n2 for AlphaBeta with depth.\n3 for AlphaBeta with memory.");
            int algo = scanner.nextInt();
            switch (algo) {
                case 1:
                    algoName = "Minimax with depth";
                    System.out.println("What is the maximum depth ?");
                    depth = scanner.nextInt();
                    search = MinimaxLimitedDepth.createFor(game,depth);
                    break;
                case 2:
                    algoName = "AlphaBeta with depth";
                    System.out.println("What is the maximum depth ?");
                    depth = scanner.nextInt();
                    search = AlphaBetaLimitedDepth.createFor(game, depth);
                    break;
                case 3:
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

            state = game.getInitialState();

            while(!game.isTerminal(state)) {
                if (computerTurn) {
                    state._player = 0;
                    System.out.println("Computer turn (0) ? " + state._player);
                    computerPlay();
                }
                else {
                    state._player = 1;
                    System.out.println("Player turn (1) ? " + state._player);
                    playerPlay();
                }
                //state = game.ChangePlayer(state);
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
            int actionPlayer = -1;
            List<Integer> actions = game.getActions(state);
            int pl = state._player;
            while (actionPlayer < 0 || actionPlayer > actions.size()) {
                System.out.println("Grille du jeu : ");
                System.out.println();
                game.Print(state);
                System.out.println("Choose between 0 to " + actions.size() + "  column to fill");
                actionPlayer = scanner.nextInt();
            }

            state = game.getResult(state, actions.get(actionPlayer));
            state._player = pl;
        }

        static void computerPlay() {
            int action = -1;
            System.out.println(algoName + ", what is your action?");
            //
            int pl = state._player;
            action = search.makeDecision(state);
            System.out.println("Chosen action is " + action);
            System.out.println("Metrics for " + algoName + " : " + search.getMetrics());
            state._player = pl;
            state = game.getResult(state, action);
            state._player = pl;
        }
    }
