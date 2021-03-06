import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

/**
 * Created by Nigel on 6/21/16.
 */
public class Sticks {
    public static void main(String[] args) throws Exception {
        HumanPlayer humanPlayer1 = new HumanPlayer("Nigel");
        HumanPlayer humanPlayer2 = new HumanPlayer("Jamal");
        DumbAI dumbAI1 = new DumbAI("JEDIEN");
        DumbAI dumbAI2 = new DumbAI("NYLA");
        Scanner scanner = new Scanner(System.in);
        Game game = null;
        Map<String, Integer> tracker = new HashMap<>();
        
        System.out.println("Which game mode would you like to play?" + System.lineSeparator() + "1. Human vs Human" + System.lineSeparator() + "2. Human vs AI" + System.lineSeparator() + "3. AI vs AI");
        int mode = Integer.parseInt(scanner.nextLine());

        do {
            switch (mode) {
                case 1:
                    game = new Game(humanPlayer1, humanPlayer2);
                    break;
                case 2:
                    game = new Game(humanPlayer1, dumbAI1);
                    break;
                case 3:
                    game = new Game(dumbAI1, dumbAI2);
                    break;
                default:
                    System.out.println("Your choice must be 1, 2, or 3.");
            }
        } while (mode < 1 || mode > 3);
        game.runGame();
        tracker = game.loadFile(tracker);
        game.updatePlayers(game, tracker);
        game.saveFile(tracker);
    }
}