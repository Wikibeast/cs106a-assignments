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
public class NameSurfer extends Program implements NameSurferConstants {

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
			String name = textField.getText();

		} else {
			println("Clear");
		}
	}
	
	//Begin Testing Code
	private NameSurferDataBase nsDatabase = new NameSurferDataBase(NAMES_DATA_FILE);
	
	//End Testing Code
	
	
	/** Instance Variables */
	private JTextField textField;

	
	
}
