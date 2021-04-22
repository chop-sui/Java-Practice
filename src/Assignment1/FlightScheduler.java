package Assignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FlightScheduler {
	
	private static FlightScheduler instance;

    public static void main(String[] args) {
        instance = new FlightScheduler(args);
        instance.run();
    }

    public static FlightScheduler getInstance() {
        return instance;
    }

    public FlightScheduler(String[] args) {}

    public void run() {
        // Do not use System.exit() anywhere in your code,
        // otherwise it will also exit the auto test suite.
        // Also, do not use static attributes otherwise
        // they will maintain the same values between testcases.

        // START YOUR CODE HERE
    }

    	
	// Add a flight to the database
	// handle error cases and return status negative if error 
	// (different status codes for different messages)
	// do not print out anything in this function
	public int addFlight(String date1, String date2, String start, String end, String capacity, int booked) {
		
	}
	
	// Add a location to the database
    // do not print out anything in this function
    // return negative numbers for error cases
	public int addLocation(String name, String lat, String lon, String demand) {
		
	}
	
	//flight import <filename>
	public void importFlights(String[] command) {
		try {
			if (command.length < 3) throw new FileNotFoundException();
			BufferedReader br = new BufferedReader(new FileReader(new File(command[2])));
			String line;
			int count = 0;
			int err = 0;
			
			while ((line = br.readLine()) != null) {
				String[] lparts = line.split(",");
				if (lparts.length < 5) continue;
				String[] dparts = lparts[0].split(" ");
				if (dparts.length < 2) continue;
				int booked = 0;
				
				try {
					booked = Integer.parseInt(lparts[4]);
					
				} catch (NumberFormatException e) {
					continue;
				}
				
				int status = addFlight(dparts[0], dparts[1], lparts[1], lparts[2], lparts[3], booked);
				if (status < 0) {
					err++;
					continue;
				}
				count++;
			}
			br.close();
			System.out.println("Imported "+count+" flight"+(count!=1?"s":"")+".");
			if (err > 0) {
				if (err == 1) System.out.println("1 line was invalid.");
				else System.out.println(err+" lines were invalid.");
			}
		} catch (IOException e) {
			System.out.println("Error reading file.");
			return;
		}
	}
	
	//location import <filename>
	public void importLocations(String[] command) {
		try {
			if (command.length < 3) throw new FileNotFoundException();
			BufferedReader br = new BufferedReader(new FileReader(new File(command[2])));
			String line;
			int count = 0;
			int err = 0;
			
			while ((line = br.readLine()) != null) {
				String[] lparts = line.split(",");
				if (lparts.length < 4) continue;
								
				int status = addLocation(lparts[0], lparts[1], lparts[2], lparts[3]);
				if (status < 0) {
					err++;
					continue;
				}
				count++;
			}
			br.close();
			System.out.println("Imported "+count+" location"+(count!=1?"s":"")+".");
			if (err > 0) {
				if (err == 1) System.out.println("1 line was invalid.");
				else System.out.println(err+" lines were invalid.");
			}
			
		} catch (IOException e) {
			System.out.println("Error reading file.");
			return;
		}
	}

}
