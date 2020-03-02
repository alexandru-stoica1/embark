import java.util.Vector;

/**
 * Clasa Group reprezinta o entitate de tip group. Aceasta contine un vector
 * de pesoane Single, deoarece o familie contine mai multe persoane de tip
 * Single.
 */
public class Group extends Pasager {
    Vector <Single> v; 
	int priority = 5;
	
    /**
     * @param id preia id-ul din clasa parinte
     * Initializeaza un vector de Single
     */
    public Group (String id) {
    	super(id);
    	v = new Vector<>();
    }
    
	/**
	 * @param name este numele persoanei
	 * @param age este varsta persoanei
	 * @param ticket este tipul biletului
	 * @param priority_embark returneaza true daca biletul are imbarcare 
	 * prioritara si false altfel
	 * @param special_needs returneaza true daca persoana are nevoi speciale si
	 * false altfel
	 * Toate aceste atribute sunt introduse in vectorul v
	 */
    void add_Group(String name, int age, char ticket, boolean priority_embark, boolean special_needs) {
    	v.add(new Single(id, name, age, ticket, priority_embark, special_needs));
    }
    
    /**
    * Metoda calculeaza prioritatea entitatii
    */
    int add_priority() {	
    	for(int i = 0; i < v.size(); i++)
    		this.priority += v.elementAt(i).add_priority();
    	
    	return this.priority;
    }
	
    /**
	 * Metoda returneaza prioritatea
	 */
    int print_priority() {
    	return priority;
    }
}