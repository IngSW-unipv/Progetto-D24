package it.unipv.insfw23.TicketWave.exceptions;

public class AccountAlreadyExistsException extends Exception{
    public AccountAlreadyExistsException() {
        super("Account già esistente");
    }
}
