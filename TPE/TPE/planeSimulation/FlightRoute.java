package planeSimulation;

public class FlightRoute {
	
	// core data: flight route
	private int length;
	private int minHeight;
	private int maxHeight;
	
	// constructor: flight route
	public FlightRoute(int length, int minHeight, int maxHeight){
		try {

			// validate route: throw exception
			validateData(length, minHeight, maxHeight);

			this.length = length;
			this.minHeight = minHeight;
			this.maxHeight = maxHeight;
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
	public int getLength(){
		return length;
	}
	public int getMinHeight(){
		return minHeight;
	}
	public int getMaxHeight(){
		return maxHeight;
	}
	public void setLength(int length){
		this.length = length;
	}
	public void setMinHeight(int minHeight){
		this.minHeight = minHeight;
	}
	public void setMaxHeight(int maxHeight){
		this.maxHeight = maxHeight;
	}
	
}
