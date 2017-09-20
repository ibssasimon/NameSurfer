/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import acm.util.RandomGenerator;

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
		entryData.clear();
		removeAll();
		addGridLines();
		addDecades();
		addMargins();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		entryData.put(entry.getName(), entry);
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
		addGridLines();
		addMargins();
		addDecades();
		
		//TODO: fix the update function when rewriting plottedlines
		addPoints(NameSurfer.x);
		
	}
	
	private void addGridLines() {
		double lineWidth = 0;
		double lineHeight = getHeight();
		double spacer = getWidth()/NDECADES;

		for(int i = 0; i < NDECADES; i++) {
			addLine(lineWidth, lineHeight);
			lineWidth += spacer;
			
		}
	}
	
	private void addLine(double cx, double cy) {
		GLine line = new GLine(cx, 0, cx, cy);
		add(line);
	}
	
	private void addMargins() {
		GLine top = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		add(top);
		
		GLine bottom = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(bottom);
	}
	
	private void addDecades() {
		double spacer = getWidth()/NDECADES;
		double width = 5;
		Integer x = 1900;
		for(int i = 0; i < NDECADES; i++) {
			String y = x.toString(); // needs to update with the change of x
			GLabel decades = new GLabel(y, width, getHeight());
			add(decades);
			x += 10; // increments decades
			width += spacer;
		}
		
	}
	
	
	public void addPoints(NameSurferEntry entry) {
		Color lineColor = rgen.nextColor();
		double start = 0;
		for(int i = 0; i < NDECADES; i++) {
			line = new GLine(start, 2*GRAPH_MARGIN_SIZE + entry.getRank(i), getWidth()/NDECADES + start, 2*GRAPH_MARGIN_SIZE +  entry.getRank(i + 1));
			line.setColor(lineColor);
			add(line);
			GLabel name = new GLabel(entry.getName());
			add(name, start, 2*GRAPH_MARGIN_SIZE + entry.getRank(i));
			start += getWidth()/NDECADES;
		}
		
	}
	
	
	
	
	
	
	/*IVARS*/
	private HashMap <String, NameSurferEntry> entryData = new HashMap <String, NameSurferEntry>();
	private RandomGenerator rgen = new RandomGenerator();
	private GLine line;
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
