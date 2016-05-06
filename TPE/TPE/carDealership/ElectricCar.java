package carDealership;

public class ElectricCar extends Car implements Electric {

	/** data: electric car */
	private int voltage;
	
	/** constructor: electric car */
	public ElectricCar(int id, String brand, int price, int constructionYear, int voltage) {
		super(id, brand, price, constructionYear);
		this.voltage = voltage;
	}
	
	// GETTER & SETTER METHODS
	public int getVoltage(){
		return voltage;
	}
	public void setVoltage(int voltage){
		this.voltage = voltage;
	}
}
