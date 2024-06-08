package it.unipv.insfw23.TicketWave.exceptions;

public class AccountNotFoundException extends Exception{
    public AccountNotFoundException() {
        super("Account non trovato");
    }
}
