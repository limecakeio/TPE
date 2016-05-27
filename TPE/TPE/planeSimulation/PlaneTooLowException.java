package planeSimulation;

@SuppressWarnings("serial")
public class PlaneTooLowException extends GeneralFlightSimulatorException {
	
	// exception data: message & reason
		private int height;
			
			// constructor: default
			public PlaneTooLowException(){
				height = 0;
			}
			
			// constructor: fleshed out
			public PlaneTooLowException(int height){
				super();
				this.height = height;
			}
			
			// AUXILIARY METHODS
			public String toString(){
				return "PlaneTooLowException #" + super.getID() + ": "
						+ "The plane is not allowed to fly lower than " + FlightRoute.getMinHeight() + "."
						+ "Current plane height is: " + height + " meters.";
			}

}
