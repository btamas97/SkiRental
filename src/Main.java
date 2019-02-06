import java.util.concurrent.ConcurrentNavigableMap;

public class Main {
    public static void main(String[] args){
        Store store = new Store();
        while(store.canExit()){
            System.out.println(Constants.WELCOME_MESSAGE);
            store.chooseAction();
        }
        System.out.print(Constants.GOODBYE_MESSAGE);
    }
}
