package Assignment1;

public class FlightScheduler {

	public static void main(String[] args) {
		Flight f1 = new Flight();
		
		double distance = f1.getDistance(37.547889, 126.997128, 35.158874, 129.043846);
		double duration = f1.getDuration(distance);
		
		double ticketPrice = f1.getTicketPrice(0.8, distance, 1, -1);
		
		System.out.println(duration);
		System.out.println(ticketPrice);

	}

}
