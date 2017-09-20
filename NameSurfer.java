/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);//sets size of application
		initializeControlBar(); // creates GUI
		graph = new NameSurferGraph();
		add(graph);
	}
	
	private void initializeControlBar() {
		add(new JLabel("Name"),SOUTH); //Label
		add(tf, SOUTH); //TextField
		tf.addActionListener(this); //Listens for actions
		add(graphButton, SOUTH); // Graph Button
		add(clearButton, SOUTH); // Clear Button
		addActionListeners();
		
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	static NameSurferEntry x;
	public void actionPerformed(ActionEvent e) {
		x = nameData.findEntry(tf.getText());// Looks for entered name in database
		if(e.getSource() == graphButton) {
			if(x == null) {
				//Stub when name doesn't exist
			}else {
				//Stub to graph name
				graph.addEntry(x);
				graph.addPoints(x);
			}
		} else if(e.getSource() == tf) {
			graph.addEntry(x);
			graph.addPoints(x);
		} else if(e.getSource() == clearButton) {
			//Stub to clear graph
			graph.clear();
		}
	}
	
	/*IVARS*/
	private JTextField tf = new JTextField(10);
	private JButton graphButton = new JButton("Graph");
	private JButton clearButton = new JButton("Clear");	
	private NameSurferDataBase nameData = new NameSurferDataBase(NAMES_DATA_FILE);
	private NameSurferGraph graph;
}
