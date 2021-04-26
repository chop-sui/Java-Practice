package Assignment1;

public class Flight {
	String flightId;
	String departureTime;
	Location srcLocation;
	Location dstLocation;
	int capacity;
	double ticketPrice;
	int numOfPassengersBooked;
	
	String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
	
	public Flight (String flightId, String departureTime, Location srcLocation, Location dstLocation, int capacity, double ticketPrice, int numOfPassengersBooked) {
		this.flightId = flightId;
		this.departureTime = departureTime;
		this.srcLocation = srcLocation;
		this.dstLocation = dstLocation;
		this.capacity = capacity;
		this.ticketPrice = ticketPrice;
		this.numOfPassengersBooked = numOfPassengersBooked;
	}
	
	public Flight(String flightId, String departureTime, Location srcLocation, Location dstLocation, int capacity, double ticketPrice) {
		this(flightId, departureTime, srcLocation, dstLocation, capacity, ticketPrice, 0);
	}
	
	public Flight(String flightId, String departureTime, Location srcLocation, Location dstLocation, int capacity) {
		this(flightId, departureTime, srcLocation, dstLocation, capacity, 0);
	}
	
//	public int cmpTime(String departureTime, String arrivalTime) {
//		String depDay = departureTime.substring(0, 3);
//		String arrDay = arrivalTime.substring(0, 3);
//		int depDayNum = 0;
//		int arrDayNum = 0;
//		for (int i = 0; i < days.length; i++) {
//			if (days[i].equals(depDay)) {
//				depDayNum = i;
//			}
//			if (days[i].equals(arrDay)) {
//				arrDayNum = i;
//			}
//		}	
//		int depHour = Integer.parseInt(departureTime.substring(4, 6));
//		int depMinutes = Integer.parseInt(departureTime.substring(7));
//		int arrHour = Integer.parseInt(arrivalTime.substring(4, 6));
//		int arrMinutes = Integer.parseInt(arrivalTime.substring(7));
//		
//		if (arrDayNum)
//	}
	
	public String getArrivalTime(double duration) {	
		String day = this.departureTime.substring(0, 3);
		int dayNum = 0;
		for (int i = 0; i < days.length; i++) {
			if (days[i].equals(day)) {
				dayNum = i;
				break;
			}
		}
		int hour = Integer.parseInt(this.departureTime.substring(4, 6));
		int minutes = Integer.parseInt(this.departureTime.substring(7));
		
		int newMinutes = minutes + (int)duration;
		int newHour = hour;
		if (newMinutes > 60) {
			newHour++;
			if (newHour > 23) {
				day = days[(dayNum + 1) % days.length];
				newHour = 0;
			}
			newMinutes = 0;
		}
		String newHourStr = Integer.toString(newHour);
		String newMinutesStr = Integer.toString(newMinutes);
		if (newHour == 0) newHourStr = "0" + newHourStr;
		if (newMinutes == 0) newMinutesStr = "0" + newMinutesStr;
		
		String arrivalTime = day + " " + newHourStr + ":" + newMinutesStr;
		
		return arrivalTime;	
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
    	double totalCost = 0;
    	for (int i = 1; i <= num; i++) {
    		this.numOfPassengersBooked += 1;
    		totalCost += this.getTicketPrice();
    	}
    	
    	return totalCost;
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
    	return 0;
    }
    	
}