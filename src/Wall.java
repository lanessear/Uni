/**
 * Created by Lanessear on 24.06.2017.
 */
public class Wall extends Tile {
    public boolean isWalkable() {
        return false;
    }

    public String toString() {
        return "#";
    }
}
