import java.io.*;
import java.util.*;

/**
 * Clasa queue contine metoda main
 */
public class queue {
	
	/**
	 * Metoda citeste un fisier de input si verifica daca acesta exista. Daca
	 * exista, se citeste primul parametru care este numarul entatiilor
	 * adaugate. Pentru fiecare entitate in parte, se citesc caracteristicile 
	 * acesteia.
	 * 
	 * In switch verific daca entitatea este de tip Single, Family sau Group. 
	 * Daca este Single, atunci o adaug in vectorul a, vector de care ne vom
	 * folosi pentru a implementa arborele. Daca este de tip Family, parcurg 
	 * vectorul a pentru a vedea daca gasesc o entitate cu acelasi id. Daca o
	 * gasesc, atunci adaug entitatea la cea gasita. Daca nu o gasesc, atunci
	 * o adaug la vectorul a si o si creez. Fac acelasi lucru si pentru Group.
	 * 
	 * In instructiunea while se citesc comenzi de la tastatura si se apeleaza, 
	 * in functie de ce e citit, functiile pe heap. Functiile delete si insert
	 * primesc ca parametrii pasagerul pe care trebuie sa il stearga sau insreze
	 * in heap, il cauta in vectoru si apoi folosesc functiile necesare.
	 * @throws IOException exceptie pentru fisiere de intrare
	 */
	public static void main(String[] args) throws IOException {
		Vector <Pasager> a = new Vector <>();
		File in = new File("queue.in");
		int found;
		
		if(in.exists() == false) {
			System.out.println("not exists");
			return;
		}
	
		Scanner sc = new Scanner(in);
	    	Scanner input = sc.useDelimiter("\\W+");
	
		int nr = input.nextInt();
	
		for(int i = 0; i < nr; i++) {
			String id = input.next();
			String name = input.next();
			int age = input.nextInt();
			char ticket = input.next().charAt(0);
			boolean priority_embark = Boolean.parseBoolean(input.next());
			boolean special_needs = Boolean.parseBoolean(input.next());
			
			switch(id.charAt(0)) {
			case 's': 
				a.add(new Single(id, name, age, ticket, priority_embark, special_needs));
				break;
			
			case 'g': 
				found = 0;
				Group grup = null;
				
				for(int j = 0; j < a.size(); j++) {
					if(a.elementAt(j).getId().compareTo(id) == 0) {
						found++;
						grup = (Group)a.elementAt(j);
					}
				}
				
				if(found == 0) {
					Group g = new Group(id);
					g.add_Group(name, age, ticket, priority_embark, special_needs);
					a.add(g);
				}
				else grup.add_Group(name, age, ticket, priority_embark, special_needs);
				break;
			
			case 'f': 
				found = 0;
				Family familie = null;
			
				for(int j = 0; j < a.size(); j++) {
					if(a.elementAt(j).getId().compareTo(id) == 0) {
						found++;
						familie = (Family)a.elementAt(j);
					}
				}
				
				if(found == 0) {
					Family g = new Family(id);
					g.add_family(name, age, ticket, priority_embark, special_needs);
					a.add(g);
				}
				else {
					familie.add_family(name, age, ticket, priority_embark, special_needs);
				}
			break;
			}
		}
		
		BuildHeap heap = new BuildHeap(nr);
	
		while(input.hasNext()) {
			String character = input.next();
		
			if(character.equals("embark"))
				heap.embark();
	
			if(character.equals("insert")) {
				character = input.next();
				Pasager p = null;
			
				for(int k = 0; k < a.size(); k++)
					if(a.elementAt(k).getId().equals(character))
					{
						a.elementAt(k).add_priority();
						p = a.elementAt(k);
					}
				
				heap.insert(p, p.print_priority());
			}
			
			if(character.equals("list"))
				heap.list(); 
			
			if(character.equals("delete")) {
				character = input.next();
				Pasager p = null;
				
				for(int k = 0; k < a.size(); k++)
					if(a.elementAt(k).getId().equals(character))
						p = a.elementAt(k);
				
				heap.delete(p);
			}
		}
	heap.wr.close();
	sc.close();
	input.close();
	}
}