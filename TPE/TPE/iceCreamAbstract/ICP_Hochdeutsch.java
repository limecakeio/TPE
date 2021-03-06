package iceCreamAbstract;

import static gdi.MakeItSimple.*;

public class ICP_Hochdeutsch extends IceCreamParlor {
	
	public ICP_Hochdeutsch(){
	}
	
	// constructor: hochdeutsch ice-cream variants
		public IceCream create(String type){

			IceCream order = null;

			// check: ice-cream available
			if (available(type)){
				order = new IC_Hochdeutsch(type);
			}
			return order;
		}

		public void greet(){
			println("\nHallo und herzlich willkommen in der Eisdiele Hochdeutsch!");
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

