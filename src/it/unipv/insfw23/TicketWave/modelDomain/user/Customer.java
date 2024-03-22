package it.unipv.insfw23.TicketWave.modelDomain.user;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.payment.IPaymentAdapter;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.*;
import java.util.ArrayList;



public class Customer extends User {

    private ArrayList<Ticket> ticketsList= new ArrayList<>();

    private double points;
    private Genre [] favoriteGenre ;
    int maxfavoriteGenre = 5;


    public Customer(String name, String surname, String dateOfBirth, String email, String password, int provinceOfResidence, Genre [] favoriteGenre ) {
        super(name,surname, dateOfBirth, email,password, provinceOfResidence);


        this.favoriteGenre= favoriteGenre;
    }




    public ArrayList<Ticket> getTicketsList() {
        return ticketsList;
    }



    public double getPoints() {

        return points;
    }


    // metodo per acquisto biglietto con controllo su paymentmethod e usepoints, quest'ultima serve per dire se si vogliono o meno usare i punti finora accumulati


    /*
    public void buyticket(IPaymentAdapter pay,Event event,TicketType type ,int usePoints) throws Exception {

        Ticket ticket= TicketHandler.getIstance().createTicket(event,type);
        if(pay.paymentMethod(ticket.getPrice()) == true && usePoints == 1 ){
                double price = ticket.getPrice() - (points* 0.25);
                points=0;
                System.out.println( "L'acquisto del tuo biglietto per " + event + " è andato a buon fine ");
                points= points + (price/10);
                addTickets(ticket);

            } else if (pay.paymentMethod(ticket.getPrice()) == true && usePoints == 0) {
                double price = ticket.getPrice();
                System.out.println( "L'acquisto del tuo biglietto per " + event + "è andato a buon fine ");
                points= points + (price/10);
                addTickets(ticket);
            } else if (pay.paymentMethod(ticket.getPrice()) == false) {

            throw new Exception ( "L'acquisto del tuo biglietto per " + event + "non è andato a buon fine ");
            }
    }
*/
    // setto generi preferiti

    public void setFavoriteGenre(Genre [] favorite) throws Exception {
        if (favorite.length > 5) {
            throw new Exception ("Impossibile selezionere più di 5 generi");
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


    @Override
    public boolean isCustomer() {
        return true;
    }
}
