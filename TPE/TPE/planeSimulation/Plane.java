package planeSimulation;

public interface Plane {

	/** 
	 * Opens the doors of the airplane.
	 * Before attempting to open the doors the airplane must land and come to a halt.
	 * @throws GeneralFlightSimulatorException
	 * 				In case the airplane has not landed and stopped yet.
	 */
	public void openDoors() throws GeneralFlightSimulatorException;
	
	/**
	 * Shuts the doors of the airplane.
	 */
	public void shutDoors();
	
	/**
	 * Halts the airplane when landed.
	 * @throws GeneralFlightSimulatorException
	 * 				In case the airplane has not landed yet. 
	 */
	public void stop() throws GeneralFlightSimulatorException;
	
	/**
	 * Forces the airplane to fly one additional kilometer.
	 * @param additionalHeight
	 * 			The altered height the airplane is forced to fly.
	 * 			+ == ascending / - == descending.
	 * @throws GeneralFlightSimulatorException
	 * 			In case any problems or unexpected situations occur during the flight.  
	 */
	public void flyNextKilometer(int additionalHeight) throws GeneralFlightSimulatorException;
}
