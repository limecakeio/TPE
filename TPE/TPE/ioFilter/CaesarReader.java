package ioFilter;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class CaesarReader extends FilterReader {
	private int key;
	private char[] alphabet = generateAlphabet();

	/**
	 * @param in
	 *            - Requires a Reader Object for the encrypted message to be
	 *            read from.
	 * @param key
	 *            - Requires the encryption key as an int which dictates the
	 *            degree of decryption.
	 * @return Returns a CaesarReader Object.
	 */
	protected CaesarReader(Reader in, int key) {
		super(in);
		//Ensure we're working with a positive key
		while (key < 0) 
			key += 58;
		this.key = key;
	}
	
	/**
	 * Reads a single character from a file
	 * 
	 * @throws IOException
	 *             if an error occurred during the reading process
	 */
	public int read() throws IOException {
		int c = unCaesar(super.read());
		return c;
	}
	
	
	/**
	 * Handles the buffered reading from a file
	 * 
	 * @throws IOException
	 *             if an error occurred during the reading process
	 */
	public int read(char[] cbuf, int off, int len) throws IOException {
		int i = off;
		int c = 0;
		while(ready()) {
			cbuf[i] = (char)read();
			i++;
			c++;
		}
		return c;
	}
	
	
	/**
	 * @param Requires an int for transformation from a Caesar-encoded int to a decoded one.
	 * @return Returns a Caesar-decoded character as an int for all alphabetic and
	 *         German-accented characters other symbols are not decoded and remain untouched.
	 */	
	private int unCaesar (int c) {
		char ce = (char) c;
		int alphaPos = getAlphaPos(ce);
		
		//Ensure the subtraction of the key doesn't get us into the negative.
		int target = (alphaPos - key) % 58;
		if(target < 0)
			target += 58;
		
		if(alphaPos != -1)
			ce = alphabet[(target) % 58];
		c = (int) ce;
		return c;
	}
	
	/**
	 * @param Requires a char to check if it is an alphabetic/German-accent character or another symbol.
	 * @return Returns the index position of the char in the alphabet map as an
	 *         int or -1 if the char is not an alphabetic or German-accented
	 *         character.
	 */
	private int getAlphaPos(char alpha) {
		for(int i = 0; i < alphabet.length; i++) {
			if(alphabet[i] == alpha)
				return i;
		}
		return -1;
	}
	
	
	/**
	 * @return Returns an array of chars containing all alphabetical characters
	 *         as well as German-accented characters arranged in upper- and
	 *         lowercase (A,..,Z,a,..z,Ö,...,Ä,ö,...,ä).
	 */
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