/**
 * A MaxHeap structured array of processes.
 * @author trevorsmith
 *
 */
public class MaxHeap {

	private Process[] heap; //Array of Processes
	private int heapSize; //The size of the MaxHeap
	
	/**
	 * Constructor
	 * Initializes heap array at 1000 and sets heapSize to 0.
	 */
	public MaxHeap() {
		heap = new Process[1000];
		heapSize = 0;
	}
	
	/**
	 * Sorts heap array into a MaxHeap structure
	 * @param i The index to start the MaxHeapify process.
	 */
	public void maxHeapify(int index) {
		int left = leftChildIndex(index);
		int right = rightChildIndex(index);
		int largest = index;
		if(left <= heapSize && heap[left].compareTo(heap[largest]) < 0) {
			largest = left;
		}
		if(right <= heapSize && heap[right].compareTo(heap[largest]) < 0) {
			largest = right;
		}
		if(largest != index) {
			Process temp = heap[index];
			heap[index] = heap[largest];
			heap[largest] = temp;
			maxHeapify(largest);
		}
	}
	
	/**
	 * Sorts the heap of Process objects into MaxHeap Structure using
	 * maxHeapify.
	 */
	public void buildMaxHeap() {
		for(int i = heapSize/2; i >= 1; i--) {
			maxHeapify(i);
		}
	}
	
	/**
	 * Pulls the highest priority Process object in the heap. This object will
	 * always be at index 1.
	 * @return The highest priority process
	 */
	public Process extractMax() {
		if(heapSize < 1) {
			throw new RuntimeException("Heap Underflow");
		}
		Process max = heap[1];
		heap[1] = heap[heapSize];
		heapSize--;
		maxHeapify(1);
		return max;
	}
	
	/**
	 * Inserts a Process object into the heap and sorts it
	 * into the correct index, using MaxHeap structure.
	 * @param key The Process object to be inserted.
	 */
	public void maxHeapInsert(Process key) {
		heapSize++;
		heap[heapSize] = key;
		heapIncreaseKey(heapSize, key);
	}
	
	/**
	 * A private helper method for maxHeapInsert().
	 * @param i The index of the Process object 
	 * @param key The Process object being inserted
	 */
	private void heapIncreaseKey(int i, Process key) {
		if(key.compareTo(heap[i]) > 0) {
			throw new RuntimeException("New key must be larger than current key");
		}
		while(i > 1 && heap[i/2].compareTo(heap[i]) > 0) {
			Process temp = heap[i];
			heap[i] = heap[i/2];
			heap[i/2] = temp;
			i = i/2;
		}
	}
	
	/**
	 * Returns the size of the heap.
	 * @return size of heap.
	 */
	public int getHeapSize() {
		return heapSize;
	}
	
	/**
	 * Returns true if the heap is empty, otherwise false.
	 * @return True if the heap is empty, otherwise false.
	 */
	public boolean isEmpty() {
		return heapSize == 0;
	}
	
	/**
	 * Returns heap.
	 * @return heap
	 */
	public Process[] getHeap() {
		return heap;
	}
	
	/**
	 * Returns the index of the left child of the given index.
	 * @param i The current index
	 * @return The index of the left child of the current index
	 */
	private int leftChildIndex(int i) {
		return 2*i;
	}
	
	/**
	 * Returns the index of the right child of the given index.
	 * @param i The current index
	 * @return The index of the right child of the current index
	 */
	private int rightChildIndex(int i) {
		return 2*i+1;
	}
}