import java.io.*;
import java.util.Scanner;
import java.io.PrintWriter;

public class Main {
    public static void registration(String username, String password) throws IOException {
        String whereWrite = "D:\\.Digital Razgrad\\Java - Модул I\\Final Project\\IT_Village\\data.csv";

        FileWriter fw = new FileWriter(whereWrite, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);


        pw.println(username + "," + password);
        System.out.println("Successfully registered!");
        pw.flush();
        pw.close();

    }

    public static String isLoginSuccessful(String loginUsername, String loginPassword) {
        String file = "D:\\.Digital Razgrad\\Java - Модул I\\Final Project\\IT_Village\\data.csv";
        BufferedReader reader = null;
        String line;
        boolean check = false;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                for (int j = 0; j < row.length / 2; j++) {
                    for (int i = 0; i < 2; i++) {
                        if (loginUsername.equalsIgnoreCase(row[i]) && loginPassword.equalsIgnoreCase(row[i + 1])) {
                            check = true;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println();
        if (check) {
            return ("Successfully logged in!");
        } else {
            System.out.println();
            return ("Wrong username/password! \n Try again!");
        }
    }

    public static boolean isUsernameTaken(String username) {
        String file = "D:\\.Digital Razgrad\\Java - Модул I\\Final Project\\IT_Village\\data.csv";
        BufferedReader reader = null;
        String line;
        boolean check = false;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(", ");
                for (int j = 0; j < row.length / 2; j++) {
                    for (int i = 0; i < 2; i++) {
                        if (username.equalsIgnoreCase(row[j])) {
                            check = true;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return check;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many people are you: ");
        String people = sc.nextLine();

        for (int i = 1; i <= Integer.parseInt(people); i++) {
            System.out.println("\nPlayer#" + i);
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.print("\nPick a choice: ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                while (true) {
                    System.out.println("\nRegister: ");
                    System.out.print("\nEnter a username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter a password: ");
                    String password = sc.nextLine();
                    if ((isUsernameTaken(username))) {
                        System.out.println("The username is already taken!");
                    } else {
                        registration(username, password);
                        break;
                    }
                }

                while (true) {
                    System.out.println("\nLogin: ");
                    System.out.print("\nEnter a username: ");
                    String loginUsername = sc.nextLine();
                    System.out.print("Enter a password: ");
                    String loginPassword = sc.nextLine();
                    System.out.println(isLoginSuccessful(loginUsername, loginPassword));
                    if (isLoginSuccessful(loginUsername, loginPassword).equalsIgnoreCase("Successfully logged in!")) {
                        if (i == Integer.parseInt(people)) {
                            System.out.println("Game starts!");
                            break;
                        } else {
                            break;
                        }
                    }
                }
            } else if (choice.equals("2")) {
                while (true) {
                    System.out.println("\nLogin: ");
                    System.out.print("\nEnter a username: ");
                    String loginUsername = sc.nextLine();
                    System.out.print("Enter a password: ");
                    String loginPassword = sc.nextLine();
                    System.out.println(isLoginSuccessful(loginUsername, loginPassword));
                    if (isLoginSuccessful(loginUsername, loginPassword).equalsIgnoreCase("Successfully logged in!")) {
                        if (i == Integer.parseInt(people)) {
                            System.out.println("Game starts!");
                            break;
                        } else {
                            break;
                        }
                    }
                }
            } else {
                System.out.println("Not a choice.");
            }
        }
    }
}