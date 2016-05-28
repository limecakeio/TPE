package planeSimulation;

@SuppressWarnings("serial")
public class SimulatorConfigurationException extends Exception {

	// exception data: message & reason
	private String message;

	// constructor: default
	public SimulatorConfigurationException(){
		super();
		PlaneSimulationMenu.exception_configuration++;
		PlaneSimulationMenu.exception_total++;
	}

	// constructor: fleshed out
	public SimulatorConfigurationException(String message){
		PlaneSimulationMenu.exception_configuration++;
		PlaneSimulationMenu.exception_total++;
		this.message = message;
	}

	// AUXILIARY METHODS
	public String toString(){
		return "SimulatorConfigurationException: " + message;
	}
}
