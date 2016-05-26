package ioFilter;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ioFilter.CaesarWriter;

public class CaesarWriterTest {
	
	/**Set-up of test objects*/
	CaesarWriter cr1;
	Writer w1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		CaesarWriter cw1 = new CaesarWriter(new FileWriter("CaesarWrite.txt"), 1);
	}
	
	@Test
	public void decryptFileTest() {
		cr1.decryptFile("noexist.txt");
		fail();
	}

}
