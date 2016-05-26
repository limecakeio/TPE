package ioFilter;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class CaesarReader extends FilterReader {
	private int key;
	private char[] alphabet = generateAlphabet();

	protected CaesarReader(Reader in, int key) {
		super(in);
		//Ensure we're working with a positive key
		while (key < 0) 
			key += 26;
		this.key = key;
	}
	
	public int read() throws IOException {
		int c = unCaesar(super.read());
		return c;
	}
	
	public int read(char[] cbuf, int off, int len) throws IOException {
		int i = off;
		int c = -1;
		while(ready()) {
			cbuf[i] = (char)read();
			i++;
			c++;
		}
		return c;
	}
		
	private int unCaesar (int c) {
		char ce = (char) c;
		int alphaPos = getAlphaPos(ce);
		if(alphaPos != -1)
			ce = alphabet[(alphaPos - key) % 58];
		c = (int) ce;
		return c;
	}
	
	private int getAlphaPos(char alpha) {
		for(int i = 0; i < alphabet.length; i++) {
			if(alphabet[i] == alpha)
				return i;
		}
		return -1;
	}
	
	private char[] generateAlphabet() {
		char[] alphaSet = new char[58];
		for(int i = 0; i < alphaSet.length; i++) {
			if(i <= 25)
				alphaSet[i] = (char) (i+'A');
			else if(i <= 51)
				alphaSet[i] = (char) (i+'G');
			else if (i == 52)
				alphaSet[i] = 'Ä';
			else if (i == 53)
				alphaSet[i] = 'Ö';
			else if (i == 54)
				alphaSet[i] = 'Ü';
			else if (i == 55)
				alphaSet[i] = 'ä';
			else if (i == 56)
				alphaSet[i] = 'ö';
			else if (i == 57)
				alphaSet[i] = 'ü';	
		}	
		return alphaSet;
	}
}
