/**
 * 
 */
package crypter;

/**
 * @author Richard Vladimirskij, Konstantin Pelevin
 *
 */
public interface Crypter {

	/**
	 * Accepts a single word as a String and returns it as encrypted.
	 * 
	 * @param Accepts
	 *            a single word as a String.
	 * @returns Returns an encrypted word as a String or "-1" if the String
	 *          contained an illegal character.
	 */
	public String encrypt(String message);

	/**
	 * Accepts a single word as a String and returns it as decrypted.
	 * 
	 * @param Accepts
	 *            a single word as a String.
	 * @returns Returns a decrypted word as a String or "-1" if the String
	 *          contained an illegal character.
	 */
	public String decrypt(String cypherText);

}
