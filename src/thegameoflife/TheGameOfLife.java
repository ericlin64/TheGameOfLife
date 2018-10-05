/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thegameoflife;

import java.util.Arrays;
import java.util.Scanner;

public class TheGameOfLife {

    /*
     PSEUDOCODE: This is "Game of Life"
    
     initialize Scanner "in" to read input
     initialize boolean array "present" with 17 rows and 17 columns to store cells
     initialize boolean array "past" to store cell positions before they change
     initialize String input to store input
     initialize String array coordinates to store row and column values
    
     WHILE LOOP: loops until player inputs "stop"
     INPUT: player inputs coordinates of cells he wants populated (ex. 1 2 is array[1][2])
     store row and column values as 2 elements inside array coordinates
     set element of array present with the coordinate to true (meaning it is populated)
     END OF WHILE LOOP

     set array past value to array present

     CALL: call void "lonely" method
     CALL: call void "crowded" method
     CALL: call void "birth" method

     METHOD: countCells method takes in integer coordinates ("i" and "j") of array past and counts the number of cells around it (max is 8)
     initialize integer "count" to count the number of cells
     if past[i-1][j-1] exists and is true, add 1 to count
     if past[i-1][j] exists and is true, add 1 to count
     if past[i-1][j+1] exists and is true, add 1 to count
     if past[i][j-1] exists and is true, add 1 to count
     if past[i][j+1] exists and is true, add 1 to count
     if past[i+1][j-1] exists and is true, add 1 to count
     if past[i+1][j] exists and is true, add 1 to count
     if past[i+1][j+1] exists and is true, add 1 to count
     return value of count

     METHOD: lonely method takes in arrays present,past and returns nothing
     initialize integer "count" to count the number of cells
     FOR LOOP: loops "i" through number of rows in present, starting at 0 and increasing by 1
     FOR LOOP: loops "j" through number of columns in present, starting at 0 and increasing by 1
     set value of count to the return value of "countCells" method (give integer i,j, and array past)
     DECISION: checks if cell is lonely (1 or 0 neighbors)
     if the value of count is less than or equal to 1, then set the element of array past to false (cell is no longer populated)
     END OF FOR LOOP
     END OF FOR LOOP
     return method

     METHOD: crowded method takes in arrays present,past and returns nothing
     initialize integer "count" to count the number of cells
     FOR LOOP: loops "i" through number of rows in present, starting at 0 and increasing by 1
     FOR LOOP: loops "j" through number of columns in present, starting at 0 and increasing by 1
     set value of count to the return value of "countCells" method (give integer i,j, and array past)
     DECISION: checks if cell is crowded (4 to 8 neighbors)
     if the value of count is between 4 and 8, then set the element of array past to false (cell is no longer populated)
     END OF FOR LOOP
     END OF FOR LOOP
     return method

     METHOD: birth method takes in arrays present,past and returns nothing
     initialize integer "count" to count the number of cells
     FOR LOOP: loops "i" through number of rows in present, starting at 0 and increasing by 1
     FOR LOOP: loops "j" through number of columns in present, starting at 0 and increasing by 1
     set value of count to the return value of "countCells" method (give integer i,j, and array past)
     DECISION: checks if cell is has 3 neighbors
     if the value of count is 3, then set the element of array past to true (cell is now populated)
     END OF FOR LOOP
     END OF FOR LOOP
     return method

     //temporary output
     initialize integer array "outputArray" to 17 rows and 17 columns to print array past as 1's and 0's
     FOR LOOP: loops "i" through number of rows in present, starting at 0 and adding 1
     FOR LOOP: loops "j" through number of columns in present, starting at 0 and adding 1
     DECISION: checks if the element of array past is true(past[i][j])
     if true, set element of outputArray to "1" (outputArray[i][j] = 1)
     END OF FOR LOOP
     END OF FOR LOOP

     OUTPUT: output array outputArray
                    
     */
    /**
     * @param args the command line arguments
     */

    public static boolean board[][];
    private static boolean copyBoard[][];
    
    public static void game(String[] args) {
        // TODO code application logic here
        /*Scanner in = new Scanner(System.in);
        System.out.println("input rows + columns");
        row = in.nextInt();
        col = in.nextInt();
        board = new boolean[row][col];
        System.out.println("input grid (1 = true) (0 = false)");
        readInput(in);
        checkNeighbors();
        outputArray();*/
    }

    /*public static void readInput(Scanner in) {
        String boardRows = "";
        in.nextLine();
        for (int i = 0; i < row; i++) {
            boardRows = in.nextLine();
            for (int j = 0; j < col; j++) {
                if (boardRows.charAt(j) == '1') {
                    board[i][j] = true;
                }
            }
        }
    }

    public static void outputArray() {
        System.out.println("your grid");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j]) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }*/
    public static void setupBoard(int row, int col) {
        board = new boolean[row][col];
    }

    public static int countCells(int i, int j) {
        int count = 0;
        for (int a = -1; a <= 1; a++) {
            for (int b = -1; b <= 1; b++) {
                if (a == 0 && b == 0) {
                    continue;
                } else {
                    try {
                        if (copyBoard[i + a][j + b]) {
                            count++;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        continue;
                    }
                }
            }
        }
        return count;
    }
    
    public static void checkCoordinate(int mouseX,int mouseY,int row,int col,int drawingAreaWidth, int drawingAreaHeight){
        int boxWidth = drawingAreaWidth/col;
        int boxHeight = drawingAreaHeight/row;
        System.out.println(boxWidth + " " +boxHeight);
        for(int i=0;i<col;i++){
            if(mouseY >= (i*boxHeight) && mouseY <= (i*boxHeight)+boxHeight){
                for(int j=0;i<row;j++){
                    if(mouseX >= (i*boxWidth) && mouseX <= (i*boxWidth)+boxWidth)
                        System.out.println(i +" "+j);
                    break;
                        //board[i][j] = true;
                }
                break;
            }
        }
    }

    public static void checkNeighbors() {
        int row = board.length;
        int col = board.length;
        copyBoard = new boolean[row][col];
        //copy board into copyBoard
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                count = countCells(i, j);
                if (copyBoard[i][j]) {
                    if (count <= 1) {
                        board[i][j] = false;
                    } else if (count >= 4) {
                        board[i][j] = false;
                    } else //keeps true
                    {
                        board[i][j] = true;
                    }
                } else if (!copyBoard[i][j] && count == 3) {
                    board[i][j] = true;

                }
            }
        }
    }
}
