package iceCream;

import static gdi.MakeItSimple.*;

public class ICP_Mannheim extends IceCreamParlor {
	
	public ICP_Mannheim(){
	}
	
	// constructor: mannheim ice-cream variant
		public IceCream create(String type){

			IceCream order = null;

			// check: ice-cream available
			if (available(type)){
				order = new IC_Mannheim(type);
			}
			return order;
		}

		public void greet(){
			println("\nDu has dich in die Monnemer Eisdiel verloffe!");
		}

		public void cashier(double price){
			println("\nLass ma " + price + " € rüwawachse.");
		}

		public void farewell(String type){
			println("Alla hopp! Lecker-specker dei " + type + "!");
		}

		public void apologize(String type){
			println("Nee jezz, " + type + " gibts bei uns net.");
		}

		// AXILIARY METHODS

		/* All ice-cream variants available to the local ice-cream parlor are listed here.
		 * Keep in mind that by adding more variants it's necessary to add their respective
		 * recipies to the 'fill', 'prepare' and 'decorate' methods within the ice-cream class. 
		 */
		private boolean available(String type){

			// local menu: all available ice cream variants go here.
			String[] menu = new String[]{
					"Spageddiais","Bananesplitt","Nussbescha"};

			// lookup the ice-cream in question
			for (int i = 0; i != menu.length; i++){
				if (type.equals(menu[i])){
					return true;
				}
			}
			return false;
		}

}
