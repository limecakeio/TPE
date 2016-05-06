package carDealership;

public class GasolineCar extends Car implements Gasoline {

	/** data: gasoline car */
	private int emissionTier;
	
	/** constructor: gasoline car */
	public GasolineCar(int id, String brand, int price, int constructionYear, int emissionTier){
		super(id, brand, price, constructionYear);
		this.emissionTier = emissionTier;
	}
	
	// GETTER & SETTER
	public int getEmissionTier(){
		return emissionTier;
	}
	public void setEmissionTier(int emissionTier){
		this.emissionTier = emissionTier;
	}

}
