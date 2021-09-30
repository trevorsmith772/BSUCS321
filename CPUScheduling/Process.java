/**
 * A Process that the CPU must schedule and execute.
 * @author trevorsmith
 *
 */
public class Process implements Comparable<Object>{

	private int priority; //The given priority
	private int timeRemaining; //The time remaining unitl the process is finished
	private int timeNotProcessed; //The time the process has not had any CPU time
	private final int ARRIVAL_TIME; //The time the process was made
	
	/**
	 * Constructor to create a process for the CPU.
	 * @param currentTime The time the process is made
	 * @param maxProcessTime The time required to finish the process
	 * @param maxLevel The priority level
	 */
	public Process(int currentTime, int maxProcessTime, int maxLevel) {
		priority = maxLevel;
		timeRemaining = maxProcessTime;
		ARRIVAL_TIME = currentTime;
		timeNotProcessed = 0;
	}
	
	/* Accessors */
	
	/**
	 * Returns the priority of the process.
	 * @return The priority
	 */
	public int getPriority() {
		return priority;
	}
	
	/**
	 * Returns the time not processed.
	 * @return The time not processed.
	 */
	public int getTimeNotProcessed() {
		return timeNotProcessed;
	}
	
	/**
	 * Returns the time remaining to finish the process.
	 * @return The time remaining to finish the process
	 */
	public int getTimeRemaining() {
		return timeRemaining;
	}
	
	/**
	 * Returns the time the process arrived at the CPU.
	 * @return The time of arrival
	 */
	public int getArrivalTime() {
		return ARRIVAL_TIME;
	}
	
	/* Mutators, etc */
	
	/**
	 * Resets the time not processed to 0.
	 */
	public void resetTimeNotProcessed() {
		timeNotProcessed = 0;
	}
	
	/**
	 * Increments the time not processed by 1.
	 */
	public void UpdateTimeNotProcessed() {
		timeNotProcessed++;
	}
	
	/**
	 * Sets the priority of the process.
	 * @param priority The priority to set the process to
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Sets the time remaining to finish the process.
	 * @param timeRemaining The time remaining to be set
	 */
	public void setTimeRemaining(int timeRemaining) {
		this.timeRemaining = timeRemaining;
	}
	
	/**
	 * Decriments the time remaining to process.
	 */
	public void reduceTimeRemaining() {
		timeRemaining--;
		timeNotProcessed = 0;
	}
	
	@Override
	public int compareTo(Object arg0) {
		Process p = (Process) arg0;
		if(priority > p.getPriority()) {
			return -1;
		}
		else if(priority < p.getPriority()) {
			return 1;
		}
		else if(ARRIVAL_TIME < p.getArrivalTime()) {
			return -1;
		}
		else if(ARRIVAL_TIME > p.getArrivalTime()){
			return 1;
		}
		return 0;
	}
	
	/**
	 * Returns whether the job is finished or not.
	 * @return True if the time remaining is 0, otherwise false
	 */
	public boolean finish() {
		return timeRemaining == 0;
	}
}