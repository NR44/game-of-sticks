import java.util.Scanner;

/**
 * Created by Nigel on 6/21/16.
 */
public class HumanPlayer extends AbstractPlayer {
    private Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String name){
        super(name);
    }

    @Override
    public int makeChoice() {
        int choice = 0;
        while (choice < 1 || choice > 3) {
            System.out.println(getName() + ": How many sticks do you take (1-3)? ");
            choice =Integer.parseInt(scanner.nextLine());
        }
        return choice;
    }
}