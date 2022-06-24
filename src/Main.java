import java.io.*;
import java.util.*;

public class Main {

    public static void registrationAndLogin() throws IOException {
        RegistrationAndLogin.main(null);
    }

    public static void board() throws IOException {
        Board.main(null);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("!!!WELCOME TO THE IT VILLAGE GAME!!!");
        System.out.println("LET'S BEGIN...");
        System.out.println();
        while (true) {
            registrationAndLogin();
            System.out.println();
            System.out.println("Press ENTER to roll the dice!");
            board();
            System.out.println();
            System.out.println("Do you want to play again? (yes/no)");
            String question = sc.nextLine();
            if (question.equalsIgnoreCase("no")){
                System.out.println("Okay then!");
                System.out.println("Have a nice day!");
                break;
            }
        }
    }
}