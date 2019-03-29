/**
 This program demonstrates a solution to Lo Shu Square
 programming challenge.
 
 Cory Iley
 Nov 10, 2017
 */
public class LoShuSquare
{
    public static void main(String[] args)
    {
        
        // valid lo shu square
        int[][] loShuSquare =   {{4,9,2},
                                {3,5,7},
                                {8,1,6}};
        
        // invalid lo shu square
        int[][] invalidLoShuSquare =    {{1,2,3},
                                        {4,5,6},
                                        {7,8,9}};
                                        
        
        // program greeting
        System.out.println("This program determines whether or not"
                        + " a square is a valid Lo Shu magic square.\n");
        
        //print results
        printSquare(loShuSquare);
        System.out.println("This square is "
        					+ displayResult(loShuSquare) + "\n");
        printSquare(invalidLoShuSquare);
        System.out.println("This square is "
        					+ displayResult(invalidLoShuSquare));
        
    }
    
    /**
     The isValid method tests a square to determine whether it's a valid lo shu
     magic square.
     @param square The square to be tested.
     @return True if valid lo shu magic square, false if otherwise.
     */
    public static boolean isValid(int[][] square)
    {
        
        // assume valid, to be tested
        boolean valid = true;
        
        // arrays to store cumulative totals of rows, cols, diagonals
        int rows[] = new int[3];
        int cols[] = new int[3];
        int diagonals[] = new int[2];
        
        // initialize counter variable
        int count = square[0].length - 1;
        
        // calculate totals for rows, cols, and diagonals
        for (int row = 0; row < square.length; row++)
        {
        	for (int col = 0; col < square[row].length; col++)
        	{
        		// add sum of each row and col to rows and cols arrays
        		rows[col] += square[col][row];
        		cols[col] += square[row][col];
        	}
            
            // add sum of first diagonal to diagonals array
            diagonals[0] += square[row][row];
            
            // add sum of second diagonal to diagonals array
            diagonals[1] += square[row][count];
            count--;
        }
        
        // validation logic
        for (int row : rows)
            if (row != 15)
                valid = false;
        
        for (int col : cols)
            if (col != 15)
                valid = false;
        
        for (int diag : diagonals)
            if (diag != 15)
                valid = false;
        
        return valid;
    }
    
    /**
     The displayResult method returns the result of the validity test
     as a string.
     @param square The square to display the result of.
     @return "valid" if valid, "not valid" otherwise.   
     */
    public static String displayResult(int[][] square)
    {
        if (isValid(square))
            return "valid";
        else
			return "not valid";
    }
    
    /**
     printSquare method prints the square to the screen
     for the user.
     @param square The square to display.
     */
    public static void printSquare(int[][] square)
    {
        for (int row = 0; row < square.length; row++)
        {
            for (int col = 0; col < square[row].length; col++)
            {
                System.out.print(square[row][col] + " ");
            }
            System.out.println();
                
        }
        
    }
}
