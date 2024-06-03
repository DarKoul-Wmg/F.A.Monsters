import java.util.Random;

import interficies.Shadow;

public  class ShadowMonster extends Monster implements Shadow{
	private final Random rand;

    // Constructor
    public ShadowMonster(String monsterName, int life, int strength, int speed) {
        super(monsterName, life, strength, speed);
        this.rand = new Random();
    }

    // Implementación del método shadowAction de la interfaz Shadow
   
    public int shadowAction() {
        if (rand.nextDouble() < 0.5) {
            return shadowShot();
        } else {
            return shadowKick();
        }
    }

    // Métodos de ataque específicos de ShadowMonster
    private int shadowShot() {
        return rand.nextInt(MAXSHOTATTACK - 5 + 1) + 5;
    }

    private int shadowKick() {
        return rand.nextInt(MAXKICKATTACK - 3 + 1) + 3;
    }

    // Implementación del método takeAttack de la clase Monster
    
    public String takeAttack(Player p) {
    	int damage;
    	if (evadeAttack(p)) {
            return "- Monster " + monsterName + " evaded the attack!";
        }
    	
    	if (p.getActualWeapon().getElement().equals("Light")) {
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
