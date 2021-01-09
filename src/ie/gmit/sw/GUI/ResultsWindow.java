package ie.gmit.sw.GUI;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class ResultsWindow {
	/**
	 * 
	 * Called when afferent coupling is complete to display classes and afferent coupling metric
	 *  @author neilb
	 */
	
	public ResultsWindow() {
	}
	
	public ResultsWindow(JFrame frame) {
		frame.setTitle("Java Reflection API - Results");
		frame.setSize(800, 500);
		frame.setResizable(true);
		frame.setLayout(new FlowLayout());
	}
	
	
}
