/**
 This class represents a plane in the airport simulation.
 Cory Iley
 March 19, 2018
 */
public class Plane
{
    private int arrivalTime;    // arrival time of the plane
    private int timeOnRunway;   // time required to land or take-off
    private int numberOfPlane;  // number of plane

    /**
     Constructor initializes a plane with passed values.
     */
    public Plane(int newArrivalTime, int newTimeOnRunway, int newNumberOfPlane)
    {
        arrivalTime = newArrivalTime;
        timeOnRunway = newTimeOnRunway;
        numberOfPlane = newNumberOfPlane;
    }
    
    /**
     getArrivalTime() method
     @return The time at which the plane was added to the queue.
     */
    public int getArrivalTime()
    {
        return arrivalTime;
    }
    
    /**
     getTimeOnRunway() method
     @return Amount of time plane will spend on runway.
     */
    public int getTimeOnRunway()
    {
        return timeOnRunway;
    }
    
    /**
     getNumberOfPlane() method
     @return The number of the plane.
     */
    public int getNumberOfPlane()
    {
        return numberOfPlane;
    }
}
