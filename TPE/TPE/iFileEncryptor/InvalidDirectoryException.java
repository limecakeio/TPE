package iFileEncryptor;

/**
 * @author Richard Vladimirskij
 * @author Konstantin Pelevin
 *
 */

@SuppressWarnings("serial")
/**This exception occurs when a user submits a File-Object which is not a directory*/
public class InvalidDirectoryException extends Exception {
	InvalidDirectoryException(){}
	InvalidDirectoryException(String message) {
		super(message);
	}	
}
