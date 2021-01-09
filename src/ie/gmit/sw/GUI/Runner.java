package ie.gmit.sw.GUI;


public class Runner {
	/**
	 * @param args
	 *	Creates a new Java swing Window
	 * @author neilb
	 */
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new AppWindow();
			}
		});
	}
}
