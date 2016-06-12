package quickSortParallel;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilterInputStream;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

import quickSortParallel.Integer;

public class QuicksortTest {
	SortAlgorithm sa;
	SortAlgorithm sa2;
	Comparable[] array = {new Integer(17), new Integer(3), new Integer(99), new Integer(14), new Integer(9)};
	Comparable[] array2 = {new Integer(-23), new Integer(-9), new Integer(-99), new Integer(-15), new Integer(-37)};
	
	@Before
	public void setUp() throws Exception {
		sa = new Quicksort();
		sa2 = new ParallelQuicksort();
	}

	@Test
	public void testQuicksortSequentialSimple() {
		sa.sort(array);
		String result = Quicksort.toString(array);
		assertEquals("| 3 | 9 | 14 | 17 | 99 | ", result);
	}
	
	@Test
	public void testQuicksortSequentialNegatives() {
		sa.sort(array2);
		String result = Quicksort.toString(array2);
		assertEquals("| -99 | -37 | -23 | -15 | -9 | ", result);
	}
	
	@Test
	public void testQuicksortSequential() {
		NumberGenerator n = new NumberGenerator(100, 666);
		sa.sort(n.getIntegerPool());
	}
	
	@Test
	public void testQuicksortParallel() {
		NumberGenerator n = new NumberGenerator(100, 666);
		sa2.sort(n.getIntegerPool());
	}
	
//	@Test
//	public void testQuicksortSequentialExtreme() {
//		Comparable[] array3 = new Comparable[0];
//		
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
//			array3 = new Comparable[i];
//			System.out.println("Array should have " + i + "Elements.");
//			int k = 0;
//			while(array3[k] != null)
//				k++;
//			
//			file = new BufferedReader(new NumberReader(new FileReader("randoms.txt")));
//			i = 0;
//			//Fill the array
//			while(file.ready()) {
//				int input = file.read();
//				System.out.println(input);
//				array3[i] = new Integer(input);
//				i++;
//			}
//			System.out.println("Array has " + (i) + "Elements.");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {e.printStackTrace();} 
//		finally{}
//		
//		sa.sort(array3);
//		String result = Quicksort.toString(array2);
//	}
	
	@Test
	public void testSort() {
	}

	@Test
	public void testPartition() {
	}

	@Test
	public void testToString() {
	}

}
