package tec;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test JUnit4 pour {@link Autobus}.
 * Vérifie le comportement des méthodes de l'interface Bus
 * et leur interaction avec des instances de {@link PassagerStandard}.
 */
public class AutobusTest {

    private Autobus bus;
    private PassagerStandard p1;
    private PassagerStandard p2;

    /**
     * Initialisation avant chaque test :
     * - Création d'un bus avec 1 place assise et 1 place debout
     * - Création de deux passagers (Alice et Bob)
     */
    @Before
    public void setUp() {
        bus = new Autobus(1, 1);
        p1 = new PassagerStandard("Roméo", 1);
        p2 = new PassagerStandard("Emmanuel", 2);
    }

    /**
     * Vérifie que la méthode  Autobus aPlaceAssise() retourne vrai
     * quand une place assise est disponible, puis faux après qu'un passager
     * a pris la seule place assise.
     */
    @Test
    public void testAPlaceAssise() {
        assertTrue(bus.aPlaceAssise());
        bus.demanderPlaceAssise(p1);
        assertFalse(bus.aPlaceAssise());
    }

    /**
     * Vérifie que la méthode aPlaceDebout() retourne vrai
     * quand une place debout est disponible, puis faux après qu'un passager
     * a pris la seule place debout.
     */
    @Test
    public void testAPlaceDebout() {
        assertTrue(bus.aPlaceDebout());
        bus.demanderPlaceDebout(p1);
        assertFalse(bus.aPlaceDebout());
    }

    /**
     * Vérifie que demanderPlaceAssise(Passager) place correctement
     * un passager assis et modifie son état.
     */
    @Test
    public void testDemanderPlaceAssise() {
        bus.demanderPlaceAssise(p1);
        assertTrue(p1.estAssis());
        assertFalse(p1.estDehors());
    }

    /**
     * Vérifie que demanderPlaceDebout(Passager) place correctement
     * un passager debout et modifie son état.
     */
    @Test
    public void testDemanderPlaceDebout() {
        bus.demanderPlaceDebout(p1);
        assertTrue(p1.estDebout());
        assertFalse(p1.estDehors());
    }

    /**
     * Vérifie que demanderChangerEnDebout(Passager) transforme
     * un passager assis en passager debout.
     */
    @Test
    public void testDemanderChangerEnDebout() {
        bus.demanderPlaceAssise(p1);
        bus.demanderChangerEnDebout(p1);
        assertTrue(p1.estDebout());
        assertFalse(p1.estAssis());
    }

    /**
     * Vérifie que demanderChangerEnAssi transforme
     * un passager debout en passager assis.
     */
    @Test
    public void testDemanderChangerEnAssis() {
        bus.demanderPlaceDebout(p1);
        bus.demanderChangerEnAssis(p1);
        assertTrue(p1.estAssis());
        assertFalse(p1.estDebout());
    }

    /**
     * Vérifie que demanderSortie fait sortir
     * correctement un passager du bus et modifie son état en dehors.
     */
    @Test
    public void testDemanderSortie() {
        bus.demanderPlaceAssise(p2);
        bus.demanderSortie(p2);
        assertTrue(p2.estDehors());
        assertFalse(p2.estAssis());
    }
}
