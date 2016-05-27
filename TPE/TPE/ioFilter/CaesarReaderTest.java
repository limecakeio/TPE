package ioFilter;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CaesarReaderTest {
	Reader cr1, cr2, cr3, cr4, cr5;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	/**Read a line of non-alphabetical chars without decoding them back*/
	public void readNonAlphaChars() {
		try {
			cr1 = new BufferedReader(new CaesarReader(new FileReader("CaesarWrite1.txt"), 3));
			String message = "";
			
			while(cr1.ready())
				message += (char)cr1.read();
			
			assertEquals(" @[`{ÃÅÕ×ÛÝãåõ÷ûýÿ", message);
		} 
		catch (FileNotFoundException e) {
			System.err.println("File cannot be located.");
		}
		catch (IOException e) {
			System.err.println("An error occured while trying to read the file");
		} 
		finally{
			try {
				cr1.close();
			} catch (IOException e) {
				System.err.println("An error occured while trying to close the file");
			}}
	}
	
	@Test
	/**Read a line of Caesar-encoded chars and decode them back to normal*/
	public void readAlphaCharTest() {
		try {
			cr2 = new BufferedReader(new CaesarReader(new FileReader("CaesarWrite2.txt"), 3));
			String message = "";
			
			while(cr2.ready())
				message += (char)cr2.read();
			
			assertEquals("AZazÄÜäü", message);
		} 
		catch (FileNotFoundException e) {
			System.err.println("File cannot be located.");
		}
		catch (IOException e) {
			System.err.println("An error occured while trying to read the file");
		} 
		finally{
			try {
				cr2.close();
			} catch (IOException e) {
				System.err.println("An error occured while trying to close the file");
			}}
	}
	
	@Test
	/**Read a line of Caesar-encoded chars and decode them back to normal with a negative key*/
	public void readWithNegativeKeyTest() {
		try {
			cr3 = new BufferedReader(new CaesarReader(new FileReader("CaesarWrite3.txt"), -3));
			String message = "";
			
			while(cr3.ready())
				message += (char)cr3.read();
			
			assertEquals("HELLO", message);
		} 
		catch (FileNotFoundException e) {
			System.err.println("File cannot be located.");
		}
		catch (IOException e) {
			System.err.println("An error occured while trying to read the file");
		} 
		finally{
			try {
				cr3.close();
			} catch (IOException e) {
				System.err.println("An error occured while trying to close the file");
			}}
	}
	
	@Test
	/**Read a line of Caesar-encoded chars and non-encoded symbols and decode them back to normal*/
	public void readLettersSymbolsMixedTest() {
		try {
			cr4 = new BufferedReader(new CaesarReader(new FileReader("CaesarWrite4.txt"), 5));
			String message = "";
			
			while(cr4.ready())
				message += (char)cr4.read();
			
			assertEquals("@HeLlo\n!hELLö|? ", message);
		} 
		catch (FileNotFoundException e) {
			System.err.println("File cannot be located.");
		}
		catch (IOException e) {
			System.err.println("An error occured while trying to read the file");
		} 
		finally{
			try {
				cr4.close();
			} catch (IOException e) {
				System.err.println("An error occured while trying to close the file");
			}}
	}
	
	@Test
	/**Read a line of Caesar-encoded chars and non-encoded symbols and decode them back to normal*/
	public void readTrivialTest() {
		try {
			cr5 = new BufferedReader(new CaesarReader(new FileReader("CaesarWrite5.txt"), 3));
			String message = "";
			
			while(cr5.ready())
				message += (char)cr5.read();
			
			assertEquals("Schnittstellen sind eine wunderschöne Sache! Oder nicht?", message);
		} 
		catch (FileNotFoundException e) {
			System.err.println("File cannot be located.");
		}
		catch (IOException e) {
			System.err.println("An error occured while trying to read the file");
		} 
		finally{
			try {
				cr5.close();
			} catch (IOException e) {
				System.err.println("An error occured while trying to close the file");
			}}
	}
}
