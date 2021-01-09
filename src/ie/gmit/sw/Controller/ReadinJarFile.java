package ie.gmit.sw.Controller;


import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Class reads a jar file and adds the resultd to the ClassList object.
 */
public class ReadinJarFile implements Readable {

	private static ClassList list;

	/**
	 *
	 * @param jarName
	 * @return list ie: ClassList
	 * @throws IOException
	 * @throws NoSuchMethodException
	 */
	public ClassList init(String jarName) throws IOException, NoSuchMethodException {
		list = new ClassList();
		JarInputStream in = new JarInputStream(new FileInputStream(new File(jarName)));
		JarEntry next = in.getNextJarEntry();
		
		while (next != null)
		{
			if (next.getName().endsWith(".class"))
			{
				String name = next.getName().replaceAll("/", "\\.");
				name = name.replaceAll(".class", "");
				if (!name.contains("$")) name.substring(0, name.length() - ".class".length());
				//System.out.println(name);
				Class queryClass = null;
				try {
					queryClass = Class.forName(name);
					list.add(queryClass);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			}
			next = in.getNextJarEntry();

		}
		return list;
	}

}
