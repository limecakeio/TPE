package iceCream;

import static gdi.MakeItSimple.*;

public class IceCream_Original {

	/* TPE EXCERSICE-SHEET #2
	 * EXCERSICE #1
	 * 
	 * Richard Vladimirskij (1527079)
	 * Konstantin Pelevin   (1525950)
	 */
	
	// core data: ice-cream
	private String name;
	private String fashion;
	private String container;
	private double price;

	private String[] sorts;
	private String[] extras;

	// constructor: ice-cream object
	public IceCream_Original(String type){
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
	
	public void fill(){
		
		// Option I - 'Spaghettieis'
		if (getName().equals("Spaghettieis")){
			fashion = "spaghetti-artig";
			sorts = new String[]{"Vanilleeis"};
			price += (0.15 + 2*0.50);
		}
		// Option II - 'Bananasplit'
		else if (getName().equals("Bananasplit")){
			fashion = "gewöhnlich";
			sorts = new String[]{
					"Vanilleeis","Schokoladeneis","Erdbeereis"};
			price += (0.05 + 0.50*3);
		}
		// Option III - 'Nussbecher'
		else if (getName().equals("Nussbecher")){
			fashion = "gewöhnlich";
			sorts = new String[]{
					"Vanilleeis","Waldnusseis","Schokoladeneis"};
			price += (0.05 + 0.50*3);
		}
		println(getName()+ " besteht aus " + printArray(sorts));
		println("Die Eiskugeln werden dabei " + getFashion() + " platziert.");
	}

	public void prepare(){
		
		// Option I - 'Spaghettieis'
		if (getName().equals("Spaghettieis")){
			container = "Becher";
			price = 0.35;
		}
		// Option II - 'Bananasplit'
		else if (getName().equals("Bananasplit")){
			container = "Teller";
			price = 0.40;
		}
		// Option III - 'Nussbecher'
		else if (getName().equals("Nussbecher")){
			container = "Becher";
			price = 0.35;
		}
		println("Ihre Bestellung - " + getName() + " - wird nun verarbeitet.");
		println("\nDie geheime Kunst der Zubereitung von " + getName() + "!");
		println(getName()+ " wird im " + getContainer()+ " serviert.");
	}

	public void decorate(){
		
		// Option I - 'Spaghettieis'
		if (getName().equals("Spaghettieis")){
			extras = new String[]{
					"Erdbeersoße","Vanillesträusel","Sahne"};
			price += (0.15 + 0.20 + 0.25);
		}
		// Option II - 'Bananasplit'
		else if (getName().equals("Bananasplit")){
			extras = new String[]{
					"Schokosoße","Banane","Biscuit"};
			price += (0.15 + 0.50 + 0.30);
		}
		// Option III - 'Nussbecher'
		else if (getName().equals("Nussbecher")){
			extras = new String[]{
					"Schokosoße","Wallnüsse","Biscuit"};
			price += (0.15 + 0.50 + 0.30);
		}
		println("Zum Abschluss wird " + getName() + " mit Extras dekoriert.");
		println("Wichtig sind dabei " + printArray(extras));
		
	}

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
	
	// AUXILIARY METHODS
	private String printArray(String[] array){
		String output = array[0];
		
		for (int i = 1; i != array.length; i++){
			output += ", " + array[i];
		}
		return (output += ".");
	}
}
