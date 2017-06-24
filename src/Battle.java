/**
 * Created by Lanessear on 24.06.2017.
 */
public class Battle extends Tile implements PlayerAction {
    private boolean used = false;

    public boolean isWalkable() {
        return true;
    }

    public void startAction(Player player) {
        if(!used) {
            boolean end = false;

            Monster monster = new Monster(0, 0, 0);

            System.out.print("You fight against the ");

            int ranNum = (int) (Math.random() * 3);

            if (ranNum == 0) {
                monster = new Monster(player.getMaxHP() * 2,
                        player.getATK() / 2, 50);
                System.out.println("standard monster.");
            }

            if (ranNum == 1) {
                monster = new MonsterSp1(player.getMaxHP() * 2,
                        player.getATK() / 2, 50,
                        player.getATK() / 5);
                System.out.println("shield monster.");
            }

            if (ranNum == 2) {
                monster = new MonsterSp2(player.getMaxHP() * 2,
                        player.getATK() / 2, 50);
                System.out.println("healing monster.");
            }

            Fight fight = new Fight(player, monster);

            while (!end) {
                System.out.println("--------------");
                System.out.println("Potions left: " + player.getHealPotions());
                System.out.println("Player HP: " + player.toString());
                System.out.println("Monster HP: " + monster.toString());
                System.out.println("--------------");
                end = fight.turn();
            }

            used = true;
        }
    }

    public String toString() {
        if(!used) {
            return "B";
        } else {
            return " ";
        }
    }
}
