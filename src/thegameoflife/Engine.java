package thegameoflife;

import java.util.Arrays;

public class Engine {

    /**
     * This program is called "Game of Life"
     *
     * @author Eric Lin
     * @course ICS4U
     *
     * The Engine class provides and updates information of board to other
     * classes
     * 
     * PSEUDOCODE FOR searching algorithm(count cells):
     * 
     *      METHOD: takes in row "i" and column "j" of cell
     *      initialize count
     *      FOR LOOP: loops from behind to ahead of i, starting at -1 and counting by 1
     * 
     *          FOR LOOP: loops from behind to ahead of j, starting at -1 and counting by 1
     * 
     *              DECISION: if the loop checks on top of the cell, do nothing because it's not supposed to be counted
     *                        else, run try and catch statement to test for ArrayOutOfBounds.
     *                        TRY: is coordinate of cell empty? If so, increase count by 1.
     *                        CATCH: does coordinate not exist? If so, do nothing
     * 
     *          END OF FOR LOOP
     * 
     *      END OF FOR LOOP
     * 
     *      RETURN: value of count
     */
    
    //board array provides the information of which cells are TRUE(occupied) or FALSE(vacant)
    //first subscript is rows, second is columns
    public static boolean board[][];
    //copyBoard makes a copy of board for reference (so that the changes in board are independent)
    //if copyBoard detects a change in a cell, board is updated with the change
    private static boolean copyBoard[][];
    //stores number of rows and columns in board
    public static int row;
    public static int col;
    
    /**
     * setupBoard is called by MainFrame to update the number of rows and columns in board. setupBoard is called
     * at the beginning of the program to set a default dimension for board
     * @param getRow gets the number of rows for board
     * @param getCol gets the number of columns for board
     */
    public static void setupBoard(int getRow, int getCol){
        row = getRow;
        col = getCol;
        //initializes board with a new number of rows and columns
        Engine.board = new boolean[row][col];
    }
            
    
    /**
     *
     * Method fillCell updates board according to the cells that have been
     * clicked on the DrawingArea JPanel. MainFrame detects when there is a
     * mouse click and sends the information regarding the mouse and DrawingArea
     * to fillCell.
     *
     * @param mouseX MainFrame sends the x-coordinate of the mouse click
     * location
     * @param mouseY sends the y-coordinate of the mouse click
     * @param drawingAreaWidth sends the width of DrawingArea's JPanel
     * @param drawingAreaHeight sends the height of DrawingArea
     */
    public static void fillCell(int mouseX, int mouseY, int drawingAreaWidth, int drawingAreaHeight) {
        //gets the width and height of each cell in DrawingArea
        //does drawingAreaWidth/col since both variables deal with the x-axis
        int boxWidth = drawingAreaWidth / col;
        //does drawingAreaHeight/row since both variables deal with the y-axis
        int boxHeight = drawingAreaHeight / row;
        //checks to see if the cell where the mouse clicked has been found
        //if so, cellFound is set to true, breaking the for loops to prevent needless searching
        boolean cellFound = false;

        //loops through the number of rows in board
        //"i" represents the row
        for (int i = 0; i < row; i++) {
            //checks to see which row the mouse click was in
            //sees if the mouse click was between the top and bottom side of the cell
            if (mouseY >= (i * boxHeight) && mouseY <= (i * boxHeight) + boxHeight) {

                //loops through the number of columns in board
                //"j" represents the column
                for (int j = 0; j < col; j++) {
                    //checks to see which column the mouse click was in
                    //sees if the mouse click was between the left and right side of the cell
                    if (mouseX >= (j * boxWidth) && mouseX <= (j * boxWidth) + boxWidth) {
                        //now that we found which row and column the mouse click was in, we can simply update the element in board with that-
                        //-row and column
                        //if element of board is true, set it to false and vice-versa
                        board[i][j] = !board[i][j];
                        //boolean helps break both for loops
                        cellFound = true;
                        //since the cell has already been found and updated, we no longer need to search
                        break;
                    }

                }//end of for loop

                //cell has been found
                if (cellFound) {
                    break;
                }
            }

        }//end of for loop

    }

    /**
     * checkNeighbors takes board and updates it according to the Game of
     * Life rules. It makes a reference copy of board, checks if cells change,
     * and then updates board with the changes
     */
    public static void checkNeighbors() {
        //counts the number of cells around each cell in the grid
        int count;
        //copyBoard makes a refernce copy of board
        copyBoard = new boolean[row][col];
        
        //copy board into copyBoard
        for (int i = 0; i < row; i++) {
            System.arraycopy(board[i], 0, copyBoard[i], 0, col);
        }
        
        //loops through rows in copyBoard
        for (int i = 0; i < row; i++) {
            //loops through columns in copyBoard
            for (int j = 0; j < col; j++) {
                //calls countCells to count cells around a specific cell
                //sends the row and column of the cell
                count = countCells(i, j);
                //checks if the cell is occupied or vacant
                if (copyBoard[i][j]) {
                    //if the cell is occupied, determine if it will die
                    //Rules: if the cell has 1,no neighbors, or more than 4 neighbors, it dies
                    if (count <= 1) {
                        board[i][j] = false;
                    } else {
                        board[i][j] = count < 4; //keeps true
                    }
                } //if the cell is vacant, determine if it get occupied
                //if the empty cell has 3 neighbors, it gets occupied
                else if (!copyBoard[i][j] && count == 3) {
                    board[i][j] = true;
                }

            }//end of for loop

        }//end of for loop

    }

    /**
     * countCells is called by checkNeighbors. It takes in the row and column of a specific cell, checks and returns
     * the number of occupied cells around said cell
     * @param i the row of the specified cell
     * @param j the column of the specified cell
     * @return the number of occupied cells around the specified cell
     */
    public static int countCells(int i, int j) {
        //counts the number of occupied cells around
        //count is returned to checkNeighbors
        int count = 0;
        
        //loops from the row behind the specified cell to the row ahead of it
        //checks cells above, below, and between the cell
        for (int a = -1; a <= 1; a++) {
            
            //loops from the column behind the cell to the column ahead of it
            //checks cells next to the cell
            for (int b = -1; b <= 1; b++) {
                //the cell is not supposed to check itself, so we skip the iteration when it tries to
                if (a == 0 && b == 0) {

                } 
                //every other case we check the cell
                else {
                    //try and catch is used in the case that the cell is against the boundaries of board
                    try {
                        //if the program finds an occupied cell, increase count by 1
                        if (copyBoard[i + a][j + b]) {
                            count++;
                        }
                    } 
                    //if the program checks a non-existent cell, do nothing
                    catch (IndexOutOfBoundsException e) {
                    }
                }
                
            }//end of for loop
            
        }//end of for loop
        
        //return the number of occupied cells
        return count;
    }
    
    /**
     * setPattern is called by MainFrame. Depending on the pattern selected, setPattern sets the pattern arrangement within board.
     * The patterns are created in the middle of the grid by using a the middle coordinates of the grid as a reference point.
     * @param pattern lists what the method will do depending on the pattern selected.
     */
    public static void setPattern(String pattern){
        //gets the coordinates for the middle of the grid
        //-1 since array starts counting at 0
        int center = row/2-1;
        switch(pattern){
            //clears the board
            case "Clear":
                break;
            //below lists the board changes for each pattern
            //test them in the program to see what they are
            //pattern diagonally goes off the board
            case "Glider":
                board[center-1][center] = true;
                board[center][center+1] = true;
                board[center+1][center-1] = true;
                board[center+1][center] = true;
                board[center+1][center+1] = true;
                break;
            //pattern turns into a "flower"
            case "Flower":
                board[center-2][center] = true;
                board[center+2][center] = true;
                for(int i=-2;i<=2;i++){
                    board[center+i][center-2] = true;
                    board[center+i][center+2] = true;
                }
                break;
            //pattern turns into 4 blinkers 
            case "Shuriken":
                board[center][center] = true;
                board[center-1][center] = true;
                board[center][center-1] = true;
                board[center][center+1] = true;
                board[center+1][center] = true;
                break;
            //pattern starts off as 10 cells in a row
            case "Ten":
                for(int i=-5;i<5;i++)
                    board[center][center+i] = true;
                break;
            //pattern horizonally goes off the screen
            case "Fireball":
                board[center-1][center-1] = true;
                board[center+1][center-1] = true;
                board[center+1][center+2] = true;
                for(int i=0;i<4;i++){
                    board[center-2][center+i] = true;
                    if(i!=3)
                        board[center-i][center+3] = true;
                }
                break;
            //pattern is a pair of "shoes" that always are symmetrical
            case "Tumbler":
                for(int i=1;i<=2;i++){
                    board[center-i][center-2] = true;
                    board[center-i][center+2] = true;
                    board[center+i][center-3] = true;
                    board[center+i][center+3] = true;
                    board[center+3][center-i-1] = true;
                    board[center+3][center+i+1] = true;
                }
                for(int i=-2;i<=2;i++){
                    board[center+i][center-1] = true;
                    board[center+i][center+1] = true;
                }
                break;
            //pattern shoots gliders
            case "Glider Gun":
                for(int i=0;i<2;i++){
                    board[center-5][center-17-i] = true;
                    board[center-6][center-17-i] = true;
                    board[center-7][center+16+i] = true;
                    board[center-8][center+16+i] = true;
                    board[center-3-i][center-i] = true;
                    board[center-i][center+19-i] = true;
                    board[center+5+i][center+6+i] = true;
                    board[center-4-i][center-10] = true;
                    board[center-5-i][center-8] = true;
                    board[center-6-i][center+4] = true;
                    board[center-7-i][center+6] = true;
                    board[center-2-(2*(i+1))][center-9] = true;
                    board[center-4-(2*(i+1))][center+5] = true;
                }
                for(int i=0;i<3;i++){
                    board[center-2-i][center-2] = true;
                    board[center-1+i][center+17] = true;
                    board[center+4][center+6+i] = true;
                }
                break;
        }//end of switch statement
    }

    /**
     * checkEmpty is called by DrawingArea to check if board is empty
     * @return true(board is empty) or false(board isn't empty)
     */
    public static boolean checkEmpty(){
        //sees if board matches an empty string with the same number of rows and columns
        boolean[][] emptyString = new boolean[row][col];
        return Arrays.deepEquals(board, emptyString);
    }
}
