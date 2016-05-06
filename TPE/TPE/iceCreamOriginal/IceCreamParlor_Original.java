package iceCreamOriginal;

import static gdi.MakeItSimple.*;

public class IceCreamParlor_Original {
	
	/* TPE EXCERSICE-SHEET #2
	 * EXCERSICE #1
	 * 
	 * Richard Vladimirskij (1527079)
	 * Konstantin Pelevin   (1525950)
	 */

	public IceCreamParlor_Original(){	
	}
	
	// CORE ICE-CREAM PARLOR METHODS
	public void order(String type){
		greet();
		IceCream_Original order = create(type);

		if (order != null) {
			order.prepare();
			order.fill();
			order.decorate();
			cashier(order.getPrice());
			farewell(order.getName());
		} 
		else {
			apologize(type);
		}
	}

	// constructor: local ice-cream variants
	public IceCream_Original create(String type){

		IceCream_Original order = null;

		// check: ice-cream available
		if (available(type)){
			order = new IceCream_Original(type);
		}
		return order;
	}

	public void greet(){
		println("Hallo und herzlich willkommen!");
	}

	public void cashier(double price){
		println("\nDas macht dann bitte " + price + " €.");
	}

	public void farewell(String type){
		println("Auf Wiedersehen! Lassen Sie sich ihr " + type + " schmecken!");
	}

	public void apologize(String type){
		println("Es tut uns schrecklich leid, aber " + type + " steht nicht auf der Speisekarte.");
		println("Kommen Sie bald wieder!");
	}

	// AXILIARY METHODS

	/* All ice-cream variants available to the local ice-cream parlor are listed here.
	 * Keep in mind that by adding more variants it's necessary to add their respective
	 * recipies to the 'fill', 'prepare' and 'decorate' methods within the ice-cream class. 
	 */
	private boolean available(String type){

		// local menu: all available ice cream variants go here.
		String[] menu = new String[]{
				"Spaghettieis","Bananasplit","Nussbecher"};

		// lookup the ice-cream in question
		for (int i = 0; i != menu.length; i++){
			if (type.equals(menu[i])){
				return true;
			}
		}
		return false;
	}

}
