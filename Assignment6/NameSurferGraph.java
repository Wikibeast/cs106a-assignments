/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		openEntries.clear();
		update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		openEntries.add(entry);
		update();
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		int decWidth = getWidth() / NDECADES;
		addLines(decWidth);
		addDates(decWidth);
		updateEntries(decWidth);
	}
	
	private void updateEntries(int decWidth) {
		Color color;
		int num = 0;
		for (NameSurferEntry entry : openEntries) {
			switch (num % 4) {
				case 0: color = Color.BLACK; break;
				case 1: color = Color.RED; break;
				case 2: color = Color.BLUE; break;
				case 3: color = Color.MAGENTA; break;
				default: color = Color.BLACK;
			}
			num++;
			for (int i = 0; i < NDECADES - 1; i++) {
				double rankAdjust = getHeight() - 2 * GRAPH_MARGIN_SIZE;
				double x1 = i * decWidth;
				double x2 = (i + 1) * decWidth;
				double y1;
				double y2;
				String labelRank = String.valueOf(entry.getRank(i));
				if (entry.getRank(i) > 0) {
					y1 = ((entry.getRank(i) / (double)MAX_RANK) * rankAdjust) + GRAPH_MARGIN_SIZE;
				} else {
					y1 = getHeight() - GRAPH_MARGIN_SIZE;
					labelRank = "*";
				}
				
				if (entry.getRank(i + 1) > 0) {
					y2 = ((entry.getRank(i + 1) / (double)MAX_RANK) * rankAdjust) + GRAPH_MARGIN_SIZE;
				} else {
					y2 = getHeight() - GRAPH_MARGIN_SIZE;
				}
				GLine segment = new GLine(x1, y1, x2, y2);
				GLabel label = new GLabel(entry.getName() + " " + labelRank);
				segment.setColor(color);
				label.setColor(color);
				add(segment);
				add(label, x1, y1);
			}
		}
	}

	private void addDates(int decWidth) {
		for (int i = 0; i < NDECADES; i++) {
			int date = 1900 + (i*10);
			GLabel dateLabel = new GLabel(String.valueOf(date));
			add(dateLabel, i * decWidth, getHeight());
		}
	}

	private void addLines(int decWidth) {
		for (int i = 1; i < NDECADES; i++) {
			GLine line = new GLine(i * decWidth, 0, i * decWidth, getHeight());
			add(line);
		}
		GLine topLine = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		GLine bottomLine = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(topLine);
		add(bottomLine);
	}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }


	/** Instance Variables */
	private ArrayList<NameSurferEntry> openEntries = new ArrayList<NameSurferEntry>();
	
}