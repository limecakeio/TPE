package ioFilter;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public class CaesarWriter extends FilterWriter {
	private int key;
	private char[] alphabet = generateAlphabet();

	/**
	 * @param out
	 *            - Requires a Writer Object for the encrypted message to be
	 *            written to.
	 * @param key
	 *            - Requires the encryption key as an int which dictates the
	 *            degree of encryption.
	 * @return Returns a CaesarWriter Object.
	 */
	public CaesarWriter(Writer out, int key) throws IOException {
		super(out);

		// Ensure we're working with a positive key
		while (key < 0)
			key += 26;
		this.key = key;

	}

	/**
	 * Writes a single character to a file
	 * 
	 * @throws IOException
	 *             if an error occurred during the writing process
	 */
	public void write(int c) throws IOException {
		super.write(toCaesar((char) c));
	}

	/**
	 * Handles the buffered writing to a file
	 * 
	 * @throws IOException
	 *             if an error occurred during the writing process
	 */
	public void write(char[] cbuf, int off, int len) throws IOException {
		int i = 0;
		while (i < len) {
			write(cbuf[i]);
			i++;
		}
	}

	/**
	 * Writes a string to a file and accepts a custom offset and length
	 * 
	 * @throws IOException
	 *             if an error occurred during the writing process
	 */
	public void write(String str, int off, int len) throws IOException {
		String cString = toCaesar(str);
		super.write(cString, off, len);
	}

	/**
	 * @param Requires a char for transformation into a Caesar-encoded character.
	 * @return Returns a Caesar-encoded character for all alphabetic and
	 *         German-accented characters other symbols are not encoded.
	 * @throws IOException
	 *             if an error occurred during the writing process
	 */
	private char toCaesar(char ce) {
		int alphaPos = getAlphaPos(ce);
		if (alphaPos != -1)
			ce = alphabet[(alphaPos + key) % 58];
		return ce;
	}

	/**
	 * @param Requires a String for transformation into a Caesar-encoded String.
	 * @return Returns a Caesar-encoded String in which all alphabetic and
	 *         German-accented characters are encoded. Other symbols are left
	 *         untreated.
	 * @throws IOException
	 *             if an error occurred during the writing process
	 */
	private String toCaesar(String str) {
		String trans = "";
		for (int i = 0; i < str.length(); i++) {
			trans += toCaesar(str.charAt(i));
		}
		return trans;
	}

	/**
	 * @param Requires a char to check if it is an alphabetic/German-accent character or another symbol.
	 * @return Returns the index position of the char in the alphabet map as an
	 *         int or -1 if the char is not an alphabetic or German-accented
	 *         character.
	 */
	private int getAlphaPos(char alpha) {
		for (int i = 0; i < alphabet.length; i++) {
			if (alphabet[i] == alpha)
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
		for (int i = 0; i < alphaSet.length; i++) {
			if (i <= 25)
				alphaSet[i] = (char) (i + 'A');
			else if (i <= 51)
				alphaSet[i] = (char) (i + 'G');
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
