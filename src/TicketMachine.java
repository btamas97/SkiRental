import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class TicketMachine {
    private ArrayList<Ticket> OpenTickets = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private int takenBoxes = 0;

    public void startRent(RentalBox[] boxes){
       if (takenBoxes < 50){
           Ticket ticket = new Ticket();
           for(RentalBox box: boxes){
               if (box.getBoxStatus()){
                   box.setBoxStatusTaken();
                   takenBoxes++;
                   ticket.setBoxID(box.getBoxID());
                   break;
               }
           }
           ticket.setTicketID();
           ticket.setStartingDate();
           OpenTickets.add(ticket);
           System.out.println("To proceed, please insert "+ ticket.getTicketPrice() +"$");
           scanner.nextLine();
           printTicket(ticket.getTicketID(),ticket.getBoxID(),ticket.getStartingDate()); //put ticket details into separate method
       }else {
           System.out.println("Sorry, we are out of boxes. Please check back later!");
           System.out.println(Constants.SEPARATOR);
       }
    }
    private void printTicket(String ticketID, int boxID, Calendar startingDate){
        System.out.println("Your Ticket ID is : \t"+ ticketID);
        System.out.println("Box number: \t\t\t"+ boxID);
        System.out.println("Start of rent: \t\t\t" + startingDate.getTime());
        System.out.println(Constants.SEPARATOR);  //used to copy the same string of lines to 4 parts. Using constants instead.
    }
    private int countRentedDays(Calendar start, Calendar end){
      return  (int)(ChronoUnit.DAYS.between(start.toInstant(), end.toInstant()));
    }
  /*  public Calendar testdate(){
        Calendar testdate = Calendar.getInstance();
        testdate.set(2019,2,9);
        return testdate;
    } */
    public boolean isOverdue(Ticket ticket){
        return countRentedDays(ticket.getStartingDate(),ticket.getEndingDate())>ticket.getRentExpiryDays();
    }
    public void stopRent(RentalBox[] boxes){
        System.out.print("Please enter your ticket ID: ");
        String ticketID = scanner.nextLine();
        boolean ticketFound = false;
        for (Ticket ticket: OpenTickets){
            if(ticket.getTicketID().equalsIgnoreCase(ticketID)){
                ticket.setEndingDate();
                if(isOverdue(ticket)){           //examination put to a separated method
                    System.out.println("Your rent is overdue! You have to pay an additional 50$ fee.");
                    scanner.nextLine();
                }
                for(RentalBox box: boxes){
                    if(ticket.getBoxID() == box.getBoxID()){
                        box.setBoxStatusFree();
                    }
                }
                System.out.println("End of rent: "+ ticket.getEndingDate().getTime());
                System.out.println("Thank you for choosing our rental services.");
                System.out.println(Constants.SEPARATOR);
                takenBoxes--;
                OpenTickets.remove(ticket);
                ticketFound = true;
                break;
            }
        }
        if (!ticketFound){
            System.out.println("Sorry, there's no such ticket ID!");
            System.out.println(Constants.SEPARATOR);
        }
    }
}
