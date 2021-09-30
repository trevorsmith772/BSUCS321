import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Tests the HashTable and HashObject classes
 * @author Trevor Smith
 *
 */
public class HashTest {

	/**
	 * The main program
	 * @param args String array of arguments
	 */
	public static void main(String[] args) {
		
		int inputType = 0;
		double loadFactor = 0.0;
		int debugLevel = 2;
		
		//check length
		if(args.length > 3 || args.length < 2) {
			printUsage();
			System.exit(-1);
		}
		
		//check input type
		String input = args[0];
		if(input.equals("1")) {
			inputType = 1;
		} else if(input.equals("2")) {
			inputType = 2;
		} else if(input.equals("3")) {
			inputType = 3;
		} else {
			printUsage();
			System.exit(-1);
		}
		
		//check load factor
		try {
			loadFactor = Double.parseDouble(args[1]);
		} catch (Exception e) {
			printUsage();
		}
		
		//check debug level
		if(args.length == 3 && args[2].equals("0")) {
			debugLevel = 0;
		} else if(args.length == 3 && args[2].equals("1")) {
			debugLevel = 1;
		} else if(args.length == 3) {
			printUsage();
		}
		
		//get primes
		PrimeGenerator gen = new PrimeGenerator(95500, 96000);
		int highP = gen.getHighPrime();
		
		//start
		double alpha = 0; 
		int tableSize = highP;
		HashTable linearTable = new HashTable(tableSize);
		HashTable doubleTable = new HashTable(tableSize);

		HashObject<?> currObjectLin = null;
		HashObject<?> currObjectDub = null;
		Random rand = null;
		File file = null;
		Scanner scanner = null;
		
		if(inputType == 1) {
			rand = new Random();
		} else if(inputType == 3) {
			file = new File("word-list");
			try {
				scanner = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		while(alpha < loadFactor) {
			
			if(inputType == 1) {
				int ranNum = rand.nextInt();
				currObjectLin = new HashObject<Integer>(ranNum);
				currObjectDub = new HashObject<Integer>(ranNum);

			} else if(inputType == 2) {
				Long currTime = System.currentTimeMillis();
				currObjectLin = new HashObject<Long>(currTime);
				currObjectDub = new HashObject<Long>(currTime);

			} else {
				if(scanner.hasNextLine()) {
					String currLine = scanner.nextLine();
					currObjectLin = new HashObject<String>(currLine);
					currObjectDub = new HashObject<String>(currLine);

				}
			}
			
			int primaryHashValue = getHashValue(currObjectLin, tableSize);
			
			linearTable.insertLinear(primaryHashValue, currObjectLin);
			doubleTable.insertDouble(primaryHashValue, currObjectDub);
			
			alpha = linearTable.getTotalNonDups() / ((double)highP);
		}
		
		//calc summary
		System.out.println("A good table size is found: " + tableSize);
		System.out.println("Data source type: " + whatData(inputType) + "\n\n");
		
		
		System.out.println("Using Linear Hashing....");
		long sumDupsLin = linearTable.getTotalDups();
		long totalElementsLin = (linearTable.getTotalNonDups()+sumDupsLin);
		double avgProbesLin = linearTable.getAvgProbesLin();

		System.out.println("Input " + totalElementsLin + " elements, of which " + sumDupsLin + " duplicates");
		System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + avgProbesLin);
		
		
		System.out.println("\n\nUsing Double Hashing....");
		long sumDupsDub = doubleTable.getTotalDups();
		long totalElementsDub = (doubleTable.getTotalNonDups()+sumDupsDub);
		double avgProbesDub = doubleTable.getAvgProbesDub();

		System.out.println("Input " + totalElementsDub + " elements, of which " + sumDupsDub + " duplicates");
		System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + avgProbesDub);

		
		//dump files
		if(debugLevel == 1) {
			try {
				linearTable.createDumpLin();
				doubleTable.createDumpDub();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	} //end main
	
	/**
	 * Prints the usage of the class if the wrong args are given
	 */
	private static void printUsage() {
		System.out.println("Usage: java HashTest <input type> <load factor> [<debug level>]");
	}

	/**
	 * Returns the hash value of the given object
	 * @param currObject The object from which the hash value is obtained
	 * @param tableSize The size of the table in which it will be entered
	 * @return The hash value
	 */
	private static <T> int getHashValue(HashObject<?> currObject, int tableSize) {
		@SuppressWarnings("unchecked")
		T key = (T) currObject.getKey();
		int code = key.hashCode();
		int primaryHashValue = code % tableSize;
		if(primaryHashValue < 0) {
			primaryHashValue += tableSize;
		}
		return primaryHashValue;
	}
	
	/**
	 * Returns a String of the data being used
	 * @param inputType
	 * @return The data being used
	 */
	private static String whatData(int inputType) {
		if(inputType == 1) {
			return "integer";

		} else if(inputType == 2) {
			return "System.currentTimeMillis";

		} else {
			return "word-list";

		}
		
	}

}