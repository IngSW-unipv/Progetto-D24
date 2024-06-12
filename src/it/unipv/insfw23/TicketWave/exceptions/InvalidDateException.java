package it.unipv.insfw23.TicketWave.exceptions;

public class InvalidDateException extends Exception{ // non serve perché ho checkato direttamente sui datapicker della view
    public InvalidDateException(){
        super("La data inserita non è valida.");
    }
}
