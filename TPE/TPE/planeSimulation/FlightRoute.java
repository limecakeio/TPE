package planeSimulation;

public class FlightRoute {
	
	// core data: flight route
	private static int length;
	private static int minHeight;
	private static int maxHeight;
	
	// constructor: flight route
	public FlightRoute(int length, int minHeight, int maxHeight){
		try {

			// validate route: throw exception
			validateData(length, minHeight, maxHeight);

			FlightRoute.length = length * 1000;
			FlightRoute.minHeight = minHeight * 1000;
			FlightRoute.maxHeight = maxHeight * 1000;
		}
		catch (SimulatorConfigurationException e){
			System.err.println(e.toString());
		}
	}
	
	// FLIGHT ROUTE VALIDATION
	private void validateData(int length, int minHeight, int maxHeight) throws SimulatorConfigurationException {
		if (length < 1){
			throw new SimulatorConfigurationException(length + " is not a valid route length.");
		}
		else if (length == 2 && minHeight < 0 || length > 2 && minHeight != 2){
			throw new SimulatorConfigurationException("Minimal route height " + minHeight 
					+ " does not match the route length.");
		}
		else if (maxHeight < minHeight){
			throw new SimulatorConfigurationException("Maximal route height " + maxHeight 
					+ " cannot be inferior to the minimal route length " + minHeight);
		}
		// Future additional restrictions go here.
	}

	// GETTER & SETTER (JIC)
	public static int getLength(){
		return length;
	}
	public static int getMinHeight(){
		return minHeight;
	}
	public static int getMaxHeight(){
		return maxHeight;
	}
	public static void setLength(int length){
		FlightRoute.length = length * 1000;
	}
	public static void setMinHeight(int minHeight){
		FlightRoute.minHeight = minHeight * 1000;
	}
	public static void setMaxHeight(int maxHeight){
		FlightRoute.maxHeight = maxHeight * 1000;
	}
	
	// ADDITIONAL METHODS
	
	
}
