package Assignment1;

public class Flight {
	String flightId;
	String departureTime;
	String srcLocation;
	String dstLocation;
	int capacity;
	double ticketPrice;
	int numOfPassengers;
	
	// Calculates the distance(in km) given lats & longs
	public double getDistance(double x1, double y1, double x2, double y2) {
		double toRadian = Math.PI / 180;
		double distance;
		double radius = 6371; // radius of Earth
		double latDiff = Math.abs(x1 - x2) * toRadian;
		double longDiff = Math.abs(y1 - y2) * toRadian;
		
		double sinLatDiff = Math.sin(latDiff / 2);
		double sinLongDiff = Math.sin(longDiff / 2);
		distance = 2 * radius * Math.asin(
				Math.sqrt(sinLatDiff * sinLatDiff + 
						Math.cos(x1 * toRadian) * Math.cos(x2 * toRadian) * sinLongDiff * sinLongDiff));
		
		return distance;
	}
	
	// Calculates the duration of the flight(in hours)
	public double getDuration(double x) {
		return x / 720;
	}
	
	// Calculates the ticketPrice
	public double getTicketPrice(double x, double d, double fromCoeff, double toCoeff) {
		double multiplier = 0;
		if (x > 0 && x <= 0.5) {
			multiplier = -0.4 * x + 1;
		}
		else if (x > 0.5 && x <= 0.7) {
			multiplier = x + 0.3;
		}
		else {
			multiplier = (0.2 / Math.PI) * Math.atan(20 * x - 14) + 1;
		}
		return multiplier * d / 100 * (30 + 4 * (toCoeff - fromCoeff));
	}
}
