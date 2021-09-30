
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A driver class that tests the Cache class. It can be used using
 * a one level cache or a two level cache system
 * @author trevorsmith772
 *
 */
public class Test {

	/**
	 * Runs the tests on the Cache class. Prints out results of 
	 * first level, second level, and global hit ratio, hit number, 
	 * and reference number.
	 * 
	 * @param args Command-line arguments: [number of caches] 
	 * [size of first cache] [size of second cache] [file name]
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		double HR = 0;
		double HR1 = 0;
		double HR2 = 0;
		int NR = 0;
		int NR1 = 0;
		int NR2 = 0;
		int NH = 0;
		int NH1 = 0;
		int NH2 = 0;
		Cache<String> cache1 = null;
		Cache<String> cache2 = null;

		if(args.length == 3 && args[0].equals("1")) {
			File file = new File(args[2]);
			cache1 = new Cache<String>(Integer.parseInt(args[1]));

			try {
				Scanner lineScanner = new Scanner(file);
				while(lineScanner.hasNext()) {
					String currentLine = lineScanner.next();
					Scanner wordScanner = new Scanner(currentLine);
					while(wordScanner.hasNext()) {
						String currentWord = wordScanner.next();
						if(cache1.contains(currentWord)) {
							cache1.addAgain(currentWord);
							NH1++;
						}
						else {
							cache1.addObject(currentWord);
						}
						NR1++;
					}
				}
			} catch (FileNotFoundException e) {
				printUsage();
			}
		}
		else if(args.length == 4 && args[0].equals("2")) {
			File file = new File(args[3]);
			cache1 = new Cache<String>(Integer.parseInt(args[1]));
			cache2 = new Cache<String>(Integer.parseInt(args[2]));

			try {
				Scanner lineScanner = new Scanner(file);
				while(lineScanner.hasNextLine()) {
					String currentLine = lineScanner.nextLine();
					Scanner wordScanner = new Scanner(currentLine);
					while(wordScanner.hasNext()) {
						String currentWord = wordScanner.next();
						if(cache1.contains(currentWord)) {
							cache1.addAgain(currentWord);
							if(cache2.contains(currentWord)) {
								cache2.addAgain(currentWord);
							}
							NH1++;
						}
						else if(!cache1.isFull()) {
							cache1.addObject(currentWord);
							cache2.addObject(currentWord);
							NR2++;
						}
						else {
							if(cache2.contains(currentWord)) {
								cache2.addAgain(currentWord);
								cache1.addObject(currentWord);
								NH2++;
							}
							else {
								cache2.addObject(currentWord);
								cache1.addObject(currentWord);
							}
							NR2++;
						}
						NR1++;
					}
				}
			} catch (FileNotFoundException e) {
				printUsage();
			}
		}
		else {
			printUsage();
			System.exit(1);
		}

		//Calculate Variables
		HR1 = (NH1)/((double) NR1);
		HR2 = (NH2)/((double) NR2);
		if(args[0].equals("1")) {
			HR = (NH)/((double) NR);

			System.out.println("First level cache with " + cache1.size() + " entries has been created");
			System.out.println("..........................................................");
			System.out.println("The number of references: " + NR1);
			System.out.println("The number of cache hits: " + NH1);
			System.out.println("The cache hit ratio: " + HR1);
			System.out.println();
			System.out.println();

		} else if(args[0].equals("2")) {
			NR = NR1;
			NH = NH1 + NH2;
			HR = (NH1 + NH2)/((double) NR1);

			System.out.println("First level cache with "+ cache1.size() +" entries has been created");
			System.out.println("Second level cache with "+ cache2.size() +" entries has been created");
			System.out.println("..........................................................");
			System.out.println("The number of global references: " + NR);
			System.out.println("The number of global cache hits: " + NH);
			System.out.println("The global hit ratio: "+ HR +"\n");
			System.out.println("The number of 1st-level references: " + NR1);
			System.out.println("The number of 1st-level cache hits: " + NH1);
			System.out.println("The 1st-level cache hit ratio: "+ HR1 +"\n");
			System.out.println("The number of 2nd-level references: " + NR2);
			System.out.println("The number of 2nd-level cache hits: " + NH2);
			System.out.println("The 2nd-level cache hit ratio: "+ HR2 +"\n");	
		}
	}

	private static void printUsage() {
		System.out.println("java Test 1 <cache size> <input textfile name> or"
				+ "\njava Test 2 <1st-level cache size> <2nd-level cache size> <input textfile name>");
	}
}