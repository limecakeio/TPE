package planeSimulation;

public class PlaneSimulation implements Plane {

	// core data: airplane
	private FlightRoute route;
	private boolean planeShut;
	private boolean planeMoving;
	private boolean planeLanded;
	private int currentHeight;

	// constructor: airplane
	public PlaneSimulation(FlightRoute route){
		this.route = route;
		planeShut = false;
		planeMoving = false;
		planeLanded = true;
		currentHeight = 0;
	}

	// OUTSOURCED ERROR MESSAGES
	private String err_01 = "Cannot attempt to open the doors while the aiplane is moving or has not landed yet.";
	private String err_02 = "Cannot attempt to stop the plane while flying. You'd crash!";
	private String err_03 = "Cannot attempt to fly while the doors of the airplane are still open!";
	private String err_04 = "Cannot attempt to change the height of the flight by more than 100 meters."
			+ " Specified change of height: ";
	private String err_05 = "Cannot attempt to fly when the flight route is over!";
	private String err_06 = "Cannot attempt to descend below the ground level. You'd crash!";
	private String err_07 = "Plane doors are already open!";
	private String err_08 = "The plane is already idle!";


	// IMPLEMENTATION METHODS
	public void openDoors() throws GeneralFlightSimulatorException {
		try {
			if (planeShut){
				if (!checkMoving() && checkLanded()){
					planeShut = false;
					PlaneSimulationMenu.simulation_success = true;
				}
				// throw exception: plane is moving or has not landed yet.
				else {
					throw new GeneralFlightSimulatorException(err_01);
				}
			}
			else {
				throw new GeneralFlightSimulatorException(err_07);
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
			if (planeMoving){
				if (planeLanded){
					planeMoving = false;
				}
				// throw exception: plane has not landed yet.
				else {
					throw new GeneralFlightSimulatorException(err_02);
				}
			}
			else {
				throw new GeneralFlightSimulatorException(err_08);
			}
		}
		catch (GeneralFlightSimulatorException e){
			System.err.println(e.toString());
		}
	}

	public void flyNextKilometer(int additionalHeight) throws GeneralFlightSimulatorException {
		try {
			if (route.getLength() != 0){
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
						if (currentHeight + additionalHeight > route.getMaxHeight()){
							throw new PlaneTooHighException(currentHeight, route.getMaxHeight());
						}
						else if (currentHeight + additionalHeight < route.getMinHeight()){
							if ((route.getFullLength()-route.getLength()) >= 2 && 
									(route.getFullLength()+ route.getLength()) > (route.getFullLength()+2)){
								throw new PlaneTooLowException(currentHeight, route.getMinHeight());
							}
							else {
								if (currentHeight + additionalHeight < 0){
									throw new GeneralFlightSimulatorException(err_06);
								}
								else {
									// update: new stats
									currentHeight += additionalHeight;
									route.setLength(route.getLength()-1);

									// check: airplane landed
									if (currentHeight == 0 && route.getLength() == 0){
										planeLanded = true;
									}
								}
							}
						}
						else {
							// update: new stats
							currentHeight += additionalHeight;
							route.setLength(route.getLength()-1);
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
	public boolean checkLanded(){
		return planeLanded;
	}
	public int checkHeight(){
		return currentHeight;
	}
	public FlightRoute getFlightRoute(){
		return route;
	}

}
