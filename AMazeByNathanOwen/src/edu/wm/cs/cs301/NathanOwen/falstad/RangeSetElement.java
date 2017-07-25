package edu.wm.cs.cs301.NathanOwen.falstad;

import java.io.Serializable;

/**
 * 
 * This code is refactored code from Maze.java by Paul Falstad, www.falstad.com, Copyright (C) 1998, all rights reserved
 * Paul Falstad granted permission to modify and use code for teaching purposes.
 * Refactored by Peter Kemper* @author peterkemper
 *
 */
public class RangeSetElement implements Serializable{
	public int min, max;

	/**
	 * Constructor
	 * @param mn
	 * @param mx
	 */
	RangeSetElement(int mn, int mx) {
		min = mn;
		max = mx;
	}
}

