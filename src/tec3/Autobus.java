package tec3;


import java.util.ArrayList;
import java.util.List;

public class Autobus implements Transport, Bus {
	
	private JaugeNaturel nombrePlacesAssises ; 
	private JaugeNaturel nombrePlacesDebout;
	private int numeroArret;
	private List<Passager> passagersAssis;
	private List<Passager> passagersDebout;

	 /**
	  * Constructeur de la classe Autobus.
	  * Initialise les jauges et les listes de passagers.
	  * * @param placesAssisesMax Nombre maximum de places assises.
	  * @param placesDeboutMax Nombre maximum de places debout.
	  */
	public Autobus(int placesAssisesMax, int placesDeboutMax) {
	    this.nombrePlacesAssises = new JaugeNaturel(0, 0, placesAssisesMax);
	    this.nombrePlacesDebout = new JaugeNaturel(0, 0, placesDeboutMax);
	    this.numeroArret = 0;
	    
	    this.passagersAssis = new ArrayList<Passager>(placesAssisesMax);  
	    this.passagersDebout = new ArrayList<Passager>(placesDeboutMax);
	}
	
	/**
	 * @return Le nombre maximum de places assises.
	 */
	public int getNombrePlacesAssisesMax() {
		return nombrePlacesAssises.getMax();
	}
	
	/**
	 * @return Le nombre maximum de places debout.
	 */
	public int getNombrePlacesDeboutMax() {
		return nombrePlacesDebout.getMax();
	}
	
	/**
	 * @return Le nombre de places assises actuellement occupées.
	 */
	public int getNombrePlacesAssisesOccupes() {
		return nombrePlacesAssises.getValeur();
	}
	
	/**
	 * @return Le nombre de places debout actuellement occupées.
	 */
	public int getNombrePlacesDeboutOccupes() {
		return nombrePlacesDebout.getValeur();
	}
	
	/**
	 * Vrai s'il existe des places assises libres.
	 * * @return true si des places sont disponibles.
	 */
	 public boolean aPlaceAssise() {
		 return !nombrePlacesAssises.estRouge();
	 }
	 
	/**
	 * Vrai s'il existe des places debout libres.
	 * * @return true si des places sont disponibles.
	 */
	 public boolean aPlaceDebout() {
		 return !nombrePlacesDebout.estRouge();
	 }
	 
	/**
	 * Tente de faire monter un passager pour une place assise.
	 * * @param p Le passager.
	 */
	 public void demanderPlaceAssise(Passager p) {
		 if (aPlaceAssise() && p.estDehors()) {
             if (passagersAssis.add(p)) {
			    nombrePlacesAssises.incrementer();
			    p.accepterPlaceAssise();
             }
		 }
	}
	 
	/**
	 * Tente de faire monter un passager pour une place debout.
	 * * @param p Le passager.
	 */
	 public void demanderPlaceDebout(Passager p){
		if (aPlaceDebout() && p.estDehors()) {
			if (passagersDebout.add(p)) {
			    nombrePlacesDebout.incrementer();
			    p.accepterPlaceDebout();
            }
		}
	}
	
	/**
	 * Tente de changer le passager de debout à assis.
	 * * @param p Le passager.
	 */
	 public void demanderChangerEnAssis(Passager p) {
		if (aPlaceAssise() && p.estDebout()) {
            if (passagersDebout.remove(p)) {
                if (passagersAssis.add(p)) {
                    nombrePlacesDebout.decrementer();
                    nombrePlacesAssises.incrementer();
                    p.accepterPlaceAssise();
                } else {
                    passagersDebout.add(p); // Annulation de la suppression
                }
            }
		}
	}
		
	/**
	 * Tente de changer le passager d'assis à debout.
	 * * @param p Le passager.
	 */
	public void demanderChangerEnDebout(Passager p) {
		if (aPlaceDebout() && p.estAssis()) {
            if (passagersAssis.remove(p)) {
                if (passagersDebout.add(p)) {
                    nombrePlacesAssises.decrementer();
                    nombrePlacesDebout.incrementer();
                    p.accepterPlaceDebout();
                } else {
                    passagersAssis.add(p); // Annulation de la suppression
                }
            }
		}
	}
		
	/**
	 * Fait sortir le passager du bus, met à jour les jauges et change son état.
	 * * @param p Le passager.
	 */
	public void demanderSortie(Passager p) {
        boolean retire = false;

		if (p.estAssis()) {
            retire = passagersAssis.remove(p);
			if (retire) {
				nombrePlacesAssises.decrementer();
			}
		} else if (p.estDebout()) {
            retire = passagersDebout.remove(p);
			if (retire) {
				
				nombrePlacesDebout.decrementer();
			}
		}
        
        if (retire) {
		    p.accepterSortie();
        }
	}
		
	/**
	 * Incrémente le numéro d'arrêt et notifie tous les passagers actuellement dans le bus.
	 */
	@Override 
	public void allerArretSuivant() {
	    numeroArret++;
	    
	
	    
	    for(Passager p : passagersAssis) {
	        if (p.estAssis()) { 
	           p.nouvelArret(this, numeroArret);
	        }
	    }
	    
	    for(Passager p : passagersDebout) {
	        if (p.estDebout()) {
	            p.nouvelArret(this, numeroArret);
	        }
	    }
	}
		
	/**
	 * Affiche l'état actuel de l'autobus.
	 * * @return Représentation textuelle de l'état du bus.
	 */
	@Override
	public String toString() {
		return "[arret: " + numeroArret +
				", assis:"+ getNombrePlacesAssisesMax() +
				", debout:" + getNombrePlacesDeboutMax() + "]";
	}
}