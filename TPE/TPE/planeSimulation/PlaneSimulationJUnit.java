package planeSimulation;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlaneSimulationJUnit {

	@Test
	public void flyNextKilometerTest_01() throws GeneralFlightSimulatorException {
		boolean thrown = false;
		FlightRoute route = new FlightRoute(10,10,50);
		PlaneSimulation plane = new PlaneSimulation(route);

		// test: 01
		try {
			plane.shutDoors();
			plane.flyNextKilometer(-10);
		} 
		catch (GeneralFlightSimulatorException e){
			thrown = true;
			assertTrue(thrown);
		}
	}
	
	@Test
	public void flyNextKilometerTest_02() throws GeneralFlightSimulatorException {
		boolean thrown = false;
		FlightRoute route = new FlightRoute(10,10,50);
		PlaneSimulation plane = new PlaneSimulation(route);

		try {
			plane.shutDoors();
			plane.flyNextKilometer(10);
		} 
		catch (GeneralFlightSimulatorException e){
			thrown = true;
		}
		assertTrue(!thrown);
	}
	
	@Test
	public void flyNextKilometerTest_03() throws GeneralFlightSimulatorException {
		boolean thrown = false;
		FlightRoute route = new FlightRoute(10,10,50);
		PlaneSimulation plane = new PlaneSimulation(route);

		// test: 01
		try {
			plane.shutDoors();
			plane.flyNextKilometer(10);
			plane.flyNextKilometer(10);
			plane.flyNextKilometer(50);
		} 
		catch (GeneralFlightSimulatorException e){
			thrown = true;
			assertTrue(thrown);
		}
	}

}
