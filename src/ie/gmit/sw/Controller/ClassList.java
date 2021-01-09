package ie.gmit.sw.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassList {
	/**
	 * Creates array list to contain classes and to call on particular class
	 * 
	 *  @author neilb
	 */
	
	private List <Class> classList  = new  ArrayList<Class> ();

	public boolean add(Class arg0) {
		return classList.add(arg0);
	}

	public void clear() {
		classList.clear();
	}

	public boolean contains(Object arg0) {

		return classList.contains(arg0);
	}

	public Class getClass(int index) {

		return classList.get(index);
	}

	public boolean isEmpty() {
		return classList.isEmpty();
	}

	public boolean remove(Object arg0) {
		return classList.remove(arg0);
	}

	public int size() {
		return classList.size();
	}

	

}
