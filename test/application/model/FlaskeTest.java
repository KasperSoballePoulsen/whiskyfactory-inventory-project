package application.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlaskeTest {

    @Test
    public void testAtFlaskenBliverOprettet() {
        Lager lager = new Lager("Sall", 10);
        Destilat destilatTest = new Destilat("testNavn", "testKorn", LocalDate.of(2024, 05, 02), LocalDate.of(2024, 05, 04), "testMalt", 10, 50.2, new Medarbejder("testMNavn"));
        Fad fad = new Fad(1, "cherry", 20, lager, "Spanien");
        Medarbejder medarbejder = new Medarbejder("Snavær");
        fad.paafyld(medarbejder, destilatTest, LocalDate.now(), 10);
        Map<String, Integer> flasker = fad.aftap(medarbejder, LocalDate.of(2030, 2, 2), "MULD-1.0");

        assertEquals(1, flasker.keySet().size());
        for (String flaske : flasker.keySet()) {
            assertEquals(10, flasker.get(flaske));
        }
    }


}
