package iceCream;

import static gdi.MakeItSimple.*;

public class IC_English extends IceCream {

	public IC_English(String type) {
		super(type);
	}

	public void fill(){

		// Option I - 'Spaghettieis'
		if (getName().equals("Spaghetti-formed ice-cream")){
			setFashion("spaghetti-formed");
			setSorts(new String[]{"vanilla"});
			setPrice(getPrice()+ 0.15 + 2*0.50);
		}
		// Option II - 'Bananasplit'
		else if (getName().equals("Bananasplit")){
			setFashion("casually");
			setSorts(new String[]{
					"vanilla","chocolate","strawberry"});
			setPrice(getPrice() +0.05 + 0.50*3);
		}
		// Option III - 'Nussbecher'
		else if (getName().equals("Nut-cup")){
			setFashion("casually");
			setSorts(new String[]{
					"vanilla","nut","chocolate"});
			setPrice(getPrice() +0.05 + 0.50*3);
		}
		println(getName()+ " is made of these ice-cream sorts: " + printArray(getSorts()));
		println("The ice-cream is placed " + getFashion() + ".");
	}

	public void prepare(){

		// Option I - 'Spaghettieis'
		if (getName().equals("Spaghetti-formed ice-cream")){
			setContainer("cup");
			setPrice(0.35);
		}
		// Option II - 'Bananasplit'
		else if (getName().equals("Bananasplit")){
			setContainer("plate");
			setPrice(0.40);
		}
		// Option III - 'Nussbecher'
		else if (getName().equals("Nut-cup")){
			setContainer("cup");
			setPrice(0.35);
		}
		println("Your order - " + getName() + " - is being prepared.");
		println("\nPreparing " + getName() + " like a Sire!");
		println(getName()+ " is served within a " + getContainer()+ ".");
	}

	public void decorate(){

		// Option I - 'Spaghettieis'
		if (getName().equals("Spaghetti-formed ice-cream")){
			setExtras(new String[]{
					"strawberry-dip","vanilla-chocolate-sprinkles","vinegar"});
			setPrice(getPrice()+0.15 + 0.20 + 0.25);
		}
		// Option II - 'Bananasplit'
		else if (getName().equals("Bananasplit")){
			setExtras(new String[]{
					"chocolate-dip","banana","biscuit"});
			setPrice(getPrice() +0.15 + 0.50 + 0.30);
		}
		// Option III - 'Nussbecher'
		else if (getName().equals("Nut-cup")){
			setExtras(new String[]{
					"chocolate-dip","some nuts","waffle"});
			setPrice(getPrice()+0.15 + 0.50 + 0.30);
		}
		println("Last but not least: " + getName() + " gets decorated.");
		println("We use the following ingredients: " + printArray(getExtras()));

	}
}


