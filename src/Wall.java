/**
 * Created by Lanessear on 24.06.2017.
 */
public class Wall extends Tile {
    private boolean isWalkable() {
        return true;
    }

    public String toString() {
        return "#";
    }
}
