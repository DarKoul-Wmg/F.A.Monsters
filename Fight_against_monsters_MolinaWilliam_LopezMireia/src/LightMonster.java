import java.util.Random;

import interficies.Light;

public  class LightMonster extends Monster implements Light {
    private final Random rand;

    // Constructor
    public LightMonster(String monsterName, int life, int strength, int speed) {
        super(monsterName, life, strength, speed);
        this.rand = new Random();
    }

    // Implementación del método lightAction de la interfaz Light
    @Override
    public int lightAction() {
        if (rand.nextDouble() < 0.5) {
            return lightShot();
        } else {
            return lightKick();
        }
    }

    // Métodos de ataque específicos de LightMonster
    private int lightShot() {
        return rand.nextInt(MAXSHOTATTACK - 5 + 1) + 5;
    }

    private int lightKick() {
        return rand.nextInt(MAXKICKATTACK - 3 + 1) + 3;
    }

    // Implementación del método takeAttack de la clase Monster

    public String takeAttack(Player p) {
    	int damage;
    	if (evadeAttack(p)) {
            return "- Monster " + monsterName + " evaded the attack!";
        }
    	
    	if (p.getActualWeapon().getElement().equals("Shadow")) {
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
