import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * A class with two arrays, one to be used 
 * with linear hashing and one to be used 
 * with double hashing
 * @author Trevor Smith
 *
 */
public class HashTable {

	private int tableSize;
	private HashObject<?>[] doubleHTable;
	private HashObject<?>[] linearHTable;
	private long totalNonDups = 0; 
	private long totalDups = 0;
	
	/**
	 * Constructor to build arrays and establish variables
	 * @param highP The higher of two twin primes to be used as the table size
	 */
	public HashTable(int highP) {
		tableSize = highP;
		
		doubleHTable = new HashObject[tableSize];
		linearHTable = new HashObject[tableSize];
	}
	
	/**
	 * Inserts an object into the linear hashing array using linear hashing
	 * @param primaryHashValue The primary hash value to be used
	 * @param obj The object to be inserted
	 */
	public void insertLinear(int primaryHashValue, HashObject<?> obj) {
		int i = 0;
		int j = primaryHashValue;
		while(i < tableSize) {
			if(linearHTable[j] != null && linearHTable[j].equals(obj)) {
				linearHTable[j].incrementDups();
				totalDups++;
				return;
			} else if(linearHTable[j] == null || linearHTable[j].isDeleted() == true) {
				linearHTable[j] = obj;
				obj.incrementProbes();
				totalNonDups++;
				return;
			}
			obj.incrementProbes();
			i++;
			
			j++;
			if(j >= tableSize) {
				j = j - tableSize;
			}
		}
		System.out.println("Error: Table is full.");
	}
	
	/**
	 * Inserts an object into the double hashing array using double hashing
	 * @param keyVal The primary hash value of the object to be inserted
	 * @param obj The object to be inserted into the array
	 */
	public void insertDouble(int keyVal, HashObject<?> obj) {
		int i = 0;
		int j = keyVal;
		int h2key = 1 + ( (obj.getKey().hashCode()) % (tableSize-2) );
		if(h2key < 0) {
			h2key += (tableSize-2);
		}
		
		while(i < tableSize) {
			if(doubleHTable[j] != null && doubleHTable[j].equals(obj)) {
				doubleHTable[j].incrementDups();
				totalDups++;
				return;
			} else if(doubleHTable[j] == null || doubleHTable[j].isDeleted() == true) {
				doubleHTable[j] = obj;
				obj.incrementProbes();
				totalNonDups++;
				return;
			}
			obj.incrementProbes();
			i++;
			
			j = ( keyVal + i * h2key ) % tableSize;
			
		}
		System.out.println("Error: Table is full.");
	}
	
	/**
	 * Returns the amount of non-duplicate elements in the array
	 * @return The amount of non-duplicate elements in the array
	 */
	public long getTotalNonDups() {
		return totalNonDups;
	}
	
	/**
	 * Returns the amount of duplicate elements in the array
	 * @return The amount of duplicate elements in the array
	 */
	public long getTotalDups() {
		return totalDups;
	}
	
	/**
	 * Adds up all the probes of every non-duplicate element in the linear array
	 * @return The sum of all the probes of every non-duplicate element in the linear array
	 */
	public long addProbesLin() {
		long sum = 0;
		for(int i = 0; i < tableSize; i++) {
			if(linearHTable[i] != null) {
				sum += linearHTable[i].getProbes();
			}
		}
		return sum;
	}
	
	/**
	 * Adds up all the probes of every non-duplicate element in the double array
	 * @return The sum of all the probes of every non-duplicate element in the double array
	 */
	public long addProbesDub() {
		long sum = 0;
		for(int i = 0; i < tableSize; i++) {
			if(doubleHTable[i] != null) {
				sum += doubleHTable[i].getProbes();
			}
		}
		return sum;
	}
	
	/**
	 * Returns the average amount of probes per non-duplicate element in the linear array
	 * @return The average amount of probes per non-duplicate element in the linear array
	 */
	public double getAvgProbesLin() {
		return ( addProbesLin() / (double) (totalNonDups) );
	}
	
	/**
	 * Returns the average amount of probes per non-duplicate element in the double array
	 * @return The average amount of probes per non-duplicate element in the double array
	 */
	public double getAvgProbesDub() {
		return ( addProbesDub() / (double) (totalNonDups) );
	}
	
	/**
	 * Creates a file called linear-dump with the linear hash array printed
	 * @throws IOException
	 */
	public void createDumpLin() throws IOException {

		File file = null;
		Writer writer = null;

		file = new File("linear-dump");
		if (!file.exists()) {
			file.createNewFile();
		}
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			for (int i = 0; i < tableSize; i++) {
				if (linearHTable[i] != null) {
					writer.write("table[" + i + "]: " + linearHTable[i].toString() + "\n");
				}
			}
		} catch (IOException ex) {

		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
			}
		}

	}
	
	/**
	 * Creates a file called double-dump with the double hash array printed
	 * @throws IOException
	 */
	public void createDumpDub() throws IOException {
		File file = null;
		Writer writer = null;

		file = new File("double-dump");
		if (!file.exists()) {
			file.createNewFile();
		}
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			for (int i = 0; i < tableSize; i++) {
				if (doubleHTable[i] != null) {
					writer.write("table[" + i + "]: " + doubleHTable[i].toString() + "\n");
				}
			}
		} catch (IOException ex) {

		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
			}
		}	
	}
}