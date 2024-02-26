package it.unipv.insfw23.TicketWave.modelView;

public class Event {
    private int cod;
    private String nome;

    public Event(int cod, String s) {
        this.cod = cod;
        nome = s;
    }

    public int getCod() {
        return cod;
    }

    public void setCodice(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String s) {
        this.nome = s;
    }


}
