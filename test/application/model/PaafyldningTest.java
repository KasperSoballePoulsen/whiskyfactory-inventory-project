package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaafyldningTest {
    private Lager lager;
    private Destilat destilat;
    //private Medarbejder medarbejder;
    private Fad fad1;
    private Fad fad2;



    @BeforeEach
    void setup() {
        //medarbejder = new Medarbejder("Rasmus");
        //destilat = new Destilat("Destilat1", "Byg", LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 02), "1", 37, 50.50, medarbejder);
        lager = new Lager("Sall", 10);
        fad1 = new Fad( "Bourbon", 37, lager, "USA");
        fad2 = new Fad( "Cherri", 30, lager, "Spanien");
    }

    @Test
    public void testAtFlaskenBliverOprettet() {
        //fad1.paafyld(medarbejder,destilat,LocalDate.now(),37);
        //fad2.paafyld(medarbejder,destilat,LocalDate.now(),30);
        List<Integer> mængdeTappet = new ArrayList<>();
        mængdeTappet.add(10);
        mængdeTappet.add(10);
        //Tapning tapning = new Tapning();
        //tapning.addFad(fad1);
        //tapning.addFad(fad2);
       //List<Flaske> flaskeList = tapning.fyldPaaFlasker(mængdeTappet,0,medarbejder,"Ralle Juice",50);
        assertEquals(27,fad1.getAntalLiterPaafyldt());
        assertEquals(20,fad2.getAntalLiterPaafyldt());
        //assertEquals(tapning,flaskeList.get(1).getPaaFyldning());






    }
}
