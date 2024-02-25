package it.unipv.insfw23.TicketWave.modelView;

public class Notification {
    private int codice;
    private String stringa1;
    private String stringa2;

    public Notification(int codice, String stringa1, String stringa2) {
        this.codice = codice;
        this.stringa1 = stringa1;
        this.stringa2 = stringa2;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public String getStringa1() {
        return stringa1;
    }

    public void setStringa1(String stringa1) {
        this.stringa1 = stringa1;
    }

    public String getStringa2() {
        return stringa2;
    }

    public void setStringa2(String stringa2) {
        this.stringa2 = stringa2;
    }


}

