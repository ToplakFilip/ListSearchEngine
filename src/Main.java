public class Main {


    public static void main(String[] args) {
        // write your code here
        if(args[0].equals("--data")) {
            UI ui = new UI();
            ui.start(args[1]);
        }
    }

}
