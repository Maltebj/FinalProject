import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Malte Bjarki
 * Date: 28.3.2013
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 */
public class BreakThroughPlayer {


    public static void main(String[] args) {
        State board = new State(6,6);
        boolean player1 = true;
        boolean victory = false;
        ArrayList<State> equalMoves;

        while (!victory)
        {
            equalMoves = new ArrayList<State>();
            int value = AlphaBetaSearch(3, board, Integer.MIN_VALUE, Integer.MAX_VALUE, player1);


            ArrayList<State> legalMoves;
            if(player1)
            {
                 legalMoves = board.getLegalMoves(1);
            }
            else
            {
                legalMoves = board.getLegalMoves(2);
            }

            for(State s : legalMoves)
            {
                if (value == s.getStateScore())
                {   if (value >= 10000 || value <= -10000)
                        victory = true;
                    equalMoves.add(s) ;

                }
            }
            Random rand = new Random();
            if (equalMoves.size() > 0)
                board = new State(equalMoves.get(rand.nextInt(equalMoves.size())).getBoard());

            board.printState();
            player1 = !player1;
        }
        System.out.println("Game Finished!");
    }

    public static int AlphaBetaSearch(int depth,State currState,int alpha, int beta, boolean player1)
    {

            if (player1)
            {
                if (depth <= 0 || currState.getLegalMoves(1).size() == 1)
                {
                    if (currState.getLegalMoves(1).size() == 1)
                    {
                        currState.getLegalMoves(1).get(0).setStateScore(10000);
                        return  currState.getLegalMoves(1).get(0).evaluateState();
                    }
                    else
                    {
                        currState.setStateScore(currState.evaluateState()) ;
                        return currState.evaluateState();
                    }
                }

                for (State s: currState.getLegalMoves(1))
                 {
                     alpha = Math.max(alpha, AlphaBetaSearch(depth-1,s,alpha,beta,false));
                     if (beta <= alpha)
                         break;

                 }
                return alpha;
            }
            else
            {
                if (depth <= 0 || currState.getLegalMoves(2).size() == 1)
                {
                    if (currState.getLegalMoves(2).size() == 1)
                    {
                        currState.getLegalMoves(2).get(0).setStateScore(-10000);
                        return currState.getLegalMoves(2).get(0).evaluateState();
                    }
                    else
                    {

                        currState.setStateScore(currState.evaluateState()) ;
                        return currState.evaluateState();
                    }
                }

                for (State s: currState.getLegalMoves(2))
                {
                  beta = Math.min(beta, AlphaBetaSearch(depth-1, s,alpha, beta, true ));
                    if(beta <= alpha)
                        break;

                }
                return beta;
            }


    }

}
