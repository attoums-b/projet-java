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
  
  
  int nbAppelNouvelArret = 0; // Ajout pour faciliter le test de notification

  public String nom() { return "FauxPassager"; }

  public boolean estDehors() { return status == DEHORS; }
  
  public boolean estAssis() { return status == ASSIS; }
  
  public boolean estDebout() { return status == DEBOUT; }

  // --- CORRECTION : Mise à jour de l'état (status) ---
  public void accepterSortie() {
    message = ":accepterSortie:";
    status = DEHORS; // Mise à jour de l'état
  }

  public void accepterPlaceAssise() {
    message = ":accepterPlaceAssise:";
    status = ASSIS; // Mise à jour de l'état
  }

  public void accepterPlaceDebout() {
    message = ":accepterPlaceDebout:";
    status = DEBOUT; // Mise à jour de l'état
  }
  // ---------------------------------------------------

  public void nouvelArret(Bus bus, int numeroArret) {
    message = ":nouvelArret " + numeroArret + ":";
    nbAppelNouvelArret++; // Incrémentation du compteur d'appels
  }

  public void monterDans(Transport t) {// throws UsagerInvalideException 
	  }
  }
