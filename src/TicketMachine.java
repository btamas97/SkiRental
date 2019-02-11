import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class TicketMachine {
    private ArrayList<Ticket> OpenTickets = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private int takenBoxes = 0;
    private int ticketPrice = 0;

    private void rentBox(Ticket ticket, RentalBox[] boxes,boolean isBigBox) {
        for (RentalBox box : boxes) {
            if (box.getBoxStatus() && (box.getIsBigBox()==isBigBox)) {
                box.setBoxStatusTaken();
                takenBoxes++;
                ticket.setBoxID(box.getBoxID());
                ticketPrice = ticket.getTicketPrice(isBigBox);
                break;
            }
        }
    }
    public void startRent(RentalBox[] boxes) {
        if (takenBoxes < boxes.length) {
            Ticket ticket = new Ticket();
            System.out.println("Choose box size: press 'S' for small and 'B' for big!");
            String input = "";
            while(!(input.equalsIgnoreCase("S") || input.equalsIgnoreCase("B"))){
                input  = scanner.nextLine();
                switch (input.toUpperCase()){
                    case "S":
                        rentBox(ticket,boxes,false);
                        break;
                    case "B":
                        rentBox(ticket,boxes,true);
                        break;
                    default:
                        System.out.println("Your input is wrong!");
                        break;
                }
            }
            ticket.setTicketID();
            ticket.setStartingDate();
            OpenTickets.add(ticket);
            System.out.println("To proceed, please insert " + ticketPrice + "$");
            scanner.nextLine();
            printTicket(ticket.getTicketID(), ticket.getBoxID(), ticket.getStartingDate()); //put ticket details into separate method
        } else {
            System.out.println("Sorry, we are out of boxes. Please check back later!");
            System.out.println(Constants.SEPARATOR);
        }
    }

    private void printTicket(String ticketID, int boxID, Calendar startingDate) {
        System.out.println("Your Ticket ID is : \t" + ticketID);
        System.out.println("Box number: \t\t\t" + boxID);
        System.out.println("Start of rent: \t\t\t" + startingDate.getTime());
        System.out.println(Constants.SEPARATOR);  //used to copy the same string of lines to 4 parts. Using constants instead.
    }

    private int countRentedDays(Calendar start, Calendar end) {
        return (int) (ChronoUnit.DAYS.between(start.toInstant(), end.toInstant()));
    }

    private Calendar testDate(){
          Calendar testdate = Calendar.getInstance();
          testdate.set(2019,Calendar.FEBRUARY,14);
          return testdate;
    }
    private boolean isOverdue(Ticket ticket) {
        return countRentedDays(ticket.getStartingDate(), ticket.getEndingDate()) > ticket.getRentExpiryDays();
    }
    private boolean usedAllDays(Ticket ticket){
        return countRentedDays(ticket.getStartingDate(),ticket.getEndingDate()) == 3;
    }

    public void stopRent(RentalBox[] boxes) {
        System.out.print("Please enter your ticket ID: ");
        String ticketID = scanner.nextLine();
        boolean ticketFound = false;
        for (Ticket ticket : OpenTickets) {
            if (ticket.getTicketID().equalsIgnoreCase(ticketID)) {
                ticket.setEndingDate();
                if (isOverdue(ticket)) {           //examination put to a separated method
                    System.out.println("Your rent is overdue! You have to pay an additional 50$ fee.");
                    scanner.nextLine();
                }
                if (usedAllDays(ticket)) {           //examination put to a separated method
                    System.out.println("You used up all 3 days. You get " + ticket.getMoneyBack()+"$ back!");
                }
                for (RentalBox box : boxes) {
                    if (ticket.getBoxID() == box.getBoxID()) {
                        box.setBoxStatusFree();
                    }
                }
                System.out.println("End of rent: " + ticket.getEndingDate().getTime());
                System.out.println("Thank you for choosing our rental services.");
                System.out.println(Constants.SEPARATOR);
                takenBoxes--;
                OpenTickets.remove(ticket);
                ticketFound = true;
                break;
            }
        }
        if (!ticketFound) {
            System.out.println("Sorry, there's no such ticket ID!");
            System.out.println(Constants.SEPARATOR);
        }
    }
}
