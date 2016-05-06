package carDealership;

/**
 * @author Richard Vladimirskij
 * @author Konstantin Pelevin
 * */

public interface HybridCar {
	
	/**
	 * @return Returns the car's voltage as a primitive integer.
	 * */
	public int getVoltage();
	
	/**
	 * @return Returns the car's emission tier for as a primitive integer;
	 * */
	public int getEmissionTier();

}
