/**
 * 
 */
package iFileEncryptor;

import java.io.File;
import java.io.FilterWriter;
import java.io.Writer;

/**
 * @author Richard Vladimirskij
 *
 */
abstract class CaesarFileEncryptor extends FilterWriter {
	private int key;
	private char[] alphabet = generateAlphabet();
	
	CaesarFileEncryptor(Writer out, int key) {
		super(out);
		// Ensure we're working with a positive key
		while (key < 0)
			key += 58;
		this.key = key;
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

	
	/**Checks if a file is a directory or an actual file*/
	private boolean isDirectory(File f) {
		//TODO
		return true;
	}
	
	/**Returns an array of the directory contents*/
	private String[] directoryContents(File f) {
		String[] dirContents = new String[0];
		//TODO
		return dirContents;
	}
}
