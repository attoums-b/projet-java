package tec;

import tec.EtatPassager.Etat;

public class PassagerStandard implements Passager, Usager{
	/**
	 * Un passager Standard se caractérise par son nom , sa destination
	 * (c'est à dire là où il va) et son etat [ASSIS , DEBOUT , DEHORS]
	 */
	private String nom;
	private int destination;
	private EtatPassager etat;
	
	
	/**
	 * Constructeur permettant d'instancier un passager
	 * NB: Pour l'état du passager , nous considerons ici que
	 * l'état par défaut lors de la création d'un nouveau passager
	 * est DEHORS
	 * 
	 * @param nom : le nom du passager
	 * @param destination : où est ce que le passager veut se rendre
	 * @param etat : soit assis ,debout ou dehors
	 */
	

	public PassagerStandard(String nom,int destination) {
		this.nom = nom;
		this.destination = destination;
		this.etat = new EtatPassager(Etat.DEHORS);

	}
	
	public String nom() {
		return nom;
	}
	
	
	
	//fonctions permettant de connaitre l'etat d'un passagerStandard
	
	public boolean estAssis() {
		/*
		 * on regarde si il est assis en prenant la methode EtatPassager que contient etat
		 */
		return etat.estAssis();
		
	}
	public boolean estDebout() {
		return etat.estDebout();
		
	}
	
	public boolean estDehors() {
		return etat.estDehors(); 
		
	}
	//fonction permettant de changer l'etat d'un passagerStandard
	public void accepterPlaceAssise() {
		this.etat = new EtatPassager(Etat.ASSIS);
		
	}
	public void accepterPlaceDebout() {
		this.etat = new EtatPassager(Etat.DEBOUT);
	
		
	}	
	public void accepterSortie() {
		this.etat = new EtatPassager(Etat.DEHORS);
		
	}
	
	  public void nouvelArret(Bus bus, int numeroArret) {
		  //informer le passager qu'il est à un nouvel arret
		  System.out.println("Attention aux passagers, vous êtes arrivé à l'arret n" + numeroArret);
		  
		  // Etudier le comportement du passager
		  if(etat.estInterieur() && numeroArret >= destination) {
			  bus.demanderSortie(this);
			  
		  }else if (etat.estDebout() && bus.aPlaceAssise()) {
			  bus.demanderChangerEnAssis(this);
			  
		  }else if (etat.estAssis() && bus.aPlaceDebout()) {
			  bus.demanderChangerEnDebout(this);
		  }else {
			  System.out.println("Pas de places assises ou debout, vous devez rester à votre etat initial");
		  }

	  }
			  /*
			   * petit affichage indiquant qu'on est à un nouveau arret et input du user
			   */
			  //System.out.println("Vous êtes arrivé à l'arrêt numero" + numeroArret + " ,voulez vous changer de place ou sortir?:")
			  /*
			   * si il est arrivé à un nouveau arret , il y'a 3 cas:
			   * - si il était débout -> il peut changer et s'assoir (accepterPlaceAssis)
			   * -si il était assis -> il peut changer et se mettre debout(accepterPlaceDebout)
			   * -sinon il peut sortir du bus (accepterSortie)
		  */
	
	  
	  public void monterDans(Transport t) throws UsagerInvalideException{
		  
		  if(etat.estDehors()) {
			  Bus bus = (Bus) t;
			  if(bus.aPlaceAssise()) {
				  bus.demanderPlaceAssise(this);
			  }else if(bus.aPlaceDebout()) {
				  bus.demanderPlaceDebout(this);
			  }else if (!bus.aPlaceAssise() && !bus.aPlaceDebout()) {
				  System.out.println("il n'ya pas de places disponibles pour ce bus! désolé :(");
			  }
		  }else  {
			  throw new UsagerInvalideException("Le passager que vous essayez de faire monter est déjà dans un autre bus", this,t);
		  }
		  

		  
	  }
	  @Override
	  public String toString() {
		  return nom.toString() + " " + etat.toString();
	  }
}
	  

		  
		  
		  
		  
		  
		  
		  
		  


	









