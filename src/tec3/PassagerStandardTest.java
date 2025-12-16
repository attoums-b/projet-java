package tec3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PassagerStandardTest {
	/**
	 * instantiation des passagers ainsi que des faux bus 
	 */
	
	PassagerStandard monPassager;
	PassagerStandard monAutrePassager;
	PassagerStandard monDernierPassager;

	FauxBusVide fauxbusVide;
	FauxBusPlein fauxbusPlein;
	FauxBusAssis fauxbusAssis;
	FauxBusDebout fauxbusDebout;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		/**
		 * instanciations:
		 * 	-un vrai passager qui va monter dans nos différents faux bus [dans les tests]
		 * 	-un faux bus vide (il y'a des places assises et des places debout disponibles )
		 * 	-un faux bus plein(il n'ya pas de places disponibles , assises comme debout)
		 * -un faux bus Assis(des places assises disponibles et des places debout pleines)
		 * -un faux bus Debout(des places debout disponibles et des places assises pleines)
		 */
		monPassager =  new PassagerStandard("Guillaume",3);
		monAutrePassager = new PassagerStandard("Elie", 2);
		monDernierPassager = new PassagerStandard("David",6);
		fauxbusVide = new FauxBusVide();
		fauxbusPlein = new FauxBusPlein();
		fauxbusAssis = new FauxBusAssis();
		fauxbusDebout = new FauxBusDebout();


	
		
		/**
		 *
		 * utilisation des classes factices pour faire les différents tests 
		 */

		
	}

	@After
	public void tearDown() throws Exception {
		monPassager = null;
		fauxbusVide = null;
		fauxbusPlein = null;
		fauxbusAssis = null;
		fauxbusDebout = null;
	}






	@Test
	public final void testMonterDans_BusVide() throws UsagerInvalideException {
		monPassager.monterDans(fauxbusVide);

	 /**
	  * verifier que le passager a bien demandé une place assise
	  * 
	  */
		assertEquals(":demanderPlaceAssise:", fauxbusVide.message);
		
		/**
		 * Vérifier que l'etat du passager est maintenant à Assis
		 */
		assertTrue(monPassager.estAssis());
		assertFalse(monPassager.estDehors());
		
		
	}
	
	@Test 
	public final void testMonterDans_BusPlein() throws UsagerInvalideException{
		monPassager.monterDans(fauxbusPlein);
			
		/**
		 * Vérifier que le système renvoie bien une erreur
		 */
		assertEquals("???",fauxbusPlein.message);
		
		/**
		 * Vérifier que l'etat du passager n'est pas changé
		 */
		assertTrue(monPassager.estDehors());
		assertFalse(monPassager.estAssis());
		

	}
	
	@Test 
	public final void testMonterDans_BusAssis() throws UsagerInvalideException{
		monPassager.monterDans(fauxbusAssis);
		
		/**
		 * Vérifier que le passager est bien assis
		 */
		assertTrue(monPassager.estAssis());
		assertFalse(monPassager.estDebout());
		assertFalse(monPassager.estDehors());
		
		/**
		 * vérifier que le passager a demandé à être assis 
		 * 
		 */
		assertEquals(":demanderPlaceAssise:",fauxbusAssis.message);
	}
	
	
	@Test 
	public final void testMonterDans_BusDebout() throws UsagerInvalideException{
		monPassager.monterDans(fauxbusDebout);
		
		
		/**
		 * vérifier que le passager a demandé à être debout 
		 * 
		 */
		assertEquals(":demanderPlaceDebout:",fauxbusDebout.message);
		
		/**
		 * Vérifier que le passager est bien debout
		 */
		assertTrue(monPassager.estDebout());
		assertFalse(monPassager.estAssis());
		assertFalse(monPassager.estDehors());
		
		

	}
	
/**
 * Ce test permet de tester si un passager avec un état déjà assis ou debout et qui essaie de monter
 * renvoi une erreur lorsqu'il veut monter dans le bus 
 * 
 */
	
	@Test
	public final void testMonterDans_DejaInterieur() {
		// mettre l'état du passager à assis
		monPassager.accepterPlaceAssise();
		assertTrue(monPassager.estAssis());
		/**
		 * on vérifie que l'erreur est renvoyé si un passager ayant déjà un etat veut monter 
		 */
		assertThrows(UsagerInvalideException.class, () ->{monPassager.monterDans(fauxbusVide);
			});
		assertThrows(UsagerInvalideException.class, () ->{monPassager.monterDans(fauxbusAssis);
		});
		
		assertTrue(monPassager.estAssis());
		
	}
	
	
	@Test
	public final void TestNouvelArret_Sortie() {
		monPassager.accepterPlaceAssise();
		
		monPassager.nouvelArret(fauxbusVide,3);/**
		guillaume descend à sa destination qui est la 3**/
		
		assertEquals(":demanderSortie:", fauxbusVide.message);
		
		
		
	}
	@Test
	public final void TestNouvelArret_Sortie_Debout() {
		/**
		 * on test le cas où la destination du passager a été dépassée
		 */
		monAutrePassager.accepterPlaceDebout();// va à l'arret 2
		
		monAutrePassager.nouvelArret(fauxbusVide, 3);// mais le bus est déjà à l'arret 3
		
		assertEquals(":demanderSortie:", fauxbusVide.message);
	}
	
	@Test
	public final void TestNouvelArret_Sortie_ADestination() {
		monPassager.accepterPlaceAssise(); // destination: 3
		
		//le bus est arrivé à l'arret 3
		monPassager.nouvelArret(fauxbusVide, 3);
		
		/**
		 * Vérification avec la classe faussaire
		 * le passager doit demander à sortir
		 * 
		 */
		
		assertEquals(":demanderSortie:",fauxbusVide.message);
		

	}
	
	@Test
	public final void TestNouvelArret_Sortie_ApresDestination() {
		monPassager.accepterPlaceDebout(); // destination: 3
		
		//le bus est arrivé à l'arret 4 (a donc dépassé l'arret du passager)
		monPassager.nouvelArret(fauxbusVide, 4);
		
		/**
		 * Vérification avec la classe faussaire
		 * le passager doit encore une fois  demander à sortir
		 * 
		 */
		
		assertEquals(":demanderSortie:",fauxbusVide.message);
		

	}
	/**
	 * arret inférieur à destination et le passager est à l'intérieur
	 */
	
	@Test
	public final void TestNouvelArret_NonSortie_AvantDestination() {
		monDernierPassager.accepterPlaceAssise(); // destination: 6
		
		//le bus est arrivé à l'arret 5 
		monPassager.nouvelArret(fauxbusVide, 5);
		
		/**
		 * Vérification avec la classe faussaire
		 * le passager ne dois pas descendre car ce n'est pas sa destination
		 * 
		 */
		
		assertEquals("???",fauxbusVide.message);
		assertTrue(monDernierPassager.estAssis());
		assertFalse(monDernierPassager.estDehors());
		

	}
	
	@Test
	public final void TestNouvelArret_DejaDehors_AvantDestination() {
		 //monDernierPassager est dehors par défaut 
		
		//le bus est arrivé à l'arret 5 
		monDernierPassager.nouvelArret(fauxbusVide, 5);
		
		/**
		 * Vérification avec la classe faussaire
		 * l'etat du passager ne doit pas changer
		 * 
		 */
		
		assertEquals("???",fauxbusVide.message);
		assertTrue(monDernierPassager.estDehors());
		

	}
	
	
	/**
	 * Teste le cas où le passager veut s'assoir à l'arrivée à 
	 * un nouvel arret(il y'a des places assises libres)
	 */
	
	@Test
	public final void TestNouvelArret_ChangeEnAssis() {
		monAutrePassager.accepterPlaceDebout();
		monAutrePassager.nouvelArret(fauxbusVide, 1);
		
		assertEquals(":demanderChangerEnAssis:",fauxbusVide.message);
		
		
		
	}
	/**
	 * teste le cas où le passager demande à s'assoir au nouvel arret alors que
	 * les places assises du bus sont pleines
	 */
	
	@Test public final void TestNouvelArret_ChangeEnAssis_Echec() {
		monPassager.accepterPlaceDebout();
		
		monPassager.nouvelArret(fauxbusDebout, 1);
		
		assertEquals("???",fauxbusDebout.message);
		assertTrue(monPassager.estDebout()); // vérifie que l'état reste inchangé
		
	}
	/**
	 * Teste le cas où le passager veut se mettre debout à l'arrivée à 
	 * un nouvel arret(il y'a des places debout libres)
	 */
	
	@Test
	public final void TestNouvelArret_ChangeEnDebout() {
		monAutrePassager.accepterPlaceAssise();
		monAutrePassager.nouvelArret(fauxbusVide,1);
		assertEquals(":demanderChangerEnDebout:",fauxbusVide.message);
		
	}
	
	@Test
	public final void TestNouvelArret_ChangeEnDebout_Echec() {
		monAutrePassager.accepterPlaceAssise();
		
		monAutrePassager.nouvelArret(fauxbusAssis, 1);
		assertEquals("???",fauxbusDebout.message);
		assertTrue(monAutrePassager.estAssis());
	}
	
	
	



}


