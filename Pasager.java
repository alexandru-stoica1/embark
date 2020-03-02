/**
 * Clasa Pasager contine String id care reprezinta id-ul.
 */
public abstract class Pasager {
	String id;
	
	/**
	 * Constructorul primeste ca parametru id-ul si initializeaza id-ul din 
	 * clasa cu cel primit
	 */
	public Pasager(String id) {
		this.id = id;
	}
	
	/**
	 * @return este returnat id-ul
	 */
	public String getId() {
		return id;
	}
	
	abstract int add_priority();
	abstract int print_priority();
}