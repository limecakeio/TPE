package quickSortParallel;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.Before;
import org.junit.Test;

import quickSortParallel.Integer;

public class QuicksortTest {
	SortAlgorithm sa;
	Comparable[] array = {new Integer(23), new Integer(9), new Integer(99), new Integer(15), new Integer(37)};
	Comparable[] array2 = {new Integer(-23), new Integer(-9), new Integer(-99), new Integer(-15), new Integer(-37)};
	
	@Before
	public void setUp() throws Exception {
		sa = new Quicksort();
	}

//	@Test
//	public void testQuicksortSequentialSimple() {
//		sa.sort(array);
//		String result = Quicksort.toString(array);
//		assertEquals("| 9 | 15 | 23 | 37 | 99 | ", result);
//	}
//	
//	@Test
//	public void testQuicksortSequentialNegatives() {
//		sa.sort(array2);
//		String result = Quicksort.toString(array2);
//		assertEquals("| -99 | -37 | -23 | -15 | -9 | ", result);
//	}
	
	@Test
	public void testQuicksortSequentialExtreme() {
		Comparable[] array3 = new Comparable[0];
		
		try {
			
			Reader file = new BufferedReader(new FileReader("randoms2.txt"));
			
			//Get the amount ints 
			int i = 0;
			
			while(file.ready()){
				file.read();
				i++;
			}
			
			System.out.println("Big file has " + i + " Numbers.");
			
			
			array3 = new Comparable[i];
			
			//Reset reader
			file = new FilterReader(new BufferedReader(new FileReader("randoms2.txt"));
			i = 0;
			//Fill the array
			while(file.ready()) {
				int input = file.read();
				System.out.println(input);
				//array3[i] = new Integer(file.read() - '0');
				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {	
		} finally{}
		
		sa.sort(array3);
		String result = Quicksort.toString(array2);
	}

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
