package tec3;



public class EtatPassager {
	/**
	 * Enum permet de représenter facilement les 3 etats d'un passager
	 * de bus : soit assis , soit debout , soit en dehors du bus
	 */
	public enum Etat { ASSIS, DEBOUT,  DEHORS}
	private Etat monEtat;
	
	public EtatPassager(Etat e) {
		monEtat = e;
		}
 
    
  public boolean estDehors() {
    return monEtat ==Etat.DEHORS;
  }

  public boolean estAssis() {
    return monEtat == Etat.ASSIS;
  }

  public boolean estDebout(){
    return monEtat == Etat.DEBOUT;
    
  }

  public boolean estInterieur() {
	  /**
	   * Si il est à l'intérieur du bus , il est forcément assis ou forcément debout
	   */
    return monEtat == Etat.ASSIS || monEtat == Etat.DEBOUT;
  }
  
  public void assis() {
	  monEtat = Etat.ASSIS;
  }
  
  public void debout() {
	  monEtat = Etat.DEBOUT;
  }
  
  public void dehors() {
	  monEtat = Etat.DEHORS;
  }


  @Override
  public String toString() {
    return "" + monEtat + "";
  }
  
}






