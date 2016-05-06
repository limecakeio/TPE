package iceCreamAbstract;

public abstract class IceCreamParlor {

	public IceCreamParlor(){	
	}
	
	// CORE ICE-CREAM PARLOR METHODS
	public void order(String type){
		greet();
		IceCream order = create(type);

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
	public abstract IceCream create(String type);

	public abstract void greet();

	public abstract void cashier(double price);

	public abstract void farewell(String type);

	public abstract void apologize(String type);

}
