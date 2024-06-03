

public abstract class Monster {


	// Clase abstracta Monster
	
	    String monsterName;
	    int life, strength, speed;
	    
	    int initLife = life;

	    // Constructor
	    public Monster(String monsterName, int life, int strength, int speed) {
	        this.monsterName = monsterName;
	        this.life = life;
	        this.strength = strength;
	        this.speed = speed;
	        this.initLife = life;
	    }
	    
	    
	   // sett get
	    
	    public String getMonsterName() {
			return monsterName;
		}


		public int getLife() {
			return life;
		}


		public int getStrength() {
			return strength;
		}


		public int getSpeed() {
			return speed;
		}

		public int getInitLife() {
			return initLife;
		}
		
		public void setLife(int life) {
			this.life = life;
		}


		//reset vida monster
		
		public void resetLife(){
			life = initLife;
		}
		
		// evade monster
		
	    public boolean evadeAttack(Player p) {
	        int evasionChance = 100 - (p.getSpeed() + p.getActualWeapon().getSpeed());
	        return Math.random() * 100 < evasionChance;
	    }

		// Método abstracto
	    abstract String takeAttack(Player p);

	    // Otros métodos comunes
	    public void displayStatus() {
	        System.out.println("Nombre: " + monsterName);
	        System.out.println("Vida: " + life);
	        System.out.println("Fuerza: " + strength);
	        System.out.println("Velocidad: " + speed);
	    }
	}
