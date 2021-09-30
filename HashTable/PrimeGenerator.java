import java.util.Random;

/**
 * Finds the first two twin primes found in a given range
 * @author Trevor Smith
 *
 */
public class PrimeGenerator {

	private int lowPrime = 0; //the lower of the twin primes
	private int highPrime = 0; //the higher of the twin primes
	
	/**
	 * Constructor to find primes
	 * @param min Lower range bound
	 * @param max Upper range bound
	 */
	public PrimeGenerator(int min, int max) {
		createPrimes(min, max);
	}
	
	/**
	 * Finds the twin primes
	 * @param min Lower range bound
	 * @param max Upper range bound
	 */
	private void createPrimes(int min, int max) {
		boolean lowSet = false;
		int i = min;
	
		while(lowSet == false && (min < max)) {
			if(isPrime(i) && isPrime(i+2)) {
				lowPrime = i;
				highPrime = i+2;
				lowSet = true;
			}
			i++;
		}
	}
	
	/**
	 * Returns true if the argument is prime
	 * @param testn The number to be tested
	 * @return True if the argument is prime 
	 */
	private boolean isPrime(int testn) {
		boolean retVal = false;
		Random rand = new Random();
		int base = rand.nextInt(testn-2) + 2;
		
		if(aModP(base, testn-1) == 1) {
			base = rand.nextInt(testn-2) + 2;
			if(aModP(base, testn-1) == 1) {
				retVal = true;
			}
		}
	
		return retVal;
	}
	
	/**
	 * Returns base^power mod power+1
	 */
	private long aModP(int base, int power) {
		long rtn = base;
		//Convert to binary
		int[] powerBinary = toBinary(power);
		for(int i = 1; i < powerBinary.length; i++) {
			if(powerBinary[i] == 1) {
				rtn = (rtn*rtn) % (power+1);
				rtn = (rtn*base) % (power+1);
			}
			else {
				rtn = (rtn*rtn) % (power+1);
			}
		}
		
		return rtn;
	}
	
	/**
	 * Converts a given int to binary
	 */
	private int[] toBinary(int n) {
		String rtn = "";
		while(n > 0) {
			if(n%2 == 1) {
				rtn = "1" + rtn;
			} else {
				rtn = "0" + rtn;
			}
			n = n/2;
		}
		
		int[] result = new int[rtn.length()];
		for(int i = 0; i < result.length; i++) {
			if(rtn.charAt(i) == '1') {
				result[i] = 1;
			} else {
				result[i] = 0;
			}
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		String rtn = lowPrime + " " + highPrime;
		return rtn;
	}
	
	/**
	 * Returns the lower twin prime
	 */
	public int getLowPrime() {
		return lowPrime;
	}
	
	/**
	 * Returns the higher twin prime
	 */
	public int getHighPrime() {
		return highPrime;
	}
}