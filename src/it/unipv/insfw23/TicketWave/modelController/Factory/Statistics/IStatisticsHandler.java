package it.unipv.insfw23.TicketWave.modelController.Factory.Statistics;
import it.unipv.insfw23.TicketWave.modelDomain.event.Type;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;


public interface IStatisticsHandler {
        public WrapType typeStats(Manager manager);

        public WrapArtist artistStats(Type typeCode, Manager manager);

        public WrapGenre genreStats(Type typeCode, Manager manager);

        public WrapProv provinceStats(Type typeCode, String artistName, Manager manager);

}
