import java.io.IOException;
import java.util.Scanner;

public class Game {
    private static boolean hasWon = false;

    public static boolean getHasWon() {
        return hasWon;
    }

    public static void setHasWon(boolean won) {
        hasWon = won;
    }

    private static int randomDimension() {
        int random = (int) (Math.random() * 45);
        if (random < 3) {
            return randomDimension();
        }
        if (random / 2 == (random + 1) / 2) {
            return random + 1;
        }
        return random;
    }

    public static void main(String[] meeps) {
        int playerHP = 0;
        int playerATK = 0;
        int playerHIT = 0;

        /**
         * �berpr�fung auf fehlerhafte Eingabe
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
        boolean error = true;

        try {
            map.load();
        } catch (IOException e) {
            System.out.println("Map does not pre-exist. Will be generated.");
            map = new GeneratedMap(randomDimension(), randomDimension(), player);

            while (error) {
                map = new GeneratedMap(randomDimension(), randomDimension(), player);
                error = false;
                try {
                    map.load();
                } catch (IOException i) {
                    error = true;
                }

                if (!error) {
                    try {
                        map.start();
                    } catch (NullPointerException j) {
                        error = true;
                    }
                }
            }
        }
    }
}