import java.util.ArrayList;

public class Player {
	private String name;
	private int life;
	private int strength;
	private int speed;
	private int exp;
	
	private ArrayList<Weapon>arrayListPlayerWeapons = new ArrayList<>();
	private Weapon actualWeapon;
	
	private static final int INITLIFE = 100;
	
	
	//Constructores
	public Player(String name, int life, int strength, int speed, int exp, Weapon actualWeapon) {
		super();
		this.name = name;
		this.life = life;
		this.strength = strength;
		this.speed = speed;
		this.exp = exp;
		this.actualWeapon = actualWeapon;
	}

	public Player() {
		super();
	}
	
	//Setters y Getters
	
	public String getName() {
		return name;
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

	public int getExp() {
		return exp;
	}

	public ArrayList<Weapon> getArrayListPlayerWeapons() {
		return arrayListPlayerWeapons;
	}

	public Weapon getActualWeapon() {
		return actualWeapon;
	}

	public int getInitLife() {
		return INITLIFE;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public void setActualWeapon(Weapon weapon){
		this.actualWeapon = weapon;
	}
	
	//toString
	
	public String toString() {
		return "Name = " + name + "\nStrength = " + strength + "\nSpeed = " + speed + "\nLife = " + life  + "\nExp = "
				+ exp + "\n";
	}

	
	//Metodos
	
	public void addWeapon(Weapon newWeapon){
		arrayListPlayerWeapons.add(newWeapon);
	}
		
	public void resetLife(){
		life = INITLIFE;
	}
	
	
	// evasion True or False: 
	public boolean evadeAttack(Monster monster) {
	    int evasionChance = 100 - monster.getSpeed();
	    return Math.random() * 100 < evasionChance;
	}

	
	public String takeAttack(Monster m) {
		int baseDamage = m.getStrength(); //pasar daño
		int exclusiveDamage = 0;
		
		//evade
		if (evadeAttack(m)) {
	        return "- Player evaded the attack!";
	    }
	
		
//pasamos monster m a element monster para implementar el metodo Action() correspondiente
		
		if (m instanceof ShadowMonster) {
			
			ShadowMonster Monster = (ShadowMonster) m;
			exclusiveDamage = Monster.shadowAction();
			
			int totalDamage = baseDamage + exclusiveDamage;
			life -= totalDamage;
			return "- Monster attack does "+baseDamage+" of basic damage\n- Monster attack does "+ exclusiveDamage + " ShadowAction ";
			
		}else if(m instanceof LightMonster) {
			
			LightMonster Monster = (LightMonster) m;
			exclusiveDamage = Monster.lightAction();
			
			int totalDamage = baseDamage + exclusiveDamage;
			life -= totalDamage;
			return "- Monster attack does "+baseDamage+" of basic damage\n- Monster attack does "+ exclusiveDamage + " LightAction ";
			
		}else if(m instanceof FireMonster) {
			
			FireMonster Monster = (FireMonster) m;
			exclusiveDamage = Monster.fireAction();
			
			int totalDamage = baseDamage + exclusiveDamage;
			life -= totalDamage;
			return "- Monster attack does "+baseDamage+" of basic damage\n- Monster attack does "+ exclusiveDamage + " FireAction ";
			
		}else if(m instanceof WaterMonster) {
			
			WaterMonster Monster = (WaterMonster) m;
			exclusiveDamage = Monster.waterAction();
			
			int totalDamage = baseDamage + exclusiveDamage;
			life -= totalDamage;
			return "- Monster attack does "+baseDamage+" of basic damage\n- Monster attack does "+ exclusiveDamage + " WaterAction ";
		}
		
		return "";
		
	}
}
