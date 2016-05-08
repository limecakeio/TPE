package carDealership;

/**
 * TPE EXCERSICE SHEET#2
 * EXCERSICE #3  
 * @author - Richard Vladimirskij 
 * @author - Konstantin Pelevin
 * */

@SuppressWarnings("rawtypes")
public abstract class Car implements Comparable {
	
	/** unique dealer id-number */
	int id;
	
	/** data: default car object */
	private String brand;
	private int price;
	private int constructionYear;
	
	/** constructor: car object */
	public Car(int id, String brand, int price, int constructionYear){
		
		this.id = id;
		this.brand = brand;
		this.price = price;
		this.constructionYear = constructionYear;
	}
	
	public int compareTo(Object otherCar) {
		int result = 0;
		
		/** check: invoking object vs. param object */
		if (getID() > ((Car)otherCar).getID()){
			result = 1;
		}
		else if (getID() < ((Car)otherCar).getID()){
			result = -1;
		}
		return result;
	}
	
	// GETTER & SETTER METHODS
	public int getID(){
		return id;
	}
	public String getBrand(){
		return brand;
	}
	public int getPrice(){
		return price;
	}
	public int getConstrYear(){
		return constructionYear;
	}
	public void setID(int id){
		this.id = id;
	}
	public void setBrand(String brand){
		this.brand = brand;
	}
	public void setPrice(int price){
		this.price = price;
	}
	public void setConstrYear(int year){
		this.constructionYear = year;
	}
	
	// SPECIAL: BTREE SUPPORT
	public static int transformInteger(Object value) {
		if(value != null) {
			int newValue = ((Car) value).getID();
			return newValue;
		}
		return 0;
	}
	
	public String toString(){
		return id + "";
	}
}
