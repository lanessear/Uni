public class Character {
    protected int maxHP;
    protected int hp;
    protected int atk;
    protected int hit;

    /**
     * Der Konstruktor für den Charakter.
     *
     * @param hp  liest die vorgegebenen HP für den Charakter ein.
     * @param atk liest die vorgegebenen Schadenspunkte für den Charakter ein.
     * @param hit liest die vorgegebene Hitchance für den Charakter ein.
     */

    public Character(int hp, int atk, int hit) {
        this.maxHP = hp;
        this.hp = hp;
        this.atk = atk;
        this.hit = hit;
    }

    /**
     * Getter hp
     *
     * @return hp gibt die HP des Charakters zurück.
     */

    public int getHP() {
        int hp = this.hp;
        return hp;
    }

    /**
     * Getter maxHp
     *
     * @return maxHp gibt die MaxHP des Charakters zurück.
     */

    public int getMaxHP() {
        int maxHp = this.maxHP;
        return maxHp;
    }

    /**
     * Getter atk
     *
     * @return atk gibt die Schadenspunkte des Charakters zurück.
     */

    public int getATK() {
        int atk = this.atk;
        return atk;
    }

    /**
     * Die Methode toString wandelt den Integer hp zu einem String um und gibt diesen zurück.
     *
     * @return currentHP gibt die HP des Charakters in einem String zurück.
     */

    public String toString() {
        String currentHP = "" + this.hp;
        return currentHP;
    }

    /**
     * Die Methode calculateAttackDamage berechnet anhand der Hitchance, ob
     * die Attacke des Charakters Schaden anrichten wird oder nicht. Sollte
     * die Hitchance des Charakters größer oder gleich der Zufallszahl sein,
     * trifft er.
     *
     * @return damage gibt die größe des Schadens zurück.
     */

    public int calculateAttackDamage() {
        int ranNum = (int) (Math.random() * 100);
        int damage = -1;
        if (ranNum <= this.hit) {
            damage = this.atk;
        }
        return damage;
    }

    /**
     * Die Methode takeDamage zieht dem Charakter HP der Größe damage ab.
     *
     * @param damage ist der Schaden, der dem Charakter zugefügt wird.
     */

    public void takeDamage(int damage) {
        if (damage > 0) {
            this.hp = this.hp - damage;
        }
    }
}