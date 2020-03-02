/**
 * Clasa Single reprezinta un pasager care are imbarcat singur. Clasa extinde
 * clasa Pasager. 
 */
public class Single extends Pasager{
	String name;
	int age;
	char ticket;
	boolean priority_embark;
	boolean special_needs;
	int priority = 0;
	
	/**
	 * @param id este id-ul persoanei
	 * @param name este numele persoanei
	 * @param age este varsta persoanei
	 * @param ticket este tipul biletului
	 * @param priority_embark returneaza true daca biletul are imbarcare 
	 * prioritara si false altfel
	 * @param special_needs returneaza true daca persoana are nevoi speciale si
	 * false altfel
	 */
	public Single(String id,String name, int age, char ticket, boolean priority_embark, boolean special_needs) {
		super(id);
		this.name = name;
		this.age = age;
		this.ticket = ticket;
		this.priority_embark = priority_embark;
		this.special_needs = special_needs;
	}

	/**
	 * Metoda calculeaza prioritatea pasagerului singur, in functie de 
	 * proprietatiile acestuia
	 */
	int add_priority() {
		if(age >= 0 && age < 2)
			priority += 20;
		if(age >= 2 && age < 5)
			priority += 10;
		if(age >= 5 && age < 10)
			priority += 5;
		if(age >= 60)
			priority += 15;
		if(ticket == 'b')
			priority += 35;
		if(ticket =='p')
			priority += 20;
		if(priority_embark == true)
			priority += 30;
		if(special_needs == true)
			priority += 100;
		
		return this.priority;
	}
    
	/**
	 * Metoda returneaza valoarea prioritatii
	 */
	int print_priority() {
    	return priority;
    }
}