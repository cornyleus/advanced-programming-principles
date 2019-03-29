import java.util.Scanner;           // for user input

/**
 This program allows the user to play Towers of Hanoi, using the Towers and Peg
 classes to represent the game and the pegs respectively.
 
 The user will be asked how many rings (1-64) to play with, and can continue
 making moves until they win or decide to quit.
 
 Cory Iley
 Jan 20, 2018
 */
public class TowersGame
{
    public static void main(String[] args)
    {
        // initialize game with greeting() method
        Towers game = greeting();
        
        // display towers to user once before entering recursive method
        game.displayTowers();
        
        // gameplay function
        makeMove(game);
    }
    
    /**
     greeting method welcomes the user, and initializes game with given number
     of rings between 1-64.
     @return Towers game object with given number of rings.
     */
    public static Towers greeting()
    {
        Scanner keyboard = new Scanner(System.in);
        int numRings;
        
        System.out.print("Welcome to Towers of Hanoi.  How many rings would" +
        				 " you like to play with? (1-64) ");
        
        numRings = keyboard.nextInt();
        
        while (!(numRings >= 1 && numRings <= 64))
        {
            System.out.println("Invalid number, try again between 1-64: ");
            numRings = keyboard.nextInt();
        }
        
        System.out.println("Game started with " + numRings + " rings.");
        return new Towers(numRings);
        
    }
    
    /**
     validMove method determines whether the given move is valid, and prints the
     relevant error message to the screen for the user.
     @param game Towers object of the game.
     @param startPeg Peg ring will be moved from.
     @param endPeg Peg ring will be moved to.
     @return True if valid move, false otherwise.
     */
    public static boolean validMove(Towers game, int startPeg, int endPeg)
    {
        boolean valid = true;
        // check peg numbers are valid between 1-3
        if ((startPeg == 1 || startPeg == 2 || startPeg == 3) &&
        	(endPeg == 1 || endPeg == 2 || endPeg == 3))
        {
            // check start peg has a ring to move
            if (game.countRings(startPeg) > 0)
            {
                // check start peg and end peg differ
                if (startPeg != endPeg)
                {
                    // check whether end peg has a ring that must be compared to
                    if (game.countRings(endPeg) > 0)
                    {
                        // check whether start ring is larger than end ring
                        if (game.getTopDiameter(startPeg) > 
                        	game.getTopDiameter(endPeg))
                        {
                            valid = false;
                            System.out.println("First selection's ring must " +
                            "be smaller than the second selection's ring.");
                        }
                    }
                }
                else
                {
                    valid = false;
                    System.out.println("Second selection must differ from "
                    					+ "first selection.");
                }
            }
            else
            {
                valid = false;
                System.out.println("First selection has no rings.");
            }
        }
        else
        {
            valid = false;
            System.out.println("Invalid peg selection.  Try again between 1-3.");
        }
        
        return valid;
    }
    
    /**
     makeMove method is a recursive method that determines whether user wants to
     make a move, completes the move, and then repeats itself until the game has
     been won or the user decides to quit.
     @param game The Towers game object.
     */
     public static void makeMove(Towers game)
    {
        Scanner keyboard = new Scanner(System.in);
        int fromRing;       // user input
        int toRing;         // user input
        char input;         // user input
        
        // ask if user would like to continue
        System.out.println("Would you like to make a move? (y/n) ");
        input = keyboard.next().charAt(0);
        if (input == 'y')
        {
            // gather peg number info from user
            System.out.print("Move ring from peg: ");
            fromRing = keyboard.nextInt();
            System.out.print("Move ring to peg: ");
            toRing = keyboard.nextInt();
            
            // make move if valid
            if (validMove(game, fromRing, toRing))
                game.move(fromRing,toRing);
            
            // display towers to user
            game.displayTowers();
            
            // continue calling method until user wins
            if (!game.isWinner())
                makeMove(game);
            else
                System.out.println("Congrats!  You win!");
        }
        else
        {
            System.out.println("Thanks for playing.");
        }
    }
}
