import java.io.IOException;
import java.util.Scanner;

public class Game {
    public static boolean hasWon = false;

    public static void main(String[] meeps) {
        int playerHP = 0;
        int playerATK = 0;
        int playerHIT = 0;

        /**
         * Überprüfung auf fehlerhafte Eingabe
         */

        if (meeps.length != 3) {
            System.out.println("Wrong input. It should be: [HP] [DMG] [HIT]");
            return;
        } else {
            try {
                playerHP = Integer.parseInt(meeps[0]);
                playerATK = Integer.parseInt(meeps[1]);
                playerHIT = Integer.parseInt(meeps[2]);

                if (playerHP <= 0) {
                    System.out.println("The HP are too low.");
                    return;
                }

                if (playerATK <= 0) {
                    System.out.println("The ATK is too low.");
                    return;
                }

                if (playerHIT <= 0) {
                    System.out.println("The HIT is too low.");
                    return;
                }

                if (playerHIT >= 100) {
                    System.out.println("The HIT is too high.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong input. It should be: [HP] [DMG] [HIT]");
                return;
            }

        }

        Player player = new Player(playerHP, playerATK, playerHIT);
        Map map = new Map("map.txt", player);

        try {
            map.load();
        } catch (IOException e) {
            System.out.println("Error, map does not exist.");
            return;
        }

        map.start();
    }
}