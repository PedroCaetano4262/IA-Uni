import java.util.List;
import java.util.*;



/* A demo reactive agent that plays randomly */

public class XAgent {
    private static Ilayout.ID myself;

	/**
     * return a valid random move.
     * @param board         the board to play on
     */
    static int play(Ilayout board) {
        int depth = 3;
        int bestMove = -1;
        int bestScore = Integer.MIN_VALUE;

        myself = board.getTurn();

        ArrayList<Integer> availableMoves = new ArrayList<>(board.getAvailableMoves());

        for (Integer move : board.getAvailableMoves()) {
            Ilayout newBoard = (Ilayout) ((Board) board).clone();
            newBoard.move(move);

            int currentScore = miniMax(newBoard, Integer.MIN_VALUE, Integer.MAX_VALUE,depth-1 , false);
            if (currentScore > bestScore) {
                bestScore = currentScore;
                bestMove = move;
            }
        }
        if(bestMove == -1){
            Random rand = new Random();
            bestMove = availableMoves.get(rand.nextInt(availableMoves.size()));
        }
        return bestMove;
    }

    static int playRandom(Ilayout board) {
        int[] moves = new int[board.getAvailableMoves().size()];
        int index = 0;
        for (Integer item : board.getAvailableMoves()) {
            moves[index++] = item;
        }
        int randomMove = moves[new java.util.Random().nextInt(moves.length)];
        return randomMove;
    }


    static int miniMax(Ilayout board, int alpha, int beta, int depth, boolean isMax) {
        if (depth == 0 || board.isGameOver()) {
            //System.out.println(board);
            //System.out.println(evaluateBoard(board));
            //System.out.println("----");
            return evaluateBoard(board);
        }

        if (isMax) {
            int maxEva = Integer.MIN_VALUE;
            for (Ilayout child : board.children()) {
                int eva = miniMax(child, alpha, beta, depth - 1, false);
                maxEva = Math.max(maxEva, eva);
                alpha = Math.max(alpha, maxEva);
                if (beta <= alpha)
                    break;
            }
            return maxEva;
        } else {
            int minEva = Integer.MAX_VALUE;
            for (Ilayout child : board.children()) {
                int eva = miniMax(child, alpha, beta, depth - 1, true);
                minEva = Math.min(minEva, eva);
                beta = Math.min(beta, minEva);

                if (beta <= alpha)
                    break;
            }
            return minEva;
        }
    }


    static int evaluateBoard(Ilayout board) {
        if(board.isGameOver()){
            if(board.getWinner() == Ilayout.ID.Blank)
                return 0;
            else if(board.getWinner() == myself)
                return Integer.MAX_VALUE;
            else return Integer.MIN_VALUE;
        }
        else {
            //System.out.println(board.countX());
            return board.countX();
        }

    }

}
