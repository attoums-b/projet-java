package tec;

import tec.EtatPassager.Etat;

public class PassagerStandard implements Passager, Usager{
	private String nom;
	private int destination;
	private EtatPassager etat;
	
	
	

	public PassagerStandard(String nom,int destination,EtatPassager etat) {
		this.nom = nom;
		this.destination = destination;
		this.etat = etat;

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
		  /*
		   * Vérifier si l'etat du passager est assis ou bebout
		   */
		  if(etat.estDebout()) {
			  accepterPlaceAssise();
		   }else if (etat.estAssis()) {
			   accepterPlaceDebout();
		   }else {
			   System.out.println("erreur , vous ne pouvez pas être dans le bus et dehors");
			   accepterSortie();
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
			   * -sinon il sort du bus (accepterSortie)
		  */
	
	  
	  public void monterDans(Transport t) throws UsagerInvalideException{
		  /*
		   * -on regarde l'etat du passager
		   * si il est dehors:
		   * on le fait monter et son état passe automatiquement à debout 
		   * -ensuite il à le choix entre rester debout et être assis 
		   * -si il choisit debout on ne change pas l'etat
		   * -si il choisit assis , on change de nouveau l'etat
		   * 
		   * -si il n'est pas dehors :
		   * -c'est comme si on faisait rentrer quelqu'un qui est déjà dans un bus dans celui 
		   * qu'on veut instancier --> ça n'a pas de sens 
		   */
	}






}


