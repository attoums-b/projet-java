package tec3;

public class FauxPassager implements Passager,Usager { 
  static final byte DEHORS = 0;
  static final byte ASSIS  = 1;
  static final byte DEBOUT = 2;
  byte status = DEHORS;

  String message ="???";
  /**
   * ajout de l'attribut nom ainsi que de son constructeur 
   */
  private String nom;
  
  public FauxPassager(String nom) {
	  this.nom = nom ;
  }

  public String nom() {
    return nom;
  }

  public boolean estDehors() {
    return status == DEHORS;
  }
  
  public boolean estAssis() {
    return status == ASSIS;
  }
  
 public  boolean estDebout() {
    return status == DEBOUT;
  }

  public void accepterSortie() {
    message = ":accepterSortie:";
  }

  public void accepterPlaceAssise() {
    message = ":accepterPlaceAssise:";
  }

  public void accepterPlaceDebout() {
    message = ":accepterPlaceDebout:";
  }

  public void nouvelArret(Bus bus, int numeroArret) {
    message = ":nouvelArret " + numeroArret + ":";
  }

  public void monterDans(Transport t) { // throws UsagerInvalideException {
  }
}
