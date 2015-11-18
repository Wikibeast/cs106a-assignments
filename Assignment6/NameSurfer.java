/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;

import java.awt.event.*;

import javax.swing.*;

//TODO temporarily extends Console Program, remember to change back to Program
public class NameSurfer extends ConsoleProgram implements NameSurferConstants {

/** Method: init()
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		initInteractors();
	    addActionListeners();
	}

	
	
	private void initInteractors() {
		add( new JLabel("Name "), SOUTH);
		textField = new JTextField(FIELD_WIDTH);
		textField.addActionListener(this);
		textField.setActionCommand("Graph");
		add(textField, SOUTH);
		add( new JButton("Graph"), SOUTH);
		add( new JButton("Clear"), SOUTH);
	}



/** Method: actionPerformed(e) 
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Graph")) {
			println("Graph: " + "\"" + textField.getText() + "\"");
			println("NSE toString: " + testEntry.toString());
			println("NSE getName: " + testEntry.getName());
			println("NSE getRank(6): " + testEntry.getRank(6));
		} else {
			println("Clear");
		}
	}
	
	//Begin Testing Code
	private NameSurferEntry testEntry = new NameSurferEntry("David 001 002 003 004 0 006 007 008 009 010 011");
	
	//End Testing Code
	
	
	/** Instance Variables */
	private JTextField textField;

	
	
}
