/**
 * 
 */
package iFileEncryptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilterWriter;
import java.io.Writer;

/**
 * @author Richard Vladimirskij
 *
 */
public class CaesarFileEncryptor implements IFileEncryptor  {
	private int key;
	
	CaesarFileEncryptor(int key) {
		// Ensure we're working with a positive key
		while (key < 0)
			key += 58;
		this.key = key;
	}
	
	
	public File encrypt(File sourceDirectory) throws InvalidDirectoryException {
		File dir = sourceDirectory;
		if(dir.isDirectory()) {
			setupStructure(dir);
		} else {
			throw new InvalidDirectoryException("The File-Object you submitted is not a valid directory.");	
		}
		
		return sourceDirectory;
	}
	public File decrypt(File sourceDirectory) {
		return sourceDirectory;
	}
	
	//Copies the directory structure into a sub-structure
	private void setupStructure(File dir) {
		//Go up a level
		String path = dir.getAbsolutePath();
		String newPath = "";
		int i = path.length()-1;
		
		while(i > 0) {
			if(path.charAt(i) != '\\')
				i--;
			else
				break;
		}
		
		for(int j = 0; j <= i; j++)
			newPath += path.charAt(j);
		
		File newSource = new File(newPath + dir.getName() + "_temp\\");
		System.out.println("New file is: " + newSource.getName() + " and it's located at " + newSource.getAbsolutePath());
		
	}
	
}
