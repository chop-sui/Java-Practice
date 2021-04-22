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

    }

    /**
	 * Check to see if Flight f can arrive at this location.
	 * A conflict is determined by if any other flights are arriving or departing at this location within an hour of this flight's arrival time.
	 * @param f The flight to check.
	 * @return String representing the clashing flight, or null if there is no clash. Eg. "Flight <id> [departing/arriving] from <name> on <clashingFlightTime>"
	 */
	public String hasRunwayArrivalSpace(Flight f) {

    }
}