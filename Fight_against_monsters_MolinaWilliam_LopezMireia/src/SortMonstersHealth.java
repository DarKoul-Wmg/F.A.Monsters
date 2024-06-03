import java.util.Comparator;

public class SortMonstersHealth implements Comparator<Monster>{
	public int compare(Monster m1, Monster m2) {
		return m2.getLife() - m1.getLife();
	}
}
