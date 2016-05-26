package planeSimulation;

@SuppressWarnings("serial")
public class GeneralFlightSimulatorException extends Exception {

	// exception data: unique serial identification
	private static int serialVersionUID = 1;

	// exception data: message & reason
	private int id;
	private String message;
	
	// constructor: default
	public GeneralFlightSimulatorException(){
		serialVersionUID++;
		
		this.id = serialVersionUID;
	}
	
	// constructor: fleshed out
	public GeneralFlightSimulatorException(String message){
		serialVersionUID++;
		
		this.id = serialVersionUID;
		this.message = message;
	}
	
	// AUXILIARY METHODS
	public String toString(){
		return "GeneralFlightSimulatorException #" + id + ": "
				+ message;
	}
}
