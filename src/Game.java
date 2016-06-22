/**
 * Created by Nigel on 6/21/16.
 */
public class Game {
    private Player player1;
    private Player player2;
    private Player currentPlayer = null;
    private int amount;

    //Was trying to get get current player from getCurrentPlayer method and got a null-pointer exception until I implemented this.
    public Game(Player player1, Player player2){
        this.player1 = currentPlayer = player1;
        this.player2 = player2;
    }
    public int getAmount(){
        return amount;
    }
    public String getCurrentPlayerName(){
        return currentPlayer.getName();
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    public void switchPlayer(){
        if (currentPlayer == player1){
            currentPlayer = player2;
        }
        else {
            currentPlayer = player1;
        }
    }
    public boolean declareLoser(int amountRemaining){
        boolean gameOver = false;
        if (amountRemaining == 1){
            switchPlayer();
            System.out.println(currentPlayer.getName() + " is the winner!");
            switchPlayer();
            System.out.println(currentPlayer.getName() + " is the loser! :'(");
            gameOver = true;
        }
        else if (amountRemaining == 0){
            System.out.println(currentPlayer.getName() + " is the winner!");
            switchPlayer();
            System.out.println(currentPlayer.getName() + " is the loser! :'(");
            gameOver = true;
        }
        return gameOver;
    }
}