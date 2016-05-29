/**
 * 
 */
package iFileEncryptor;

import java.io.File;
import java.io.IOException;

/**
 * @author Richard Vladimirskij
 * @author Konstantin Pelevin
 *
 */
public interface IFileEncryptor {

	/**
	 * @return File-Object of a file-directory in which all files have been encrypted
	 * @param File-Object of the directory which is to be encrypted
	 * @throws InvalidDirectoryException if the File-Object submitted is not a valid directory 
	 * @throws IOException if a problem occurred during the read/write operations.
	 * */
	public File encrypt(File sourceDirectory) throws InvalidDirectoryException, IOException;
	
	/**
	 * @return File Object of a file-directory in which all files have been decrypted.
	 * @param File-Object of the directory which is to be decrypted.
	 * @throws InvalidDirectoryException if the File-Object submitted is not a valid directory.
	 * @throws IOException if a problem occurred during the read/write operations.
	 * */
	public File decrypt(File sourceDirectory) throws InvalidDirectoryException, IOException;
}
