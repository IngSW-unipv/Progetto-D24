package it.unipv.insfw23.TicketWave.test.Statistics;

import it.unipv.insfw23.TicketWave.modelController.factory.statistics.IStatisticsHandler;
import it.unipv.insfw23.TicketWave.modelController.factory.statistics.StatisticsHandlerFactory;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.scene.image.Image;

import org.junit.Before;
import org.junit.Test;

import java.sql.Blob;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class StatisticsTest {
    private IStatisticsHandler statisticsHandler;
    private ArrayList<Event> events;
    private Manager manager;
    private Manager emptyManager;
    private WrapType typeResultTest;
    private WrapGenre genreResultTest;
    private WrapArtist artistResultTest;
    private WrapProv provResultTest;

    @Before
    public void setUp() {
        // Preparazione dei dati per i test
        int [] af1 = {20};
        int [] bf1 = {1125};
        double [] pf1 = {50};

        int [] af2 = {20};
        int [] bf2 = {1125};
        double [] pf2 = {50};

        int [] ac1 = {20};
        int [] bc1 = {2250};
        double [] pc1 = {50};

        Image bl = null;

        events = new ArrayList<Event>();

        Festival f1 = new Festival(0, "Nameless", "Como", "Parco di Como", LocalDate.of(2024,4,20), LocalTime.parse("14:04:00"), Province.COMO, Genre.EDM, 2250,
                1, af1, bf1, pf1, manager, "Rooler, Salmo, Nello Taver", "Festival di musica EDM", 3, bl);
        events.add(f1);

        Festival f2 = new Festival(0, "Nameless", "Pavia", "Parco di Pavia", LocalDate.of(2024,4,28), LocalTime.parse("14:04:00"), Province.PAVIA, Genre.EDM, 2250,
                1, af2, bf2, pf2, manager, "Rooler, Salmo, Nello Taver", "Festival di musica EDM", 3, bl);
        events.add(f2);

        Concert c1 = new Concert(0, "Nameless", "Milano", "Forum", LocalDate.of(2024,3,20), LocalTime.parse("14:04:00"), Province.MILANO, Genre.RAP, 2250,
                1, ac1, bc1, pc1, manager, "Salmo", "Festival di musica EDM", bl);
        events.add(c1);

        manager = new Manager("Giorgio", "Mastrota", "1990-01-01", "giorgiom@example.com", "eminflex", Province.COMO, "1234567890123456", events, 5, 1, LocalDate.now(), 0);

        ArrayList<Event> emptyEvents = new ArrayList<>();
        emptyManager = new Manager("Giorg", "Mastrota", "1990-01-01", "giorg@example.com", "eminflex", Province.PAVIA, "1234567890123456", emptyEvents, 5, 1, LocalDate.now(), 0);

        Type[] typeNameArray = {Type.FESTIVAL, Type.CONCERT, Type.THEATER, Type.OTHER};
        double[] typeResults = {50.0, 100.0, 0.0, 0.0};
        typeResultTest = new WrapType(typeResults, typeNameArray);

        ArrayList<String> artistNameArray = new ArrayList<>();
        artistNameArray.add("Rooler, Salmo, Nello Taver");
        ArrayList<Double> artistResults = new ArrayList<>();
        artistResults.add(50.0);
        artistResultTest = new WrapArtist(artistResults, artistNameArray);

        ArrayList<Genre> genreNameArray = new ArrayList<>();
        genreNameArray.add(Genre.EDM);
        ArrayList<Double> genreResults = new ArrayList<>();
        genreResults.add(50.0);
        genreResultTest = new WrapGenre(genreResults, genreNameArray);

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
        assertArrayEquals(typeResultTest.getTypeArray(), statisticsHandler.typeStats(manager).getTypeArray());
        assertArrayEquals(typeResultTest.getTypeResult(), statisticsHandler.typeStats(manager).getTypeResult(), 0.0001);
    }

    @Test
    public void typeTestExeption(){
        statisticsHandler = StatisticsHandlerFactory.getInstance().getStatisticsHandler();
        statisticsHandler.typeStats(emptyManager);
    }

    @Test
    public void ArtistTest(){
        statisticsHandler = StatisticsHandlerFactory.getInstance().getStatisticsHandler();
        assertEquals(artistResultTest.getArtistNameArray(), statisticsHandler.artistStats(Type.FESTIVAL, manager).getArtistNameArray());
        assertEquals(artistResultTest.getArtistResult(), statisticsHandler.artistStats(Type.FESTIVAL, manager).getArtistResult());
    }

    @Test
    public void genreTest(){
        statisticsHandler = StatisticsHandlerFactory.getInstance().getStatisticsHandler();
        assertEquals(genreResultTest.getGenreArray(), statisticsHandler.genreStats(Type.FESTIVAL, manager).getGenreArray());
        assertEquals(genreResultTest.getGenreResult(), statisticsHandler.genreStats(Type.FESTIVAL, manager).getGenreResult());
    }

    @Test
    public void ProvTest(){
        statisticsHandler = StatisticsHandlerFactory.getInstance().getStatisticsHandler();
        assertEquals(provResultTest.getProvinceArray(), statisticsHandler.provinceStats(Type.FESTIVAL,"Rooler, Salmo, Nello Taver", manager).getProvinceArray());
        assertEquals(provResultTest.getProvResult(), statisticsHandler.provinceStats(Type.FESTIVAL,"Rooler, Salmo, Nello Taver", manager).getProvResult());
    }

}
