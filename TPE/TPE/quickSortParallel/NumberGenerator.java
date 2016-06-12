package quickSortParallel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Generates a random number sequence and stores it in an array of ints.
 * @author Richard Vladimirskij.
 * */

public class NumberGenerator {
private Random rnd;
private int[] numberPool;
private Integer[] integerPool;
private static final String filenamePrefix = "NumberGeneratorResult"; 

NumberGenerator(int amount) {
	this.rnd = new Random();
	this.numberPool = generatePool(amount, rnd);
	this.integerPool = generateIntegerPool(amount);
}

NumberGenerator(int amount, int cap) {
	this.rnd = new Random();
	this.numberPool = generatePool(amount, rnd, cap); 
	this.integerPool = generateIntegerPool(amount);
}

/**
 * @return an unsorted array of ints containing random numbers.
 * @param amount - the amount of numbers to generate.
 * @param rnd - the random number generator object
 * */
private int[] generatePool(int amount, Random rnd) {
	int[] pool = new int[amount];
	for(int i = 0; i < pool.length; i++) {
		pool[i] = rnd.nextInt();
	}
	return pool;
}

/**
 * @return an unsorted array of ints containing random numbers which are limited to a number between 0 and the submitted limit.
 * @param amount - the amount of numbers to generate.
 * @param rnd - the random number generator object
 * @param cap - the limit of numbers between 0 and the limit itself.
 * */
private int[] generatePool(int amount, Random rnd, int cap) {
	int[] pool = new int[amount];
	for(int i = 0; i < pool.length; i++) {
		pool[i] = rnd.nextInt(cap);
	}
	return pool;
}

/**
 * @return an deep copy of an array of integers, which contains an unsorted sequence of numbers, as an array of Integer-Objects.
 * */
private Integer[] generateIntegerPool(int amount) {
	Integer[] pool = new Integer[amount];
	for(int i = 0; i < pool.length; i++)
		pool[i] = new Integer(numberPool[i]);
	return pool;
}

/**
 * Exports a NumberGenerator's number pool to a textfile with the values separated by spaces.
 * The filename is set as: "NumberGenerator_[today's date].txt" or has a number appended to it if the file already exists.
 * @throws IOException if an error occurred during the write operation.
 * */
public void toFile() throws IOException {
	String filename = filenamePrefix + ".txt";
	File f = new File(filename);
	
	//Append an index to the filename if it exists
	int i = 1;
	while(f.exists()) {
		filename = filenamePrefix + "-(" + i + ").txt";
		f = new File(filename);
		i++;
	}
	
	PrintWriter fw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
	
	String result = "";
	for(i = 0; i < numberPool.length; i++) {
		if(i != (numberPool.length-1))
			result += numberPool[i] + " ";
		else
			result += numberPool[i] + "";
	}
	
	fw.write(result);
	fw.close();
}

public Integer[] getIntegerPool(){
	return integerPool;
}
}
