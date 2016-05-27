package planeSimulation;

public class PlaneSimulation implements Plane {

	// core data: airplane
	private boolean planeShut;
	private boolean planeMoving;
	private boolean planeFlying;
	private boolean planeLanded;
	private int currentHeight;

	// constructor: airplane
	public PlaneSimulation(){

		planeShut = false;
		planeMoving = false;
		planeFlying = false;
		planeLanded = true;
		currentHeight = 0;
	}

	// OUTSOURCED ERROR MESSAGES
	private String err_01 = "Cannot attempt to open the doors while the aiplane is moving or has not landed yet.";
	private String err_02 = "Cannot attempt to stop the plane while flying. You'd crash!";
	private String err_03 = "Cannot attempt to fly while the doors of the airplane are still open!";
	private String err_04 = "Cannot attempt to change the height of the flight by more than 100 meters."
			+ "/nSpecified change of height: ";
	private String err_05 = "Cannot attempt to fly when the flight route is over!";


	// IMPLEMENTATION METHODS
	public void openDoors() throws GeneralFlightSimulatorException {
		try {
			if (!checkMoving() && checkLanded()){
				planeShut = false;
			}
			// throw exception: plane is moving or has not landed yet.
			else {
				throw new GeneralFlightSimulatorException(err_01);
			}
		}
		catch (GeneralFlightSimulatorException e){
			System.err.println(e.toString());
		}
	}

	public void shutDoors(){
		planeShut = true;
	}

	public void stop() throws GeneralFlightSimulatorException {
		try {
			if (planeLanded){
				planeMoving = false;
			}
			// throw exception: plane has not landed yet.
			else {
				throw new GeneralFlightSimulatorException(err_02);
			}
		}
		catch (GeneralFlightSimulatorException e){
			System.err.println(e.toString());
		}
	}

	public void flyNextKilometer(int additionalHeight) throws GeneralFlightSimulatorException {
		try {
			if (FlightRoute.getLength() != 0){
				// check: doors
				if (planeShut){
					planeMoving = true;
					planeLanded = false;

					// check: additional height is too high 
					if (additionalHeight > 100 || additionalHeight < -100){
						throw new GeneralFlightSimulatorException(err_04 + additionalHeight + " meters.");
					}
					else {
						// check: additional height would endanger the safe flight
						if (currentHeight + additionalHeight > FlightRoute.getMaxHeight() && FlightRoute.getLength() < 2000){
							throw new PlaneTooHighException(currentHeight + additionalHeight);
						}
						else if (currentHeight + additionalHeight < FlightRoute.getMinHeight() && FlightRoute.getLength() > 2000){
							throw new PlaneTooLowException(currentHeight + additionalHeight);
						}
						else {
							// update: new states
							currentHeight += additionalHeight;
							FlightRoute.setLength(FlightRoute.getLength()-1000);

							// check: airplane landed
							if (currentHeight <= 0 && FlightRoute.getLength() == 0){
								planeLanded = true;
							}
						}
					}
				}
				else {
					throw new GeneralFlightSimulatorException(err_03);
				}
			}
			else {
				throw new GeneralFlightSimulatorException(err_05);
			}
		}
		// catch: doors are still open
		catch (GeneralFlightSimulatorException e){
			System.err.println(e.toString());
		}	
	}
	
	// GETTER & SETTER
	public boolean checkDoors(){
		return planeShut;
	}
	public boolean checkMoving(){
		return planeMoving;
	}
	public boolean checkFlying(){
		return planeFlying;
	}
	public boolean checkLanded(){
		return planeLanded;
	}
	public int checkHeight(){
		return currentHeight;
	}

}
