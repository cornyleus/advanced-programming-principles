/**
 This class represents the runway in the airport simulation.
 Cory Iley
 March 19, 2018
 */
public class Runway
{
    private QueueInterface<Plane> takeoff;  // planes waiting to depart
    private QueueInterface<Plane> landing;  // planes waiting to land
    private int numberOfDepartures;         // number of planes in takeoff queue
    private int numberDeparted;             // number of planes that have taken off
    private int numberOfLandings;           // number of planes in landing queue
    private int numberLanded;               // number of planes that have landed
    private int numberOfCrashes;            // number of planes that crashed
    private int totalTakeOffTimeWaited;     // total time waiting to take off
    private int totalLandingTimeWaited;     // total time waiting to land
    
    /**
     Default constructor initializes a Runway object with empty
     queues for landing and takeoff.
     */
    public Runway()
    {
        takeoff = new LinkedQueue<>();
        landing = new LinkedQueue<>();
        reset();
    }
    
    /**
     Simulates the arrivals and departures of planes with specified values
     @param duration Length of time in minutes to run simulation
     @param takeoffQueueProbaility Probability plane enters takeoff queue
     @param landingQueueProbability Probability plane enters landing queue
     @param requiredTakeOffTime Time needed to take off
     @param requiredLandingTime Time needed to land
     @param maxLandingDuration Time plane can wait in air before running out of
     fuel
     @throws IllegalArgumentException if any values are 0
     */
    public void simulate(int duration, double takeoffQueueProbability,
    					double landingQueueProbability, int requiredTakeOffTime,
    					int requiredLandingTime, int maxLandingDuration)
    {
        if (duration == 0 || takeoffQueueProbability == 0 ||
        	landingQueueProbability == 0 || requiredTakeOffTime == 0 ||
        	requiredLandingTime == 0 || maxLandingDuration == 0)
        {
            throw new IllegalArgumentException("All values must be greater" +
            									" than 0. ");
        }
        int timeLeft = 0;
        
        for (int clock = 0; clock < duration; clock++)
        {
            if (Math.random() < takeoffQueueProbability)
            {
                numberOfDepartures++;
                Plane nextDeparture = new Plane(clock, requiredTakeOffTime,
                                                    numberOfDepartures);
                takeoff.enqueue(nextDeparture);
                System.out.println("Departure #" + numberOfDepartures
                                   + " enters takeoff queue at minute " + clock);
            }
            
            if (Math.random() < landingQueueProbability)
            {
                numberOfLandings++;
                Plane nextLanding = new Plane(clock, requiredLandingTime,
                                                numberOfLandings);
                landing.enqueue(nextLanding);
                System.out.println("Arrival #" + numberOfLandings
                                   + " enters landing queue at minute " + clock);
            }
            
            if (timeLeft > 0)
                timeLeft--;
            else if (!landing.isEmpty())
            {
                
                Plane nextLanding = landing.dequeue();
                
                timeLeft = nextLanding.getTimeOnRunway() - 1;
                int timeWaited = clock - nextLanding.getArrivalTime();
                
                if (timeWaited > maxLandingDuration)
                {
                    System.out.println("Arrival #" + nextLanding.getNumberOfPlane()
                    					+ " attempted to land at minute " + clock
                    					+ " but had to wait" + " too long (" +
                    					timeWaited + " minutes) and has crashed");
                    numberOfCrashes++;
                } else {
                    totalLandingTimeWaited += timeWaited;
                    numberLanded++;
                    System.out.println("Arrival #" + nextLanding.getNumberOfPlane()
                   					 	+ " begins using runway at minute " + clock
                   					 	+ " for " + nextLanding.getTimeOnRunway()
                   					 	+ " minutes.  Time in queue: " + timeWaited
                   					 	+ " minutes");
                }
            }
            else if (!takeoff.isEmpty())
            {
                Plane nextDeparture = takeoff.dequeue();
                
                timeLeft = nextDeparture.getTimeOnRunway() - 1;
                int timeWaited = clock - nextDeparture.getArrivalTime();
                totalTakeOffTimeWaited += timeWaited;
                numberDeparted++;
                System.out.println("Departure #" + nextDeparture.getNumberOfPlane()
               				 		+ " begins using runway at minute " + clock +
               				 		" for " + nextDeparture.getTimeOnRunway() +
               				 		" minutes.  Time in queue: " + timeWaited +
               				 		" minutes");
            }
        }
        
        // check planes remaining in landing queue for crashes
        while (!landing.isEmpty())
        {
            Plane nextLanding = landing.dequeue();
            int timeWaited = duration - nextLanding.getArrivalTime();
            if (timeWaited > maxLandingDuration)
            {
                System.out.println("Arrival #" + nextLanding.getNumberOfPlane() +
                					" remaining in queue has waited too long ("
                					+ timeWaited + " minutes) and has crashed");
                numberOfCrashes++;
            }
        }
        
        
        
    }
    
    /** Displays summary results of the simulation. */
    public void displayResults()
    {
        System.out.println();
        System.out.println("Total entered takeoff queue: " + numberOfDepartures);
        System.out.println("Successful departures: " + numberDeparted);
        System.out.println("Total entered landing queue: " + numberOfLandings);
        System.out.println("Successful landings: " + numberLanded);
        System.out.println("Number of crashes: " + numberOfCrashes);
        
        System.out.println("Total time waited in take-off queue: " +
        					totalTakeOffTimeWaited + " minutes");
        System.out.println("Total time waited in landing queue: " +
        					totalLandingTimeWaited + " minutes");
        
        double averageTakeOffTimeWaited = ((double)totalTakeOffTimeWaited) /
        									numberDeparted;
        if (numberDeparted > 0)
        {
            System.out.println("Average take-off time waited: " +
            					averageTakeOffTimeWaited + " minutes");
    	}
        else
        {
            System.out.println("Average take-off time waited: no successful "
            					+ "departures");
        }
        double averageLandingTimeWaited = ((double)totalLandingTimeWaited) /
        									numberLanded;
        if (numberLanded > 0)
        {
            System.out.println("Average landing time waited: " +
            					averageLandingTimeWaited + " minutes");
        }
        else
            System.out.println("Average landing time waited: no successful landings");
    }
    
    /** Initializes the simulation. */
    public final void reset()
    {
        takeoff.clear();
        landing.clear();
        numberOfDepartures = 0;
        numberDeparted = 0;
        numberOfLandings = 0;
        numberLanded = 0;
        numberOfCrashes = 0;
        totalTakeOffTimeWaited = 0;
        totalLandingTimeWaited = 0;
    }
}
