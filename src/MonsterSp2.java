public class MonsterSp2 extends Monster {

    /**
     * Konstruktor für das MonsterSp2. Die Parameter werden von der Klasse
     * Monster übernommen.
     *
     * @param hp  liest die vorgegebenen HP für das Monster ein.
     * @param atk liest die vorgegebenen Schadenspunkte für das Monster ein.
     * @param hit liest die vorgegebene Hitchance für das Monster ein.
     */

    public MonsterSp2(int hp, int atk, int hit) {
        super(hp, atk, hit);
    }

    /**
     * Die Methode calculateAttackDamage berechnet anhand der Hitchance, ob
     * die Attacke des Monsters Schaden anrichten wird oder nicht. Sollte
     * die Hitchance des Monsters größer oder gleich der Zufallszahl sein,
     * trifft er, außerdem heilt es sich für den zugefügen Schaden.
     *
     * @return damage gibt die größe des Schadens zurück.
     */

    public int calculateAttackDamage() {
        int ranNum = (int) (Math.random() * 100);
        int damage = -1;
        if (ranNum <= this.hit) {
            damage = this.atk;
            this.hp = this.hp + this.atk;
            if (this.hp > this.maxHP) {
                this.hp = this.maxHP;
            }
        }
        return damage;
    }
}