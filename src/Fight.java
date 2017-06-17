import java.util.Scanner;

public class Fight {
    private Scanner sc = new Scanner(System.in);
    private Player player;
    private Monster monster;
    private int damage;

    private boolean end = false;

    /**
     * Der Konstruktor für den Kampf.
     *
     * @param player  initialisiert den Spieler, der für diese Klasse verwendet
     *                werden soll.
     * @param monster initialisiert das Monster, das für diese Klasse verwendet
     *                werden soll.
     */

    public Fight(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
    }

    /**
     * Die Methode move prüft, welchen Zug der Spieler wählt. Außerdem prüft sie,
     * ob eine valide Eingabe getätigt wurde und fragt erneut ab, wenn dies nicht
     * geschehen ist.
     *
     * @return move gibt zurück, welchen der beiden Züge der Spieler wählt.
     */

    public boolean move() {
        boolean wrongInput = false;
        boolean move = true;

        do {
            wrongInput = false;
            if (!this.end) {
                System.out.println("Use:\n(0) Potion\n(1) Attack");
            }

            System.out.print("You enter: ");
            String input = sc.next();

            switch (input) {
                case "0":
                    move = false;
                    break;
                case "1":
                    move = true;
                    break;
                default:
                    wrongInput = true;
                    System.out.println("Invalid input!");
                    System.out.println("--------------");
            }
        } while (wrongInput);
        System.out.println("--------------");
        return move;
    }

    /**
     * Die Methode turn prüft, ob das Spiel endet, indem es prüft, ob
     * die HP des Monsters oder des Spielers auf oder unter 0 gesunken sind.
     * Die Methode führt den Zug des Spielers aus und gibt die jeweiligen Nachrichten
     * aus.
     *
     * @return end gibt zurück, ob das Spiel beendet werden kann/soll.
     */

    public boolean turn() {
        boolean end = false;
        if (!this.move()) {
            if (player.usePotion()) {
                System.out.println("Player used potion and healed for 50 HP.");
            } else {
                System.out.println("No potions left.");
            }
        } else {
            int initialHpMonster = monster.getHP();
            monster.takeDamage(player.calculateAttackDamage());
            damage = initialHpMonster - monster.getHP();
            if (monster.getHP() <= 0) {
                System.out.println("Monster took " + damage + " damage from "
                        + "Player and is defeated." + "\nPlayer wins!");
                end = true;
            } else {
                System.out.println("Monster took " + damage + " damage from Player!");
            }
        }

        if (!end) {
            int initialHpMonster = monster.getHP();
            int initialHpPlayer = player.getHP();
            player.takeDamage(monster.calculateAttackDamage());
            damage = initialHpPlayer - player.getHP();
            if (player.getHP() <= 0) {
                System.out.println("Player took " + damage + " damage from "
                        + "Monster and is defeated." + "\nPlayer loses!");
                end = true;
            } else {
                System.out.println("Player took " + damage + " damage from Monster!");
                if (initialHpMonster < monster.getHP()) {
                    System.out.println("Monster healed for " + damage + " HP.");
                }
            }
        }

        return end;
    }
}