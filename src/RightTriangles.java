/*
Write a program that takes integer input from the user (using args) and creates a right-sided right-angle triangle made
with stars (*)
*/

public class RightTriangles {

    // the printRightTriangle class will print a * filled triangle based on input (i) from the main class
    public static void printRightTriangle(int i){
        System.out.println(); //adding a blank line to give the triangle art the space it deserves :)
        //this loop will run i times creating i rows
        for (int y = 1; y <=i; y++){
            //this loop will run i times, creating i columns and filling them with a * when if = true
            for (int x = 1; x <= i; x++){
                if (x > (i-y)){
                    System.out.print("*");
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args){

        int triangleSize = Integer.parseInt(args[0]); //assign an integer value to input argument
        // check for an acceptable input
        if(triangleSize<0) {
            System.out.println("Error: input value must be >=0");
            return;
        }
        // call the method
        printRightTriangle(triangleSize);
    }
}