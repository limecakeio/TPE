package bTree;

public class BTreeRichard {
	public boolean insert(String filename) {
		int value = 0;

		// check: file is present & readable
		if (isFilePresent(filename) && (isFileReadable(filename))){
			Object inputFile = openInputFile(filename);

			while (!isEndOfInputFile(inputFile)){
				value = readInt(inputFile);

				// attempt to insert value into tree
				if(insert(value) == true)
					print("Inserted: " + value + ".");
				else
					print("Failed to insert: " + value + ".");

				// check: read-away delimiter characters
				if (!isEndOfInputFile(inputFile)){
					readChar(inputFile);
				}
			}
			closeInputFile(inputFile);
			return true;
		}
		else {
			println("Error: Input file \"" + filename +  "\" is either unreadable or does not exist.");
			return false;
		}
	}
}