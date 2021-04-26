package Assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FlightScheduler {
	
	private static ArrayList<Location> locations = new ArrayList<>();
	private static ArrayList<Flight> flights = new ArrayList<>();
	private static int flightId = 0;
	
	private static FlightScheduler instance;

    public static void main(String[] args) {
        instance = new FlightScheduler(args);
        instance.run();
    }

    public static ArrayList<Flight> getFlightsList() {
    	return flights;
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
    	
    	//my test cases
//    	addLocation("Sydney", "33", "31", "0.5");
//    	addLocation("Beijing", "23", "21", "0.5");
//    	addFlight("Mon", "09:00", "Sydney", "Beijing", "100", 20);
    	
    	while(true) {
    		Scanner sc = new Scanner(System.in);
        	
        	if(sc.hasNext("exit")) {
        		System.out.println("Application closed.");
        		sc.close();
        		return;
        	}
        	
        	if (sc.hasNext("flights")) {
        		System.out.println("-------------------------------------------------------------");
        		System.out.println("Id      Departure      Arrival      Source --> Destination");
        		System.out.println("-------------------------------------------------------------");
        		for(Flight f : flights) {
        			System.out.printf("%s     %10s    %10s    %10s --> %10s\n", f.flightId, f.departureTime, f.getArrivalTime(f.getDuration()), f.srcLocation.name, f.dstLocation.name);
        		}
        	}
        	if (sc.hasNext("flight")) {
        		sc.next();
        		String header = "Flight";
        		if (sc.hasNextInt()) {
    				int param = sc.nextInt();
    				header += " " + Integer.toString(param);
    				System.out.println(header);
    				Flight f = flights.get(param);
    	    		System.out.println("Departure:   " + f.departureTime + " " + f.srcLocation.name);
    	    		System.out.println("Arrival:   " + f.getArrivalTime(f.getDuration()) + " " + f.dstLocation.name);
    	    		System.out.println("Distance:   " + f.getDistance());
    	    		System.out.println("Ticket Cost:   " + f.ticketPrice);
    	    		System.out.println("Passengers:   " + f.numOfPassengersBooked + "/" + Integer.toString(f.capacity));
    			}
    			if (sc.hasNext("add")) {
    				sc.next();
    				String day = sc.next();
    				String depTime = sc.next();
    				String src = sc.next();
    				String dst = sc.next();
    				String cap = sc.next();
    				
    				if (day.equals("monday")) day = "mon";
    				if (day.equals("tuesday")) day = "tue";
    				if (day.equals("wednesday")) day = "wed";
    				if (day.equals("thursday")) day = "thu";
    				if (day.equals("friday")) day = "fri";
    				if (day.equals("saturday")) day = "sat";
    				if (day.equals("sunday")) day = "sun";
    				int flag = addFlight(day, depTime, src, dst, cap, 0);
    				if (flag == 1) System.out.println("Successfully added Flight " + flightId + ".");
    			} 		
        	}
        	if (sc.hasNext("locations")) {
        		int countLocations = locations.size();
        		System.out.println("Locations (" + countLocations + "):");
        		if (countLocations > 1)
	        		for (Location location : locations) {
	        			System.out.print(location.name + ", ");
	        		}
        		else System.out.print(locations.get(0).name);
        	}
        	
        	if (sc.hasNext("location")) {
        		sc.next();
        		if (sc.hasNext("add")) {
    				sc.next();
    				String name = sc.next();
    				String lat = sc.next();
    				String lon = sc.next();
    				String demand = sc.next();
    				
    				int flag = addLocation(name, lat, lon, demand);
    				if (flag == 1) System.out.println("Successfully added location " + name + ".");
    			} 	
        	}
    	}
    	
    }

    	
	// Add a flight to the database
	// handle error cases and return status negative if error 
	// (different status codes for different messages)
	// do not print out anything in this function
	public int addFlight(String date1, String date2, String start, String end, String capacity, int booked) {
		String departure = date1 + " " + date2;
		Location src = null, dst = null;
		for (Location location : locations) {
			if (location.name.equals(start)) {
				src = location;
			}
			if (location.name.equals(end)) {
				dst = location;
			}
		}
		if (src == null) {
			System.out.println("Invalid starting location.");
			return -1;
		}
		else if (dst == null) {
			System.out.println("invalid ending location.");
			return -2;
		}
		Flight flight = new Flight(Integer.toString(flightId), departure, src, dst, Integer.parseInt(capacity));
				
		flights.add(flight);
		flightId++;
		
		return 1;
	}
	
	// Add a location to the database
    // do not print out anything in this function
    // return negative numbers for error cases
	public int addLocation(String name, String lat, String lon, String demand) {
		Location newLocation = new Location(name, Double.parseDouble(lat), Double.parseDouble(lon), Double.parseDouble(demand));
		
		
		if (locations.contains(newLocation)) {
			System.out.println("The location already exists");
			return -1;
		}
		
		if (Double.parseDouble(lat) < -85 || Double.parseDouble(lat) > 85) {
			System.out.println("Invalid latitude. It must be a number of degrees between -85 and +85");
			return -1;
		}
		
		if (Double.parseDouble(lon) < -180 || Double.parseDouble(lon) > 180) {
			System.out.println("Invalid longitude. It must be a number of degrees between -180 and +180");
			return -1;
		}
		
		locations.add(newLocation);
		return 1;
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
