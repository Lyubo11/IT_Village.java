import java.io.*;

public class Main {

    public static final String white = "\u001B[37m";

    public static final String green = "\u001B[32m";

    public static final String redBackground = "\u001B[41m";

    public static final String resetColor = "\u001B[0m";

    public static void registrationAndLogin() throws IOException {
        RegistrationAndLogin.main(null);
    }

    public static void board() throws IOException {
        Board.main(null);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(redBackground + white + "________________________________________" + resetColor);
        System.out.println(redBackground + white + "__" + resetColor + "!!!WELCOME TO THE IT VILLAGE GAME!!!" + redBackground + white + "__" + resetColor);
        System.out.println(redBackground + white + "________________________________________" + resetColor);
        System.out.println();
        System.out.println(green + "LET'S BEGIN..." + resetColor);
        System.out.println();
        registrationAndLogin();
        System.out.println();
        System.out.println("Press ENTER to roll the dice!");
        board();
        System.out.println();
        System.out.println("Have a nice day!");
    }
}