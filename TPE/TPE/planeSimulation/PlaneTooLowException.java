package planeSimulation;

@SuppressWarnings("serial")
public class PlaneTooLowException extends GeneralFlightSimulatorException {

	// exception data: message & reason
	private int height;
	private int minheight;

	// constructor: default
	public PlaneTooLowException(){
		height = 0;
		minheight = 0;
	}

	// constructor: fleshed out
	public PlaneTooLowException(int height, int minheight){
		super();
		this.height = height;
		this.minheight = minheight;
		PlaneSimulationMenu.exception_low++;
		PlaneSimulationMenu.exception_general--;
	}

	// AUXILIARY METHODS
	public String toString(){
		return "PlaneTooLowException: "
				+ "The plane is not allowed to fly lower than " + minheight + " meters. "
				+ "Current plane height is: " + height + " meters.";
	}

}
