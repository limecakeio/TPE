package quickSortParallel;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

/**Especially created to test the bloody QS exercise*/
public class NumberReader extends FilterReader {

	public NumberReader(Reader in) {
		super(in);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return a numberical integer-value read directly from a file
	 * @throws IOException if an error occured during the read-process or if an illegal character was encountered
	 * */
	public int read() throws IOException {
		boolean endOfNumber = false;
		boolean neg = false;
		int readAway = 0;
		String temp = "";
		char symbol = ' ';
		int number = 0;
		int i = 0;
		
		//Grab the number as a string
		while(!endOfNumber && super.ready()){
			symbol = (char) (super.read());
			if(symbol >= '0' && symbol <= '9')
				temp += symbol;
			else if(symbol == ' ' && i > 0 || symbol == ',' && i > 0 )
				endOfNumber = true;
			else if(symbol == ' ' && i == 0)
				readAway++;
			else if(symbol == '-' && i == 0)
				neg = true;
			else
				throw new IOException("The input \"" + symbol + "\" is not a valid numerical character.");
			i++;
		}
		
		//See if we just read a space or minus at the end of the file to avoid returning a false 0
		if(temp.length() == 0 && readAway > 0 || temp.length() == 0 && neg)
			throw new IOException("The input \"" + symbol + "\" is not a valid numerical character (most likely at the end of the file).");
		
		
		//Convert the string to a number and return it
		i = 0;
		for(int k = temp.length()-1; k >= 0; k--) {
			symbol = temp.charAt(k);
			number += (symbol - '0') * (Math.pow(10, i));
			i++;
		}
		
		
		
		// Negate the number if required
		if(neg)
			number *= (-1);
		
		return number;
	}
	
	
	/**
	 * Handles the buffered reading from a file
	 * 
	 * @throws IOException
	 *             if an error occurred during the reading process
	 */
	public int read(char[] cbuf, int off, int len) throws IOException {
		int i = off;
		int c = 0;
		while(ready() && c < cbuf.length) {
			cbuf[i] = (char)read();
			i++;
			c++;
		}
		return c;
	}

	
}
