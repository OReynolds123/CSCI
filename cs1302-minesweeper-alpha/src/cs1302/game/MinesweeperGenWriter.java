package cs1302.game;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 * The class responsible for generating a seed file.
 */
public class MinesweeperGenWriter {

    /**
     * Creates the seed file based on the main method's arguments following the '--gen'.
     *
     * @param path The path of where to write the contents of the seed file being generated.
     * @param rowSize The row dimension of the minesweeper board.
     * @param colSize The column dimension of the minesweeper board.
     * @param mineNum The number of mines to generate for the seed file.
     */
    void genWriter(String path, int rowSize, int colSize, int mineNum) {
        boolean [][] mineLoc = new boolean [rowSize][colSize];
        int mineAmnt = 0; // sets the amount of generated mines to 0
        for (int i = 0; i < mineLoc.length; i++) { // sets the mineLoc board to false
            for (int j = 0; j < mineLoc[i].length; j++) {
                mineLoc[i][j] = false;
            } // for
        } // for
        try {
            File genFile = new File(path);
            PrintWriter out = new PrintWriter(genFile);
            out.println(rowSize + " " + colSize); // writes the row and column size
            out.println(mineNum); // writes the amount of mines
            while (mineAmnt < mineNum) {
                int tempX = (int) (Math.random() * rowSize); // generate a row coordinate
                int tempY = (int) (Math.random() * colSize); // generate a column coordinate
                if (mineLoc[tempX][tempY] != true) { // check if it's already been made
                    mineLoc[tempX][tempY] = true;
                    out.println(tempX + " " + tempY); // print out the valid coordinate
                    mineAmnt++;
                } // if
            } // while
            out.close(); // close the PrintWriter
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe);
        } // try
    } // genWriter
} // MinesweeperGenWriter
