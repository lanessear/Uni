/**
 * Created by Lanessear on 24.06.2017.
 */
public class Well extends Tile implements PlayerAction {
    private boolean isWalkable() {
        return true;
    }

    public void startAction(Player player) {

    }

    public String toString() {
        return "O";
    }
}
