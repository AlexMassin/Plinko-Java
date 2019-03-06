/*
Alexander Massin 12A
Plinko
October 20 2016
 */
import java.util.*;
public class Plinko
{
    public static void main( String[] args) throws InterruptedException, NumberFormatException
    {
        Scanner input = new Scanner(System.in);
        Random numGen = new Random();
        //Over here we are assigning variables that will be used later in the program
        int chipCount = 0;

        //SleepCounter is a variable that increases the suspense of the game as the chip drops more
        int sleepCounter = 0;
        int spotSelected = 0;
        int sumOfWinnings = 0;
        //This is an array that deals with the different columns the player may drop the chip
        int[] pathOfBall = new int[10];
        //This array deals with the prizes that are assigned to each column. It is later used to tell the player where the chip is as it drops.
        int[] prizeMarks = {0,1,5,10,0,100,0,10,5,1};
        String name = "";
        System.out.println("What is your name? (First Name Only)");
        name = input.nextLine();
        //These if statements deal with which people get special treatment
        chipCount = 5;
        //This is where the Plinko rules and instructions are printed. It also shows where they can drop the chip and the prizes under every section
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|   1   |   2   |   3   |   4   |   5   |   6   |   7   |   8   |   9   |");
        System.out.println("   $1      $5      $10     $0     $100     $0      $10     $5      $1   ");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Welcome to Plinko " + name + "! The ultimate game of chance.");
        System.out.println("You have " + chipCount + " chips left.");
        System.out.println("Drop a chip into the desired spot on the board(1-9)");
        //I use a do loop here so that we can circulate through all the chips until chipCount = 0
        do{
            //This resets sleepCounter each time so that the suspense can rebuild
            sleepCounter = 400;
            //This checks if the input is between 1 and 9 and is not a letter
            while (spotSelected>9 || spotSelected<1)
            {
                try{
                    spotSelected = Integer.parseInt(input.nextLine());
                    if (spotSelected<1||spotSelected>9){System.out.println("Input a number between 1-9 please");}
                }
                catch (NumberFormatException e) {System.out.println("Input a number between 1-9 please");}
            }
            for (int x = 0; x<pathOfBall.length; x++)
            {
                //This is where the programs begins to slow down
                Thread.sleep(sleepCounter);
                //This determines if the ball goes left(1) or right(2)
                pathOfBall[x] = numGen.nextInt(2) + 1;
                //sleepCounter increases so that it can continue to build suspense
                sleepCounter = sleepCounter + 50;
                //If the spot is 1 then it can only go right and if the spot is 9 then it can only go left
                if (spotSelected == 9){pathOfBall[x] = 1;}
                if (spotSelected == 1){pathOfBall[x] = 2;}
                //This prints out to the player where the location of the chip is.
                //It also shows the prize under their chip
                if (pathOfBall[x] == 1){
                    spotSelected = spotSelected -1;
                    System.out.println("The ball moves left to " + spotSelected +" ---> $" + prizeMarks[spotSelected]);
                }
                else{
                    spotSelected = spotSelected + 1;
                    System.out.println("The ball moves right to " + spotSelected +" ---> $" + prizeMarks[spotSelected]);
                }
            }
            //I use System.err so that it adds flavour to the program and prints out red. The red symbolizes the importance of the message
            System.err.println("\n\nYou win $" + prizeMarks[spotSelected]+"! ");
            //This is an a cumulative variable that prints out the total amount of bonus marks won
            sumOfWinnings = sumOfWinnings+ prizeMarks[spotSelected];
            //This decreases chipCount by 1 because they used a chip
            chipCount--;
            //spotSelected needs to be 0 because the game is restarting
            spotSelected = 0;
            //This alerts the player how many chips are left
            System.out.println("You have " +chipCount+ " chip(s) left");
             if (chipCount > 0){System.out.println("Drop a chip between (1-9)");}
            //When chipCount = 0, the while loop will stop and the game will be done
        }while(chipCount != 0);
        System.out.println("Thank you for playing! You have won an amazing total of $" + sumOfWinnings);
    }

}
