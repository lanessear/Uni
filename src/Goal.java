/**
 * Created by Lanessear on 24.06.2017.
 */
public class Goal extends Tile implements PlayerAction {
    public boolean isWalkable() {
        return true;
    }

    public void startAction(Player player) {
        Game.hasWon = true;
        System.out.println("You have won!");
    }

    public String toString() {
        return "G";
    }
}
