package iceCream;

public abstract class IceCream {

	// core data: ice-cream
	private String name;
	private String fashion;
	private String container;
	private double price;

	private String[] sorts;
	private String[] extras;

	// constructor: ice-cream object
	public IceCream(String type){
		name = type;
	}

	// CORE METHODS

	/* PRICE TABLE
	 * ------------
	 * 
	 * The price for each ice-cream is calculated separately,
	 * depending on the materials used for the creation. Prices occur as followed:
	 * 
	 * Container
	 * ---------
	 * Cup - 0.35
	 * Plate - 0.40
	 * Waffle - 0.50
	 * 
	 * Fashion
	 * --------
	 * Solid - 0.05
	 * Spaghettify - 0.15
	 * 
	 * Sorts
	 * -----
	 * 0.50 each
	 * 
	 * Etras
	 * ------
	 * Dip - 0.15
	 * Fruit - 0.50
	 * Cream - 0.20
	 * Biscuit - 0.30
	 * Sprinkles - 0.25
	 */

	public abstract void fill();

	public abstract void prepare();

	public abstract void decorate();

	// GETTER && SETTER METHODS
	public String getName(){
		return name;
	}

	public String getFashion(){
		return fashion;
	}

	public String getContainer(){
		return container;
	}

	public double getPrice(){
		return price - price % 0.01d;
	}

	public String[] getSorts(){
		return sorts;
	}

	public String[] getExtras(){
		return extras;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setFashion(String fashion){
		this.fashion = fashion;
	}

	public void setContainer(String container){
		this.container = container;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public void setSorts(String[] sorts){
		this.sorts = sorts;
	}

	public void setExtras(String[] extras){
		this.extras = extras;
	}

	// AUXILIARY METHODS
	public String printArray(String[] array){
		String output = array[0];

		for (int i = 1; i != array.length; i++){
			output += ", " + array[i];
		}
		return (output += ".");
	}
}
