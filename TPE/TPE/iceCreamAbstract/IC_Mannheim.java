package iceCreamAbstract;

import static gdi.MakeItSimple.*;

public class IC_Mannheim extends IceCream {

	public IC_Mannheim(String type) {
		super(type);
	}

	public void fill(){

		// Option I - 'Spaghettieis'
		if (getName().equals("Spageddiais")){
			setFashion("spaghettihaft");
			setSorts(new String[]{"Vanilleeis"});
			setPrice(getPrice()+ 0.15 + 2*0.50);
		}
		// Option II - 'Bananasplit'
		else if (getName().equals("Bananesplitt")){
			setFashion("normal");
			setSorts(new String[]{
					"Vanilleeis","Schokoladeneis","Erdbeereis"});
			setPrice(getPrice() +0.05 + 0.50*3);
		}
		// Option III - 'Nussbecher'
		else if (getName().equals("Nussbescha")){
			setFashion("normal");
			setSorts(new String[]{
					"Vanilleeis","Waldnusseis","Schokoladeneis"});
			setPrice(getPrice() +0.05 + 0.50*3);
		}
		println(getName()+ " besteht aus " + printArray(getSorts()));
		println("Alles wird " + getFashion() + " uff n' Haufe geworfe'.");
	}

	public void prepare(){

		// Option I - 'Spaghettieis'
		if (getName().equals("Spageddiais")){
			setContainer("Bescha");
			setPrice(0.35);
		}
		// Option II - 'Bananasplit'
		else if (getName().equals("Bananesplitt")){
			setContainer("Tella");
			setPrice(0.40);
		}
		// Option III - 'Nussbecher'
		else if (getName().equals("Nussbescha")){
			setContainer("Bescha");
			setPrice(0.35);
		}
		println("Auftrag - " + getName() + " - angenomme'.");
		println("\nSo machts der Monnemer sei " + getName() + "!");
		println(getName()+ " wird in dee " + getContainer()+ " getan.");
	}

	public void decorate(){

		// Option I - 'Spaghettieis'
		if (getName().equals("Spageddiais")){
			setExtras(new String[]{
					"Erdbeersoß'","Vanilleknusperl","Sahn","Monnemer Dreck"});
			setPrice(getPrice()+0.15 + 0.20 + 0.25);
		}
		// Option II - 'Bananasplit'
		else if (getName().equals("Bananesplitt")){
			setExtras(new String[]{
					"Schokosoß","ne Banaan'","Keks"});
			setPrice(getPrice() +0.15 + 0.50 + 0.30);
		}
		// Option III - 'Nussbecher'
		else if (getName().equals("Nussbescha")){
			setExtras(new String[]{
					"Schokosoß","Wallnüss","Keks"});
			setPrice(getPrice()+0.15 + 0.50 + 0.30);
		}
		println("Danach mach der Monnemer " + getName() + " schää.");
		println("Owedruff gibts da noch bissl " + printArray(getExtras()));

	}
}
