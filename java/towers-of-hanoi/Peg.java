/**
 This class represents a peg in the game Towers of Hanoi.
 Cory Iley
 Jan 20, 2018
 */
public class Peg
{
    private int[] rings;                    // array of ring diameters
    private int numberOfRings;              // number of rings
    private final int MAX_RING_SIZE = 64;   // max possible number of rings
    
    /**
     Initialize a peg with n rings, add rings with smallest on top.
     @param n Number of rings.
     */
    public Peg(int n)
    {
        if (n <= MAX_RING_SIZE)
        {
            numberOfRings = n;
            rings = new int[n];
            for (int i = 0; i < n; i++)
                // reverse order for ease of addition/removal
                rings[i] = Math.abs(i-n);
        }
    }
    
    /**
     Initialize empty peg.
     */
    public Peg()
    {
        numberOfRings = 0;
        rings = new int[MAX_RING_SIZE];
    }
    
    /**
     getNumberOfRings method.
     @return Number of rings on peg.
     */
    public int getNumberOfRings()
    {
        return numberOfRings;
    }
    
    /**
     getTopDiameter method
     @return Diameter of ring on top of peg.
     */
    public int getTopDiameter()
    {
        return rings[numberOfRings-1];
    }
    
    /**
     addRing method adds ring with given diameter, incrementing numberOfRings.
     @param diameter Diameter of the ring to add.
     @return True if ring was added, false otherwise.
     */
    public boolean addRing(int diameter)
    {
        boolean result = false;
        if (!isArrayFull())
        {
            rings[numberOfRings] = diameter;
            numberOfRings++;
        }
        
        return result;
        
    }
    
    /**
     removeTopRing method
     Removes the top ring from the peg, decrementing numberOfRings.
     */
    public void removeTopRing()
    {
        
        int newRing = rings[numberOfRings-1];
        rings[numberOfRings-1] = 0;
        numberOfRings--;
        
    }
    
    /**
     isArrayFull method
     @return True if the array is full.
     */
    public boolean isArrayFull()
    {
        return numberOfRings >= rings.length;
    }
    
    /**
     isArrayEmpty method
     @return True if the array is empty.
     */
    public boolean isArrayEmpty()
    {
        return numberOfRings == 0;
    }
    
    /**
     toArray method
     @return Rings as an array
     */
    public int[] toArray()
    {
        int[] result = new int[rings.length];
        for (int i = 0; i < rings.length; i++)
            // return to client in correct order
            result[Math.abs((i+1)-rings.length)] = rings[i];
        return result;
    }
    
    /**
     displayStats method
     Displays the rings with smallest on top.
     */
    public void displayStats()
    {
        if (!isArrayEmpty())
        {
            int[] result = toArray();
            for (int ring : result)
                if (ring != 0)
                    System.out.println("   " + ring);
        }
    }
}
