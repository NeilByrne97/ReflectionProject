package ie.gmit.sw.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class ReadJarFile{
	/**
	 *
	 * @param jarName
	 * @return list ie: ClassList
	 * @throws IOException
	 * @throws NoSuchMethodException
	 */
	
	List<Class> classList = new ArrayList<Class>();
	
	public List<Class> getJarContents(String jarName) throws IOException, NoSuchMethodException {
		
		//Create JarInputStream for jar file
				JarInputStream in = new JarInputStream(new FileInputStream(jarName));
				JarEntry next = in.getNextJarEntry();//Assign JarEntry to next
				
				//while next is not null
				while (next != null) {
					if (next.getName().endsWith(".class")) {
						String name = next.getName().replaceAll("/", "\\.");
						name = name.replaceAll(".class", "");
						if (!name.contains("$")) name.substring(0, name.length() - ".class".length());
						
						Class cls;
						try {
							cls = Class.forName(name);
							classList.add(cls);
							System.out.println("The class is " + cls);
						} 
						catch (ClassNotFoundException e) {
							System.out.println("Couldn't find class : " + name);
							System.out.println("Exception : " + e);
							System.exit(0);
						}
					}
					next = in.getNextJarEntry();
				}
				return classList;	
	}
}
