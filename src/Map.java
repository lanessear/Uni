import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Lanessear on 24.06.2017.
 */
public class Map {
    protected int playerXCoordinate;
    protected int playerYCoordinate;
    protected Player player;
    protected Tile[][] map;
    private Scanner sc;
    private String path;

    public Map(String path, Player player) {
        this.sc = new Scanner(System.in);
        this.player = player;
        this.path = path;
    }

    public void load() throws IOException {
        String file = path;
        BufferedReader br = Files.newBufferedReader(Paths.get(file));
        String line = null;
        int x = 0;
        int y = 0;
        while ((line = br.readLine()) != null) {
            x = 0;
            for (int i = 0; i < line.length(); i++) {
                x++;
            }
            y++;
        }
        map = new Tile[y][x];

        y = 0;
        br = Files.newBufferedReader(Paths.get(file));

        while ((line = br.readLine()) != null) {
            x = 0;

            for (int i = 0; i < line.length(); i++) {
                switch (line.charAt(i)) {
                    case 'P':
                        playerXCoordinate = x;
                        playerYCoordinate = y;
                    case ' ':
                        map[y][x] = new Empty();
                        break;
                    case '#':
                        map[y][x] = new Wall();
                        break;
                    case 'B':
                        map[y][x] = new Battle();
                        break;
                    case 'O':
                        map[y][x] = new Well();
                        break;
                    case 'G':
                        map[y][x] = new Goal();
                        break;
                    default: map[y][x] = null;
                }

                x++;
            }

            y++;
        }

    }

    /**
     * Diese soll in einer Schleife dem Benutzer die
     * Karte ausgeben und im Anschluss nach einer gew¨unschten
     * Richtung fragen. Nutzen Sie hierf¨ur die WASD-Steuerung10.
     * Sollte der Benutzer gegen eine Wand laufen oder eine
     * falsche Eingabe t¨atigen, soll es ihm mitgeteilt
     * werden. Betritt ein Spieler ein Feld, dass das
     * PlayerAction-Interface implementiert, so wird die
     * entsprechende startAction()-Methode aufgerufen.
     */
    public void start() {
        while(!Game.getHasWon()) {
            printMap();
            String input = readInput();
            move(input);
            if(player.getHP() == 0) {
                Game.setHasWon(true);
            }
        }
    }

    private String readInput() {
        boolean validInput = false;
        String direction;
        while (!validInput) {
            System.out.println("Which direction do you want" +
                    " to go?");
            direction = sc.next();
            if (direction.equals("w") || direction.equals("a")
                    || direction.equals("s") || direction.equals("d")) {
                return direction;
            } else {
                System.out.println("Invalid input.");
            }
        }
        return null;
    }

    private void move(String input) {
        switch(input) {
            case "w":
            case "W":
                if(map[playerYCoordinate - 1][playerXCoordinate].isWalkable()) {
                    playerYCoordinate = playerYCoordinate - 1;
                } else {
                    System.out.println("You can't walk there!");
                }
                break;
            case "a":
            case "A":
                if(map[playerYCoordinate][playerXCoordinate - 1].isWalkable()) {
                    playerXCoordinate = playerXCoordinate - 1;
                } else {
                    System.out.println("You can't walk there!");
                }
                break;
            case "s":
            case "S":
                if(map[playerYCoordinate + 1][playerXCoordinate].isWalkable()) {
                    playerYCoordinate = playerYCoordinate + 1;
                } else {
                    System.out.println("You can't walk there!");
                }
                break;
            case "d":
            case "D":
                if(map[playerYCoordinate][playerXCoordinate + 1].isWalkable()) {
                    playerXCoordinate = playerXCoordinate + 1;
                } else {
                    System.out.println("You can't walk there!");
                }
                break;
        }

        Tile playerPosition = map[playerYCoordinate][playerXCoordinate];

        if(playerPosition instanceof PlayerAction) {
            ((PlayerAction)playerPosition).startAction(player);
        }
    }

    private void printMap() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (x == playerXCoordinate && y == playerYCoordinate) {
                    System.out.print("P");
                } else {
                    System.out.print(map[y][x].toString());
                }
            }
            System.out.println();
        }
    }
}
