package ie.gmit.sw.Controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;

public class Reflection {
	/**
	 * Deals with Reflection API to count the fields, methods, construtors, interfaces and parameters to calculate afferent coupling
	 * 
	 *  @returns map
	 *  @author neilb
	 */
	private static Map map;

    private String Name;

    private int afferentCoupling;

    public Reflection(){
    	super();
    }
    
    public Reflection(ClassList classList){
        linesOfCode(classList);
    }
    
    
    public Map linesOfCode(ClassList classList){
    	map = new Map();
    	
		int countFields =0;
		int countMethods =0;
		int countInterfaces =0;
		int countConstr =0;
		int countParams =0;

		
        for(int i=0; i<classList.size(); i++) {
        	Class cls = classList.getClass(i);
            Class[] interfaces = cls.getInterfaces();
            Constructor[] constructors = cls.getConstructors();
            Field[] fields = cls.getFields();
            TypeVariable[] params = cls.getTypeParameters();

            System.out.println(cls.toString());
            
            for(Class inter : interfaces){
    				if(!classList.contains(inter.getName())){
    					//classList.add(inter);
    					countInterfaces++;
    				}
    				System.out.println("Interface is " + countInterfaces);
    			}
            
            for(Constructor con : constructors){
				if(!classList.contains(con.getName())){
					countConstr++;
				}
				System.out.println("Constructors is " + countConstr);
			}
            
            for(Field field : fields){
				if(!classList.contains(field.getName())){
					countFields++;
				}
				System.out.println("Constructors is " + countFields);
			}
            
            for(TypeVariable param : params){
				if(!classList.contains(param.getName())){
					countParams++;
				}
				System.out.println("Constructors is " + countParams);
			}
            
            
            
            
    		}
		return map;        

    }
}

    