import java.util.Random;

/**
 * Generator to create Process objects with random values, except the time of arrival value.
 * @author trevorsmith
 *
 */
public class ProcessGenerator {

	public final double PROBABILITY; //The probability a new process is generated
	
	/**
	 * Constructor to establish probability.
	 * @param prob The probability to be established
	 */
	public ProcessGenerator(double prob) {
		PROBABILITY = prob;
	}
	
	/**
	 * Determines if a new process should be made given probability.
	 * @return True if a new process is to be made, otherwise false
	 */
	public boolean query() {
		Random rand = new Random();
		double event = rand.nextDouble();
		if(event < PROBABILITY) {
			return true;
		}
		return false;
	}
	
	/**
	 * Creates a new process with random values, except for arrival time.
	 * @param currentTime The time the process arrived at the CPU
	 * @param maxProcessTime The maximum time the process takes to process
	 * @param maxLevel The maximum priority a process can be
	 * @return
	 */
	public Process getNewProcess(int currentTime, int maxProcessTime, int maxLevel) {
		Random rand = new Random();
		int ct = currentTime;
		int mpt = rand.nextInt(maxProcessTime)+1;
		int ml = rand.nextInt(maxLevel)+1;
		return new Process(ct, mpt, ml);
	}
}