package planeSimulation;

public class FlightRoute {

	// notify the menu when set
	public static boolean set = false;

	// core data: flight route
	private int length;
	private int fullLength;
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
			this.fullLength = length;

			// required for the core menu
			set = true;
		}
		catch (SimulatorConfigurationException e){
			System.err.println(e.toString());
		}
	}

	// FLIGHT ROUTE VALIDATION
	private void validateData(int length, int minHeight, int maxHeight) throws SimulatorConfigurationException {
		if (length < 2){
			throw new SimulatorConfigurationException(length + " km is not a valid route length.");
		}
		if (minHeight > 200){
			throw new SimulatorConfigurationException("The plane cannot ascend faster than 100 meters per kilometer.");
		}
		if (minHeight > maxHeight){
			throw new SimulatorConfigurationException("The minimal flight height cannot excel the maximal flight height.");
		}
		if (minHeight < 2 && length > 2){
			throw new SimulatorConfigurationException("The minimal flight height cannot be lower than 2 meters!");
		}
		if (maxHeight < 10 && length > 2){
			throw new SimulatorConfigurationException("The maximal flight height cannot be lower than 10 meters!");
		}
		if (maxHeight <= 0){
			throw new SimulatorConfigurationException("The maximal flight height cannot be negative!");
		}
		if (minHeight < 0){
			throw new SimulatorConfigurationException("The minimal flight height cannot be negative!");
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
	public int getFullLength(){
		return fullLength;
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
