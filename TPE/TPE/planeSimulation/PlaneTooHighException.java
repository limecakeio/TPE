package planeSimulation;

@SuppressWarnings("serial")
public class PlaneTooHighException extends GeneralFlightSimulatorException {

	// exception data: message & reason
	private int height;
		
		// constructor: default
		public PlaneTooHighException(){
			height = 0;
		}
		
		// constructor: fleshed out
		public PlaneTooHighException(int height){
			super();
			this.height = height;
		}
		
		// AUXILIARY METHODS
		public String toString(){
			return "PlaneTooHighException #" + super.getID() + ": "
					+ "The plane is not allowed to fly higher than " + FlightRoute.getMaxHeight() + "."
					+ "Current plane height is: " + height + " meters.";
		}
}
