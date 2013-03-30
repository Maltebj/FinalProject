import java.util.ArrayList;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Malte Bjarki
 * Date: 28.3.2013
 * Time: 13:31
 * To change this template use File | Settings | File Templates.
 */

public class State {

    //Each tile of the game board can belong to a player or be empty.
    //empty = 0
    //player 1 = 1
    //player 2 = 2
    private ArrayList<ArrayList<Integer>> m_board;
    private int m_stateScore;

    State(ArrayList<ArrayList<Integer>> board)
    {
        this.m_board = new ArrayList<ArrayList<Integer>>();
        for (int i= 0; i < board.size(); i++)
        {
            this.m_board.add(i, (ArrayList<Integer>)board.get(i).clone());

        }
    }

    State(int col, int row)
    {
        this.m_board = new ArrayList<ArrayList<Integer>>();
        for (int i = 0 ; i < row; i++)
        {
            this.m_board.add(i, new ArrayList<Integer>());
            for (int j = 0; j < col; j++)
            {
                if (i == 0 || i == 1)
                {
                   this. m_board.get(i).add(j, 2);
                }
                else if (i == row-1 || i == row-2)
                {
                    this.m_board.get(i).add(j,1);
                }
                else
                    this.m_board.get(i).add(j,0);
            }
        }

    }

    public ArrayList<ArrayList<Integer>> getBoard()
    {
        return this.m_board;
    }

    public int getStateScore()
    {
        return this.m_stateScore     ;
    }

    void setStateScore(int stateScore)
    {
       m_stateScore = stateScore;
    }





    public  ArrayList<State> getLegalMoves(int player)
    {
        boolean won = false;
        ArrayList<State> moves = new ArrayList<State>();
        for (int i = 0; i < this.m_board.size(); i++)
        {
            for (int j = 0; j < this.m_board.get(i).size(); j++)
            {
                if (player == 1)
                {
                    if (this.m_board.get(i).get(j) == 1)
                    {

                        //check if empty in front
                        if(i > 0)
                        {

                            if (this.m_board.get(i-1).get(j) == 0)
                            {
                               State nextMove = new State(this.m_board);
                               nextMove.getBoard().get(i).set(j,0);
                               nextMove.getBoard().get(i-1).set(j,1);
                                if (i-1 == 0)
                                {
                                    moves.clear();
                                    moves.add(nextMove);
                                    return moves;
                                }
                               moves.add(nextMove);
                              // nextMove.printState();
                            }
                            //check if enemy or empty to the left
                             if(j > 0)
                            {
                                if (this.m_board.get(i-1).get(j-1) == 0 || this.m_board.get(i-1).get(j-1) == 2 )
                                {
                                    State nextMove = new State(this.m_board);
                                    nextMove.getBoard().get(i).set(j,0);
                                    nextMove.getBoard().get(i-1).set(j-1,1);
                                    moves.add(nextMove);
                                }
                            }
                            //check if enemy or empty to the right
                             if( j < m_board.get(i).size()-1)
                            {
                                if (this.m_board.get(i-1).get(j+1) == 0 || this.m_board.get(i-1).get(j+1) == 2 )
                                {
                                    State nextMove = new State(this.m_board);
                                    nextMove.getBoard().get(i).set(j,0);
                                    nextMove.getBoard().get(i-1).set(j+1,1);
                                    moves.add(nextMove);
                                }
                            }
                        }
                    }
                }
                else if(player == 2)
                {
                    if (this.m_board.get(i).get(j) == 2)
                    {



                        //check if empty in front
                        if(i < this.m_board.get(i).size()-1)
                        {
                            if (this.m_board.get(i+1).get(j) == 0)
                            {
                                State nextMove = new State(this.m_board);
                                nextMove.getBoard().get(i).set(j,0);
                                nextMove.getBoard().get(i+1).set(j,2);
                                moves.add(nextMove);
                            }
                            //check if enemy or empty to the left
                             if(j > 0)
                            {
                                if (this.m_board.get(i+1).get(j-1) == 0 || this.m_board.get(i+1).get(j-1) == 1 )
                                {
                                    State nextMove = new State(this.m_board);

                                    nextMove.getBoard().get(i).set(j,0);
                                    nextMove.getBoard().get(i+1).set(j-1,2);
                                    moves.add(nextMove);
                                }
                            }
                            //check if enemy or empty to the right
                             if( j < m_board.size()-1)
                            {
                                if (this.m_board.get(i+1).get(j+1) == 0 || this.m_board.get(i+1).get(j+1) == 1 )
                                {
                                    State nextMove = new State(this.m_board);

                                    nextMove.getBoard().get(i).set(j,0);
                                    nextMove.getBoard().get(i+1).set(j+1,2);
                                    moves.add(nextMove);
                                }
                            }
                        }
                    }
                }
            }
        }

        return moves;
    }

    public int evaluateState()
    {
        int counter =0;
        for (int i = 0; i < this.m_board.size(); i++)
        {
            for (int j = 0; j < this.m_board.get(i).size(); j++)
            {
               if (this.m_board.get(i).get(j) == 1)
               {
                   //win check
                   if (i == 0)
                       return 1000;
                   counter++;
               }
                else if (this.m_board.get(i).get(j) == 2)
               {
                   if (i == m_board.size()-1)
                       return -1000;
                   counter--;
               }
            }
        }
        return counter;
    }

    public void printState()
    {
        for (int i = 0; i < this.m_board.size(); i++)
        {

            for (int j = 0; j < this.m_board.get(i).size(); j++)
            {
                System.out.print(this.m_board.get(i).get(j)+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }


}
