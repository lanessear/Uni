import java.io.IOException;

public class GeneratedMap extends Map {
    private int width;
    private int height;
    private int pathX;
    private int pathY;
    private int counter = 0;

    public GeneratedMap(int width, int height, Player player) {
        super(null, player);
        this.width = width;
        this.height = height;
        this.map = new Tile[height][width];
    }

    public void load() throws IOException {
        if (counter > 1) {
            throw new IOException("Error loading map. StackOverflowError.");
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = initMap(i, j);
            }
        }
        System.out.println("1% ... Map initialized.");

        if (!playerPlaced() && !goalPlaced()) {
            counter++;
            load();
            return;
        }
        System.out.println("20% ... Player & Goal placed.");

        counter = 0;
        while (!checkPathDone()) {
            if (counter > 5) {
                load();
                return;
            }
            counter++;
            try {
                System.out.println("30% ... Generaing path...");
                generatePath();
            } catch (IOException e) {
                System.out.println(e);
                load();
                return;
            }
        }
        System.out.println("40% ... Path generated.");

        fillBlanks();
        System.out.println("60% ... Blanks Filled.");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                initTiles(i, j);
            }
        }
        System.out.println("80% ... Battles initialized.");

        if (!tilesPlaced()) {
            load();
            return;
        }
        System.out.println("100% ... Tiles Placed.");
    }

    private Tile initMap(int height, int width) {
        if (map[height][width] == null) {
            if (height == 0 || width == 0 ||
                    height / 2 == (height + 1) / 2 || width / 2 == (width + 1) / 2) {
                return new Wall();
            } else {
                return new Empty();
            }
        } else {
            return initGoal(height, width);
        }
    }

    private Tile initGoal(int height, int width) {
        if (!isTaken(height, width)) {
            if (!playerPlaced() && placeChance()) {
                playerXCoordinate = width;
                playerYCoordinate = height;
                pathX = width;
                pathY = height;
            }
            if (!goalPlaced() && placeChance() && !map[height][width].toString().equals("P")) {
                return new Goal();
            }
        }
        return map[height][width];
    }

    private void initTiles(int height, int width) {
        if (placeChance() && !isTaken(height, width)) {
            map[height][width] = new Battle();
        }
        if (placeChance() && !isTaken(height, width)) {
            map[height][width] = new Well();
        }
    }

    private void generatePath() throws IOException {
        if (counter > 500) {
            throw new IOException("Error generating path. StackoverflowError");
        }
        while (!checkPathDone()) {
            counter++;
            randomDirection(pathY, pathX);
            generatePath();
        }
    }

    private boolean placeChance() {
        if ((int) (Math.random() * 100) < 5) {
            return true;
        }
        return false;
    }

    private boolean playerPlaced() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j].toString().equals("P")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean goalPlaced() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j].toString().equals("G")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean tilesPlaced() {
        int counterBattles = 0;
        int counterWells = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j].toString().equals("B")) {
                    counterBattles++;
                }
                if (map[i][j].toString().equals("O")) {
                    counterWells++;
                }
            }
        }

        if (counterBattles >= 1 && counterWells >= 1) {
            return true;
        }
        return false;
    }

    private void randomDirection(int height, int width) {
        int random = (int) (Math.random() * 4);
        switch (random) {
            case 0:
                if (!isOuterWall(height, width + 2)) {
                    pathX += 2;
                    map[pathY][pathX - 1] = new Empty();
                }
                break;
            case 1:
                if (!isOuterWall(height, width - 2)) {
                    pathX -= 2;
                    map[pathY][pathX + 1] = new Empty();
                }
                break;
            case 2:
                if (!isOuterWall(height + 2, width)) {
                    pathY += 2;
                    map[pathY - 1][pathX] = new Empty();
                }
                break;
            case 3:
                if (!isOuterWall(height - 2, width)) {
                    pathY -= 2;
                    map[pathY + 1][pathX] = new Empty();
                }
                break;
        }
    }

    private boolean isOuterWall(int height, int width) {
        if (height <= 0 || width <= 0 || height > this.height - 2 || width > this.width - 2) {
            return true;
        }
        return false;
    }

    private boolean isTaken(int height, int width) {
        if (map[height][width].toString().equals(" ") && width != playerXCoordinate && height != playerYCoordinate) {
            return false;
        }
        return true;
    }

    private void fillBlanks() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!isOuterWall(i, j)) {
                    if (map[i - 1][j].toString().equals("#")
                            && map[i + 1][j].toString().equals("#")
                            && map[i][j - 1].toString().equals("#")
                            && map[i][j + 1].toString().equals("#")) {
                        map[i][j] = new Wall();
                    }
                }
            }
        }
    }

    private boolean checkPathDone() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j].toString().equals("G")) {
                    if (!map[i - 1][j].toString().equals("#")
                            || !map[i + 1][j].toString().equals("#")
                            || !map[i][j - 1].toString().equals("#")
                            || !map[i][j + 1].toString().equals("#")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
