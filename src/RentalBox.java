import java.util.concurrent.atomic.AtomicInteger;

public class RentalBox {
    private boolean isFree;
    private static final AtomicInteger count = new AtomicInteger(0);
    private int boxNumber;
    private boolean isBigBox;

    public RentalBox(boolean isBigBox) {
        isFree = true;
        boxNumber = count.incrementAndGet();
        this.isBigBox = isBigBox;
    }

    public int getBoxID() {
        return this.boxNumber;
    }

    public void setBoxStatusFree() {
        isFree = true;
    }

    public void setBoxStatusTaken() {
        isFree = false;
    }

    public boolean getBoxStatus() {
        return this.isFree;
    }

    public boolean getIsBigBox(){return  isBigBox;}
}
