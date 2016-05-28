package planeSimulation;

@SuppressWarnings("serial")
public class PlaneTooHighException extends GeneralFlightSimulatorException {

	// exception data: message & reason
	private int height;
	private int maxheight;

	// constructor: default
	public PlaneTooHighException(){
		height = 0;
		maxheight = 0;
	}

	// constructor: fleshed out
	public PlaneTooHighException(int height, int maxheight){
		super();
		this.height = height;
		this.maxheight = maxheight;
		PlaneSimulationMenu.exception_high++;
		PlaneSimulationMenu.exception_general--;
	}

	// AUXILIARY METHODS
	public String toString(){
		return "PlaneTooHighException: "
				+ "The plane is not allowed to fly higher than " + maxheight + " meters. "
				+ "Current plane height is: " + height + " meters.";
	}
}
