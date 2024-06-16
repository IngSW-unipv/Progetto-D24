package it.unipv.insfw23.TicketWave.modelDomain.user;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.payment.IPaymentAdapter;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.*;
import java.util.ArrayList;


/**
 *
 * Customer domain class extends the {@link User} class for common attributes and methods

 */
public class Customer extends User {

    private ArrayList<Ticket> ticketsList;
    private int points;
    private Genre [] favoriteGenre ;
    int maxfavoriteGenre = 5;

    /**
     *
     * Customer uses some of the User class attributes
     * @param name
     * @param surname
     * @param dateOfBirth
     * @param email
     * @param password
     *
     * and others that define the class itself
     * @param provinceOfResidence
     * @param favoriteGenre
     * @param points
     * @param ticketsList
     */
    public Customer(String name, String surname, String dateOfBirth, String email, String password, Province provinceOfResidence, Genre [] favoriteGenre, int points,ArrayList<Ticket> ticketsList) {
        super(name,surname, dateOfBirth, email,password, provinceOfResidence);
        this.points = points;
        this.favoriteGenre= favoriteGenre;
        this.ticketsList= ticketsList;
    }

    // metodo per acquisto biglietto con controllo su paymentmethod e usepoints,
    // quest'ultima serve per
    // dire se si vogliono o meno usare i punti finora accumulati


    /**
     * This method requires some parameters as
     * @param pay
     * @param event
     * @param type
     * @param usePoints
     * and returns
     * @return ticket as a {@link Ticket}
     * and if something goes wrong
     * @throws Exception
     */

    public Ticket buyticket(IPaymentAdapter pay,Event event,TicketType type ,int usePoints) throws Exception {

        double startPrice = event.getPrice(type);
        double endPrice;
        Ticket ticket;
        if(usePoints == 1 ){

            endPrice = startPrice - (0.25 * usablePoints(startPrice, points));

            if(pay.paymentMethod(endPrice) == true) {
                points = points - usablePoints(startPrice, points);
                System.out.println("L'acquisto del tuo biglietto per " + event + " was successful");
                ticket = TicketHandler.getIstance().createTicket(event, type);
                points = points + (int) (endPrice / 10);
                ticket.setPrice(endPrice);
                addTickets(ticket);
            } else {
                throw new Exception ( "Your ticket purchase for " + event + "non è andato a buon fine ");
            }

        } else if (usePoints == 0) {
            endPrice = startPrice;
            if (pay.paymentMethod(endPrice) == true){
                System.out.println( "Your ticket purchase for " + event + "was successful");
                points = points + (int) (endPrice/10);
                 ticket= TicketHandler.getIstance().createTicket(event,type);
                addTickets(ticket);
            }  else {
                throw new Exception ( "Your ticket purchase for  " + event + "didn't work out ");
            }
        }
        else {
            throw new Exception("Select a valid number");
        }
        return ticket;
    }
    // setto generi preferiti

    /**
     * Here we can set the favorite genres for the customer at the moment of registration
     * ,and you have to give to the method an array of genre where you con save the genres
     * @param favorite
     * if the genres are too much the method trows
     * @throws Exception
     */
    public void setFavoriteGenre(Genre [] favorite) throws Exception {
        if (favorite.length > 5) {
            throw new Exception ("Impossible to select more the 5 genres");
        } else {

            for (int i = 0; i < maxfavoriteGenre; i++) {
                favoriteGenre[i] = favorite[i];

            }
        }
    }

    /**
     * the method adds a ticket to the ticketArrayList
     * @param ticket
     */
    public void addTickets( Ticket ticket){
            ticketsList.add(ticket);
    }

    /**
     * returns
     * @return favoriteGenre as an array og {@link Genre}
     */
    public Genre[] getFavoriteGenre() {
        return favoriteGenre;
    }

    /**
     * sets the wavePoints of the customer
     * @param points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     *
     * @return ticketsList as an arraylist of{@link Ticket}
     */
    public ArrayList<Ticket> getTicketsList() {
        return ticketsList;
    }

    /**
     *
     * @return points as an int
     */

    public int getPoints() {

        return points;
    }

    /**
     * This Method is useful to understand if the user is a customer,
     * and
     * @return true as a boolean
     */
    @Override
    public boolean isCustomer() {
        return true;
    }

    /**
     *This method is used to calculate the maximum of the points that a customer can
     * use at the moment of purchase of a ticket to have a discount
     * @param price
     * @param points
     * @return
     */
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
