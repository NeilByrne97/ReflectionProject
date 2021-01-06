package ie.gmit.sw.GUI;


import javax.swing.*;
import javax.swing.border.BevelBorder;

import ie.gmit.sw.Controller.JarContents;
import ie.gmit.sw.Controller.ReadJarFile;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class AppWindow  {
	private JFrame frame;
	private Class myClass;


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
        
        final JTextField txtFileName =  new JTextField(20);
		txtFileName.setPreferredSize(dimensions(100, 30));
		txtFileName.setMaximumSize(dimensions(100, 30));
		txtFileName.setMargin(new java.awt.Insets(2, 2, 2, 2));
		txtFileName.setMinimumSize(dimensions(100, 30));


        //	Choose jar file
		JButton btnChooseFile = new JButton("Browse...");
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

                        try {
							JarContents contents = new JarContents(name);
		                	ReadJarFile jar = new ReadJarFile();
							jar.getJarContents(name);
							System.out.println("jar is " + jar);

							
						} catch (NoSuchMethodException | IOException e) {
							System.out.println("Unable to parse");
							e.printStackTrace();
						}
                    }
                 	
            	}
			}
        });
		
		JButton btnOther = new JButton("Do Something");
		btnOther.setToolTipText("Something 1");
		btnOther.setPreferredSize(dimensions(150, 30));
		btnOther.setMaximumSize(dimensions(150, 30));
		btnOther.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnOther.setMinimumSize(dimensions(150, 30));
		btnOther.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
            	

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
        
        JButton btnDialog = new JButton("Do Something"); //Create Quit button
        btnDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	
               
			}
        });
        
        JButton btnQuit = new JButton("Quit"); //Create Quit button
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	System.exit(0);
			}
        });
        bottom.add(btnDialog);
        bottom.add(btnQuit);

        frame.getContentPane().add(bottom);       
		frame.setVisible(true);
	}
	
	private Dimension dimensions(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		new AppWindow();
	}
	
	
}