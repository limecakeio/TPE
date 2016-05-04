package iceCream;

import static gdi.MakeItSimple.*;

public class ICP_English extends IceCreamParlor {
	
	public ICP_English(){
	}
	
	// constructor: hochdeutsch ice-cream variants
		public IceCream create(String type){

			IceCream order = null;

			// check: ice-cream available
			if (available(type)){
				order = new IC_English(type);
			}
			return order;
		}

		public void greet(){
			println("\nWelcome to our english-speaking ice-cream parlor!");
		}

		public void cashier(double price){
			println("\nThat makes " + price + " €, please.");
		}

		public void farewell(String type){
			println("See you! Enjoy your " + type + "!");
		}

		public void apologize(String type){
			println("We are terribly sorry, " + type + " is not on our menu.");
			println("Please come again!");
		}

		// AXILIARY METHODS

		/* All ice-cream variants available to the local ice-cream parlor are listed here.
		 * Keep in mind that by adding more variants it's necessary to add their respective
		 * recipies to the 'fill', 'prepare' and 'decorate' methods within the ice-cream class. 
		 */
		private boolean available(String type){

			// local menu: all available ice cream variants go here.
			String[] menu = new String[]{
					"Spaghetti-formed ice-cream","Bananasplit","Nut-cup"};

			// lookup the ice-cream in question
			for (int i = 0; i != menu.length; i++){
				if (type.equals(menu[i])){
					return true;
				}
			}
			return false;
		}

}


