package iceCream;

import static gdi.MakeItSimple.*;

public class IC_Hochdeutsch extends IceCream {

	public IC_Hochdeutsch(String type) {
		super(type);
	}

	public void fill(){

		// Option I - 'Spaghettieis'
		if (getName().equals("Spaghettieis")){
			setFashion("spaghetti-artig");
			setSorts(new String[]{"Vanilleeis"});
			setPrice(getPrice()+ 0.15 + 2*0.50);
		}
		// Option II - 'Bananasplit'
		else if (getName().equals("Bananasplit")){
			setFashion("gewöhnlich");
			setSorts(new String[]{
					"Vanilleeis","Schokoladeneis","Erdbeereis"});
			setPrice(getPrice() +0.05 + 0.50*3);
		}
		// Option III - 'Nussbecher'
		else if (getName().equals("Nussbecher")){
			setFashion("gewöhnlich");
			setSorts(new String[]{
					"Vanilleeis","Waldnusseis","Schokoladeneis"});
			setPrice(getPrice() +0.05 + 0.50*3);
		}
		println(getName()+ " besteht aus " + printArray(getSorts()));
		println("Die Eiskugeln werden dabei " + getFashion() + " platziert.");
	}

	public void prepare(){

		// Option I - 'Spaghettieis'
		if (getName().equals("Spaghettieis")){
			setContainer("Becher");
			setPrice(0.35);
		}
		// Option II - 'Bananasplit'
		else if (getName().equals("Bananasplit")){
			setContainer("Teller");
			setPrice(0.40);
		}
		// Option III - 'Nussbecher'
		else if (getName().equals("Nussbecher")){
			setContainer("Becher");
			setPrice(0.35);
		}
		println("Ihre Bestellung - " + getName() + " - wird nun verarbeitet.");
		println("\nDie geheime Kunst der Zubereitung von " + getName() + "!");
		println(getName()+ " wird im " + getContainer()+ " serviert.");
	}

	public void decorate(){

		// Option I - 'Spaghettieis'
		if (getName().equals("Spaghettieis")){
			setExtras(new String[]{
					"Erdbeersoße","Vanillesträusel","Sahne"});
			setPrice(getPrice()+0.15 + 0.20 + 0.25);
		}
		// Option II - 'Bananasplit'
		else if (getName().equals("Bananasplit")){
			setExtras(new String[]{
					"Schokosoße","Banane","Biscuit"});
			setPrice(getPrice() +0.15 + 0.50 + 0.30);
		}
		// Option III - 'Nussbecher'
		else if (getName().equals("Nussbecher")){
			setExtras(new String[]{
					"Schokosoße","Wallnüsse","Biscuit"});
			setPrice(getPrice()+0.15 + 0.50 + 0.30);
		}
		println("Zum Abschluss wird " + getName() + " mit Extras dekoriert.");
		println("Wichtig sind dabei " + printArray(getExtras()));

	}
}

