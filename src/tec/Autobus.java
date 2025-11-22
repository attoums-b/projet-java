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
	private JaugeNaturel nombrePlacesAssises ; // avec un nombre min et un nombre max
	private JaugeNaturel nombrePlacesDebout;// idem
	private int numeroArret;// 

	/**
	 * 
	 * @param nbAssOcc capacité actuelle de places assises
	 * @param nbDebOcc capacité actuelle de places debout
	 * ici on initialise tout les bus à 20 places (donc 10 places assises et 10 places debout)
	 */

	public Autobus(int nbAssisActu, int nbDeboutActu){
		
		this.nombrePlacesAssises = new JaugeNaturel(1,nbAssisActu,10);
		this.nombrePlacesDebout = new JaugeNaturel(1, nbDeboutActu,10);
		this.numeroArret = 0;

	}
	
	// methodes associés à l'autobus 
	
	 public boolean aPlaceAssise() {
		 // il y'a une place assise si la jauge du nombre de places assis est verte
		 return nombrePlacesAssises.estVert();
	 }
	 
	 public boolean aPlaceDebout() {
		 // il y'a une place debout si la jauge du nombre de places debout est verte
		 return nombrePlacesDebout.estVert();
	 }
	 
	 

	
	 public void demanderPlaceAssise(Passager p) {
		 if(aPlaceAssise() && p.estDehors()) {
			 nombrePlacesAssises.incrementer();
			 p.accepterPlaceAssise();
		 }

		 }
	
	public void demanderPlaceDebout(Passager p){
		if(aPlaceDebout() && p.estDehors()) {
			nombrePlacesDebout.incrementer();
			p.accepterPlaceDebout();

			// si il y'a des places debout dispo 
			// changer l'etat du passager de dehors en debout
			// incrémenter la valeur de la jauge de nombre de places debout
		}
		
		

		}
	
		public void demanderChangerEnAssis(Passager p) {
			if(aPlaceAssise() && p.estDebout()) {
				nombrePlacesDebout.decrementer();
				nombrePlacesDebout.incrementer();
				p.accepterPlaceAssise();
			}

		}
		
		public void demanderChangerEnDebout(Passager p) {
			if (aPlaceDebout() && p.estAssis()) {
				nombrePlacesAssises.decrementer();
				nombrePlacesDebout.incrementer();
				p.accepterPlaceDebout();
				
			}
		}
		
		/**
		 * -si il est assis : la place sur laquelle il était
		 * assis est libérée
		 * -idem pour si il était debout
		 * -et on le fait sortir(accepterSortie --> passer de assis ou debout à dehors)
		 * @param p : le passager
		 */
		
		public void demanderSortie(Passager p) {
			if(p.estAssis()) {
				nombrePlacesAssises.decrementer();
				
			}else if (p.estDebout()) {
				nombrePlacesDebout.decrementer();
			}
			p.accepterSortie();
		}
		
		/**
		 * Cette methode:
		 * 		-incrémente l'arret
		 * 		-informe les passager qu'ils sont au nouvel arret (avec la fonction nouvelArret)
		 * 
		 */
		
		public void allerArretSuivant() throws UsagerInvalideException {
			
			numeroArret ++;

		}
		
		
		/**
		 * afficher correctement l'état entier d'un autobus
		 * 
		 * @return : cela retourne un arret , son nombre d'assis et son nombre de debouts
		 */
		@Override
		public String toString() {
			return "[arret: " + numeroArret + ", assis:"+ nombrePlacesAssises.toString() + ", debout:" + nombrePlacesDebout.toString() + "]";
		}



	
	
		 
		 
		
		 
		 

}

