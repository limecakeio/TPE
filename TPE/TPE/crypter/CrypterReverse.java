package crypter;

/**
 * @author Richard Vladimirskij, Konstantin Pelevin
 *
 */

public class CrypterReverse implements Crypter {

	public String encrypt(String message) {
		return crypt(message);
	}

	public String decrypt(String message) {
		return crypt(message);
	}

	private String crypt(String message) {
		String result = "";
		char alpha;
		boolean validString = validateString(message);

		if (validString) {
			for (int i = message.length() - 1; i >= 0; i--) {

				alpha = message.charAt(i);

				// Convert any lowercase letters into uppercase;
				if (alpha >= 'a' && alpha <= 'z')
					alpha = toUppercase(alpha);

				result += alpha;
			}
			return result;
		}
		return "-1";
	}

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
