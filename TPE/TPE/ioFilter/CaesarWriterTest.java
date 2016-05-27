package ioFilter;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import org.junit.Test;
import ioFilter.CaesarWriter;

public class CaesarWriterTest {
	
	/**Set-up of test objects*/
	PrintWriter cw1, cw2, cw3, cw4, cw5;
	
	/**Set-up Reader to test files*/
	Reader input;
	
	@Test
	/**
	 * Testing that the Caesar Writer ignores non-alphabetical/German-accented characters */
	public void writeNonAlphaCharTest() {
		try {
			cw1 = new PrintWriter(new BufferedWriter(new CaesarWriter(new FileWriter("CaesarWrite1.txt"), 3)));
			cw1.write(' ');
			cw1.write('@');
			cw1.write('[');
			cw1.write('`');
			cw1.write('{');
			cw1.write('Ã');
			cw1.write('Å');
			cw1.write('Õ');
			cw1.write('×');
			cw1.write('Û');
			cw1.write('Ý');
			cw1.write('ã');
			cw1.write('å');
			cw1.write('õ');
			cw1.write('÷');
			cw1.write('û');
			cw1.write('ý');
			cw1.write('ÿ');
		} catch (IOException e) {
			System.err.println("An Error has occured while writing the file.");
		} finally {cw1.close();}
		
		try{
			input = new BufferedReader(new FileReader("CaesarWrite1.txt"));
			String result = "";
			while(input.ready()) {
				result += (char) input.read();
			}
			assertEquals(" @[`{ÃÅÕ×ÛÝãåõ÷ûýÿ", result);
		}
		catch(FileNotFoundException e){
			System.err.println("The file could not be located.");	
		}
		catch(IOException e) {
			System.err.println("An error occured while trying to read the file. ");
		}
		finally{
			try {
			input.close();
			} catch (IOException e) {
			System.err.println("An error occured while trying to close the file.");
			e.printStackTrace();
		}}
	}
	
	@Test
	/**
	 * Testing that the Caesar Writer converts characters with a key of 3*/
	public void writeAlphaCharTest() {
		try {
			cw2 = new PrintWriter(new BufferedWriter(new CaesarWriter(new FileWriter("CaesarWrite2.txt"), 3)));
			cw2.write('A');
			cw2.write('Z');
			cw2.write('a');
			cw2.write('z');
			cw2.write('Ä');
			cw2.write('Ü');
			cw2.write('ä');
			cw2.write('ü');
			
		} catch (IOException e) {
			System.err.println("An Error has occured while writing the file.");
		} finally {cw2.close();}
		
		try{
			input = new BufferedReader(new FileReader("CaesarWrite2.txt"));
			String result = "";
			
			while(input.ready()) {
				result += (char) input.read();
			}
			
			assertEquals("DcdÜäüAC", result);
		}
		catch(FileNotFoundException e){
			System.err.println("The file could not be located.");	
		}
		catch(IOException e) {
			System.err.println("An error occured while trying to read the file. ");
		}
		finally{
			try {
			input.close();
			} catch (IOException e) {
			System.err.println("An error occured while trying to close the file.");
			e.printStackTrace();
		}}
	}
	
	
	
	@Test
	/**Testing a string with parameters and a negative key*/
	public void writeStringWithParamsTests() {
		try {
			cw3 = new PrintWriter(new BufferedWriter(new CaesarWriter(new FileWriter("CaesarWrite3.txt"), -3)));
			cw3.write("HELLO", 0, 5);
			
		} catch (IOException e) {
			System.err.println("An Error has occured while writing the file. ");
		} finally {cw3.close();}
		
		try{
			input = new BufferedReader(new FileReader("CaesarWrite3.txt"));
			String result = "";
			
			while(input.ready()) {
				result += (char) input.read();
			}
			
			assertEquals("EBIIL", result);
		}
		catch(FileNotFoundException e){
			System.err.println("The file could not be located.");	
		}
		catch(IOException e) {
			System.err.println("An error occured while trying to read the file. ");
		}
		finally{
			try {
			input.close();
			} catch (IOException e) {
			System.err.println("An error occured while trying to close the file.");
			e.printStackTrace();
		}}
			
			
	}
	
	@Test
	/**Testing a string with no parameters with a positive key and letters as well as symbols mixed*/
	public void writeStringNoParamsTests() {
		try {
			cw4 = new PrintWriter(new BufferedWriter(new CaesarWriter(new FileWriter("CaesarWrite4.txt"), 5)));
			cw4.write("@HeLlo\n!hELLö|? ");
		} catch (IOException e) {
			System.err.println("An Error has occured while writing the file. ");
		} finally{cw4.close();}
		
		try{
			input = new BufferedReader(new FileReader("CaesarWrite4.txt"));
			String result = "";
			
			while(input.ready()) {
				result += (char) input.read();
			}
			
			assertEquals("@MjQqt\n!mJQQD|? ", result);
		}
		catch(FileNotFoundException e){
			System.err.println("The file could not be located.");	
		}
		catch(IOException e) {
			System.err.println("An error occured while trying to read the file. ");
		}
		finally{
			try {
			input.close();
			} catch (IOException e) {
			System.err.println("An error occured while trying to close the file.");
			e.printStackTrace();
		}}
	}
	
	@Test
	/**Final trivial test*/
	public void writeTestTrivial() {
		try {
			cw5 = new PrintWriter(new BufferedWriter(new CaesarWriter(new FileWriter("CaesarWrite5.txt"), 3)));
			cw5.write("Schnittstellen sind eine wunderschöne Sache! Oder nicht?");
		} catch (IOException e) {
			System.err.println("An Error has occured while writing the file. ");
		} finally{cw5.close();}
		
		try{
			input = new BufferedReader(new FileReader("CaesarWrite5.txt"));
			String result = "";
			
			while(input.ready()) {
				result += (char) input.read();
			}
			
			assertEquals("Vfkqlwwvwhoohq vlqg hlqh zxqghuvfkBqh Vdfkh! Rghu qlfkw?", result);
		}
		catch(FileNotFoundException e){
			System.err.println("The file could not be located.");	
		}
		catch(IOException e) {
			System.err.println("An error occured while trying to read the file. ");
		}
		finally{
			try {
			input.close();
			} catch (IOException e) {
			System.err.println("An error occured while trying to close the file.");
			e.printStackTrace();
		}}
		
	}
}
