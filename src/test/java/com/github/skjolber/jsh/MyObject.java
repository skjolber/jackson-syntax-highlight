package com.github.skjolber.jsh;

/**
 * Test object for object serialization via jackson databind.
 *
 */

public class MyObject {

	private int number;
	private String string;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	
	
}
