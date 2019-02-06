public class Main {
    public static void main(String[] args){
        Store store = new Store();
        while(store.canExit()){
            System.out.println("Press 'S' to start renting a box, press 'E' to end renting and press 'Q' to quit.");
            store.chooseAction();
        }
        System.out.print("Good Bye!");
    }
}
