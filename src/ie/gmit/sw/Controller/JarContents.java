package ie.gmit.sw.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JarContents{

	private List<Class> classList;
	private String name;
	private ReadJarFile jar = new ReadJarFile();
	
	/**
	 * @param jarName Holds jar file location
	 * @throws FileNotFoundException Throws a file not found exception
	 * @throws IOException Throws input/output exception
	 * @throws NoSuchMethodException 
	 */
	public JarContents(String jarName) throws FileNotFoundException, IOException, NoSuchMethodException{
		this.name = jarName;
		this.classList = jar.getJarContents(name);
	}
   
	/**
	 *Reflection API used to get Interfaces, Constructors, Fields, Methods and params from classes
	 * @param cls Class
	 */
	public void reflection(Class cls){
		// A list to track each referenced class to avoid duplicates
		List<String> classList = new ArrayList<String>();

		Class[] interfaces = cls.getInterfaces(); // Get the set of Interfaces
		for(Class inter : interfaces){
				System.out.println("Interfaces is " + inter);
			}
		}
}
