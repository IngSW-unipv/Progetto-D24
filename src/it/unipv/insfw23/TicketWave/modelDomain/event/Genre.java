package it.unipv.insfw23.TicketWave.modelDomain.event;

public enum Genre {
    ROCK,
    METAL,
    INDIE,
    EDM,
    HOUSE,
    TECHNO,
    RAP,
    TRAP,
    POP;

    // Ritorna un'array delle scritte associate ai valori della enum
    public static String[] getGenreNameArray() {
        String[] genreNames = new String[Genre.values().length];
        for(Genre x : Genre.values()) {
            genreNames[x.ordinal()] = String.valueOf(x);
        }
        return genreNames;
    }

    // Ritorna un'array dei valori associati ai nomi della enum
    public static int[] getGenreCodeArray() {
        int[] genreCode = new int[Genre.values().length];
        for(Genre x : Genre.values()) {
            genreCode[x.ordinal()] = x.ordinal();
        }
        return genreCode;
    }
}

    
