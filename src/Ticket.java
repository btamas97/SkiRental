import java.util.Calendar;
import java.util.Random;

public class Ticket{
    private Calendar startingDate;
    private Calendar endingDate;
    private String ticketID;
    private int boxID;

    public void setTicketID(){
        Random rnd = new Random();
        StringBuilder builder = new StringBuilder();
        int leftLimit = 97;
        int rightLimit = 122;
        for (int i = 0;i<8;i++){
           int idChar = leftLimit+ (int) (rnd.nextFloat()* (rightLimit -leftLimit + 1));
           builder.append((char) idChar);
        }
        ticketID = builder.toString();
    }
    public String getTicketID(){
        return ticketID;
    }
    private Calendar getToday() { return Calendar.getInstance(); }
    public void setStartingDate(){
        startingDate = getToday();
    }
    public Calendar getStartingDate(){
       return startingDate;
    }
    public  void  setEndingDate(){
        endingDate = getToday();
    }
    public Calendar getEndingDate(){
        return endingDate;
    }
    public int getBoxID() {
        return boxID;
    }
    public void setBoxID(int boxID) {
        this.boxID = boxID;
    }
}
