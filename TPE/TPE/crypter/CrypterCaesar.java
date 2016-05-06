package crypter;

/**
 * @author Richard Vladimirskij, Konstantin Pelevin
 *
 */

public class CrypterCaesar implements Crypter {
	private int key;

	/**
	 * CrypterCaesar @param - requires an int-value to initialize the encryption/decryption key.
	 */
	
	CrypterCaesar(int key) {
		this.key = key;
	}

	public String encrypt(String message) {
		return crypter(message, true);
	}

	public String decrypt(String message) {
		return crypter(message, false);
	}

	private String crypter(String message, boolean encrypt) {
		String result = "";
		int alphaValue;
		boolean validMessage = validateString(message);

		if (validMessage) {

			// Encrypt/Decrypt the string
			for (int i = 0; i < message.length(); i++) {
				char alpha = message.charAt(i);

				// Convert any lowercase letters into uppercase;
				if (alpha >= 'a' && alpha <= 'z')
					alpha = toUppercase(alpha);

				// If the key is a negative use its first positive equivalent
				// value.
				while (key < 0)
					key += 26;

				// Determine the distance between the new letter and the first
				// position of the alphabet (A)
				if (encrypt) {
					alphaValue = (((alpha - 'A') + key) % 26);
				} else {
					alphaValue = (((alpha - 'A') - key) % 26);
					if (alphaValue < 0) {
						alphaValue += 26;
					}
				}

				// Set the new letter
				alpha = (char) ('A' + alphaValue);

				// Construct the output
				result += alpha;
			}
			return result;
		}
		return "-1";
	}

	/** ASSISTING METHODS */

	/** CHECKS THE ENTERED STRING FOR ANY ILLEGAL CHARACTERS */
	private boolean validateString(String message) {

		for (int i = 0; i < message.length(); i++) {
			char alpha = message.charAt(i);

			if (alpha < 'A' || (alpha > 'Z' && alpha < 'a') || alpha > 'z') {
				return false;
			}
		}
		return true;
	}

	/** CONVERTS A LOWERCASE CHARACTER INTO UPPERCASE */
	private char toUppercase(char alpha) {
		return (char) (alpha - ' ');
	}

}
