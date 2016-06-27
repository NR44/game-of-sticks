import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Nigel on 6/21/16.
 */
public class Game {
    File file = new File("players.json");
    private int totalSticks;
    private Player player1, player2, currentPlayer = null;
    Scanner scanner = new Scanner(System.in);
    public static final int STICKMAX = 100;
    public static final int STICKMIN = 10;
    public static final int SELECTMIN = 1;
    public static final int SELECTMAX = 3;
    String winnerName;
    String loserName;

    public Game(Player player1, Player player2) {
        this.player1 = currentPlayer = player1;
        this.player2 = player2;
    }

    public String setWinner(Player name) {
        return winnerName = name.getName();
    }

    public String setLoser(Player name) {
        return loserName = name.getName();
    }

    public String getWinner() {
        return winnerName;
    }

    public String getLoser() {
        return loserName;
    }

    public void runGame() {
        int choice;
        setBoard();
        System.out.println("Ok, let's play!");
        while (!declareLoser(totalSticks)) {
            choice = currentPlayer.makeChoice();
            checkChoice(choice);
            totalSticks -= choice;
            System.out.format("There are %d sticks remaining.\n", totalSticks);
            switchPlayer();
        }
    }

    public void setBoard() {
        int stickSelect = 0;
        while (stickSelect < STICKMIN || stickSelect > STICKMAX) {
            System.out.println("How many sticks will be on the table (10 - 100): ");
            stickSelect = Integer.parseInt(scanner.nextLine());
            if (stickSelect < STICKMIN || stickSelect > STICKMAX) {
                System.out.println("Nope, enter a number between 1 & 100.");
            }
        }
        totalSticks = stickSelect;
    }

    public void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public boolean declareLoser(int amountRemaining) {
        boolean gameOver = false;
        if (amountRemaining == 1) {
            switchPlayer();
            setWinner(currentPlayer);
            System.out.println(currentPlayer.getName() + " is the winner!");
            switchPlayer();
            setLoser(currentPlayer);
            System.out.println(currentPlayer.getName() + " is the loser! :'(");
            gameOver = true;
        } else if (amountRemaining == 0) {
            setWinner(currentPlayer);
            System.out.println(currentPlayer.getName() + " is the winner!");
            switchPlayer();
            setLoser(currentPlayer);
            System.out.println(currentPlayer.getName() + " is the loser! :'(");
            gameOver = true;
        }
        return gameOver;
    }

    public int checkChoice(int choice) {
        while ( choice < SELECTMIN || choice > SELECTMAX || choice > totalSticks) {
            if (choice > totalSticks) {
                System.out.format("There are only %d sticks. You cannot take more than that.\n", totalSticks);
                currentPlayer.makeChoice();
            } else if (choice < SELECTMIN || choice > SELECTMAX) {
                System.out.println("You can only take 1, 2 or 3 sticks.");
                currentPlayer.makeChoice();
            }
        }
        return choice;
    }

    public Map loadFile(Map players) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        fileScanner.useDelimiter("\\Z");
        String contents = fileScanner.next();

        //Parsing - String to Object (Map in this case)
        JsonParser parser = new JsonParser();
        players = parser.parse(contents, HashMap.class);

        return players;
    }

    public void saveFile (Map players) throws IOException {
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.serialize(players);
        FileWriter fw = new FileWriter(file);
        fw.write(json);
        fw.close();
    }

    public Map updatePlayers(Game game, Map<String, Integer> track){
        boolean winnerExist = false, loserExist = false;
        for (String name : track.keySet()){

            if (name.equalsIgnoreCase(game.getWinner()) ){
                winnerExist = true;
                track.put(name, track.get(name)+1);
            }else if (name.equalsIgnoreCase(game.getLoser())){
                loserExist = true;
                track.put(name, track.get(name) - 1);
            }
        }
        if (winnerExist == false){
            track.put(game.getWinner(), 1);
        }

        if (loserExist == false){
            track.put(game.getLoser(), 1);
        }
        if (track.isEmpty()){
            System.out.println("Record keeper array is blank.");
        }
        return track;
    }
}