/**
 * 
 */
package iFileEncryptor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import ioFilter.CaesarReader;
import ioFilter.CaesarWriter;

/**
 * @author Richard Vladimirskij
 * @author Konstantin Pelevin
 */
public class CaesarFileEncryptor implements IFileEncryptor {
	private int key;

	CaesarFileEncryptor(int key) {
		// Ensure we're working with a positive key
		while (key < 0)
			key += 58;
		this.key = key;
	}

	/**Creates a new directory containing encrypted copies of all the files in the source directory submitted.
	 * @param File-Object which is a file-directory.
	 * @throws InvalidDirectoryException if the sourceDirectory is not a directory
	 * @throws IOException if an error occured during the read/write process. */
	public File encrypt(File sourceDirectory) throws InvalidDirectoryException, IOException {
		File encryptedDirectory;
		if (sourceDirectory.isDirectory()) {
			//Get the first part of the name we want to set
			String orgName = sourceDirectory.getName();
			//Go up a level to find the directory in which the new one is to be created
			String newDirPath = getPreviousDirectory(sourceDirectory);
			//Create the new directory
			encryptedDirectory = setNewDirectory(newDirPath, orgName + "_encrypted");
		} else
			throw new InvalidDirectoryException("The File-Object you submitted: \"" + sourceDirectory.getName() + "\" is not a valid directory.");

		encryptContent(sourceDirectory, encryptedDirectory);

		return encryptedDirectory;
	}

	/**Creates a new directory containing decrypted copies of all the files in the source directory submitted.
	 * @param File-Object which is a file-directory.
	 * @throws InvalidDirectoryException if the sourceDirectory is not a directory
	 * @throws IOException if an error occured during the read/write process. */
	public File decrypt(File sourceDirectory) throws InvalidDirectoryException, IOException {
		File decryptedDirectory;
		if (sourceDirectory.isDirectory()) {
			//Get the first part of the name we want to set
			String orgName = sourceDirectory.getName();
			//Go up a level to find the directory in which the new one is to be created
			String newDirPath = getPreviousDirectory(sourceDirectory);
			//Create the new directory
			decryptedDirectory = setNewDirectory(newDirPath, orgName + "_decrypted");
		} else
			throw new InvalidDirectoryException(
					"The File-Object you submitted: \"" + sourceDirectory.getName() + "\" is not a valid directory.");

		decryptContent(sourceDirectory, decryptedDirectory);

		return decryptedDirectory;
	}

	/**
	 * @return - A directory's previous directory-level as a String
	 * @param -
	 *            dir accepts a File-Object
	 */
	private String getPreviousDirectory(File dir) {
		String cDir = dir.getAbsolutePath();
		String pDir = "";

		int i = cDir.length() - 1;

		//Iterate back to front and find the first instance of a "\" to get the previous level.
		while (i > 0) {
			if (cDir.charAt(i) != '\\')
				i--;
			else
				break;
		}

		//Establish the previous directory by building the String of the up to previously gained index.
		for (int j = 0; j <= i; j++)
			pDir += cDir.charAt(j);

		return pDir;
	}

	/**
	 * @return - Returns a new directory as a File-Object by attempting to set
	 *         in in a specified path with a specific name if the directory
	 *         already exits it will post-fix a count to the directory name.
	 * @param -
	 *            path accepts the full path name of the source folder where the
	 *            new directory is to be created as a String.
	 * @param -
	 *            name accepts the name of the new directory that is to be
	 *            created as a String.
	 * @throws IOException if a read/write error occured
	 */
	private File setNewDirectory(String path, String name) throws IOException {
		File newDir = new File(path);
		String[] contents = newDir.list();

		// Check if the directory already exists and add a numeric index to its name if so.
		int i = 0;
		int postFix = 1;
		String newName = name;
		while (i < contents.length) {
			if (contents[i].equals(newName)) {
				newName = name + "(" + postFix + ")";
				postFix++;
				i = 0;
			} else {
				i++;
			}
		}

		newDir = new File(path + "\\" + newName);
		newDir.mkdir();

		return newDir;
	};

	/**
	 * Creates encrypted copies of all files within a source directory as well
	 * as its sub-directories.
	 * 
	 * @param -
	 *            source accepts a directory File-Object which specifies the
	 *            source folder that contains the files which are to be
	 *            encrypted.
	 * @param -
	 *            target accepts a directory File-Object which contains all
	 *            encrypted files in the same directory-structure of the source.
	 * @throws IOException if a read/write error occured.
	 */
	private void encryptContent(File source, File target) throws IOException {
		String[] sourceContents = source.list();
		String pathToFile = source.getAbsolutePath();
		int i = 0;
		while (i < sourceContents.length) {
			File f = new File(pathToFile + "\\" + sourceContents[i]);
			String filename = f.getName();
			if (f.isFile()) {
				//Read the current file using a standard reader.
				Reader r = new BufferedReader(new FileReader(f));
				//Print the result using the custom CaesarWriter
				PrintWriter cw = new PrintWriter(
						new BufferedWriter(new CaesarWriter(new FileWriter(target + "\\" + filename), key)));
				while (r.ready())
					cw.write(r.read());
				
				r.close();
				cw.close();
			} else {
				File nextTarget = new File(target + "\\" + filename);
				nextTarget.mkdir();
				//Recursively call the next sub-folder if the current file is a directory.
				encryptContent(new File(source + "\\" + filename), nextTarget);
			}
			i++;
		}
	}

	/**
	 * Creates decrypted copies of all files within a source directory as well
	 * as its sub-directories.
	 * 
	 * @param A
	 *            directory File-Object which specifies the source folder that
	 *            contains the files which are to be decrypted.
	 * @param A
	 *            directory File-Object which contains all decrypted files in
	 *            the same directory-structure of the source.
	 * @throws IOException
	 */
	private void decryptContent(File source, File target) throws IOException {
		String[] sourceContents = source.list();
		String pathToFile = source.getAbsolutePath();
		int i = 0;
		while (i < sourceContents.length) {
			File f = new File(pathToFile + "\\" + sourceContents[i]);
			String filename = f.getName();
			if (f.isFile()) {
				//Read the current file using the custom CaesarReader.
				Reader cr = new BufferedReader(new CaesarReader(new FileReader(f), key));
				//Print the result using a standard PrintWriter.
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(target + "\\" + filename)));
				while (cr.ready())
					pw.write((char) cr.read());
				
				pw.close();
				cr.close();
			} else {
				File nextTarget = new File(target + "\\" + filename);
				nextTarget.mkdir();
				//Recursively call the next sub-folder if the current file is a directory.
				decryptContent(new File(source + "\\" + filename), nextTarget);
			}
			i++;
		}
	}
}