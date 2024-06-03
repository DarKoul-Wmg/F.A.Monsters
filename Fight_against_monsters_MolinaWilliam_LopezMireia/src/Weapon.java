
public class Weapon implements Comparable<Weapon>{
	private String name;
	private String element;
	private int damage;
	private int speed;
	private String id;
	
	//constr
	public Weapon(String name, String element, int damage, int speed, String id) {
		super();
		this.name = name;
		this.element = element;
		this.damage = damage;
		this.speed = speed;
		this.id = id;
	}
	public Weapon() {
		super();
	}
	
	//get-set
	public String getName() {
		return name;
	}
	public String getElement() {
		return element;
	}
	public int getDamage() {
		return damage;
	}
	public int getSpeed() {
		return speed;
	}
	public String getId() {
		return id;
	}
	
	// toString (usado para seleccion de arma)
	public String toString() {
		return "Name = " + name + "\nElement = " + element + "\nDamage = " + damage + "\nSpeed = " + speed +"\n";
	}
	
	// usado para mostrar info de weapons en linea (creación de personaje)
	public String infoArma() {
		return "Name: " + name + " Element: " + element + " Damage: " + damage + " Speed: " + speed +"\n";
	}
	
	public int compareTo(Weapon w) {
		return w.damage - this.damage;
	}
	
}
