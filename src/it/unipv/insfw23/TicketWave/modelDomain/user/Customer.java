package it.unipv.insfw23.TicketWave.modelDomain.user;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.*;
import java.util.ArrayList;
import it.unipv.ingsfw.modelDomain.IPaymentAdapter;
public class Customer extends User {
    private int [] creditCard= new int[16];
    private ArrayList<Ticket> ticketsList= new ArrayList<>();
    private Event event;
    private double points;
    private Genre [] favoriteGenre ;
    private int  maxfavoriteGenre = 5;




    public Customer(String name, String surname, String dateOfBirth, String email, String password, int provinceOfResidence,int [] reditCard,Genre [] favoriteGenre ) {
        super(name,surname, dateOfBirth, email,password, provinceOfResidence);
        this.creditCard= creditCard;
        this.favoriteGenre= favoriteGenre;
    }


    public int [] getCreditCard() {
        return creditCard;
    }

    public ArrayList<Ticket> getTicketsList() {
        return ticketsList;
    }

    public Event getEvent() {
        return event;
    }

    public double getPoints() {

        return points;
    }
    // acquisto biglietto

   /* public boolean buy (int [] creditCard) {
        if (creditCard.length == 16) {
            return true;
        }
            else{
                System.out.println("Numero carta non corretto, riprovare");
                return false;
            }


    }
    */

    public void buyticket(Ticket ticket , int [] cerditCard , int usePoints){
            if(UserpaymentMetod(Customer) == true && usePoints == 1 ){

                double price = ticket.getPrice() - (points* 0.25);
                points=0;
                System.out.println( "L'acquisto del tuo biglietto :" + ticket + "è andato a buon fine ");
                points= points + (price/10);
                addTickets(ticket);

            } else if (buy(creditCard) == true && usePoints == 0) {
                double price = ticket.getPrice();
                System.out.println( "L'acquisto del tuo biglietto :" + ticket + "è andato a buon fine ");
                points= points + (price/10);
                addTickets(ticket);
            }
    }
// exception false e buy con payment

    public void setFavoriteGenre(Genre [] favorite) {
        if (favorite.length > 5) {
            System.out.println("Impossibile selezionere più di 5 generi");
        } else {
            for (int i = 0; i < maxfavoriteGenre; i++) {
                favoriteGenre[i] = favorite[i];

            }
        }
    }
    public void addTickets( Ticket ticket){
            ticketsList.add(ticket);
    }

    public Genre[] getFavoriteGenre() {
        return favoriteGenre;
    }
}
