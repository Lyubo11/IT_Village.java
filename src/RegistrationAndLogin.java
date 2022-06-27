import java.io.*;
import java.util.*;

public class RegistrationAndLogin {

    public static final String green = "\u001B[32m";

    public static final String white = "\u001B[37m";

    public static final String yellow = "\u001B[33m";

    public static final String red = "\u001B[31m";

    public static final String redBackground = "\u001B[41m";

    public static final String resetColor = "\u001B[0m";

    public static void isRegistrationSuccessful(String username, String password) throws IOException {
        String filePath = "D:\\.Digital Razgrad\\Java - Модул I\\Final Project\\IT_Village\\data.csv";

        FileWriter fw = new FileWriter(filePath, true);  // Writes entered values to the csv file
        BufferedWriter bw = new BufferedWriter(fw);             // An efficient way to write to a file
        PrintWriter pw = new PrintWriter(bw);                   // Get access to all print methods like println() and etc.

        pw.println(username + ", " + password);
        System.out.println();
        System.out.println(green + "Successfully registered!" + resetColor);
        System.out.println();
        pw.flush();
        pw.close();
    }

    public static boolean isLoginSuccessful(String loginUsername, String loginPassword) throws IOException {
        String filePath = "D:\\.Digital Razgrad\\Java - Модул I\\Final Project\\IT_Village\\data.csv";
        BufferedReader reader;
        String line;
        boolean check = false;
        reader = new BufferedReader(new FileReader(filePath));

        while ((line = reader.readLine()) != null) {            // Loops through the csv file and if username and password match
            String[] row = line.split(", ");

            if (loginUsername.equalsIgnoreCase(row[0]) && loginPassword.equalsIgnoreCase(row[1])) {
                check = true;
                break;
            }
        }
        return check;
    }

    public static String loginMessage(String loginUsername, String loginPassword) throws IOException {
        if (isLoginSuccessful(loginUsername, loginPassword)) {
            return (green + "Successfully logged in!" + resetColor);
        } else {
            return (red + "Wrong username/password!" + resetColor);
        }
    }

    public static boolean isUsernameTaken(String username) throws IOException {
        String file = "D:\\.Digital Razgrad\\Java - Модул I\\Final Project\\IT_Village\\data.csv";
        BufferedReader reader;
        String line;
        boolean check = false;
        reader = new BufferedReader(new FileReader(file));

        while ((line = reader.readLine()) != null) {
            String[] row = line.split(", ");

            for (int i = 0; i < row.length / 2; ++i) {          // Loops through(column) every username in the csv file and checks if the entered value is in any of the usernames in there
                for (int j = 0; j < 2; ++j) {
                    if (username.equalsIgnoreCase(row[i])) {
                        check = true;
                        break;
                    }
                }
            }
        }
        return check;
    }

    public static String isAccountLoggedAlready(String[] tempUsernames, String[] tempPasswords, int index) {
        boolean checkUser = false;
        boolean checkPass = false;

        if (index > 0) {
            for (int i = 1; i < index + 1; ++i) {           //loops through and compares all the already logged usernames and passwords with the currently entered ones
                if (tempUsernames[index].equalsIgnoreCase(tempUsernames[index - i])) {
                    checkUser = true;
                }
                if (tempPasswords[index].equalsIgnoreCase(tempPasswords[index - i])) {
                    checkPass = true;
                }
            }
        }
        ++index;

        if (checkUser && checkPass) {
            return red + "There is already an account logged with the same credentials!" + resetColor;
        } else {
            return "";
        }
    }

    public static void AlreadyLoggedMessage(String loginUsername, String loginPassword, String[] tempUsernames, String[] tempPasswords, int index) throws IOException {
        if (loginMessage(loginUsername, loginPassword).equalsIgnoreCase(green + "Successfully logged in!" + resetColor)) {
            if (isAccountLoggedAlready(tempUsernames, tempPasswords, index).equalsIgnoreCase(red + "There is already an account logged with the same credentials!" + resetColor)) {
                System.out.println();
                System.out.println(isAccountLoggedAlready(tempUsernames, tempPasswords, index));
                System.out.println();
            } else {
                System.out.println();
                System.out.println(loginMessage(loginUsername, loginPassword));
                System.out.println();
            }
        } else {
            System.out.println();
            System.out.println(loginMessage(loginUsername, loginPassword));
            System.out.println();
        }
    }

    public static boolean registrationAndLoginNotMatching(String username, String password, String loginUsername, String loginPassword) {
        if (!(username.equalsIgnoreCase(loginUsername) && password.equalsIgnoreCase(loginPassword))) {
            System.out.println();
            System.out.println(red + "The entered credentials don't match with the registered ones!" + resetColor);
            System.out.println();
            return true;
        } else {
            System.out.println();
            System.out.println(green + "Successfully logged in!" + resetColor);
            System.out.println();
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many people are you: ");
        String players = sc.nextLine();
        File csvFile = new File("D:\\.Digital Razgrad\\Java - Модул I\\Final Project\\IT_Village\\Players_count.csv");
        PrintWriter pw = new PrintWriter(csvFile);
        pw.print(players);           // Prints the amount of people you entered in csv file
        pw.close();

        int index = 0;
        String[] tempUsernames = new String[Integer.parseInt(players)];
        String[] tempPasswords = new String[Integer.parseInt(players)];

        for (int i = 1; i <= Integer.parseInt(players); ++i) {
            System.out.println(yellow + "\nPlayer#" + i + resetColor);
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.print("\nPick a choice: ");
            String choice = sc.nextLine();
            String username, password;

            if (choice.equals("1")) {
                while (true) {
                    System.out.println("\nRegister: ");
                    System.out.print("\nEnter a username: ");
                    username = sc.nextLine();
                    System.out.print("Enter a password: ");
                    password = sc.nextLine();

                    if ((isUsernameTaken(username))) {
                        System.out.println();
                        System.out.println(red + "The username is already taken!" + resetColor);
                        System.out.println();
                    } else {
                        isRegistrationSuccessful(username, password);
                        break;
                    }
                }
                while (true) {
                    System.out.println("\nLogin: ");
                    System.out.print("\nEnter a username: ");
                    String loginUsername = sc.nextLine();
                    System.out.print("Enter a password: ");
                    String loginPassword = sc.nextLine();

                    tempUsernames[index] = loginUsername;
                    tempPasswords[index] = loginPassword;

                    if (!registrationAndLoginNotMatching(username, password, loginUsername, loginPassword) && loginMessage(loginUsername, loginPassword).equalsIgnoreCase(green + "Successfully logged in!" + resetColor) && isAccountLoggedAlready(tempUsernames, tempPasswords, index).equalsIgnoreCase("")) {
                        ++index;

                        if (i == Integer.parseInt(players)) {           // If we are looping through the last person of the list then start the game
                            System.out.println(redBackground + white + "___________________" + resetColor);
                            System.out.println(redBackground + white + "_" + resetColor + "!!!Game starts!!!" + redBackground + white + "_" + resetColor);
                            System.out.println(redBackground + white + "___________________" + resetColor);
                        }
                        break;
                    }
                }
            } else if (choice.equals("2")) {
                while (true) {
                    System.out.println("\nLogin: ");
                    System.out.print("\nEnter a username: ");
                    String loginUsername = sc.nextLine();
                    System.out.print("Enter a password: ");
                    String loginPassword = sc.nextLine();

                    tempUsernames[index] = loginUsername;
                    tempPasswords[index] = loginPassword;

                    AlreadyLoggedMessage(loginUsername, loginPassword, tempUsernames, tempPasswords, index);

                    if (loginMessage(loginUsername, loginPassword).equalsIgnoreCase(green + "Successfully logged in!" + resetColor) && isAccountLoggedAlready(tempUsernames, tempPasswords, index).equalsIgnoreCase("")) {
                        ++index;

                        if (i == Integer.parseInt(players)) {
                            System.out.println(redBackground + white + "___________________" + resetColor);
                            System.out.println(redBackground + white + "_" + resetColor + "!!!Game starts!!!" + redBackground + white + "_" + resetColor);
                            System.out.println(redBackground + white + "___________________" + resetColor);
                        }
                        break;
                    }
                }
            } else {
                System.out.println();
                System.out.println(red + "Not a choice!" + resetColor);

                while (true) {
                    System.out.print("\nEnter \"continue\" to pick a choice again: ");
                    choice = sc.nextLine();

                    if (choice.equalsIgnoreCase("continue")) {
                        --i;
                        break;
                    }
                }
            }
        }
    }
}
