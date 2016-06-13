package quicksortParallel;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import quicksortParallel.Integer;

public class QuicksortTest {
	SortAlgorithm sa, sa1, sa2, sa3, sa4, sa5;
	Integer[] array2 = {new Integer(-23), new Integer(-9), new Integer(-99), new Integer(-15), new Integer(-37)};
	Integer[] array = {new Integer(17), new Integer(3), new Integer(99), new Integer(14), new Integer(9)};
	private static Integer[] array3, array4, array5;
	
	/**Generate a random number-sequence for the tests which gets saved as a file for both sorts to use*/
	@BeforeClass
	public static void setUpClass() {
		//Params are Length and sequence cap [0 - cap].
		NumberGenerator n = new NumberGenerator(10, 100);
		try {
			n.toFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Reader file = new BufferedReader(new NumberReader(new FileReader("NumberGeneratorResult.txt")));
			
			//Get the amount ints 
			int i = 0;
			
			while(file.ready()){
				file.read();
				i++;
			}
			array3 = new Integer[i];
			array4 = new Integer[i];
			
			file = new BufferedReader(new NumberReader(new FileReader("NumberGeneratorResult.txt")));
			i = 0;
			//Fill the array
			while(file.ready()) {
				int input = file.read();
				array3[i] = new Integer(input);
				array4[i] = new Integer(input);
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();} 
	}
	
	@Before
	public void setUp() throws Exception {
	}

	/**A small sequence to test QS*/
	@Test
	public void testQuicksortSequentialSimple() {
		sa = new Quicksort();
		sa.sort(array);
		String result = Quicksort.toString(array);
		assertEquals("| 3 | 9 | 14 | 17 | 99 | ", result);
	}
	
	/**A small sequence to test QS with negative numbers*/
	@Test
	public void testQuicksortSequentialNegatives() {
		sa1 = new Quicksort();
		sa1.sort(array2);
		String result = Quicksort.toString(array2);
		assertEquals("| -99 | -37 | -23 | -15 | -9 | ", result);
	}
	
	
	@Test
	public void testQuicksortParallelSimple() {
		sa = new ParallelQuicksort();
		sa.sort(array);
		String result = Quicksort.toString(array);
		assertEquals("| 3 | 9 | 14 | 17 | 99 | ", result);
	}
	
	/**A small sequence to test QS with negative numbers*/
	@Test
	public void testQuicksortParallelNegatives() {
		sa1 = new ParallelQuicksort();
		sa1.sort(array2);
		String result = Quicksort.toString(array2);
		assertEquals("| -99 | -37 | -23 | -15 | -9 | ", result);
	}
	
	
	/**Test using randomly generated file for QS-Sequential*/
	@Test
	public void testQuicksortSequential() {	
		sa3 = new Quicksort();
		sa3.sort(array3);
	}
	
	/**Test using randomly generated file for QS-Parallel*/
	@Test
	public void testQuicksortParallel() {
		sa4 = new ParallelQuicksort();
		sa4.sort(array4);
	}
	
	@Test
	public void testQuicksortSequentialExtreme() {
		sa5 = new Quicksort();
		try {
			Reader file = new BufferedReader(new NumberReader(new FileReader("randoms.txt")));
			
			//Get the amount ints 
			int i = 0;
			
			while(file.ready()){
				file.read();
				i++;
			}
			array5 = new Integer[i];
			
			file = new BufferedReader(new NumberReader(new FileReader("randoms.txt")));
			i = 0;
			//Fill the array
			while(file.ready()) {
				int input = file.read();
				array5[i] = new Integer(input);
				i++;
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();}
		
		sa5.sort(array5);
		String result = Quicksort.toString(array5);
		System.out.println(result);
	}
	
//	@Test
//	public void testQuicksortParallelExtreme() {
//		sa5 = new ParallelQuicksort();
//		try {
//			Reader file = new BufferedReader(new NumberReader(new FileReader("randoms.txt")));
//			
//			//Get the amount ints 
//			int i = 0;
//			
//			while(file.ready()){
//				file.read();
//				i++;
//			}
//			array5 = new Integer[i];
//			
//			file = new BufferedReader(new NumberReader(new FileReader("randoms.txt")));
//			i = 0;
//			//Fill the array
//			while(file.ready()) {
//				int input = file.read();
//				array5[i] = new Integer(input);
//				i++;
//			}
//			file.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {e.printStackTrace();}
//		
//		sa5.sort(array5);
//		String result = Quicksort.toString(array5);
//	}
	
}
