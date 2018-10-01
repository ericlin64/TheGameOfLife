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
    static int row = 0;
    static int col = 0;
    static boolean[][] oldBoard;
    static boolean[][] newBoard;
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);

        readInput(in);
        checkNeighbors();
        //lonely();
        //crowded();
        //birth();
        //System.out.println(Arrays.deepToString(oldBoard));
        //System.out.println(Arrays.deepToString(newBoard));
        //temporary
        System.out.println(Arrays.deepToString(oldBoard));
        System.out.println(Arrays.deepToString(newBoard));
        outputArray();
    }

    public static void readInput(Scanner in) {
        String boardRows = "";
        System.out.println("input grid (1 = true) (0 = false)");
        row = in.nextInt();
        col = in.nextInt();
        oldBoard = new boolean[row][col];
        newBoard = new boolean[row][col];
        //takes in "\n"
        in.nextLine();
        for (int i = 0; i < row; i++) {
            boardRows = in.nextLine();
            for (int j = 0; j < col; j++) {
                if (boardRows.charAt(j) == '1') {
                    oldBoard[i][j] = true;
                }
            }
        }
        //System.out.println(Arrays.deepToString(oldBoard));
        /*int setRow = 0;
        int setCol = 0;
        String input = in.nextLine();
        String[] coordinates = new String[2];
        while (!input.equals("stop")) {
            coordinates = input.split(" ");
            setRow = Integer.parseInt(coordinates[0]);
            setCol = Integer.parseInt(coordinates[1]);
            oldBoard[setRow][setCol] = true;
            input = in.nextLine();
        }*/
        return;
    }

    public static void outputArray() {
        int[][] output = new int[row][col];
        System.out.println("your grid");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (newBoard[i][j]) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }

    public static int countCells(int i, int j) {
        int count = 0;
        for (int a = -1; a <= 1; a++) {
            for (int b = -1; b <= 1; b++) {
                if (a == 0 && b == 0) {
                    continue;
                } else {
                    try {
                        if (oldBoard[i + a][j + b]) {
                            count++;
                            //originally was in here
                        }
                    } catch (IndexOutOfBoundsException e) {
                        continue;
                    }
                    //test this
                    //count++;
                }
            }
        }
        //System.out.println(count);
        //System.out.println(Arrays.deepToString(oldBoard));
        return count;
    }

    public static void checkNeighbors() {
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                count = countCells(i, j);
                if (oldBoard[i][j]) {
                    if (count <= 1) {
                        newBoard[i][j] = false;
                    } else if (count >= 4) {
                        newBoard[i][j] = false;
                    }
                    else
                        //keeps true
                        newBoard[i][j] = true;
                }
                else if (!oldBoard[i][j] && count == 3) {
                    newBoard[i][j] = true;

                }
            }
        }
        return;
    }

    /*public static void crowded() {
        int count = 0;
        for (int i = 0; i < present.length; i++) {
            for (int j = 0; j < present.length; j++) {
                if (past[i][j]) {
                    count = countCells(i, j);
                    if (count >= 4 && count <= 8) {
                        past[i][j] = false;
                    }
                }
            }
        }
        return;
    }

    public static void birth() {
        int count = 0;
        for (int i = 0; i < present.length; i++) {
            for (int j = 0; j < present.length; j++) {
                if (!past[i][j]) {
                    count = countCells(i, j);
                    if (count >= 2 && count <= 3) {
                        past[i][j] = true;
                    }
                }
            }
        }
        return;
    }*/
}
