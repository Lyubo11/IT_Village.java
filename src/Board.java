import java.io.*;
import java.util.*;

public class Board {

    public static final String green = "\u001B[32m";  //TODO Color-ni si game-a

    public static final String red = "\u001B[31m";

    public static final String yellow = "\u001B[33m";

    public static final String resetColor = "\u001B[0m";

    public static int positionOnBoard(int moves, int idInGame, int[] randPosition, int[] currentPosition) {
        if (moves != 0) {
            return currentPosition[idInGame];
        } else {
            return randPosition[idInGame];
        }
    }

    public static char letterFromBoard(char[] myCoord, int moves, int idInGame, int[] randPosition, int[] currentPosition) {
        return myCoord[positionOnBoard(moves, idInGame, randPosition, currentPosition) - 1];
    }

    public static void namesOfPlacesOnBoard(char[] myCoord, int moves, int idInGame, int[] randPosition, int[] currentPosition) {
        switch (letterFromBoard(myCoord, moves, idInGame, randPosition, currentPosition)) {
            case 'P' -> System.out.println("Wi-Fi Pub");
            case 'I' -> System.out.println("Wi-Fi Motel");
            case 'F' -> System.out.println("Freelance Project");
            case 'S' -> System.out.println("Storm");
            case 'V' -> System.out.println("Super PHP");
            case 'N' -> System.out.println("VSC");
        }
    }

    public static void descOfPlacesOnBoard(char[] myCoord, int moves, int idInGame, int[] randPosition, int[] currentPosition) {
        switch (letterFromBoard(myCoord, moves, idInGame, randPosition, currentPosition)) {
            case 'P' -> System.out.println("You have to buy a Cloud Cocktail! (-5)");
            case 'I' -> {
                System.out.println("If you have enough money, you have to buy it! (-100)");
                System.out.println("If not - you have to pay your stay! (-10)");
            }
            case 'F' -> System.out.println("You receive a payment! (+20)");
            case 'S' ->
                    System.out.println("The Wi-Fi in the village dies, you become depressed and skip two rows! (-2 rows)");
            case 'V' -> System.out.println("Your money multiplies by 10! (*10)");
            case 'N' -> System.out.println("If you step on this you win the game! (GG)");
        }
    }

    public static void moneyOfPlayers(char[] myCoord, int moves, int idInGame, int[] randPosition, int[] currentPosition, int[] playersMoney, boolean[] isItBought, int[] BuyersID, int[] missingMoves) {
        switch (letterFromBoard(myCoord, moves, idInGame, randPosition, currentPosition)) {
            case 'P' -> playersMoney[idInGame] = playersMoney[idInGame] - 5;
            case 'I' -> {
                if (currentPosition[idInGame] == 2 && isItBought[0]) {
                    if (BuyersID[0] != idInGame) {
                        System.out.println("You stepped on Player#" + (BuyersID[0] + 1) + "'s motel");
                        System.out.println("You need to pay 20 money to Player#" + (BuyersID[0] + 1) + "! (-20)");
                        if (missingMoves[BuyersID[0]] == 0){
                            playersMoney[BuyersID[0]] = playersMoney[BuyersID[0]] + 20;     //if the owner of the motel is not missing moves give his money else not
                        }
                        playersMoney[idInGame] = playersMoney[idInGame] - 20;
                    }
                }
                if (currentPosition[idInGame] == 7 && isItBought[1]) {
                    if (BuyersID[1] != idInGame) {
                        System.out.println("You stepped on Player#" + (BuyersID[1] + 1) + "'s motel");
                        System.out.println("You need to pay 20 money to Player#" + (BuyersID[1] + 1) + "! (-20)");
                        if (missingMoves[BuyersID[1]] == 0){
                            playersMoney[BuyersID[1]] = playersMoney[BuyersID[1]] + 20;     //if the owner of the motel is not missing moves give his money else not
                        }
                        playersMoney[idInGame] = playersMoney[idInGame] - 20;
                    }
                }
                if (currentPosition[idInGame] == 10 && isItBought[2]) {
                    if (BuyersID[2] != idInGame) {
                        System.out.println("You stepped on Player#" + (BuyersID[2] + 1) + "'s motel");
                        System.out.println("You need to pay 20 money to Player#" + (BuyersID[2] + 1) + "! (-20)");
                        if (missingMoves[BuyersID[2]] == 0){
                            playersMoney[BuyersID[2]] = playersMoney[BuyersID[2]] + 20;     //if the owner of the motel is not missing moves give his money else not
                        }
                        playersMoney[idInGame] = playersMoney[idInGame] - 20;
                    }
                }
                if ((playersMoney[idInGame] >= 100 && currentPosition[idInGame] == 2) && !isItBought[0]) {
                    playersMoney[idInGame] = playersMoney[idInGame] - 100;
                    isItBought[0] = true;
                    System.out.println("You bought the first motel!");
                    BuyersID[0] = idInGame;
                } else if ((playersMoney[idInGame] >= 100 && currentPosition[idInGame] == 7) && !isItBought[1]) {
                    playersMoney[idInGame] = playersMoney[idInGame] - 100;
                    isItBought[1] = true;
                    System.out.println("You bought the second motel!");
                    BuyersID[1] = idInGame;
                } else if ((playersMoney[idInGame] >= 100 && currentPosition[idInGame] == 10) && !isItBought[2]) {
                    playersMoney[idInGame] = playersMoney[idInGame] - 100;
                    isItBought[2] = true;
                    System.out.println("You bought the third motel!");
                    BuyersID[2] = idInGame;
                } else if ((playersMoney[idInGame] < 100 && currentPosition[idInGame] == 2 && !isItBought[0]) || (playersMoney[idInGame] < 100 && currentPosition[idInGame] == 7 && !isItBought[1]) || (playersMoney[idInGame] < 100 && currentPosition[idInGame] == 10 && !isItBought[2])) {
                    playersMoney[idInGame] = playersMoney[idInGame] - 10;
                }
            }
            case 'F' -> playersMoney[idInGame] = playersMoney[idInGame] + 20;
            case 'V' -> playersMoney[idInGame] = playersMoney[idInGame] * 10;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int moves = 0;
        boolean[] isItBought = new boolean[3];
        String filePath = "D:\\.Digital Razgrad\\Java - Модул I\\Final Project\\IT_Village\\Players_count.csv";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int players = Integer.parseInt(reader.readLine());
        char[] myCoord = new char[12];
        Random rand = new Random();
        char[][] test = {
                {'P', 'I', 'F', 'S'},
                {'P', ' ', ' ', 'F'},
                {'N', ' ', ' ', 'V'},
                {'I', 'F', 'F', 'I'}
        };

        myCoord[0] = test[0][0];
        myCoord[1] = test[0][1];
        myCoord[2] = test[0][2];
        myCoord[3] = test[0][3];
        myCoord[4] = test[1][3];
        myCoord[5] = test[2][3];
        myCoord[6] = test[3][3];
        myCoord[7] = test[3][2];
        myCoord[8] = test[3][1];
        myCoord[9] = test[3][0];
        myCoord[10] = test[2][0];
        myCoord[11] = test[1][0];


        int[] randPosition = new int[players];
        int[] currentPosition = new int[players];
        int[] playersMoney = new int[players];
        int[] buyersID = new int[3];
        Arrays.fill(playersMoney, 50);          // Fills the array with equal values (gives every player 50 money)
        int[] missingMoves = new int[players];
        int[] exLetters = new int[players];

        while (moves <= 25) {
            if (moves == 25) {
                for (int i = 1; i <= players; ++i) {
                    System.out.println();
                    System.out.println("Player#" + i + " ran out of moves. Player#" + i + " has got " + playersMoney[i - 1] + " money!");
                }
                System.exit(0);
            } else {
                String rollAgain = sc.nextLine();
                String wait = "Rolling the dice";
                System.out.print(wait);

            }
            new Timer().scheduleAtFixedRate(new TimerTask() {           // A timer that runs a function every second until the dots become three (simulating dice rolling)
                int times = 0;

                @Override
                public void run() {
                    times++;
                    System.out.print(".");
                    if (times == 3) {
                        System.out.println();
                        cancel();
                    }
                }
            }, 1000, 1000);

            int tempMoves = moves;
            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    int idInGame = 0;
                    for (int i = 1; i <= players; ++i) {
                        if (playersMoney[i - 1] <= 0) {             //TODO sled kato mqkoi run outne ot pari,drugite da produljavat da poluchavat pari
                            System.out.println();
                            System.out.println("Player#" + i + " ran out of money!");
                            continue;
                        }
                        if ((isItBought[0] && (buyersID[0] == i - 1)) && (isItBought[1] && (buyersID[1] == i - 1)) && (isItBought[2] && (buyersID[2] == i - 1))) {
                            System.out.println("Player#" + i + " bought all motels. He has " + playersMoney[i - 1] + " money!");
                            System.out.println("You have won the game!!!");
                            System.exit(0);

                        }

                        randPosition[i - 1] = rand.nextInt(myCoord.length) + 1;
                        System.out.println();
                        System.out.println("Player #" + i + ":");
                        int randNumForDice = rand.nextInt(6) + 1;

                        if (missingMoves[i - 1] == 0) {
                            if (tempMoves != 0) {
                                int count = 1;
                                for (int j = 1; j <= randNumForDice; ++j) {
                                    currentPosition[idInGame] = currentPosition[idInGame] + count;
                                    if (currentPosition[idInGame] > 12) {
                                        currentPosition[idInGame] = 1;
                                    }
                                }
                                System.out.println("Result from the dice: " + randNumForDice);
                                System.out.println("Position: " + positionOnBoard(tempMoves, idInGame, randPosition, currentPosition));
                            } else {
                                currentPosition[idInGame] = randPosition[idInGame];
                                System.out.println("Position in the start: " + positionOnBoard(tempMoves, idInGame, randPosition, currentPosition));
                            }
                        }
                        exLetters[i - 1] = letterFromBoard(myCoord, tempMoves, idInGame, randPosition, currentPosition);

                        if ((exLetters[i - 1] == 'S') && (missingMoves[i - 1] <= 3)) {
                            if (tempMoves != 0 && missingMoves[i - 1] != 0) {
                                System.out.println("Position: " + positionOnBoard(tempMoves, idInGame, randPosition, currentPosition));
                            }
                            if (missingMoves[i - 1] > 0) {
                                System.out.println();
                                System.out.println("Player#" + i + " is missing a move!");
                                System.out.println();
                                ++missingMoves[i - 1];
                                if (missingMoves[i - 1] == 3) {
                                    missingMoves[i - 1] = 0;
                                }
                                ++idInGame;
                                continue;
                            }
                            ++missingMoves[i - 1];

                            if (missingMoves[i - 1] == 2) {
                                missingMoves[i - 1] = 0;
                            }
                        }

                        System.out.println("Letter from the board: " + letterFromBoard(myCoord, tempMoves, idInGame, randPosition, currentPosition));
                        System.out.print("Name of place on the board: ");
                        namesOfPlacesOnBoard(myCoord, tempMoves, idInGame, randPosition, currentPosition);
                        descOfPlacesOnBoard(myCoord, tempMoves, idInGame, randPosition, currentPosition);
                        moneyOfPlayers(myCoord, tempMoves, idInGame, randPosition, currentPosition, playersMoney, isItBought, buyersID, missingMoves);
                        System.out.println("Money: " + playersMoney[i - 1]);

                        if ((letterFromBoard(myCoord, tempMoves, idInGame, randPosition, currentPosition) == 'N') && tempMoves != 0) {
                            System.out.println();
                            System.out.println("Player#" + i + " has won the game from " + (tempMoves + 1) + " moves!");
                            System.exit(0);
                        } else if ((letterFromBoard(myCoord, tempMoves, idInGame, randPosition, currentPosition) == 'N') && tempMoves == 0) {
                            System.out.println("Sorry, you can't win from the first time!");
                        }
                        ++idInGame;
                    }
                    cancel();
                }
            }, 3500, 100);
            ++moves;
        }
    }
}
