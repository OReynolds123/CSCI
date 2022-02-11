package cs1302.game;

import cs1302.game.MinesweeperGame;
import cs1302.game.MinesweeperGenWriter;

/**
 * This class implements {@code MinesweeperGame} and {@code MinesweeperGenWriter} to run the
 * Minesweeper Alpha game or generate a seed file for it.
 */
public class MinesweeperDriver {
/**
 * The main method of the Minesweeper game. Either starts the game or creates a seed file. Takes in
 * '--seed' to run the game with a specified path or '--gen' to generate a seed file.
 *
 * @param args Reads the {@code args} for the seed and gen arguments.
 */
    public static void main(String [] args) {
        if (args.length == 2 && args[0].equals("--seed")) { // checks for --seed
            String seed = args[1];
            MinesweeperGame game = new MinesweeperGame(seed);
            game.play();
        } else if (args.length == 5 && args[0].equals("--gen")) { // checks for --gen
            MinesweeperGenWriter genCreator = new MinesweeperGenWriter();
            genCreator.genWriter(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]),
                                 Integer.parseInt(args[4]));
        } else { // invalid options
            System.err.println("Unable to interpret supplied command-line arguments.");
            System.exit(1);
        } // if
    } // main
} // MinesweeperDriver
