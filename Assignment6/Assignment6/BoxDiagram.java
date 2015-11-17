/*
 * File: BoxDiagram.java
 * Author: David Seamans
 * ------------------
 * This program creates an interactive program for creating diagrams with named boxes.
 */

import acm.graphics.*;
import acm.program.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class BoxDiagram extends GraphicsProgram {

	public void init() {
		initInterface();
		addMouseListeners();
		addActionListeners();
	}

	/** Initialize */
	private void initInterface() {
		add( new JLabel("Name "), SOUTH);
		textField = new JTextField(FIELD_WIDTH);
		add(textField, SOUTH);
		add( new JButton("Add"), SOUTH);
		add( new JButton("Remove"), SOUTH);
		add( new JButton("Clear"), SOUTH);
	}
	
	/** Reacts appropriately to the three buttons: Add, Remove, and Clear */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Clear")) {
			removeAll(); // Clears the canvas
		} else if (e.getActionCommand().equals("Add")) {
			GLabel newLabel = new GLabel(textField.getText());
			GCompound newBox = new DiaBox(newLabel);
			boxMap.put(textField.getText(), newBox);
			add(newBox, (getWidth() - BOX_WIDTH)/2, (getHeight() - BOX_HEIGHT)/2);
		} else if (e.getActionCommand().equals("Remove")) {
			if (boxMap.get(textField.getText()) != null) {
				remove(boxMap.get(textField.getText()));
			}
		};
	}

	/** Create a private-package class for the box and label objects */
	class DiaBox extends GCompound {
		public DiaBox(GLabel label) {
			add(new GRect(BOX_WIDTH, BOX_HEIGHT));
			add(label, (BOX_WIDTH - label.getWidth())/2, (BOX_HEIGHT + label.getAscent())/2);
		};
	}
	
	/** Called on mouse press to record the clicked box */
	public void mousePressed(MouseEvent e) {
		clickLoc = new GPoint(e.getPoint());
		selectedBox = getElementAt(clickLoc);
	}
	
	/** Called on mouse drag to move selected box */
	public void mouseDragged(MouseEvent e) {
		if (selectedBox != null) {
			selectedBox.move(e.getX() - clickLoc.getX(), e.getY() - clickLoc.getY());
			clickLoc = new GPoint(e.getPoint());
		};
	}
	
	
	/** Constants */
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	private static final int FIELD_WIDTH = 30;
	
	/** Instance Variables */
	private GPoint clickLoc;
	private GObject selectedBox;
	private JTextField textField;
	private Map<String, GCompound> boxMap = new HashMap<String, GCompound>();
}
