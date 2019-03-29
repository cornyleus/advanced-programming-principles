/**
 This class tracks the status of the three pegs in the game Towers of Hanoi.
 Cory Iley
 Jan 20, 2018
 */
public class Towers
{
    
    private Peg[] pegs;             // field to hold peg objects array
    
    /**
     Towers constructor initializes the three pegs, putting numRings pegs onto
     the first peg and none onto the second and third.
     @param numRings The number of rings to put on the first peg.
     */
    public Towers(int numRings)
    {
        if (numRings >= 1 && numRings <= 64)
        {
            //Peg pegs = {new Peg(3), new Peg(), new Peg()};
            pegs = new Peg[3];
            pegs[0] = new Peg(numRings);
            pegs[1] = new Peg();
            pegs[2] = new Peg();
        }
    }
    
    /**
     countRings method returns number of rings on a peg.
     @param pegNumber The peg number you wish to count the rings of.
     @return The number of rings on a peg, or -1 if invalid peg number.
     */
    public int countRings(int pegNumber)
    {
        if (pegNumber == 1 || pegNumber == 2 || pegNumber == 3)
            return pegs[pegNumber-1].getNumberOfRings();
        else
            return -1;
    }
    
    /**
     getTopDiameter method returns the diameter of the top ring of the given peg
     @param pegNumber The peg number.
     @return The diameter of the top ring of given peg, or -1 if invalid peg
     number.
     */
    public int getTopDiameter(int pegNumber)
    {
        if (pegNumber == 1 || pegNumber == 2 || pegNumber == 3)
        {
            if (countRings(pegNumber) > 0)
                return pegs[pegNumber-1].getTopDiameter();
            else
                return 0;
        }
        else
            return -1;
    }
    
    /**
     move method attempts to move a peg from startPeg to endPeg.
     */
    public void move(int startPeg, int endPeg)
    {
        boolean valid = true;
        if (startPeg == 1 || startPeg == 2 || startPeg == 3 &&
        	countRings(startPeg) > 0)
        {
            //System.out.println("true");
            if (startPeg != endPeg)
            {
                //System.out.println("also true");
                if (countRings(endPeg) > 0)
                {
                    //System.out.println("still true");
                    if (getTopDiameter(startPeg) < getTopDiameter(endPeg))
                    {
                        //System.out.println("valid");
                        //valid move
                    }
                    else
                        valid = false;
                }
            }
            else
                valid = false;
        }
        else
            valid = false;
        
        if (valid)
        {
            pegs[endPeg-1].addRing(pegs[startPeg-1].getTopDiameter());
            pegs[startPeg-1].removeTopRing();
        }
        else
            System.out.println("Invalid move");
        
    }
    
    /**
     displayTowers method cycles through the three towers, displaying their
     rings in order with smallest on top.
     */
    public void displayTowers()
    {
        // check if game started
        if (pegs.length == 3)
        {
            for (int i = 0; i < pegs.length; i++)
            {
                System.out.println();
                pegs[i].displayStats();
                System.out.println(" -----");
                System.out.println(" Peg " + (i+1) + " ");
            }
        }
    }
    
    /**
     isWinner method determines whether the user has won the game.
     @return True if user has won, false otherwise.
     */
    public boolean isWinner()
    {
        return (countRings(1) == 0 && countRings(3) == 0);
    }
}
