package carDealership;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import crapPack.*;

@SuppressWarnings("unused")
public class CarDealerMeetsJUnit {
	
	@Test
	public void test(){
		Car[] dealersBooty = new Car[4];
		dealersBooty[0] = new GasolineCar(01, "BMW", 25000, 2012, 2);
		dealersBooty[1] = new GasolineCar(02, "Audi", 22000, 2011, 3);
		dealersBooty[2] = new ElectricCar(03, "Tesla", 50000, 2015, Electric.HIGH_VOLTAGE);
		dealersBooty[3] = new HybridCar(04, "VW", 30000, 2014, 1, Electric.LOW_VOLTAGE);
		
		// Car: BMW
		System.out.println("Car #01");
		System.out.println("Brand: " + dealersBooty[0].getBrand());
		System.out.println("Price: " + dealersBooty[0].getPrice());
		System.out.println("Construction Year: " + dealersBooty[0].getConstrYear());
		System.out.println("Gasoline Emission Tier: " + ((Gasoline) dealersBooty[0]).getEmissionTier()+ "\n");
		
		// Car: Audi
		System.out.println("Car #02");
		System.out.println("Brand: " + dealersBooty[1].getBrand());
		System.out.println("Price: " + dealersBooty[1].getPrice());
		System.out.println("Construction Year: " + dealersBooty[1].getConstrYear());
		System.out.println("Gasoline Emission Tier: " + ((Gasoline) dealersBooty[1]).getEmissionTier()+ "\n");
		
		// Car: Tesla
		System.out.println("Car #03");
		System.out.println("Brand: " + dealersBooty[2].getBrand());
		System.out.println("Price: " + dealersBooty[2].getPrice());
		System.out.println("Construction Year: " + dealersBooty[2].getConstrYear());
		System.out.println("Electric Voltage: " + ((Electric) dealersBooty[2]).getVoltage()+ "\n");
	
		// Car: VW
		System.out.println("Car #04");
		System.out.println("Brand: " + dealersBooty[3].getBrand());
		System.out.println("Price: " + dealersBooty[3].getPrice());
		System.out.println("Construction Year: " + dealersBooty[3].getConstrYear());
		System.out.println("Gasoline Emission Tier: " + ((Gasoline) dealersBooty[3]).getEmissionTier());
		System.out.println("Electric Voltage: " + ((Electric) dealersBooty[3]).getVoltage()+ "\n");
	}
	
	@Test
	public void compareToTest(){
		Car[] dealersBooty = new Car[4];
		dealersBooty[0] = new GasolineCar(01, "BMW", 25000, 2012, 2);
		dealersBooty[1] = new GasolineCar(02, "Audi", 22000, 2011, 3);
		dealersBooty[2] = new ElectricCar(03, "Tesla", 50000, 2015, Electric.HIGH_VOLTAGE);
		dealersBooty[3] = new HybridCar(04, "VW", 30000, 2014, 1, Electric.LOW_VOLTAGE);
		
		// this.object < param.object
		assertTrue((dealersBooty[0].compareTo(dealersBooty[1]) == -1));
		assertTrue((dealersBooty[1].compareTo(dealersBooty[2]) == -1));
		assertTrue((dealersBooty[2].compareTo(dealersBooty[3]) == -1));
		
		// this.object > param.object
		assertTrue((dealersBooty[1].compareTo(dealersBooty[0]) == 1));
		assertTrue((dealersBooty[2].compareTo(dealersBooty[1]) == 1));
		assertTrue((dealersBooty[3].compareTo(dealersBooty[2]) == 1));
		
		// this.object == param.object
		assertTrue((dealersBooty[0].compareTo(dealersBooty[0]) == 0));
		assertTrue((dealersBooty[1].compareTo(dealersBooty[1]) == 0));
		assertTrue((dealersBooty[2].compareTo(dealersBooty[2]) == 0));
		assertTrue((dealersBooty[3].compareTo(dealersBooty[3]) == 0));
		
	}
	
	@Test
	public void bTeeStorageTest(){
		Car car1 = new GasolineCar(01, "BMW", 25000, 2012, 2);
		Car car2 = new GasolineCar(02, "Audi", 22000, 2011, 3);
		Car car3 = new ElectricCar(03, "Tesla", 50000, 2015, Electric.HIGH_VOLTAGE);
		Car car4 = new HybridCar(04, "VW", 30000, 2014, 1, Electric.LOW_VOLTAGE);
		
		BTree dealership = new BTree(1);
		dealership.insert(car1);
		dealership.insert(car2);
		dealership.insert(car3);
		dealership.insert(car4);
		
		dealership.printLevelorder();
		System.out.println("END");
	}

}
