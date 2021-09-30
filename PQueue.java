/**
 * A queue-type data structure of Process objects, established using a MaxHeap.
 * @author trevorsmith
 *
 */
public class PQueue {

	private MaxHeap queue; //The queue implemented using MaxHeap
	
	/**
	 * Constructor to build queue from MaxHeap.
	 */
	public PQueue() {
		queue = new MaxHeap();
	}
	
	/**
	 * Adds Process to end of queue then sorts by priority
	 * @param p The process being added
	 */
	public void enPQueue(Process p) {
		queue.maxHeapInsert(p);
	}
	
	/**
	 * Returns whether or not the queue is empty.
	 * @return True if the queue is empty, otherwise false
	 */
	public boolean isEmpty() {
		return queue.getHeapSize() == 0;
	}
	
	/**
	 * Takes Process with highest priority out of the queue.
	 * @return The process with highest priority
	 */
	public Process dePQueue() {
		return queue.extractMax();
	}
	
	/**
	 * Updates the time not processed and the priority of every Process in queue.
	 * @param timeToIncrementLevel The time slices processed before increasing the priority
	 * @param maxLevel The highest priority a process can be
	 */
	public void update(int timeToIncrementLevel, int maxLevel) {
		if(!queue.isEmpty()) {
			for(int i = 1; i <= queue.getHeapSize(); i++) {
				Process p = queue.getHeap()[i];
				p.UpdateTimeNotProcessed();
				if(p.getTimeNotProcessed() >= timeToIncrementLevel) {
					p.resetTimeNotProcessed();
					if(p.getPriority() < maxLevel) {
						p.setPriority(p.getPriority() + 1);
						queue.buildMaxHeap();
					} //end if
				} //end if
			} //end for
		} //end if
	}
}