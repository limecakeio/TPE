package crypter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Richard Vladimirskij, Konstantin Pelevin
 *
 */

public class CrypterTest {

	Crypter c;
	Crypter r;

	@Before
	public void setUp() throws Exception {
		c = new CrypterCaesar(3);
		r = new CrypterReverse();
	}

	@Test
	public void testCeasarLetterInputs() {

		/** TESTING LETTER INPUTS */

		/** Check that a lowercase letter gets converted into uppercase */
		c = new CrypterCaesar(0);
		assertEquals("A", c.encrypt("a"));

		/** Check that an illegal input in upper-lower boundary is caught */
		assertEquals("-1", c.encrypt("@"));

		/** Check that an illegal input in lower-middle boundary is caught */
		assertEquals("-1", c.encrypt("["));

		/** Check that an illegal input in higher-middle boundary is caught */
		assertEquals("-1", c.encrypt("`"));

		/** Check that an illegal input in lower-higher boundary is caught */
		assertEquals("-1", c.encrypt("{"));
	}

	@Test
	public void testCeasarKeyInputs() {

		/** Check Caesar with a negative key */
		c = new CrypterCaesar(-2601);
		assertEquals("Z", c.encrypt("A"));

		/** Check Caesar with a neutral value (0) */
		c = new CrypterCaesar(0);
		assertEquals("C", c.encrypt("C"));

		/** Check Caesar with a middle range value (between 0 and 26) */
		c = new CrypterCaesar(13);
		assertEquals("N", c.encrypt("A"));

		/** Check Caesar with an upper range value (26) */
		c = new CrypterCaesar(26);
		assertEquals("T", c.encrypt("T"));

		/** Check Caesar a positive value outside modulo 26 (27) */
		c = new CrypterCaesar(27);
		assertEquals("B", c.encrypt("A"));

	}

	@Test
	public void testEncryptCaesar() {
		assertEquals("FDHVDU", c.encrypt("caesar"));
	}

	@Test
	public void testDecryptCaesar() {
		assertEquals("CAESAR", c.decrypt("FDHVDU"));
	}

	@Test
	public void testEncryptReverse() {
		assertEquals("RASEAC", r.encrypt("CAESAR"));
	}

	@Test
	public void testDecryptReverse() {
		assertEquals("CAESAR", r.decrypt("RASEAC"));
	}

	@Test
	public void testSecretMessage() {
		c = new CrypterCaesar(5);
		r = new CrypterReverse();
		String message = "XHMSNYYXYJQQJS";
		assertEquals("SCHNITTSTELLEN", r.decrypt(c.decrypt(r.decrypt(message))));
	}

}
