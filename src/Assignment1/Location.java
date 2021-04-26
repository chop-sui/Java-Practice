package Assignment1;

public class Location {
	String name;
	double lat;
	double lng;
	double demand;
	
	public Location(String name, double lat, double lng, double demand) {
		this.name = name;
		this.lat = lat;
		this.lng = lng;
		this.demand = demand; 
	}
	
	//Implement the Haversine formula - return value in kilometres
    public static double distance(Location l1, Location l2) {
    	double toRadian = Math.PI / 180;
		double distance;
		double radius = 6371; // radius of Earth
		double latDiff = Math.abs(l1.lat - l2.lat) * toRadian;
		double longDiff = Math.abs(l1.lng - l2.lng) * toRadian;
		
		double sinLatDiff = Math.sin(latDiff / 2);
		double sinLongDiff = Math.sin(longDiff / 2);
		distance = 2 * radius * Math.asin(
				Math.sqrt(sinLatDiff * sinLatDiff + 
						Math.cos(l1.lat * toRadian) * Math.cos(l2.lat * toRadian) * sinLongDiff * sinLongDiff));
		
		return distance;
    }
    
    public void addArrival(Flight f) {
    	
	}
	
	public void addDeparture(Flight f) {

	}
	
	/**
	 * Check to see if Flight f can depart from this location.
	 * If there is a clash, the clashing flight string is returned, otherwise null is returned.
	 * A conflict is determined by if any other flights are arriving or departing at this location within an hour of this flight's departure time.
	 * @param f The flight to check.
	 * @return "Flight <id> [departing/arriving] from <name> on <clashingFlightTime>". Return null if there is no clash.
	 */
	public String hasRunwayDepartureSpace(Flight f) {
		for (Flight flight : FlightScheduler.getFlightsList()) {
			int fHour = 0, fMinutes = 0, flightHour = 0, flightMinutes = 0;
			String depOrArr = "";
			String clashingTime = "";
			
    		if (f.departureTime.substring(0, 3).equals(flight.departureTime.substring(0,3))) {
    			fHour = Integer.parseInt(f.departureTime.substring(4, 6));
    			fMinutes = Integer.parseInt(f.departureTime.substring(7));
    			flightHour = Integer.parseInt(flight.departureTime.substring(4, 6));
    			flightMinutes = Integer.parseInt(flight.departureTime.substring(7));
    			depOrArr = "departing";
    			clashingTime = flight.departureTime;
    			
    		}
    		else if (f.departureTime.substring(0, 3).equals(flight.getArrivalTime(flight.getDuration()).substring(0,3))) {
    			fHour = Integer.parseInt(f.departureTime.substring(4, 6));
    			fMinutes = Integer.parseInt(f.departureTime.substring(7));
    			flightHour = Integer.parseInt(flight.getArrivalTime(flight.getDuration()).substring(4, 6));
    			flightMinutes = Integer.parseInt(flight.getArrivalTime(flight.getDuration()).substring(7));
    			depOrArr = "arriving";
    			clashingTime = flight.getArrivalTime(flight.getDuration());
    		}
    		
    		if (fHour == flightHour) {
    			return "Flight " + flight.flightId + depOrArr + "from " + f.srcLocation + "on " + clashingTime;
    		}
    		else if (Math.abs(fHour - flightHour) == 1) {
    			int biggerMinutes = 0;
    			int smallerMinutes = 0;
    			if (fHour > flightHour) {
    				biggerMinutes = fMinutes;
    				smallerMinutes = flightMinutes;
    			} else {
    				biggerMinutes = flightMinutes;
    				smallerMinutes = fMinutes;
    			}
    			
    			if (biggerMinutes - smallerMinutes >= 0) {
    				return "Flight " + flight.flightId + depOrArr + "from " + f.srcLocation + "on " + clashingTime;
    			}
    			return null;
    		}
    	}
		return null;
    }

    /**
	 * Check to see if Flight f can arrive at this location.
	 * A conflict is determined by if any other flights are arriving or departing at this location within an hour of this flight's arrival time.
	 * @param f The flight to check.
	 * @return String representing the clashing flight, or null if there is no clash. Eg. "Flight <id> [departing/arriving] from <name> on <clashingFlightTime>"
	 */
	public String hasRunwayArrivalSpace(Flight f) {
		for (Flight flight : FlightScheduler.getFlightsList()) {
			int fHour = 0, fMinutes = 0, flightHour = 0, flightMinutes = 0;
			String depOrArr = "";
			String clashingTime = "";
			
    		if (f.getArrivalTime(f.getDuration()).substring(0, 3).equals(flight.departureTime.substring(0,3))) {
    			fHour = Integer.parseInt(f.getArrivalTime(f.getDuration()).substring(4, 6));
    			fMinutes = Integer.parseInt(f.getArrivalTime(f.getDuration()).substring(7));
    			flightHour = Integer.parseInt(flight.departureTime.substring(4, 6));
    			flightMinutes = Integer.parseInt(flight.departureTime.substring(7));
    			depOrArr = "departing";
    			clashingTime = flight.departureTime;
    			
    		}
    		else if (f.departureTime.substring(0, 3).equals(flight.getArrivalTime(flight.getDuration()).substring(0,3))) {
    			fHour = Integer.parseInt(f.getArrivalTime(f.getDuration()).substring(4, 6));
    			fMinutes = Integer.parseInt(f.getArrivalTime(f.getDuration()).substring(7));
    			flightHour = Integer.parseInt(flight.getArrivalTime(flight.getDuration()).substring(4, 6));
    			flightMinutes = Integer.parseInt(flight.getArrivalTime(flight.getDuration()).substring(7));
    			depOrArr = "arriving";
    			clashingTime = flight.getArrivalTime(flight.getDuration());
    		}
    		
    		if (fHour == flightHour) {
    			return "Flight " + flight.flightId + depOrArr + "from " + f.srcLocation + "on " + clashingTime;
    		}
    		else if (Math.abs(fHour - flightHour) == 1) {
    			int biggerMinutes = 0;
    			int smallerMinutes = 0;
    			if (fHour > flightHour) {
    				biggerMinutes = fMinutes;
    				smallerMinutes = flightMinutes;
    			} else {
    				biggerMinutes = flightMinutes;
    				smallerMinutes = fMinutes;
    			}
    			
    			if (biggerMinutes - smallerMinutes >= 0) {
    				return "Flight " + flight.flightId + depOrArr + "from " + f.srcLocation + "on " + clashingTime;
    			}
    			return null;
    		}
    	}
		return null;
    }
}