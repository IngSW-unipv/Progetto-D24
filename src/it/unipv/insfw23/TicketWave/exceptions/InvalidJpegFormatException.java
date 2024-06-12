package it.unipv.insfw23.TicketWave.exceptions;

public class InvalidJpegFormatException extends Exception{
    public InvalidJpegFormatException(){
        super("L'immagine inserita non valida o assente");
    }
}
