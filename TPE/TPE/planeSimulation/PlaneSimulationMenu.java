package planeSimulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlaneSimulationMenu {

	// KEEP TRACK OF EXCEPTIONS THROWN
	public static int exception_io = 0;
	public static int exception_low = 0;
	public static int exception_high = 0;
	public static int exception_general = 0;
	public static int exception_configuration = 0;
	public static boolean simulation_success = false;

	public static void main(String[] args) throws GeneralFlightSimulatorException {

		// OUTSOURCED MENU MESSAGES
		String mess_01 = "Welcome to the Plane Simulation Menu! \nPlease define your flight route.";
		String mess_02 = "Enter the length of the route in kilometers:";
		String mess_06 = "Enter the minimal height of the route in meters:";
		String mess_03 = "Enter the maximal height of the route in meters:";
		String mess_04 = "You are all set. Good luck and watch out!";
		String mess_05 = "Please specify the change of height in meters: ";
		String mess_07 = "Simulation completed successfully: ";
		String info_01 = "Plane doors shut: ";
		String info_02 = "Current plane height: ";
		String info_03 = "Current flight route progress: ";
		String info_04 = "Maximal height allowed: ";
		String info_05 = "Minimal height allowed: ";
		String partition = "+-------------------------------------------------------------+";
		String end_ex_01 = "# configuration exceptions: ";
		String end_ex_02 = "# general simulation exceptions: ";
		String end_ex_03 = "# plane too high exceptions: ";
		String end_ex_04 = "# plane too low exceptions: ";
		String end_ex_05 = "# java IO exceptions: ";

		// core variables
		int command = 0;
		int addHeight = 0;
		int minHeight = 0;
		int maxHeight = 0;
		int routeLength = 0;
		boolean shutdown = false;

		FlightRoute route = null;
		PlaneSimulation airplane = null;
		BufferedReader cons = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(mess_01);

		// MENU: SET FLIGHT ROUTE
		while (!FlightRoute.set){
			try {
				System.out.println(mess_02);
				routeLength = Integer.parseInt(cons.readLine());
				System.out.println(mess_06);
				minHeight = Integer.parseInt(cons.readLine());
				System.out.println(mess_03);
				maxHeight = Integer.parseInt(cons.readLine());
				route = new FlightRoute(routeLength, minHeight, maxHeight);
			} 
			catch (IOException e){
				System.err.println("Aww no. Concentrate, future pilot!");
				e.printStackTrace();
			}
		}
		System.out.println(mess_04);
		airplane = new PlaneSimulation(route);

		// MENU: MAIN LOOP
		while(!shutdown){
			try {
				if (!shutdown && command == 0){

					// stat box: keep track of the airplane and the route
					System.out.println("\n" + partition);
					System.out.println(info_03 + airplane.getFlightRoute().getLength() + " / " + 
							airplane.getFlightRoute().getFullLength() + " km.");
					System.out.println(info_01 + airplane.checkDoors() + " | " + 
							info_02 + airplane.checkHeight() + " m.");
					System.out.println(info_04 + airplane.getFlightRoute().getMaxHeight() + " m. | " 
							+ info_05 + airplane.getFlightRoute().getMinHeight() + " m. ");
					System.out.println(partition);

					// list menu options
					System.out.println("Enter: [1] to open the doors of the airplane.");
					System.out.println("Enter: [2] to shut the doors of the airplane.");
					System.out.println("Enter: [3] to stop the airplane.");
					System.out.println("Enter: [4] to fly a kilometer with an altered height.");
					System.out.println("Enter: [5] to shutdown the simulation and view the statistics screen.\n");

					command = Integer.parseInt(cons.readLine());

					// command: open doors
					if (command == 1){
						command = 0;
						airplane.openDoors();
					}
					// command: shut doors
					if (command == 2){
						command = 0;
						airplane.shutDoors();
					}
					// command: stop
					if (command == 3){
						command = 0;
						airplane.stop();
					}
					// command: fly
					if (command == 4){
						command = 0;
						System.out.println(mess_05);
						addHeight = Integer.parseInt(cons.readLine());
						airplane.flyNextKilometer(addHeight);
					}
					// command: shutdown execution
					if (command == 5){
						command = 0;
						shutdown = true;
					}

					// validate command input
					if (command != 0 && (command > 5 | command < 0)){
						command = 0;
						System.out.println("Error: Wrong command line. Please try again.");
					}	
				}
			}
			catch (IOException e){
				System.err.println("Aww no. Concentrate, future pilot!");
				exception_io++;
			}
		}
		System.out.println("Your personal accomplishments!");
		System.out.println(mess_07 + simulation_success);
		System.out.println("# exceptions thrown: " 
				+ (exception_configuration + exception_general+ exception_low + exception_high + exception_io));
		System.out.println(end_ex_01 + exception_configuration);
		System.out.println(end_ex_02 + exception_general);
		System.out.println(end_ex_03 + exception_high);
		System.out.println(end_ex_04 + exception_low);
		System.out.println(end_ex_05 + exception_io);
	}
}