import java.util.Scanner;

/**
 * Created by Nigel on 6/21/16.
 */
public class Sticks {
    public static void main(String[] args) {
        Player player1 = new Player();
        Player player2 = new Player();
        Game sticks = new Game(player1, player2);

        int stickSelect = 0;
        Scanner input = new Scanner(System.in);
        player1.setName("Player 1");
        player2.setName("Player 2");

        System.out.println("Welcome to my Game of Sticks!");
            //Check that total number of stick is between 10 and 100.
            while (!(stickSelect >= 10 && stickSelect <= 100)) {
                System.out.println("How many sticks will be on the table (10 - 100): ");
                stickSelect = Integer.parseInt(input.nextLine());
                if (stickSelect < 10 || stickSelect > 100) {
                    System.out.println("Nope, enter a number between 1 & 100.");
                }
            }
            sticks.setAmount(stickSelect); //Set the total amount of sticks on the board.
        do {
            String currentPlayerName;
            int playerSelect = 0;

            //Allow and check that current player takes at least 1 stick and no more than 3 and no more the the remaining number of sticks.
            while (!(playerSelect >= 1 && playerSelect <= 3 && playerSelect <= sticks.getAmount())) {
                currentPlayerName = sticks.getCurrentPlayerName();
                System.out.println(currentPlayerName + ": How many sticks do you take (1-3)? ");
                playerSelect = Integer.parseInt(input.nextLine());
                if (playerSelect > sticks.getAmount()){
                    System.out.format("There are only %d sticks. You cannot take more than that.\n", sticks.getAmount());
                }
                else if (!(playerSelect >= 1 && playerSelect <= 3)) {
                    System.out.println("You can only take 1, 2 or 3 sticks.");
                }
            }
            sticks.setAmount(sticks.getAmount()-playerSelect);
            System.out.format("There are %d sticks remaining.\n", sticks.getAmount());
            sticks.switchPlayer();
        }while(!(sticks.declareLoser(sticks.getAmount())));
    }
}