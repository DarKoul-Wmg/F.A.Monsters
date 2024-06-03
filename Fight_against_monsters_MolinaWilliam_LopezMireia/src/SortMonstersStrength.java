import java.util.Comparator;


public class SortMonstersStrength implements Comparator<Monster> {
	public int compare(Monster m1, Monster m2) {
		return m2.getStrength() - m1.getStrength();
	}
}
