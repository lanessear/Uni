/**
 * Created by Lanessear on 24.06.2017.
 */
public class Battle extends Tile implements PlayerAction {
    private boolean used = false;

    public boolean isWalkable() {
        return true;
    }

    public void startAction(Player player) {

    }

    public String toString() {
        return "B";
    }
}
