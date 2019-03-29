import java.util.Scanner;

/**
 Airport class is the driver file for the single runway airport simulation.
 Cory Iley
 March 19, 2018
 */
public class Airport
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);

        // greet user and retrieve necessary parameters
        System.out.println("Welcome to the single runway airport simulation.");
        System.out.println("This program will simulate the arrivals and departures"
        					+ " of planes over a specified length of time at an "
        					+ "airport with one runway.\n");
        System.out.println("Please provide the following...");
        System.out.print("Length of time to simulate (in minutes): ");
        int duration = keyboard.nextInt(); // duration
        System.out.print("Probability of a plane arriving to the takeoff queue "
        					+ "(value between 0-1): ");
        float takeoffQueueProbability = keyboard.nextFloat();
        // ensure value between 0-1
        while (takeoffQueueProbability <= 0 || takeoffQueueProbability >= 1)
        {
            System.out.print("Enter the probability as a value between 0-1: ");
            takeoffQueueProbability = keyboard.nextFloat();
        }
        System.out.print("Probability of a plane arriving to the landing queue "
        					+ "(value between 0-1): ");
        float landingQueueProbability = keyboard.nextFloat();
        // ensure value between 0-1
        while (landingQueueProbability <= 0 || landingQueueProbability >= 1)
        {
            System.out.print("Enter the probability as a value between 0-1: ");
            landingQueueProbability = keyboard.nextFloat();
        }
        System.out.print("Amount of time required for takeoff (in minutes): ");
        int requiredTakeOffTime = keyboard.nextInt();   // requiredTakeOffTime
        System.out.print("Amount of time required to land (in minutes): ");
        int requiredLandingTime = keyboard.nextInt();   // requiredLandingTIme
        System.out.print("Amount of time plane can wait in the air before running"
        					+ " out of fuel (in minutes): ");
        int maxLandingDuration = keyboard.nextInt();   // maxLandingDuration

        System.out.println();

        System.out.println("Beginning simulation...");

        // initialize runway object and call simulate function
        Runway runway = new Runway();
        runway.simulate(duration, takeoffQueueProbability, landingQueueProbability,
        				requiredTakeOffTime, requiredLandingTime, maxLandingDuration);
        
        // display results of simulation
        runway.displayResults();
    }
}
