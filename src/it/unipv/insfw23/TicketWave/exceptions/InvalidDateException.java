package it.unipv.insfw23.TicketWave.exceptions;

public class InvalidDateException extends Exception{
    public InvalidDateException(){
        super("La data inserita non è valida.");
    }
}
