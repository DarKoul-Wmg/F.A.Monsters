import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Main_FAM {
	
	// static para acceder a la clase sin instanciarla
	
	private static Scanner sc = new Scanner(System.in);
	private static int opcion;
	private static Player player;
	private static ArrayList<Weapon> weapons = new ArrayList<>();
	private static ArrayList<Monster> monsters = new ArrayList<>();

	public static void main(String[] args) {
		crearDataPartida();
		
		
		//MENU 
		do {
		    System.out.println("FIGTH AGAINST MONSTERS");
		    System.out.println("");
		    System.out.println("1. Create your character");
		    System.out.println("2. Fight against a monster");
		    System.out.println("3. Sort by...");
		    System.out.println("4. Exit");
		    System.out.print("Option: ");
		    
		    while (!sc.hasNextInt()) {
		        System.out.println("Error: Enter a number option.");
		        System.out.print("Option: ");
		        sc.nextLine(); // Limpiar el búfer de entrada
		    }
		    
		    opcion = sc.nextInt();
		
		    switch (opcion) {
		        case 1:
		            crearPersonaje();
		            break;
		        case 2:
		            partida();
		            break;
		        case 3:
		            menuSort();
		            break;
		        case 4:
		            System.out.println("Thanks for playing...");
		            break;
		        default:
		            System.out.println("Enter a valid option");
		    }
		} while (opcion != 4);
	}

	

	private static void crearDataPartida() {
		
		weapons.add(new Weapon("Fire Sword", "Fire", 20, 12, "ST12"));
        weapons.add(new Weapon("Emo sword", "Shadow", 14, 24, "TR16"));
        weapons.add(new Weapon("Light gravestone", "Light", 22, 13, "NT15"));
        weapons.add(new Weapon("Sea Slayer", "Water", 14, 25, "TG23"));
        weapons.add(new Weapon("Life Sword", "Grass", 18, 22, "AM27"));

        monsters.add(new WaterMonster("Oceanid", 120, 15, 5));
        monsters.add(new ShadowMonster("Mama grande", 130, 25, 2));
        monsters.add(new LightMonster("Sun Dragon", 200, 10, 10));
		monsters.add(new FireMonster("Nuclear Dragon", 200, 30, 10));
		monsters.add(new WaterMonster("Oceanid-minion", 120, 15, 5));
        monsters.add(new ShadowMonster("Mama grande-minion", 75, 15, 2));
        monsters.add(new LightMonster("Sun Dragon-baby", 60, 5, 10));
		monsters.add(new FireMonster("Nuclear Dragon-baby", 60, 10, 10));
		//System.out.println("datos cargados");
	}
	
	
	private static void crearPersonaje() {
		System.out.println("What's your name? ");
		String name = sc.next();
		sc.nextLine();
		
		int strength = (int) (5 + Math.random()*6);
		int speed = (int) (5 + Math.random()*6);
		
		System.out.println("Pick one of these weapons to start the fight: ");
		
		armasDisponibles();
		
		System.out.println("Enter the number of the weapon:");
		
		while (!sc.hasNextInt()) {
	        System.out.println("Error: Enter a number option.");
	        System.out.print("Option: ");
	        sc.next();
	    }
		
		int indexArma = sc.nextInt();
		sc.nextLine();
		
		if (indexArma >= 0 && indexArma < weapons.size()) {
			
			Weapon eleccionWeapon = weapons.get(indexArma);
			
			player = new Player(name,100,strength,speed,0,eleccionWeapon);
			System.out.println("Created Character:\n");
			System.out.println("\nPlayer:\n"+ player.toString());
			System.out.println("\nWeapons:\n"+ eleccionWeapon.infoArma());
			
		}else {
			System.out.println("Invalid weapon selection.");
		}
	}

	
	private static void armasDisponibles() {
		for (int i = 0; i < weapons.size(); i++) {
			System.out.println("-------------------------");
			System.out.println(i +"\n"+ weapons.get(i).toString());
		}
		
	}
	
	private static void partida(){
		Random rand = new Random();
		int turno = rand.nextInt(2);
		
		//comprobar player
		if (player == null) {
			
			System.out.println("Error: Create your character first.");
			return; //salir del metodo
		}
		
		Monster monster = getRandomMonster();
		System.out.println("\n================================================");
		System.out.println("THE FIGHT WILL START");
		System.out.println("================================================\n");
		
		
		// Logica de la partida 
		while (player.getLife() > 0 && monster.getLife() > 0) {
			
			System.out.println("\n================================================");
			System.out.println("THOSE ARE THE CURRENT STANDINGS\n");
			System.out.println(player.getName() +" has "+ player.getLife()+ " remaining Life");
			System.out.println(monster.getMonsterName() +" has "+ monster.getLife()+ " remaining Life");
			System.out.println("================================================");
			System.out.println("\n New round");
			
			if (turno == 0) {
				//player primero
				
				if (monster.getLife() <= 0) {
					break;
				}
				playerTurn(monster);
				
				monsterTurn(monster);
				
				System.out.println("================================================\n");
				System.out.println("Enter to continue...");
				sc.nextLine();// pausa el turno
				
			}else {
				
				//Monster primero
				if (player.getLife() <=0) {
					break;
				}
				monsterTurn(monster);
												
				playerTurn(monster);
				
				System.out.println("================================================\n");
				System.out.println("Enter to continue...");
				sc.nextLine();
				
			}
		}
		
	//Final del combate
		
		System.out.println("\nEnd of the combat\n");
		if (player.getLife() > 0) {
			System.out.println("-------------------------");
			System.out.println("Player " + player.getName() + " won!!");
			System.out.println("-------------------------");
			player.setExp(player.getExp() +1);
			System.out.println("Current experience of player " + player.getName()+": " + player.getExp()+"\n");
		}else {
			System.out.println("-------------------------");
			System.out.println(monster.getMonsterName() + " won!!\n");
			System.out.println("---------Game Over---------");
		}
		
		monster.resetLife();
		player.resetLife();

	}


	private static void monsterTurn(Monster monster) {
		System.out.println("\nTurn of the Player!");
		System.out.println("-------------------------\n");
        String attackResult = monster.takeAttack(player);
        System.out.println(attackResult);
    }
		

	private static void playerTurn(Monster monster) {
		System.out.println("\nTurn of the Monster!");
		System.out.println("-------------------------\n");
		String attack = player.takeAttack(monster);
		System.out.println(attack);
		
	}


	private static Monster getRandomMonster() {
		
		int index = (int) (Math.random() * monsters.size());
		return monsters.get(index);
	}
	
	
	private static void menuSort() {
		do {
			System.out.println("Sort by:");
		    System.out.println("\n1. Show Player Weapons ordered by damage");
		    System.out.println("2. Show Player Weapons ordered by speed");
		    System.out.println("3. Show Monsters ordered by Strength");
		    System.out.println("4. Show Monsters ordered by Health");
		    System.out.println("5. Back");
		    System.out.print("Option: ");
		    
		    while (!sc.hasNextInt()) {
		        System.out.println("Error: Enter a number option.");
		        System.out.print("Option: ");
		        sc.nextLine(); // Limpiar el búfer de entrada
		    }
		    
		    opcion = sc.nextInt();
		
		    switch (opcion) {
		        case 1:
		        	Collections.sort(weapons);
		        	System.out.println("*******************************");
		        	System.out.println("Weapons Ordered by Strength");
		        	System.out.println("*******************************\n");
		        	for (Weapon weapon : weapons) {
		        		String print = String.format("%-20s - Damage: %d", weapon.getName(),weapon.getDamage());
		                System.out.println(print);
		            }
		            break;
		            
		        case 2:
		        	
		        	//Comparación de la Speed de cada arma y ordenación de esta
		        	
		        	Comparator<Weapon> bySpeed = new Comparator<Weapon>() {
		        	   
		        	    public int compare(Weapon w1, Weapon w2) {
		        	        return w2.getSpeed() - w1.getSpeed();
		        	    }
		        	};

		        	Collections.sort(weapons, bySpeed);
		        	System.out.println("*******************************");
		        	System.out.println("Weapons Ordered by Speed");
		        	System.out.println("*******************************\n");
		        	for (Weapon weapon : weapons) {
		        		String print = String.format("%-20s - Speed: %d", weapon.getName(),weapon.getSpeed());
		                System.out.println(print);
		            }

		            break;
		            
		        case 3:
		        	
		        	Collections.sort(monsters, new SortMonstersStrength());
		        	System.out.println("*******************************");
		        	System.out.println("Monsters Ordered by Strength");
		        	System.out.println("*******************************\n");
		        	for (Monster monster : monsters) {
		        		String print = String.format("%-20s - Strength: %d", monster.getMonsterName(),monster.getStrength());
		                System.out.println(print);
		            }
		            break;
		        case 4:
		        	
		        	Collections.sort(monsters, new SortMonstersHealth());
		        	System.out.println("*******************************");
		        	System.out.println("Monsters Ordered by Health");
		        	System.out.println("*******************************\n");
		        	for (Monster monster : monsters) {
		        		String print = String.format("%-20s - Health: %d", monster.getMonsterName(),monster.getLife());
		                System.out.println(print);
		            }
		            break;
		            
		        case 5:
		        	
		        	System.out.println("Going to menu...");
		        	break;
		        	
		        default:
		            System.out.println("Enter a valid option");
		    }
		} while (opcion != 5);
	}

}

