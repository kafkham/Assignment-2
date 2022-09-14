/*
Write a program that takes integer input from the user (using args) and creates a left-sided right-angle triangle made
with stars (*)
*/

public class LeftTriangles {

    // the printLeftTriangle class will print a * filled triangle based on input from the main class
    public static void printLeftTriangle(int i) {
        System.out.println(); //adding a blank line to give the triangle art the space it deserves :)
        /*the first loop represents y coordinates and will run a number of times based on the user's input (i)
        the second loop represents x coordinates and will run a number of times based on the value of the first loop*/
        for (int y = 1; y <= i; y++) {
            for (int x = 0; x < y; x++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int triangleSize = Integer.parseInt(args[0]); //assign an integer value to input argument
        // check for an acceptable input
        if (triangleSize < 0) {
            System.out.println("Error: input value must be >=0");
            return;
        }
        // call the method
        printLeftTriangle(triangleSize);
    }
}