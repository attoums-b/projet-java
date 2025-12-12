package tec3;

import tec3.EtatPassager.Etat;

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
	
	public void setNom(String n) {
		this.nom = n;
	}
	
	public int getDestination() {
		return destination;
	}
	
	public void setDestination(int d) {
		this.destination = d;
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
	
	/**
	 * change l'état courant du passager en "Assis"
	 */


	public void accepterPlaceAssise() {
		etat.assis();
		
	}
	
	/**
	 * change l'état courant du passager en "Debout"
	 */
	public void accepterPlaceDebout() {
		etat.debout();
	
		
	}	
	
	/**
	 * change l'état courant du passager en "Dehors"
	 */
	public void accepterSortie() {
		etat.dehors();
		
	}
	
	  /**
	   * informer le passager qu'il est à un nouvel arret
	   * Etudier le comportement du passager:
	   *  -si il est arrivé ou a dépassé sa destination et qu'il est assis ou debout dans le bus, il sort
	   *  -sinon , il peut décider de changer de place selon son etat actuel(mais on vérifie à chaque fois
	   *  si c'est possible )
	   * 
	   */

	  // Etudier le comportement du passager
	
	  public void nouvelArret(Bus bus, int numeroArret) {

		  if(numeroArret >= destination && etat.estInterieur()) {
			  bus.demanderSortie(this);
			  return;
			  
		  } if (etat.estDebout() && bus.aPlaceAssise()) {
			  bus.demanderChangerEnAssis(this);
			  
		  }else if (etat.estAssis() && bus.aPlaceDebout()) {
			  bus.demanderChangerEnDebout(this);


		  }

	  }

			 
			  /**
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
		  return nom.toString() + " " + etat.toString().toLowerCase();
	  }
}
	  

		  
		  
		  
		  
		  
		  
		  
		  


	









