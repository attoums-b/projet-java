package tec;

public class Autobus implements Transport, Bus {
	/*
	 * La jauge naturel permettra de représenter 3 valeurs
	 * -Pour les places debouts et les places assises:
	 * 			-la valeur minimale de places assise
	 * 			-la valeur maximale de places assise
	 * 			-la valeur actuelle de places assises(pour gérer par exemple le fait que 
	 * 			une personne veuille s'assoir et donc vérifier qui il y'a des places assises libres
	 * 			ou encore qu'une personne peut etre dehors et rentrer donc vérifier qu'il y'a des places
	 * 			debouts disponibles ou des places assises disponibles 
	 */
	private JaugeNaturel nombrePlacesAssises ;
	private JaugeNaturel nombrePlacesDebout;
	private PassagerStandard[] nbPassagers;
	

	public Autobus() {


	}

}
