package lab13;

import java.util.HashSet;
import java.util.Set;

public class MyHashSet {

	private Set<Integer> hashSet = new HashSet<Integer>();
	
	
	public synchronized void add(Integer arg){
		hashSet.add(arg);
		System.out.println("Dodaje: "+arg);
	}
	public synchronized void remove(Integer arg){
		hashSet.remove(arg);
	}
	public synchronized String getToString(){
		return hashSet.toString();
	}
	
}
