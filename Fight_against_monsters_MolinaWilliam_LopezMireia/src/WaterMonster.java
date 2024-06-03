import java.util.Random;

import interficies.Water;

public  class WaterMonster extends Monster implements Water{
	private final Random rand;

    // Constructor
    public WaterMonster(String monsterName, int life, int strength, int speed) {
        super(monsterName, life, strength, speed);
        this.rand = new Random();
    }

    // Implementación del método waterAction de la interfaz Water
    @Override
    public int waterAction() {
        if (rand.nextDouble() < 0.5) {
            return waterShot();
        } else {
            return waterKick();
        }
    }

    // Métodos de ataque específicos de WaterMonster
    private int waterShot() {
        return rand.nextInt(MAXSHOTATTACK - 5 + 1) + 5;
    }

    private int waterKick() {
        return rand.nextInt(MAXKICKATTACK - 3 + 1) + 3;
    }
    

    // Implementación del método takeAttack de la clase Monster

    public String takeAttack(Player p) {
    	int damage;
    	if (evadeAttack(p)) {
            return "- Monster " + monsterName + " evaded the attack!";
        }
    	
    	if (p.getActualWeapon().getElement().equals("Fire")) {
            damage = (int) (1.5 * (p.getStrength() + p.getActualWeapon().getDamage()));
            life -= damage;
            return "- Monster " + monsterName + " take " + damage + " of damage";
            
        } else {
            damage = p.getStrength() + p.getActualWeapon().getDamage();
            life -= damage;
            return "- Monster " + monsterName + " take " + damage + " of damage";
        }
    	
    }
}
