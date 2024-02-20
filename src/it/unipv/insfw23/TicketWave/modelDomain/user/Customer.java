package it.unipv.insfw23.TicketWave.modelDomain.user;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.payment.IPaymentAdapter;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.*;
import java.util.ArrayList;


public class Customer extends User implements IPaymentAdapter {
    private int [] creditCard= new int[16];
    private ArrayList<Ticket> ticketsList= new ArrayList<>();
    private Event event;
    private double points;
    private Genre [] favoriteGenre ;


    public Customer(String name, String surname, String dateOfBirth, String email, String password, int provinceOfResidence,int [] creditCard,Genre [] favoriteGenre ) {
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
    // metodo per acquisto biglietto con controllo su paymentmethod e usepoints

    public void buyticket(IPaymentAdapter pay,Event event,TicketType type ,int usePoints){
        Customer customer;
        customer= new Customer(getName(),getSurname(),getDateOfBirth(),getEmail(),getPassword(),getProvinceOfResidence(),getCreditCard(),getFavoriteGenre());
        if(pay.paymentMethod(customer) == true && usePoints == 1 ){
                Ticket ticket= TicketHandler.getIstance().createTicket(event,type);
                double price = ticket.getPrice() - (points* 0.25);
                points=0;
                System.out.println( "L'acquisto del tuo biglietto per " + event + " è andato a buon fine ");
                points= points + (price/10);
                addTickets(ticket);

            } else if (pay.paymentMethod(customer) == true && usePoints == 0) {
                Ticket ticket= TicketHandler.getIstance().createTicket(event,type);
                double price = ticket.getPrice();
                System.out.println( "L'acquisto del tuo biglietto per " + event + "è andato a buon fine ");
                points= points + (price/10);
                addTickets(ticket);
            } else if (pay.paymentMethod(customer) == false) {
                System.out.println( "L'acquisto del tuo biglietto per " + event + "non è andato a buon fine ");
            }
    }

    // setto generi preferiti

    public void setFavoriteGenre(Genre [] favorite) {
        if (favorite.length > 5) {
            System.out.println("Impossibile selezionere più di 5 generi");
        } else {
            int maxfavoriteGenre = 5;
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
    public boolean paymentMethod(User user) {
        return true;
    }


}
