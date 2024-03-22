package it.unipv.insfw23.TicketWave.modelDomain.user;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.payment.IPaymentAdapter;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.*;
import java.util.ArrayList;



public class Customer extends User {

    private ArrayList<Ticket> ticketsList= new ArrayList<>();

    private int points;
    private Genre [] favoriteGenre ;
    int maxfavoriteGenre = 5;


    public Customer(String name, String surname, String dateOfBirth, String email, String password, int provinceOfResidence, Genre [] favoriteGenre, int points) {
        super(name,surname, dateOfBirth, email,password, provinceOfResidence);

        this.points = points;
        this.favoriteGenre= favoriteGenre;
    }







    // metodo per acquisto biglietto con controllo su paymentmethod e usepoints, quest'ultima serve per dire se si vogliono o meno usare i punti finora accumulati


    /*
    public void buyticket(IPaymentAdapter pay,Event event,TicketType type ,int usePoints) throws Exception {

        double startPrice = event.getPrice(type);
        double endPrice;
        if(usePoints == 1 ){

            endPrice = startPrice - (0.25 * usablePoints(startPrice, points));

            if(pay.paymentMethod(endPrice) == true) {
                points = points - usablePoints(startPrice, points);
                System.out.println("L'acquisto del tuo biglietto per " + event + " è andato a buon fine ");
                points = points + (int) (endPrice / 10);
                Ticket ticket = TicketHandler.getIstance().createTicket(event, type);
                ticket.setPrice(endPrice);
                addTickets(ticket);
            } else {
                throw new Exception ( "L'acquisto del tuo biglietto per " + event + "non è andato a buon fine ");
            }

        } else if (usePoints == 0) {
            endPrice = startPrice;
            if (pay.paymentMethod(endPrice) == true){
                System.out.println( "L'acquisto del tuo biglietto per " + event + "è andato a buon fine ");
                points = points + (int) (endPrice/10);
                Ticket ticket= TicketHandler.getIstance().createTicket(event,type);
                addTickets(ticket);
            }  else {
                throw new Exception ( "L'acquisto del tuo biglietto per " + event + "non è andato a buon fine ");
            }
        }
        else {
            throw new Exception("UsePoints problem");
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

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<Ticket> getTicketsList() {
        return ticketsList;
    }

    public double getPoints() {

        return points;
    }


    @Override
    public boolean isCustomer() {
        return true;
    }

    private int usablePoints(double price, int points){

        int maxusable = (int) price * 4;

        if (maxusable > points){
            return points;
        }
        else {
            return maxusable;
        }
    }

}
