package tec3;

import java.util.ArrayList;

import tec3.Passager;

public class Autobus implements Transport, Bus {
	/**La class Autobus contient:
	 * 	-le nombre de places assises 
	 * 	-le nombre de places debout 
	 * 	-le numero de l'arret
	 * Pour le stockage des passagers, on a :
	 * 	-la liste des passagersAssis 
	 * 	-enfin la liste des passagers debout 
	 * On utilise ici des arrayList pour permettre de le stockage facile et dynamique de nos passagers
	 * 
	 * 
	 * Les deux jauges naturelles permettront de représenter 3 valeurs
	 * -Pour les places debouts et les places assises:
	 * 			-la valeur minimale de places assise
	 * 			-la valeur maximale de places assise
	 * 			-la valeur actuelle de places assises(pour gérer par exemple le fait que 
	 * 			une personne veuille s'assoir et donc vérifier qui il y'a des places assises libres
	 * 			ou encore qu'une personne peut etre dehors et rentrer donc vérifier qu'il y'a des places
	 * 			debouts disponibles ou des places assises disponibles 
	 */
	private JaugeNaturel nombrePlacesAssises ; // avec un nombre min et un nombre max
	private JaugeNaturel nombrePlacesDebout;// idem
	private int numeroArret;
	private ArrayList<Passager> passagersAssis;
	private ArrayList<Passager> passagersDebout;

	 /**
	  * CONSTRUCTEUR DE LA CLASSE AUTOBUS :
	  * 
	  * 
	  * 
	  * @param nombresPlacesAssisesMax : permet d'instancier une jauge avec un nombre de places assises
	  * @param nombresPlacesDeboutMax :permet d'instancier une jauge avec un nombre de places debout
	  */

	public Autobus(int nombresPlacesAssisesMax,int nombresPlacesDeboutMax){
		
		this.nombrePlacesAssises = new JaugeNaturel(0,0,nombresPlacesAssisesMax);
		this.nombrePlacesDebout = new JaugeNaturel(0, 0,nombresPlacesDeboutMax);
		this.numeroArret = 0;
		this.passagersAssis = new ArrayList<>();
		this.passagersDebout = new ArrayList<>();
		




	}
	
	
	/**
	 * Mise en place des getters
	 */
	
	public int getNombrePlacesAssisesMax() {
		return nombrePlacesAssises.getMax();
	}
	
	public int getNombrePlacesDeboutMax() {
		return nombrePlacesDebout.getMax();
	}
	
	public int getNombrePlacesAssisesOccupes() {
		return nombrePlacesAssises.getValeur();
	}
	
	public int getNombrePlacesDeboutOccupes() {
		return nombrePlacesDebout.getValeur();
	}

	/**
	 * On ne peut pas faire ici de setters car le nombre maximal et minimal de places assises et de places debout
	 *  sont des constantes
	 * (on été définis comme final dans JaugeNaturel)
	 */
	
	
	 public void ajouterPassagerDebout(Passager p) {
		 passagersDebout.add(p);
	 }
	 
	 public void ajouterPassagerAssis(Passager p) {
		 passagersAssis.add(p);

	 }
	 
	 public void retirerPassagerDebout(Passager p) {
		 passagersDebout.remove(p);

	 }
	 
	 public void retirerPassagerAssis(Passager p) {
		 passagersDebout.remove(p);

	 }
	 
	 
	 
	
	
	
	
	  /**
	   * vrai s'il existe des places assises.
	   * On vérifie ici que l'état de la jauge du nombre de places assises n'est pas rouge (! val <= min )
	   * @return vrai s'il existe des places assises
	   */
	
	 public boolean aPlaceAssise() {
		 /**
		  * il y'a une place assise si la jauge du nombre de places assis n'est pas rouge
		  */
		 return !nombrePlacesAssises.estRouge();
	 }
	 
	  /**
	   * vrai s'il existe des places debouts.
	   * On vérifie ici que l'état de la jauge du nombre de places debout n'est pas rouge
	   * 
	   * @return vrai s'il existe des places debouts
	   */
	 
	 public boolean aPlaceDebout() {
		 /**
		  *  il y'a une place debout si la jauge du nombre de places debout n'est pas rouge
		  */
		 return !nombrePlacesDebout.estRouge();
	 }
	 

	 
	 
	
	 public void demanderPlaceAssise(Passager p) {
		 if(aPlaceAssise() && p.estDehors()) {
			 nombrePlacesAssises.incrementer();
			 p.accepterPlaceAssise();
			 ajouterPassagerAssis(p);

		 }

		 }
	 /**
	  * 
	  */
	
	 public void demanderPlaceDebout(Passager p){
		if(aPlaceDebout() && p.estDehors()) {
		
			nombrePlacesDebout.incrementer();
			p.accepterPlaceDebout();
			ajouterPassagerDebout(p);


			// si il y'a des places debout dispo 
			// changer l'etat du passager de dehors en debout
			// incrémenter la valeur de la jauge de nombre de places debout
		}
		
		

		}
	
	 public void demanderChangerEnAssis(Passager p) {
			if(aPlaceAssise() && p.estDebout()) {
				nombrePlacesDebout.decrementer();
				nombrePlacesAssises.incrementer();
				p.accepterPlaceAssise();
				retirerPassagerDebout(p);
				ajouterPassagerAssis(p);

			}

		}
		
	public void demanderChangerEnDebout(Passager p) {
			if (aPlaceDebout() && p.estAssis()) {
				nombrePlacesAssises.decrementer();
				nombrePlacesDebout.incrementer();
				p.accepterPlaceDebout();
				retirerPassagerAssis(p);
				ajouterPassagerDebout(p);

				
			}
		}
		
		/**
		 * -si il est assis : la place sur laquelle il était
		 * assis est libérée
		 * -idem pour si il était debout
		 * -sinon on le fait sortir(accepterSortie --> passer de assis ou debout à dehors)
		 * @param p : le passager
		 */
		
	public void demanderSortie(Passager p) {
			if(p.estAssis()) {
				nombrePlacesAssises.decrementer();
				retirerPassagerAssis(p);

				
			}else if (p.estDebout()) {
				nombrePlacesDebout.decrementer();
				retirerPassagerDebout(p);

			}
			p.accepterSortie();
		}
		
		/**
		 * Cette methode:
		 * 		-incrémente l'arret de 1
		 * 		-informe les passagers qu'ils sont au nouvel arret (avec la fonction nouvelArret) en:
		 * 			-parcourant la liste des passagers assis et la liste des passagers debout
		 * 				-pour chaque passager des assis et des debout, on active la fonction monterDans
		 * 	ce qui suppose que soit pour un passager p donné, il reste à son état normal , change d'état au sein du bus ou sort du bus
		 * (en fonction des critères définis dans monterDans)
		 * 
		 */
		
	public void allerArretSuivant() throws UsagerInvalideException {
			
			numeroArret ++;

		}
		
		
		/**
		 * afficher correctement l'état entier d'un autobus
		 * 
		 * @return : cela retourne un arret , son nombre d'assis et son nombre de debouts
		 * comme défini dans simple
		 */
		@Override
		public String toString() {
			return "[arret: " + numeroArret +
					", assis:"+ getNombrePlacesAssisesMax() +
					", debout:" + getNombrePlacesDeboutMax() + "]";
		}
 
		 

}

