package planeSimulation;

@SuppressWarnings("serial")
public class SimulatorConfigurationException extends Exception {

	// exception data: unique serial identification
	private static int serialVersionUID = 1;
	
	// exception data: message & reason
	private int id;
	private String message;
	
	// constructor: default
	public SimulatorConfigurationException(){
		serialVersionUID++;
		
		this.id = serialVersionUID;
	}
	
	// constructor: fleshed out
	public SimulatorConfigurationException(String message){
		serialVersionUID++;
		
		this.id = serialVersionUID;
		this.message = message;
	}
	
	// AUXILIARY METHODS
	public String toString(){
		return "SimulatorConfigurationException #" + id + ": "
				+ message;
	}
}
