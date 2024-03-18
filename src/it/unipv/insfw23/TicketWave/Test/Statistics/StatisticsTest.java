package it.unipv.insfw23.TicketWave.Test.Statistics;

import it.unipv.insfw23.TicketWave.modelController.Factory.Statistics.IStatisticsHandler;
import it.unipv.insfw23.TicketWave.modelController.Factory.Statistics.StatisticsHandlerFactory;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class StatisticsTest {
    private IStatisticsHandler statisticsHandler;
    private ArrayList<Event> events;
    private Manager manager;

    private WrapType typeResultTest;
    private WrapGenre genreResultTest;
    private WrapArtist artistResultTest;
    private WrapProv provResultTest;

    @Before
    public void setUp() {
        // Preparazione dei dati per i test
        int [] af1 = {10};
        int [] bf1 = {1040};
        double [] pf1 = {75};

        int [] af2 = {10};
        int [] bf2 = {1040};
        double [] pf2 = {75};

        int [] ac1 = {0};
        int [] bc1 = {0};
        double [] pc1 = {0};

        Festival f1 = new Festival(0, "Nameless", "Como", "Parco di Como", LocalDate.of(2024,4,20), Time.valueOf("14:04:00"), Province.COMO, Genre.EDM, Type.FESTIVAL, 2250,
                1, af1, bf1, pf1, manager, "Rooler, Salmo, Nello Taver", "Festival di musica EDM", 3);
        events.add(f1);

        Festival f2 = new Festival(0, "Nameless", "Pavia", "Parco di Pavia", LocalDate.of(2024,4,28), Time.valueOf("14:04:00"), Province.PAVIA, Genre.EDM, Type.FESTIVAL, 2250,
                1, af2, bf2, pf2, manager, "Rooler, Salmo, Nello Taver", "Festival di musica EDM", 3);
        events.add(f2);

        Concert c1 = new Concert(0, "Nameless", "Milano", "Forum", LocalDate.of(2024,3,20), Time.valueOf("14:04:00"), Province.MILANO, Genre.RAP, Type.CONCERT, 2250,
                1, ac1, bc1, pc1, manager, "Salmo", "Festival di musica EDM");
        events.add(c1);

        manager = new Manager("Giorgio", "Mastrota", "1990-01-01", "giorgiom@example.com", "eminflex", 1, "1234567890123456", events, 5, 1, LocalDate.now(), 0);


        Type[] typeNameArray = {Type.FESTIVAL, Type.CONCERT, Type.THEATER, Type.OTHER};
        double[] typeResults = {50, 100, 0, 0};
        typeResultTest = new WrapType(typeResults, typeNameArray);

        ArrayList<Genre> genreNameArray = new ArrayList<>();
        genreNameArray.add(Genre.EDM);
        ArrayList<Double> genreResults = new ArrayList<>();
        genreResults.add(50.0);
        genreResultTest = new WrapGenre(genreResults, genreNameArray);

        ArrayList<String> artistNameArray = new ArrayList<>();
        artistNameArray.add("Rooler, Salmo, Nello Taver");
        ArrayList<Double> artistResults = new ArrayList<>();
        genreResults.add(50.0);
        artistResultTest = new WrapArtist(artistResults, artistNameArray);

        ArrayList<Province> provNameArray = new ArrayList<>();
        provNameArray.add(Province.COMO);
        provNameArray.add(Province.PAVIA);
        ArrayList<Double> provResults = new ArrayList<>();
        provResults.add(50.0);
        provResults.add(50.0);
        provResultTest = new WrapProv(provResults, provNameArray);


    }

    @Test
    public void typeTest(){
        statisticsHandler = StatisticsHandlerFactory.getInstance().getStatisticsHandler();
        assertEquals(typeResultTest, statisticsHandler.typeStats(manager));
    }

    @Test
    public void ArtistTest(){
        statisticsHandler = StatisticsHandlerFactory.getInstance().getStatisticsHandler();
        assertEquals(artistResultTest, statisticsHandler.artistStats(Type.FESTIVAL, manager));
    }

    @Test
    public void genreTest(){
        statisticsHandler = StatisticsHandlerFactory.getInstance().getStatisticsHandler();
        assertEquals(genreResultTest, statisticsHandler.genreStats(Type.FESTIVAL, manager));
    }

    @Test
    public void ProvTest(){
        statisticsHandler = StatisticsHandlerFactory.getInstance().getStatisticsHandler();
        assertEquals(provResultTest, statisticsHandler.provinceStats(Type.FESTIVAL,"Rooler, Salmo, Nello Taver", manager));
    }

}