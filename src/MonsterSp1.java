public class MonsterSp1 extends Monster {
    private int shield;

    /**
     * Konstruktor für das MonsterSp2. Die Parameter werden von der Klasse
     * Monster übernommen.
     *
     * @param hp     liest die vorgegebenen HP für das Monster ein.
     * @param atk    liest die vorgegebenen Schadenspunkte für das Monster ein.
     * @param hit    liest die vorgegebene Hitchance für das Monster ein.
     * @param shield liest das vorgegebene Schild für das Monster ein.
     */

    public MonsterSp1(int hp, int atk, int hit, int shield) {
        super(hp, atk, hit);
        this.shield = shield;
    }

    /**
     * Die Methode takeDamage zieht dem Monster HP der Größe damage minus
     * seines Schildes ab.
     *
     * @param damage ist der Schaden, der dem Monster zugefügt wird.
     */

    public void takeDamage(int damage) {
        if (damage > 0) {
            this.hp = (this.hp - damage) + this.shield;
        }
    }
}