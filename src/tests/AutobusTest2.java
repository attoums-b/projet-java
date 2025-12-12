package tec;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test JUnit4 pour {@link Autobus}.
 * <p>
 * Vérifie les principales fonctionnalités du bus :
 * - disponibilité des places
 * - montée assise/debout
 * - changement de place
 * - sortie
 * - progression aux arrêts
 * <p>
 * Utilise deux passagers : Roméo et Sadia.
 */
public class AutobusTest2 {

    private Autobus bus;
    private PassagerStandard romeo;
    private PassagerStandard sadia;

    /**
     * Préparation avant chaque test :
     * création d'un bus avec 1 place assise et 1 place debout,
     * et deux passagers Roméo et Sadia.
     */
    @Before
    public void setUp() {
        bus = new Autobus(1, 1);
        romeo = new PassagerStandard("Roméo", 1);
        sadia = new PassagerStandard("Sadia", 2);
    }

    /**
     * Nettoyage après chaque test.
     */
    @After
    public void tearDown() {
        bus = null;
        romeo = null;
        sadia = null;
    }

    /**
     * Vérifie que la place assise est disponible au départ,
     * puis qu'elle n'est plus disponible après que Roméo s'y installe.
     */
    @Test
    public void testAPlaceAssise() {
        assertTrue(bus.aPlaceAssise());
        bus.demanderPlaceAssise(romeo);
        assertFalse(bus.aPlaceAssise());
    }

    /**
     * Vérifie que la place debout est disponible au départ,
     * puis qu'elle n'est plus disponible après que Sadia s'y installe.
     */
    @Test
    public void testAPlaceDebout() {
        assertTrue(bus.aPlaceDebout());
        bus.demanderPlaceDebout(sadia);
        assertFalse(bus.aPlaceDebout());
    }

    /**
     * Vérifie que Roméo peut monter assis
     * et que son état est bien mis à jour.
     */
    @Test
    public void testDemanderPlaceAssise() {
        bus.demanderPlaceAssise(romeo);
        assertTrue(romeo.estAssis());
    }

    /**
     * Vérifie que Sadia peut monter debout
     * et que son état est bien mis à jour.
     */
    @Test
    public void testDemanderPlaceDebout() {
        bus.demanderPlaceDebout(sadia);
        assertTrue(sadia.estDebout());
    }

    /**
     * Vérifie qu'un passager assis (Roméo) peut changer en debout.
     */
    @Test
    public void testDemanderChangerEnDebout() {
        bus.demanderPlaceAssise(romeo);
        bus.demanderChangerEnDebout(romeo);
        assertTrue(romeo.estDebout());
    }

    /**
     * Vérifie qu'un passager debout (Sadia) peut changer en assis.
     */
    @Test
    public void testDemanderChangerEnAssis() {
        bus.demanderPlaceDebout(sadia);
        bus.demanderChangerEnAssis(sadia);
        assertTrue(sadia.estAssis());
    }

    /**
     * Vérifie qu'un passager peut sortir du bus
     * et que son état est bien mis à jour.
     */
    @Test
    public void testDemanderSortie() {
        bus.demanderPlaceAssise(romeo);
        bus.demanderSortie(romeo);
        assertTrue(romeo.estDehors());
    }

    /**
     * Vérifie que le bus avance correctement d'un arrêt à l'autre.
     * Ici, on ne teste pas la sortie automatique des passagers
     * car la version minimaliste du bus ne gère pas leur notification.
     */
    @Test
    public void testAllerArretSuivant() throws UsagerInvalideException {
        bus.allerArretSuivant();
        assertTrue(bus.toString().contains("arret:1"));
   

        bus.allerArretSuivant();
        assertTrue(bus.toString().contains("arret:2"));
    }
}
