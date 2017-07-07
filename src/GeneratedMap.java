
public class GeneratedMap extends Map {
	private int width;
	private int height;
	private int pathX;
	private int pathY;
	
	public GeneratedMap(int width, int height, Player player) {
		super(null, null);
		this.width = width;
		this.height = height;
		this.player = player;
		this.map = new Tile[height][width];
	}
	
	public void load() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				map[i][j] = initMap(i,j);
			}
		}
		
		if(!playerPlaced() && !goalPlaced()) {
			load();
		}
		generatePath();
		fillBlanks();
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				initBattles(i, j);
			}
		}
		
		if(!tilesPlaced()) {
			load();
		}
	}
	
	private Tile initMap(int height, int width) {
		if(map[height][width] == null){
			if(height == 0 || width == 0 ||
					height/2 == (height + 1)/2 || width/2 == (width + 1)/2) {
				return new Wall();
			} else {
				return new Empty();
			}
		} else {
			return initGoal(height, width);
		}
	}
	
	private Tile initGoal(int height, int width) {
		if(map[height][width] != null && map[height][width].toString().equals(" ")) {
			if(!playerPlaced() && placeChance()) {
				playerXCoordinate = width;
				playerYCoordinate = height;
				pathX = width;
				pathY = height;
			}
			if(!goalPlaced() && placeChance() && !map[height][width].toString().equals("P")) {
				return new Goal();
			}
		}
		return map[height][width];
	}
	
	private void initBattles(int height, int width) {
		if(placeChance() && !isTaken(height, width)) {
			map[height][width] = new Battle();
		}
		if(placeChance() && !isTaken(height, width)) {
			map[height][width] = new Well();
		}
		map[height][width] = map[height][width];
	}
	
	private void generatePath() {
		while(!map[pathY][pathX].toString().equals("G")) {
			randomDirection(pathY, pathX);
			generatePath();
		}
	}
	
	private boolean placeChance() {
		if((int)(Math.random()*100) < 5) {
			return true;
		}
		return false;
	}
	
	private boolean playerPlaced() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(map[i][j].toString().equals("P")) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean goalPlaced() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(map[i][j].toString().equals("G")) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean tilesPlaced() {
		int counterBattles = 0;
		int counterWells = 0;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(map[i][j].toString().equals("B")) {
					counterBattles++;
				}
				if(map[i][j].toString().equals("O")) {
					counterWells++;
				}
			}
		}
		
		if(counterBattles > 1 && counterWells > 1) {
			return true;
		}
		return false;
	}
	
	private void randomDirection(int height, int width) {
		int random = (int)(Math.random()*4);
		switch(random) {
			case 0:
				if(!isOuterWall(height, width + 2)) {
					pathX += 2;
					map[pathY][pathX - 1] = new Empty();
				}
				break;
			case 1:
				if(!isOuterWall(height, width - 2)) {
					pathX -= 2;
					map[pathY][pathX + 1] = new Empty();
				}
				break;
			case 2:
				if(!isOuterWall(height + 2, width)) {
					pathY += 2;
					map[pathY - 1][pathX] = new Empty();
				}
				break;
			case 3:
				if(!isOuterWall(height - 2, width)) {
					pathY -= 2;
					map[pathY + 1][pathX] = new Empty();
				}
				break;
		}
	}
	
	private boolean isOuterWall(int height, int width) {
		if(height <= 0 || width <= 0 || height > this.height - 2 || width > this.width - 2) {
			return true;
		}
		return false;
	}
	
	private boolean isTaken(int height, int width) {
		if(map[height][width].toString().equals(" ")) {
			return false;
		}
		return true;
	}
	
	private void fillBlanks() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(!isOuterWall(i, j)) {
					if(map[i - 1][j].toString().equals("#")
							&& map[i + 1][j].toString().equals("#")
							&& map[i][j - 1].toString().equals("#")
							&& map[i][j + 1].toString().equals("#")) {
						map[i][j] = new Wall();
					}
				}
			}
		}
	}
}
