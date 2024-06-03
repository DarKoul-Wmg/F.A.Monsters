import java.util.Random;

import interficies.Fire;

public  class FireMonster extends Monster implements Fire{
	private final Random rand;

    // Constructor
    public FireMonster(String monsterName, int life, int strength, int speed) {
        super(monsterName, life, strength, speed);
        this.rand = new Random();
    }

    // Implementación del método waterAction de la interfaz Water
    @Override
    public int fireAction() {
        if (rand.nextDouble() < 0.5) {
            return fireShot();
        } else {
            return fireKick();
        }
    }

    // Métodos de ataque específicos de WaterMonster
    private int fireShot() {
        return rand.nextInt(MAXSHOTATTACK - 5 + 1) + 5;
    }

    private int fireKick() {
        return rand.nextInt(MAXKICKATTACK - 3 + 1) + 3;
    }

    // Implementación del método takeAttack de la clase Monster
    
    public String takeAttack(Player p) {
    	int damage;
    	if (evadeAttack(p)) {
            return "- Monster " + monsterName + " evaded the attack!";
        }
    	
    	if (p.getActualWeapon().getElement().equals("Water")) {
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
