package cs1302.game;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

/**
 * Instances of this class represent a game of Minesweeper Alpha.
 */
public class MinesweeperGame {
    int rowSize;
    int colSize;
    int mineNum;
    String [][] board;
    boolean [][] mineLoc;
    int round;
    double score;
    boolean nofogActive;
    String fileName;
    Scanner mainScanner = new Scanner(System.in);

/**
 * Sets up the game based on a seed file that is read for the {@code rowSize}, {@code colSize},
 * {@code mineNum}, and the location of each mine, {@code mineLoc}.
 *
 * @param seed The location of the seed file to read for the configuration of the game.
 */
    MinesweeperGame(String seed) {
        try {
            File seedFile = new File(seed); // create a file object of the seed
            Scanner configScanner = new Scanner(seedFile); // creating the object to read the file
            this.rowSize = configScanner.nextInt(); // row size
            this.colSize = configScanner.nextInt(); // column size
            this.mineNum = configScanner.nextInt(); // number of mines
            if (rowSize < 5 || colSize < 5) { // check if row or column size is too small
                System.out.println("");
                System.err.println("Seedfile Value Error: Cannot create a mine field with that many"
                                   + " rows and/or columns!");
                System.exit(3);
            } // if
            if (mineNum > (rowSize * colSize) || mineNum < 0) { // if there are too many mines
                System.err.println("Seedfile Format Error: Cannot create game with " + seed + ", "
                                   + "because it is not formatted correctly.");
                System.out.println("");
                System.exit(1);
            } // if
            board = new String[this.rowSize][this.colSize]; // setting the board size
            mineLoc = new boolean[this.rowSize][this.colSize]; // setting the mineLoc size
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j] = ""; // setting up the board array
                    mineLoc[i][j] = false; // setting up the mineLoc array
                } // for
            } // for
            this.round = 0; // set the round to 0
            this.score = 0; // set the score to 0
            this.nofogActive = false; // nofog is not activated
            for (int i = 0; i < this.mineNum; i++) { // getting the coordinates of the mines
                int tempLocRow = configScanner.nextInt();
                int tempLocCol = configScanner.nextInt();
                mineLoc[tempLocRow][tempLocCol] = true;
            } // for
            if (configScanner.hasNextInt() == true) { // check if there are too many mine locations
                System.err.println("Seedfile Format Error: Cannot create game with " + seed + ", "
                                   + "because it is not formatted correctly.");
                System.out.println("");
                System.exit(1);
            }
            configScanner.close(); // close the configScanner
        } catch (FileNotFoundException fnfe) { // file not found
            System.err.println("Seedfile Not Found Error: Cannot create game with " + seed + ""
                               + ", because it cannot be found");
            System.err.println("                          or cannot be read due to permission.");
            System.out.println("");
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException aioobe) { // seed not formatted correctly
            System.err.println("Seedfile Format Error: Cannot create game with " + seed + ", "
                               + "because it is not formatted correctly.");
            System.out.println("");
            System.exit(1);
        } catch (NoSuchElementException nsee) { // seed not formatted correctly
            System.err.println("Seedfile Format Error: Cannot create game with " + seed + ", "
                               + "because it is not formatted correctly.");
            System.out.println("");
            System.exit(1);
        } // try
    } // MinesweeperGame

    /**
     * Prints the welcome banner page.
     */
    void printWelcome() {
        System.out.println("");
        System.out.println("        _");
        System.out.println("  /\\/\\ (_)_ __   ___  _____      _____  ___ _ __   ___ _ __");
        System.out.println(" /    \\| | '_ \\ / _ \\/ __\\ \\ /\\ / / _ \\/ _ \\ '_ \\ / _ \\ '__|"
        );
        System.out.println("/ /\\/\\ \\ | | | |  __/\\__ \\\\ V  V /  __/  __/ |_) |  __/ |");
        System.out.println("\\/    \\/_|_| |_|\\___||___/ \\_/\\_/ \\___|\\___| .__/ \\___|_|");
        System.out.println("                 A L P H A   E D I T I O N |_| v2020.sp");
        System.out.println("");
    } // printWelcome

    /**
     * Prints the current contents of the minefield upon each call.
     * The row and column size are defined by {@code rowSize} and {@code colSize}.
     */
    void printMineField() {
        String colPadding = "%4s"; // default column padding
        String rowPadding = "%4s"; // default row padding
        System.out.println("");
        System.out.println(" Rounds Completed: " + round);
        System.out.println("");
        if (colSize > 10) {
            colPadding = "%5s";
        } // if
        if (rowSize > 10) {
            rowPadding = "%5s";
        } // if
        for (int i = 0; i < rowSize; i++) {
            System.out.printf(rowPadding, i + " |"); // prints the side column of numbers
            for (int j = 0; j < colSize; j++ ) {
                System.out.printf(colPadding, board[i][j] + "|"); // prints the contents of each row
            } // for
            System.out.println("");
        } // for
        if (rowSize > 10) {
            System.out.print("   ");
        } else {
            System.out.print("  ");
        } // if
        for (int i = 0; i < colSize; i++) {
            System.out.printf(colPadding, i); // prints the last row of numbers
        } // for
        System.out.println("\n");
        System.out.print("minesweeper-alpha: ");
    } // printMineField

    /**
     * Prompts the user for an input. The user can {@code reveal} a square, {@code mark} a square,
     * {@code guess} a square, ask for {@code help} to show all of the valid commands, {@code quit}
     * the game, and use the {@code nofog} command to show all of the squares with a mine for one
     * round.
     */
    void promptUser() {
        String [] input = new String [3]; // an array for the inputs to be stored in
        String trimmedInput = ((mainScanner.nextLine()).trim()).replaceAll("( )+", " "); // trims
        input = trimmedInput.split(" "); // stores each input in the array
        boolean validCommand = false;

        if (input.length == 3) { // check for 3 entered items
            try {
                int tempX = Integer.parseInt(input[1]); // try to read the row location
                int tempY = Integer.parseInt(input[2]); // try to read the column location
                if (isInBounds(tempX, tempY) == true) { // check if the locations are in bounds
                    if (input[0].equals("reveal") || input[0].equals("r")) { // reveal
                        reveal(tempX, tempY);
                        validCommand = true;
                    } else if (input[0].equals("guess") || input[0].equals("g")) { // guess
                        guess(tempX, tempY);
                        validCommand = true;
                    } else if (input[0].equals("mark") || input[0].equals("m")) { // mark
                        mark(tempX, tempY);
                        validCommand = true;
                    } // if
                } // if
            } catch (NumberFormatException nfe) { // anything entered other than an int
                System.out.println("");
                System.out.println("Input Error: Command not recognized!");
                validCommand = true;
            } // try catch
        } else if (input.length == 1) { // check for 1 entered item
            if (input[0].equals("help") || input[0].equals("h")) {
                help(); // help
                validCommand = true;
            } else if (input[0].equals("quit") || input[0].equals("q")) {
                mainScanner.close();
                quit(); // quit
            } else if (input[0].equals("nofog")) {
                nofog(); // nofog
                validCommand = true;
            } // if
        } // if
        if (validCommand == false) { // invalid command
            System.out.println("");
            System.out.println("Input Error: Command not recognized!");
        } // if
        if (isWon() == true) { // checks if the game is won
            printWin();
        } else if (isLost() == true) { // checks if the game is lost
            printLost();
        } // if
    } // promptUser

    /**
     * Checks to see if the game is won.
     *
     * @return True if all squares containing a mine are marked as definitely containing a mine
     * and all squares not containing a mine are revealed; false otherwise.
     */
    boolean isWon() {
        int correctMines = 0;
        int incorrectMines = 0;
        int filledSpaces = 0;
        for (int i = 0; i < mineLoc.length; i++) {
            for (int j = 0; j < mineLoc[i].length; j++) { // reads through the board
                if (board[i][j].trim().equals("F") && mineLoc[i][j] == true) {
                    correctMines++; // if the mine is correctly located on the board with 'F'
                } // if
                if (board[i][j].trim().equals("F") && mineLoc[i][j] == false) {
                    incorrectMines++; // if the mine is incorrectly located on the board with 'F'
                } // if
                if (board[i][j].trim().matches("[^\\?]+") == true) {
                    filledSpaces++; // if all of the spaces on the board are revealed
                } // if
            } // for
        } // for
        if (correctMines == mineNum && incorrectMines == 0 &&
            filledSpaces == (this.colSize * this.rowSize)) {
            return true;
        } else {
            return false;
        } // if
    } // isWon

    /**
     * Checks to see if the game is lost.
     *
     * @return True if a square containing a mine is revealed; false otherwise.
     */
    boolean isLost() {
        for (int i = 0; i < mineLoc.length; i++) {
            for (int j = 0; j < mineLoc[i].length; j++) { // reads through the board
                if (board[i][j].trim().matches("[*0-9]") && mineLoc[i][j] == true) {
                    return true; // if a board space has a number and has a mine
                } // if
            } // for
        } // for
        return false;
    } // isLost

    /**
     * Prints the win screen with a {@code score}.
     * The score is calculated by (100 * {@code rowSize} * {@code colSize}) / {@code round}.
     */
    void printWin() {
        this.score = (100.0 * this.rowSize * this.colSize) / this.round;
        if (this.score >= 100) {
            this.score = 100;
        } // if
        if (this.score <= 0) {
            this.score = 0;
        } // if
        System.out.println("");
        System.out.println(" ░░░░░░░░░▄░░░░░░░░░░░░░░▄░░░░ \"So Doge\"");
        System.out.println(" ░░░░░░░░▌▒█░░░░░░░░░░░▄▀▒▌░░░");
        System.out.println(" ░░░░░░░░▌▒▒█░░░░░░░░▄▀▒▒▒▐░░░ \"Such Score\"");
        System.out.println(" ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐░░░");
        System.out.println(" ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐░░░ \"Much Minesweeping\"");
        System.out.println(" ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌░░░");
        System.out.println(" ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒▌░░ \"Wow\"");
        System.out.println(" ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐░░");
        System.out.println(" ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄▌░");
        System.out.println(" ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▌░");
        System.out.println(" ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒▐░");
        System.out.println(" ▐▒▒▐▀▐▀▒░▄▄▒▄▒▒▒▒▒▒░▒░▒░▒▒▒▒▌");
        System.out.println(" ▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒▒▒░▒░▒░▒▒▐░");
        System.out.println(" ░▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒░▒░▒░▒░▒▒▒▌░");
        System.out.println(" ░▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▒▄▒▒▐░░");
        System.out.println(" ░░▀▄▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▄▒▒▒▒▌░░");
        System.out.println(" ░░░░▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀░░░ CONGRATULATIONS!");
        System.out.println(" ░░░░░░▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀░░░░░ YOU HAVE WON!");
        System.out.printf(" ░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▀▀░░░░░░░░ SCORE: %.2f\n", this.score);
        System.out.println("");
        System.exit(0);
    } // printWin

    /**
     * Prints the lost screen.
     */
    void printLost() {
        System.out.println("");
        System.out.println(" Oh no... You revealed a mine!");
        System.out.println("  __ _  __ _ _ __ ___   ___    _____   _____ _ __");
        System.out.println(" / _` |/ _` | '_ ` _ \\ / _ \\  / _ \\ \\ / / _ \\ '__|");
        System.out.println("| (_| | (_| | | | | | |  __/ | (_) \\ V /  __/ |");
        System.out.println(" \\__, |\\__,_|_| |_| |_|\\___|  \\___/ \\_/ \\___|_|");
        System.out.println(" |___/");
        System.out.println("");
        System.exit(0);
    } // printLost

    /**
     * This method is the main game loop. Upon first call from the MinesweeperDriver class, the
     * {@code printWelcome} method is invoked. The {@code printMineField} method, the
     * {@code checkNofog} method, and the {@code promptUser} method are called every round.
     */
    void play() {
        printWelcome(); // prints the welcome page
        boolean flag = true;
        while (flag) {
            printMineField(); // prints the minefield
            checkNofog(); // checks if nofog was just played
            promptUser(); // prompts the user
        } // while
    } // play

    /**
     * Reveals a square on the minefield. Calls the {@code getNumAdjMines} method and places the
     * returned number in the square.
     *
     * @param tempX The row coordinate of the square to reveal.
     * @param tempY The column coordinate of the square to reveal.
     */
    private void reveal(int tempX, int tempY) {
        board[tempX][tempY] = (Integer.toString(getNumAdjMines(tempX, tempY))) + " ";
        this.round++; // increment round
    } // reveal

    /**
     * Marks a square on the minefield with 'F'.
     *
     * @param tempX The row coordinate of the square to mark.
     * @param tempY The column coordinate of the square to mark.
     */
    private void mark(int tempX, int tempY) {
        board[tempX][tempY] = "F ";
        this.round++; // increment round
    } // mark

    /**
     * Places a '?' on a square on the minefield.
     *
     * @param tempX The row coordinate of the square to guess.
     * @param tempY The column coordinate of the square to guess.
     */
    private void guess(int tempX, int tempY) {
        board[tempX][tempY] = "? ";
        this.round++; // increment round
    } // guess

    /**
     * Displays the valid commands for a user to enter while playing the game.
     */
    private void help() {
        System.out.println("");
        System.out.println("Commands Available...");
        System.out.println("  - Reveal: r/reveal row col");
        System.out.println("  -   Mark: m/mark   row col");
        System.out.println("  -  Guess: g/guess  row col");
        System.out.println("  -   Help: h/help");
        System.out.println("  -   Quit: q/quit");
        this.round++; // increment round
    } // help

    /**
     * Quits the game.
     */
    private void quit() {
        System.out.println("");
        System.out.println("Quitting the game...");
        System.out.println("Bye!");
        System.out.println("");
        System.exit(0);
    } // quit

    /**
     * A cheat code invoked by the user to display all of the mines on the minefield for one round.
     * Sets the {@code nofogActive} boolean to true.
     */
    private void nofog() {
        this.nofogActive = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (mineLoc[i][j] == true) {
                    if (this.colSize < 11) {
                        if (board[i][j].trim().equals("F") || board[i][j].trim().equals("?")) {
                            board[i][j] = "<" + board[i][j].trim() + ">";
                        } else {
                            board[i][j] = "<" + " " + ">";
                        } // if
                    } else {
                        if (board[i][j].trim().equals("F") || board[i][j].trim().equals("?")) {
                            board[i][j] = "<" + " " + board[i][j].trim() + ">";
                        } else {
                            board[i][j] = "<" + "  " + ">";
                        } // if
                    } // if
                } // if
            } // for
        } // for
        this.round++; // increment round
    } // nofog

    /**
     * Checks to see if the {@code nofog} command has been used in the past round by using the
     * {@code nofogActive} boolean. If {@code nofogActive} is true, the board is reset to its
     * previous state. Otherwise, it does nothing.
     */
    private void checkNofog() {
        if (this.nofogActive = true) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j].equals("<F>") || board[i][j].equals("< F>")) {
                        board[i][j] = "F ";
                    } else if (board[i][j].equals("<?>") || board[i][j].equals("< ?>")) {
                        board[i][j] = "? ";
                    } else if (board[i][j].equals("< >") || board[i][j].equals("<  >")) {
                        board[i][j] = "";
                    } // if
                } // for
            } // for
            this.nofogActive = false;
        } // if
    } //checkNofog

    /**
     * Returns the number of mines adjacent to the specified square in the grid.
     *
     * @param row The row index of the square
     * @param col The column index of the square
     * @return The number of adjacent mines
     */
    private int getNumAdjMines(int row, int col) {
        int adjMineCount = 0;
        if ((isInBounds(row - 1, col) == true) && (mineLoc[row - 1][col] == true)) {
            adjMineCount++;
        }
        if ((isInBounds(row + 1, col) == true) && (mineLoc[row + 1][col] == true)) {
            adjMineCount++;
        }
        if ((isInBounds(row, col - 1) == true) && (mineLoc[row][col - 1] == true)) {
            adjMineCount++;
        }
        if ((isInBounds(row, col + 1) == true) && (mineLoc[row][col + 1] == true)) {
            adjMineCount++;
        }
        if ((isInBounds(row - 1, col - 1) == true) && (mineLoc[row - 1][col - 1] == true)) {
            adjMineCount++;
        }
        if ((isInBounds(row - 1, col + 1) == true) && (mineLoc[row - 1][col + 1] == true)) {
            adjMineCount++;
        }
        if ((isInBounds(row + 1, col - 1) == true) && (mineLoc[row + 1][col - 1] == true)) {
            adjMineCount++;
        }
        if ((isInBounds(row + 1, col + 1) == true) && (mineLoc[row + 1][col + 1] == true)) {
            adjMineCount++;
        }
        return adjMineCount;
    } // getNumAdjMines

    /**
     * Indicates whether or not the square is in the game grid.
     *
     * @param row the row index of the square
     * @param col the column index of the square
     * @return true if the square is in the game grid; false otherwise
     */
    private boolean isInBounds(int row, int col) {
        boolean inBounds = true;
        if (row < 0 || row >= this.rowSize || col < 0 || col >= this.colSize) {
            inBounds = false;
        } // if
        return inBounds;
    } // isInBounds

} // MinesweeperGame
