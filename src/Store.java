import java.util.Scanner;

public class Store {
    private int numberOfBoxes = 50;
    private Scanner scanner = new Scanner(System.in);
    private String userInput = "";
    private RentalBox[] boxes = initializeBoxes();
    private TicketMachine ticketMachine = new TicketMachine();

    private RentalBox[] initializeBoxes(){
        RentalBox[] boxes = new RentalBox[numberOfBoxes];
        for (int i = 0; i< boxes.length; i++){
            boxes[i] = new RentalBox();
        }
        return boxes;
    }

    public boolean canExit(){
            return !userInput.equalsIgnoreCase("q");
    }
    public void chooseAction(){
        userInput = scanner.nextLine();
       if (userInput.equalsIgnoreCase("s"))
           ticketMachine.startRent(boxes);
       else if(userInput.equalsIgnoreCase("e"))
           ticketMachine.stopRent(boxes);
    }
}
