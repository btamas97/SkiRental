import java.util.Scanner;

public class Store {
    private Scanner scanner = new Scanner(System.in);
    private String userInput = "";
    private RentalBox[] boxes = initializeBoxes();
    private TicketMachine ticketMachine = new TicketMachine();

    private RentalBox[] initializeBoxes() {
        int numberOfBigBoxes = 50;
        int numberOfSmallBoxes = 25;
        RentalBox[] boxes = new RentalBox[numberOfSmallBoxes+numberOfBigBoxes];
        for (int i = 0; i < boxes.length; i++) {
            if(numberOfBigBoxes>i)
                boxes[i] = new RentalBox(true);
            else
                boxes[i] = new RentalBox(false);
        }
        return boxes;
    }

    public boolean canExit() {
        return !userInput.equalsIgnoreCase("q");
    }

    public void chooseAction() {
        userInput = scanner.nextLine();
        if (userInput.equalsIgnoreCase("s"))
            ticketMachine.startRent(boxes);
        else if (userInput.equalsIgnoreCase("e"))
            ticketMachine.stopRent(boxes);
    }
}
