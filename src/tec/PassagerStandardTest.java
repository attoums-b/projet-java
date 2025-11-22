package tec;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tec.EtatPassager.Etat;

public class PassagerStandardTest {
	PassagerStandard passager1;
	PassagerStandard passager2;
	PassagerStandard passager3;
	PassagerStandard passager4;
	PassagerStandard passager5;
	Autobus bus1;
	Autobus bus2;
	Autobus bus3;
	Autobus bus4;
	
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	//instanciation de la classe 

	public void setUp() throws Exception {
		passager1 =  new PassagerStandard("Emmanuel", 5);
		passager2 = new PassagerStandard("Romeo", 10 );
		passager3 = new PassagerStandard("Julien", 9);
		passager4 = new PassagerStandard("Killian",7);
		passager5 = new PassagerStandard("Victor",8);
		bus1 = new Autobus(5,5);
		bus2 = new Autobus(10,6);
		bus3 = new Autobus(6,10);
		bus4 = new Autobus(10,10);
		/**
		 * RAPPEL DE LA STRUCTURE DE AUTOBUS :
		 * 
	public Autobus(int nbAssisActu, int nbDeboutActu){
		
		this.nombrePlacesAssises = new JaugeNaturel(1,nbAssisActu,10);
		this.nombrePlacesDebout = new JaugeNaturel(1, nbDeboutActu,10);
		this.numeroArret = 0;

		 * 
		 * 
		 * -pour le bus1 crée , 5 , 5  signifie que il a 5 places assises occupées , 
		 * 5 places debout occupées et 10 places assises et debout dans ce bus
		 * -permet de gérer le cas où il y'a des places assises et debout dispo
		 * 
		 * -pour le bus2 crée (10,6): 10 places assises occupées, 6 places debout occupées et 
		 * 10 places max assises et debout dans ce bus
		 * -permet de gérer le cas où il n'ya plus de places assises disponibles mais des places debout sont disponibles
		 * 
		 * 
		 * -pour le bus3 crée(6,10): 6 places assises occupées , 15 places debout occupées et 
		 * 15 places max assises et debout dans ce bus
		 * permet de gérer le cas où il n'ya plus de places debout disponibles , mais des places assises sont disponibles
		 * 
		 * ET ENFIN 
		 * 
		 * -Pour le bus4 crée(10,10) veut dire tout simplement qu'il y'a aucune place disponible debout comme assis
		 * 
		 * 
		 */
	}

	@After
	public void tearDown() throws Exception {
		// vider le contenu des variables crées
		passager1 = null;
		passager2 = null;
		passager3 = null;
		passager4 = null;
		passager5 = null;
		bus1 = null;
		bus2 = null;
		bus3 = null;
		bus4 = null;
	}

	@Test
	/**
	 * Ici , on vérifie que la fonction nom renvoie effectivement le nom instancié 
	 * assertEquals("Emmanuel",passager1.nom()) return True car le "Emmanuel" = "Emmanuel
	 * assertNotEquals("Elodie",passager2.nom()); return False car "Elodie" != "Romeo"
	 */
	public final void testNom() {
		assertEquals("Emmanuel",passager1.nom());
		assertNotEquals("Elodie",passager2.nom());
	}
	
	/**
	 * test permettant de vérifier si la fonction ramène l'état initial de tout les passagers instanciées
	 * sachant que tout les passagers sont dehors par défaut , il ne doit pas ramener faux 
	 */
	@Test
	public final void testEtatInitial() {
		// test permettant de tester les etat du passager
		assertFalse(passager1.estAssis());// doit retourner True(normalement)
		assertTrue(passager2.estDehors());// doit retourner True 
		assertFalse(passager3.estDebout()); // doit retourner True
			
	}
	
	/**
	 * series de test permettant de vérifier si la fonction change effectivement l'etat actuel du passager
	 */
	@Test
	public final void testAccepterPlaceAssise() {
	
		passager1.accepterPlaceAssise();// changement de l'etat du passager1 de dehors à assis
		assertTrue(passager1.estAssis());// vérifier si la fonction fait le changement demandé
		assertFalse(passager1.estDebout());
		assertFalse(passager1.estDehors());
	}
	@Test
	public final void testAccepterPlaceDebout() {
		passager2.accepterPlaceDebout();
		assertFalse(passager2.estAssis());// retourne vrai car le passager n'est plus assis (etat changé)
		assertTrue(passager2.estDebout());
		assertFalse(passager2.estDehors());
	}
	@Test
	public final void testAccepterSortie() {
		//passage du passager de dehors à debout
		passager3.accepterPlaceDebout();
		//passage du meme passager de debout à dehors
		passager3.accepterSortie();
		assertTrue(passager3.estDehors());// retourne vrai car l'etat du passager a été changé en debout
	}
	
	
	@Test
	public final void testNouvelArret() {
		passager1.accepterPlaceDebout();// le mettre en etat debout
		bus1.demanderChangerEnAssis(passager1);
		assertTrue(passager1.estAssis());
		
		
		passager2.accepterPlaceAssise();
		bus2.demanderChangerEnDebout(passager2);
		assertFalse(passager2.estAssis());
		assertTrue(passager2.estDebout());
		
	}

	
	@Test
	public final void testMonterDans() throws Exception{
		// cas de base : l'état initial du passager n'est pas dehors
		passager3.accepterPlaceAssise();
		assertTrue(bus1.aPlaceAssise());
		// permet de tester que l'exeception est bien renvoyée
		assertThrows(UsagerInvalideException.class, () -> {
			passager3.monterDans(bus1);
		});
		// si le passager veut monter dans le bus 1 ,cela renvoyera une erreur car son état ne corresond pas à sa demande
		assertTrue(passager3.estAssis());// ici il est assis dans un autre bus , pas dans le bus dans lequel il veut monter
		assertFalse(passager3.estDebout());
		
		
		
		
		
		//cas où il y'a des places debout et assises
		assertTrue(bus1.aPlaceAssise() && bus1.aPlaceDebout());
		passager1.monterDans(bus1);
		// si le passager monte dans le bus 1 il est soit debout , soit assis
		assertFalse(passager1.estDehors());
		assertTrue(passager1.estDebout() || passager1.estAssis());
		
		
		
		//cas où il n'ya que des places debout
		assertFalse(bus2.aPlaceAssise());
		assertTrue(bus2.aPlaceDebout());
		passager2.monterDans(bus2);
		
		// si le passager monte dans le bus 2 alors il forcément debout
		
		assertTrue(passager2.estDebout());// il est debout normalement
		assertFalse(passager2.estAssis()); 
		
		
		// cas où il n'ya que des places assises
		assertFalse(bus3.aPlaceDebout());
		assertTrue(bus3.aPlaceAssise());	
		passager4.monterDans(bus3);
		// si le passager monte dans le bus 3 , alors il est forcément assis 
		assertTrue(passager4.estAssis());// il est assis normalement
		
		
		
		
		// cas ou il y'a pas de places: le bus est plein
		assertFalse(bus4.aPlaceAssise());
		assertFalse(bus4.aPlaceDebout());
		passager5.monterDans(bus4);
		// si le passager monte dans le bus 4 son etat restera à dehors car il n'ya pas de places dispo dans le bus
		assertTrue(passager5.estDehors());// son Etat normalement reste à dehors
		
		
		
		

	}

		
		
	}
	
	
	
	
	
	
	


