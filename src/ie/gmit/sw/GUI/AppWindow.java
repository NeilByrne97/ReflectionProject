package ie.gmit.sw.GUI;


import javax.swing.*;
import javax.swing.border.BevelBorder;

import ie.gmit.sw.Controller.ReadJarFile;
import ie.gmit.sw.Controller.Reflection;
import ie.gmit.sw.Controller.ClassList;
import ie.gmit.sw.Controller.Map;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class AppWindow  {
	/**
	 * Creates Jframe containing an action listener for File Chooser and to call Results window class
	 *
	 * @author neilb
	 * @throws IOException
	 * @throws NoSuchMethodException
	 */
	
	private ResultsWindow resultsWindow;
	private JFrame frame;
	private ClassList classList;
	private Class cls;

	public AppWindow(){
		//	Create a window for the application
		frame = new JFrame();
		frame.setTitle("Java Reflection API");
		frame.setSize(600, 500);
		frame.setResizable(true);
		frame.setLayout(new FlowLayout());
				
        //	File chooser inside File Panel
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEADING));
        top.setBorder(new javax.swing.border.TitledBorder("Select Jar File"));
        top.setPreferredSize(dimensions(500, 100));
        top.setMaximumSize(dimensions(500, 100));
        top.setMinimumSize(dimensions(500, 100));
        
        JTextField txtFileName =  new JTextField(20);
		txtFileName.setPreferredSize(dimensions(100, 30));
		txtFileName.setMaximumSize(dimensions(100, 30));
		txtFileName.setMargin(new java.awt.Insets(2, 2, 2, 2));
		txtFileName.setMinimumSize(dimensions(100, 30));


        //	Choose jar file
		JButton btnChooseFile = new JButton("Browse");
		btnChooseFile.setToolTipText("Please Select Jar File");
        btnChooseFile.setPreferredSize(dimensions(90, 30));
        btnChooseFile.setMaximumSize(dimensions(90, 30));
        btnChooseFile.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnChooseFile.setMinimumSize(dimensions(90, 30));
		btnChooseFile.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent evt) {
        		JFileChooser fc = new JFileChooser("./");
        		int returnVal = fc.showOpenDialog(frame);
        		
            	if (returnVal == JFileChooser.APPROVE_OPTION) {
                	File file = fc.getSelectedFile().getAbsoluteFile();
                	String name = file.getAbsolutePath();
                    
                    if(name.endsWith(".jar") == true){
                        txtFileName.setText(name);
                    	System.out.println("You selected the following jar file: " + name);
                    	                     
                    }
                 	
            	}
			}
        });
		
		JButton btnOther = new JButton("Afferent Coupling");
		btnOther.setPreferredSize(dimensions(150, 30));
		btnOther.setMaximumSize(dimensions(150, 30));
		btnOther.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnOther.setMinimumSize(dimensions(150, 30));
		btnOther.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
            	resultsWindow =  new ResultsWindow(frame);
                
            	try {
            		ReadJarFile jar = new ReadJarFile();

                    classList = new ClassList();
					classList = jar.getJarContents(txtFileName.getText());

	                Reflection refl = new Reflection();
	                Map map = new Map();
	                map = refl.linesOfCode(classList);
	
				} catch (NoSuchMethodException | IOException e) {
					System.out.println("Unable to parse");
					e.printStackTrace();
				}

            }
        });
		
        top.add(txtFileName);
        top.add(btnChooseFile);
        top.add(btnOther);
        frame.getContentPane().add(top); //Add the panel to the window
        
        
        //	New panel to display result
        JPanel mid = new JPanel(new FlowLayout(FlowLayout.LEADING));
        mid.setBorder(new BevelBorder(BevelBorder.RAISED));
        mid.setPreferredSize(dimensions(500, 300));
        mid.setMaximumSize(dimensions(500, 300));
        mid.setMinimumSize(dimensions(500, 300));
        
       
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setPreferredSize(dimensions(500, 50));
        bottom.setMaximumSize(dimensions(500, 50));
        bottom.setMinimumSize(dimensions(500, 50));
                   
        JButton btnQuit = new JButton("Quit"); //Create Quit button
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	System.exit(0);
			}
        });
        bottom.add(btnQuit);

        frame.getContentPane().add(bottom);       
		frame.setVisible(true);
	}
	
	private Dimension dimensions(int i, int j) {
		return null;
	}

	public static void main(String[] args) {
		new AppWindow();
	}
	
	
}