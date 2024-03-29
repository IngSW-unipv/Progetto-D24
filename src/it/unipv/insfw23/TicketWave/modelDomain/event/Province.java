package it.unipv.insfw23.TicketWave.modelDomain.event;

public enum Province {

    AGRIGENTO,
    ALESSANDRIA,
    ANCONA,
    AOSTA,
    AREZZO,
    ASCOLI_PICENO,
    ASTI,
    AVELLINO,
    BARI,
    BARLETTA_ANDRIA_TRANI,
    BELLUNO,
    BENEVENTO,
    BERGAMO,
    BIELLA,
    BOLOGNA,
    BOLZANO,
    BRESCIA,
    BRINDISI,
    CAGLIARI,
    CALTANISSETTA,
    CAMPOBASSO,
    CASERTA,
    CATANIA,
    CATANZARO,
    CHIETI,
    COMO,
    COSENZA,
    CREMONA,
    CROTONE,
    CUNEO,
    ENNA,
    FERMO,
    FERRARA,
    FIRENZE,
    FOGGIA,
    FORLÌ_CESENA,
    FROSINONE,
    GENOVA,
    GORIZIA,
    GROSSETO,
    IMPERIA,
    ISERNIA,
    LAQUILA,
    LA_SPEZIA,
    LATINA,
    LECCE,
    LECCO,
    LIVORNO,
    LODI,
    LUCCA,
    MACERATA,
    MANTOVA,
    MASSA_CARRARA,
    MATERA,
    MESSINA,
    MILANO,
    MODENA,
    MONZA_BRIANZA,
    NAPOLI,
    NOVARA,
    NUORO,
    ORISTANO,
    PADOVA,
    PALERMO,
    PARMA,
    PAVIA,
    PERUGIA,
    PESARO_URBINO,
    PESCARA,
    PIACENZA,
    PISA,
    PISTOIA,
    PORDENONE,
    POTENZA,
    PRATO,
    RAGUSA,
    RAVENNA,
    REGGIO_CALABRIA,
    REGGIO_EMILIA,
    RIETI,
    RIMINI,
    ROMA,
    ROVIGO,
    SALERNO,
    SASSARI,
    SAVONA,
    SIENA,
    SIRACUSA,
    SONDRIO,
    SUD_SARDEGNA,
    TARANTO,
    TERAMO,
    TERNI,
    TORINO,
    TRAPANI,
    TRENTO,
    TREVISO,
    TRIESTE,
    UDINE,
    VARESE,
    VENEZIA,
    VERBANO_CUSIO_OSSOLA,
    VERCELLI,
    VERONA,
    VIBO_VALENTIA,
    VICENZA,
    VITERBO;
	
/*	 metodo che ritorna un array di stringhe con i nomi delle province, se preferiamo lasciare pulita la enum
 *   puoi copiare il procedimento nel pezzo di codice di interesse, anche se secondo me avere un metodo qua e' meglio
 *   perche' cosi' si ha un  metodo sempre usabile e non si deve implementare ogni volta
 */ 
 	public static String[] getProvinceNameArray() {
 		String[] prnames = new String[Province.values().length];
 		for(Province x : Province.values()) {
 			prnames[x.ordinal()] = String.valueOf(x);
 		}
 		return prnames;
 	}

     // metodo che ritorna un'array di interi associati ai nomi della enum
    public static int[] getProvinceCodeArray() {
        int[] provinceCode = new int[Province.values().length];
        for(Province x : Province.values()) {
            provinceCode[x.ordinal()] = x.ordinal();
        }
        return provinceCode;
    }
 	
}

