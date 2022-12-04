import java.util.ArrayList;
import java.util.Iterator;

public class Elf {

	private ArrayList<Integer> snacks;
	
	public Elf() {
		snacks=new ArrayList<>();
	}
	
	public boolean addSnack(int snack) {
		return  snacks.add(snack);
	}
	
	public int getPesoElf() {
		int retorno=0;
		Iterator<Integer> i=snacks.iterator();
		while(i.hasNext()) {
			retorno=retorno + i.next();
		}
		return retorno;
	}
	
}
