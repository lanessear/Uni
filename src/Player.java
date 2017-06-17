public class Player extends Character {
    private int healPotions = 3;

    /**
     * Der Konstruktor für den Spieler, der die Parameter der Character-Klasse übernimmt.
     * @param hp liest die vorgegebenen HP für den Spieler ein.
     * @param atk liest die vorgegebenen Schadenspunkte für den Spieler ein.
     * @param hit liest die vorgegebene Hitchance für den Spieler ein.
     */

    public Player(int hp, int atk, int hit) {
        super(hp, atk, hit);
    }

    /**
     * Getter healPotions
     * @return gibt die restilchen Heiltränke zurück.
     */

    public int getHealPotions() {
        return this.healPotions;
    }

    /**
     * Die Methode usePotion erhöht die HP des Spielers um 50, soweit die
     * maximalen HP nicht überschritten werden. In dem Fall, werden die HP
     * bis zu den maximalen HP erhöht.
     * Die HP des Spielers werden nur dann erhöht, wenn die Anzahl der
     * healPotions größer als 0 ist. Ist dies der Fall, wird die Zahl der
     * healPotions um 1 niedriger.
     * @return potionsLeft gibt an, ob noch Heiltränke übrig sind.
     */

    public boolean usePotion() {
        boolean potionsLeft = false;
        if (this.healPotions > 0) {
            this.hp = this.hp + 50;
            if (this.hp > this.maxHP) {
                this.hp = this.maxHP;
            }
            this.healPotions = this.healPotions - 1;
            potionsLeft = true;
        }
        return potionsLeft;
    }
}