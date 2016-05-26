package ioFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public class CaesarWriter extends FilterWriter {
	private int key;
	
	public CaesarWriter(Writer out, int key) {
		super(out);
		
		//Ensure we're working with a positive key
		while (key < 0) 
			key += 26;
		
		this.key = key;
		
	}
	
	/**HANDLES THE ENCRYPTING*/
	public void write(int c) {
		
	}
	/***/
	public void write (File file){
		//TODO
	}
	public void write(char[] cbuf, int off, int len) {}
	public void write(String str, int off, int len) {}
	
	private void toCaesar(char ce) {
		int alphaValue;
		
		if(isAlpha(ce))
			alphaValue = (((ce - 'A') + key) % 26);
		try {
			super.write(ce);
		} catch (IOException e) {
			System.err.println("An error has occured while writing to file. ");
			e.printStackTrace();
		}
	}
	}
