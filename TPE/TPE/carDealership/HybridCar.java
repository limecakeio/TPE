package carDealership;

public class HybridCar extends Car implements Electric, Gasoline {

	/** data: hybrid car */
	int voltage;
	int emissionTier;
	
	/** constructor: hybrid car */
	public HybridCar(int id, String brand, int price, int constructionYear, int emissionTier, int voltage){
		super(id, brand, price, constructionYear);
		this.voltage = voltage;
		this.emissionTier = emissionTier;
		
	}

	// GETTER & SETTER
	public int getVoltage(){
		return voltage;
	}
	public int getEmissionTier(){
		return emissionTier;
	}
	public void setVoltage(int voltage){
		this.voltage = voltage;
	}
	public void setEmissionTier(int emissionTier){
		this.emissionTier = emissionTier;
	}

}
