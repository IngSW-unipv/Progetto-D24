package it.unipv.insfw23.TicketWave.Factory.Statistics;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;


public interface IStatisticsHandler {
        public WrapType typeStats(Manager manager);

        public WrapArtist artistStats(enumType typeCode, Manager manager);

        public WrapGenre genreStats(enumType typeCode, Manager manager);

        public WrapProv provinceStats(enumType typeCode, String artistName, Manager manager);

}
