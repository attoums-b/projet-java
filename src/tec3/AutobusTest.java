package tec3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests unitaires de la classe Autobus (avec un petit bus nombre de place: 1 Assis, 1 Debout).
 * Couverture complète des cas de succès, des limites et des états invalides.
 */
public class AutobusTest {
	
	private Autobus petitBus;
	
	@Before
	public void setUp() throws Exception {
		/**
		 * le petit bus contient une place assise et une place debout 
		 */
		petitBus = new Autobus(1, 1);
	}

	@After
	public void tearDown() throws Exception {
		petitBus = null;
	}

    /**
     * Vérification la disponibilité des places initialement et après remplissage.
     */
	@Test
	public void testPlaceDisponible() {
        assertTrue(petitBus.aPlaceAssise());
        assertTrue(petitBus.aPlaceDebout());

        petitBus.demanderPlaceAssise(new FauxPassager("p1"));
        petitBus.demanderPlaceDebout(new FauxPassager("p2"));
        /**
         * le petit bus est maintenant plein donc il n'ya plus de places assises et debout disponibles 
         */

        assertFalse(petitBus.aPlaceAssise());
        assertFalse(petitBus.aPlaceDebout());
	}
    
    /**
     * Vérification de la réussite d'une demande de place assise.
     */
	@Test
    public void testDemanderPlaceAssise_Succes() {
        FauxPassager p = new FauxPassager("p3");
        petitBus.demanderPlaceAssise(p);

        assertTrue(p.estAssis()); 
        assertEquals(1, petitBus.getNombrePlacesAssisesOccupes());
    }

    /**
     * Vérification de la réussite d'une demande de place debout.
     */
	@Test
    public void testDemanderPlaceDebout_Succes() {
        FauxPassager p = new FauxPassager("p4");
        petitBus.demanderPlaceDebout(p);

        assertTrue(p.estDebout()); 
        assertEquals(1, petitBus.getNombrePlacesDeboutOccupes());
    }


    /**
     * Test de l'échec de la demande de place assise lorsque le bus est plein.
     */
	@Test
    public void testDemanderPlaceAssise_BusPlein() {
        petitBus.demanderPlaceAssise(new FauxPassager("p5"));

        FauxPassager p = new FauxPassager("p6");
        petitBus.demanderPlaceAssise(p);

        assertTrue(p.estDehors()); 
        assertEquals(1, petitBus.getNombrePlacesAssisesOccupes());
    }

    /**
     * Test de l'échec de la demande de place debout lorsque le bus est plein.
     */
    @Test
    public void testDemanderPlaceDebout_BusPlein() {
    	petitBus.demanderPlaceDebout(new FauxPassager("p7"));

        FauxPassager p = new FauxPassager("p8");
        petitBus.demanderPlaceDebout(p);

        assertTrue(p.estDehors());
        assertEquals(1, petitBus.getNombrePlacesDeboutOccupes());
    }
    
   

    /**
     * Test de l'échec de demanderPlaceAssise() si le passager est déjà assis.
     */
    @Test
    public void testDemanderPlaceAssise_PassagerDejaAssis() {
        FauxPassager pAssis = new FauxPassager("p9");
        petitBus.demanderPlaceAssise(pAssis);
        pAssis.status = FauxPassager.ASSIS;
        
        pAssis.message = "READY_TO_FAIL"; // Réinitialisation du du mock
        petitBus.demanderPlaceAssise(pAssis); 
        
        assertTrue(pAssis.estAssis());
        assertEquals(1, petitBus.getNombrePlacesAssisesOccupes());
        assertNotEquals(":accepterPlaceAssise:", pAssis.message);
    }
    
    /**
     * Test de l'échec de demanderPlaceDebout() si le passager est déjà debout.
     */
    @Test
    public void testDemanderPlaceDebout_PassagerDejaDebout() {
        FauxPassager pDebout = new FauxPassager("p10");
        petitBus.demanderPlaceDebout(pDebout);
        pDebout.status = FauxPassager.DEBOUT;
        
        pDebout.message = "READY_TO_FAIL"; // Réinitialisation du mock
        petitBus.demanderPlaceDebout(pDebout);
        
        assertTrue(pDebout.estDebout());
        assertEquals(1, petitBus.getNombrePlacesDeboutOccupes());
        assertNotEquals(":accepterPlaceDebout:", pDebout.message);
    }
    

    /**
     * Vérification de la réussite du changement de place (Assis en Debout).
     */
    @Test
    public void testChangerEnDebout_Succes() {
        FauxPassager p = new FauxPassager("emmanuel");
        petitBus.demanderPlaceAssise(p);

        petitBus.demanderChangerEnDebout(p);

        assertTrue(p.estDebout());
        assertEquals(0, petitBus.getNombrePlacesAssisesOccupes());
        assertEquals(1, petitBus.getNombrePlacesDeboutOccupes());
    }

    /**
     * Vérification de la réussite du changement de place (Debout en  Assis).
     */
    @Test
    public void testChangerEnAssis_Succes() {
        FauxPassager p = new FauxPassager("junior");
        petitBus.demanderPlaceDebout(p);

        petitBus.demanderChangerEnAssis(p); 

        assertTrue(p.estAssis());
        assertEquals(1, petitBus.getNombrePlacesAssisesOccupes());
        assertEquals(0, petitBus.getNombrePlacesDeboutOccupes());
    }
  
    /**
     * Test de l'échec d'un changement d'état si la place désirée est pleine.
     */
    @Test
    public void testChangerEnAssis_PlaceAssisePleine() {
        FauxPassager pAssis = new FauxPassager("elena");
        petitBus.demanderPlaceAssise(pAssis);
        
        FauxPassager pDebout = new FauxPassager("hugo");
        petitBus.demanderPlaceDebout(pDebout);
        
        pDebout.message = "READY_TO_FAIL"; // Réinitialisation du mock
        petitBus.demanderChangerEnAssis(pDebout);
        
        assertTrue(pDebout.estDebout());
        assertEquals(1, petitBus.getNombrePlacesAssisesOccupes());
        assertNotEquals(":accepterPlaceAssise:", pDebout.message);
    }
    
    /**
     * Test de l'échec d'un changement d'état si le passager est dehors.
     */
    @Test
    public void testChangerEnDebout_PassagerDehors() {
        FauxPassager pDehors = new FauxPassager("hugette");
        pDehors.message = "READY_TO_FAIL";
        
        petitBus.demanderChangerEnDebout(pDehors);
        
        assertTrue(pDehors.estDehors());
        assertEquals(0, petitBus.getNombrePlacesDeboutOccupes());
        assertNotEquals(":accepterPlaceDebout:", pDehors.message);
    }
    
    /**
     * Vérifie que demanderSortie() met à jour l'état du passager et les jauges.
     */
    @Test
    public void testDemanderSortie() {
        FauxPassager p = new FauxPassager("roger");
        petitBus.demanderPlaceDebout(p);

        petitBus.demanderSortie(p);

        assertTrue(p.estDehors());
        assertEquals(0, petitBus.getNombrePlacesDeboutOccupes());
        assertEquals(":accepterSortie:", p.message);
    }
    
    /**
     * Vérifie que allerArretSuivant() notifie tous les passagers.
     */
    @Test
    public void testAllerArretSuivant_Notification() {
        FauxPassager p1 = new FauxPassager("gilbert");
        FauxPassager p2 = new FauxPassager("elie");
        
        petitBus.demanderPlaceAssise(p1);
        petitBus.demanderPlaceDebout(p2);
        
        petitBus.allerArretSuivant(); 
        
        assertEquals(1, p1.nbAppelNouvelArret);
        assertEquals(1, p2.nbAppelNouvelArret);
    }
}