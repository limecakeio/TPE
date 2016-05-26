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
	Reader cr1, cr2, cr3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void readFromFileTest() {
		try {
			cr1 = new BufferedReader(new CaesarReader(new FileReader("CaesarWrite3.txt"), 3));
			String message = "";
			while(cr1.ready())
				message +=(char)cr1.read();
			assertEquals("HELLO\n!HELLÖ||", message);
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

}
