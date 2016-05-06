package carDealership;

/**
 * @author Richard Vladimirskij
 * @author Konstantin Pelevin
 */

public interface Electric {
	
	public final int HIGH_VOLTAGE = 600;
	public final int LOW_VOLTAGE = 480;
	
	/** @return Returns the car's voltage as a primitive integer.
	 */
	public int getVoltage();

}
