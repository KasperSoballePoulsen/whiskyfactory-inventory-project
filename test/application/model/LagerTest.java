package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LagerTest {
    private Lager lager;
    private Fad[] fade;


    @BeforeEach
    void lavObjekter() {
        this.lager = new Lager("test", 10);
        fade = new Fad[] {lager.opretFad("Cherry", 10, "Leverandoer"),
                lager.opretFad("Cherry", 20, "Leverandoer"),
                lager.opretFad("Cherry", 30, "Leverandoer")
        };
    }

    @Test
    void testAtDerOprettesEtFad() {
        assertEquals("Cherry", fade[2].getType());
        assertEquals(30, fade[2].getLiterKapacitet());
        assertEquals("Leverandoer", fade[2].getLeverandoer());
    }

    @Test
    void testAtFadetStårPåKorrektPlads() {
        assertEquals(fade[0], lager.getPladser()[0]);
        assertEquals(fade[1], lager.getPladser()[1]);
        assertEquals(fade[2], lager.getPladser()[2]);
    }
}
