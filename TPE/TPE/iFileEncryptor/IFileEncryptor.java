/**
 * 
 */
package iFileEncryptor;

import java.io.File;

/**
 * @author Richard Vladimirskij
 * @author Konstantin Pelevin
 *
 */
public interface IFileEncryptor {

	/**
	 * @param - File Object which reflects a directory
	 * @return - File Object of an file-directory in which all files have been encrypted
	 * @throws InvalidDirectoryException if the File-Object submitted is not a valid directory
	 * */
	public File encrypt(File sourceDirectory) throws InvalidDirectoryException;
	
	/**
	 * @param - Requires a File Object which reflects a directory
	 * @return - Returns a File Object of a file-directory in which all files have been decrypted
	 * */
	public File decrypt(File sourceDirectory);
}
