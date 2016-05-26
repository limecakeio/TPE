package ioFilter;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


import org.junit.Test;
import ioFilter.CaesarWriter;

public class CaesarWriterTest {
	
	/**Set-up of test objects*/
	PrintWriter cw1, cw2, cw3;
	
	@Test
	public void writeCharTest() {
		try {
			cw1 = new PrintWriter(new BufferedWriter(new CaesarWriter(new FileWriter("CaesarWrite1.txt"), 3)));
			cw1.write(65);
		} catch (IOException e) {
			System.err.println("An Error has occured while writing the file. ");
		} finally {cw1.close();}
	}
	
	@Test
	public void writeStringWithParamsTests() {
		try {
			cw2 = new PrintWriter(new BufferedWriter(new CaesarWriter(new FileWriter("CaesarWrite2.txt"), 3)));
			cw2.write("HELLO", 0, 5);
		} catch (IOException e) {
			System.err.println("An Error has occured while writing the file. ");
		} finally {cw2.close();}
			
			
	}
	
	@Test
	public void writeStringNoParamsTests() {
		try {
			cw3 = new PrintWriter(new BufferedWriter(new CaesarWriter(new FileWriter("CaesarWrite3.txt"), 3)));
			cw3.write("HELLO\n");
			cw3.write("!HELLÖ||?");
		} catch (IOException e) {
			System.err.println("An Error has occured while writing the file. ");
		} finally{cw3.close();}
	}

}
