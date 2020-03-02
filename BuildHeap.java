import java.io.FileWriter;
import java.io.IOException;

/**
 * Clasa BuildHeap are ca variabile size, care reprezinta lungimea vectorului
 * de pasageri, vectorului pasagers care contine entitati si wr, fisierul de
 * output
 */
public class BuildHeap {
	int size;
	Pasager[] pasagers;
    FileWriter wr;
	
	/**
	 * Constructorul initializeaza lungimea vectorului cu 0
	 * @param max initializeaza vectorului cu numarul exact de entitati
	 * @throws IOException este exceptia pentru fisierul de iesire
	 */
	BuildHeap(int max) throws IOException{
		size = 0;
		pasagers = new Pasager[max];
		this.wr = new FileWriter("queue.out");
	}
	
	/**
	 * Metoda returneaza copilul stang
	 * @param i reprezinta parintele caruia vrem sa ii calculam copilul stang
	 * @return copilul stang
	 */
	public int Left(int i) {
		return (2*i + 1);
	}
	
	/**
	 * Metoda returneaza copilul drept
	 * @param i reprezinta parintele caruia vrem sa ii calculam copilul drept
	 * @return copilul drept
	 */
	public int Right(int i ) {
		return (2 * i + 2);
	}
	
	/**
	 * Metoda returneaza parintele unui copil sau 0 daca acesta este root
	 * @param i este copilul caruia ii calculam patintele
	 * @return returneaza parintele
	 */
	public int root(int i) {
		if ( i == 0)
			return 0;
		
		return (i - 1) /2;
	}
	
	/**
	 * Metoda verifica daca nodul i este frunza sau nu
	 * @param i reprezinta nodul de verificat
	 * @return true daca acesta e frunza si false in caz contrar
	 */
	public boolean isLeaf(int i) {
		if ( i >= (size / 2) && i <= size)
			return true;
		
		return false;
	}

	/**
	 * Metoda Heapify contstruieste heapul sau il sorteaza pornind de la nodul i
	 * Se foloseste de functiile Left si Right. Daca nodul respectiv e frunza,
	 * nu are ce sa contruiasca si se opreste.
	 */
	public void Heapify(int i) {
		int left = Left(i);
		int right = Right(i);
		
		if(isLeaf(i))
			return;
		
		int largest = i;
		
		if(left < size && pasagers[left]!= null && pasagers[left].print_priority() > pasagers[largest].print_priority() )
			largest = left;
		
		if(right < size && pasagers[right] != null && pasagers[right].print_priority() > pasagers[largest].print_priority())
			largest = right;
		
		if(largest != i) {
			 Pasager aux = pasagers[i];
			 pasagers[i] = pasagers[largest];
			 pasagers[largest] = aux;
			 Heapify(largest);
		}
		else Heapify(largest + 1);
	}
		
	/**
	 * Metoda insereaza un pasager in arbore. Inserarea se realizeaza pe ultima
	 * pozitie din arbore, elementul fiind apoi urcat succesiv pana cand se 
	 * indeplineste conditia de max heap. 
	 * @param p reperezinta pasagerul inserat
	 * @param priority este prioritatea acestuia
	 */
	public void insert(Pasager p, int priority) {
		pasagers[size] = p;
		size++;
		int current = size-1;

		if (size != 1)
			while ((pasagers[current].print_priority() > pasagers[root(current)].print_priority())) {
				 Pasager aux = pasagers[current];
				 pasagers[current] = pasagers[root(current)];
				 pasagers[root(current)] = aux;
				 current = root(current);
			}
	}
	
	/**
	 * Metoda afiseaza recursiv in fisier arborele, plecand de la nodul index
	 * @param index este pozitia de la care incepe printarea
	 * @throws IOException exceptie pentru fisierul de output
	 */
	public void print(int index) throws IOException {
		if(index < size) {
			if(index > 0)
				this.wr.write(" ");
			
			this.wr.write(pasagers[index].getId());
			print(Left(index));
			print(Right(index));
		}
	}

	/**
	 * Metoda afiseaza arborele in fisier, folosindu-se de metoda print
	 */
	public void list() throws IOException {
		print(0);
		this.wr.write("\n");
	}
	
	/**
	 * Metoda cauta in vectorul de persoane pasagerul p, il sterge si dupa 
	 * reface arborele
	 * @param p reprezinta pasagerul care trebuie sters
	 */
	public void delete(Pasager p) {
		for(int i = 0; i < size; i++)
			if(pasagers[i].equals(p))
			{
				pasagers[i] = pasagers[size - 1];
				pasagers[size - 1] = null;
				size --;
				Heapify(0);
			}
	}
	
	/**
	 * Metoda sterge rootul, pune ultimul element inserat pe prima pozitie, dupa
	 * care arborele este rearanjat
	 */
	public void embark() {
		pasagers[0]=pasagers[size-1];
		pasagers[size-1]=null;
		size --;
		Heapify(0);
	}
}