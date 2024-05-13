package application.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class DestillatTest {
    @Test
    void testAtDerOprettesEtDestilat() {
        Destillat batchTest = new Destillat("testNavn", "testKorn", LocalDate.of(2024, 05, 02), LocalDate.of(2024, 05, 04), "testMalt", 10, 50.2, "Medarbejder");
        assertEquals("testNavn", batchTest.getNavn());
        assertEquals("testKorn", batchTest.getKornsort());
        assertEquals(LocalDate.of(2024, 05, 02), batchTest.getStartdato());
        assertEquals(LocalDate.of(2024, 05, 04), batchTest.getSlutdato());
        assertEquals("testMalt", batchTest.getMaltdestillat());
        assertEquals(10, batchTest.getMaengdeVaeskeILiter());
        assertEquals(50.2, batchTest.getAlkoholprocent());
    }
}
