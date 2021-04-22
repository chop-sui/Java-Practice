package Assignment1;

public class Flight {
	String flightId;
	String departureTime;
	Location srcLocation;
	Location dstLocation;
	int capacity;
	double ticketPrice;
	int numOfPassengersBooked;
	
	
	public String getArrivalTime(double duration) {
		String day = this.departureTime.substring(0, 3);
		int hour = Integer.parseInt(this.departureTime.substring(4, 6));
		int minutes = Integer.parseInt(this.departureTime.substring(7));
		
		int new_minutes = minutes + duration;
		
	}
	
	// Calculates the ticketPrice
	public double getTicketPrice() {
		double multiplier = 0;
		double x = (double)this.numOfPassengersBooked / (double)this.capacity;
		double d = Location.distance(this.srcLocation, this.dstLocation);
		double df = this.srcLocation.demand;
		double dt = this.dstLocation.demand;
		
		if (x > 0 && x <= 0.5) {
			multiplier = -0.4 * x + 1;
		}
		else if (x > 0.5 && x <= 0.7) {
			multiplier = x + 0.3;
		}
		else if (x > 0.7 && x <= 1.0){
			multiplier = (0.2 / Math.PI) * Math.atan(20 * x - 14) + 1;
		}
		else
			return this.ticketPrice;
		return multiplier * d / 100 * (30 + 4 * (dt - df));
	}
	
	//get the number of minutes this flight takes (round to nearest whole number)
    public int getDuration() {
    	int duration = (int)(this.getDistance() / 720 * 60);
    	return duration;
    }

    //book the given number of passengers onto this flight, returning the total cost
    public double book(int num) {
    	this.numOfPassengersBooked += num;
    	return 
    }

    //return whether or not this flight is full
    public boolean isFull() {
		return this.numOfPassengersBooked == this.capacity;
	}

    //get the distance of this flight in km
    public double getDistance() {
		return Location.distance(this.srcLocation, this.dstLocation);
	}

    //get the layover time, in minutes, between two flights
    public static int layover(Flight x, Flight y) {

    }
}