package ie.gmit.Model;

public class Afferent {
	/**
	 * Getters and setters forr afferent coupling and class
	 * 
	 *  @author neilb
	 */
	    private int afferentCoupling;
	    private String className;

	    public Afferent(int afferentCoupling, String className){
	        this.afferentCoupling = afferentCoupling;
	        this.className = className;
	    }

	    public Afferent(){

	        super();
	    }

	    public int getAfferentCoupling() {

	        return afferentCoupling;
	    }

	    public void setAfferentCoupling(int afferentCoupling) {

	        this.afferentCoupling = afferentCoupling;
	    }

	    public String getClassName() {

	        return className;
	    }

	    public void setClassName(String className) {

	        this.className = className;
	    }

}
