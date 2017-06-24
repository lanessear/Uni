/**
 * Created by Lanessear on 24.06.2017.
 */
public class Well extends Tile implements PlayerAction {
    public boolean isWalkable() {
        return true;
    }

    public void startAction(Player player) {
        player.setHealPotions(3);
        player.setHp(player.getMaxHP());
        System.out.println("Player regenerated health and potions.");
    }

    public String toString() {
        return "O";
    }
}
