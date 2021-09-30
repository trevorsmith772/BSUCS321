
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * A class that creates a Cache used for temporary quick memory access
 * 
 * @author trevorsmith772
 *
 * @param <T>
 */
public class Cache<T> {
	
	private LinkedList<T> cache;
	private final int SIZE;

	/**
	 * Constructs a cache using a linked list
	 * @param size The maximum size of the cache. 
	 */
	public Cache(int size) {
		cache = new LinkedList<T>();
		SIZE = size;		
	}
	
	/**
	 * Another add method used to do add portion of moving object
	 * from end of cache to beginning
	 * @param object The object that is moved to start of
	 * cache
	 */
	public void addAgain(T object) {
		getObject(object);
	}
	
	/**
	 * Removes an object from cache. Throws a 
	 * NoSuchElementException if the cache is empty. 
	 * @param object The object to be removed
	 */
	public void removeObject(T object) {
		if(cache.isEmpty()) {
			throw new NoSuchElementException();
		}
		cache.remove(object);
	}
	
	/**
	 * Adds an object to the beginning of the cache. If cache
	 * is full, the last object in the cache is removed before the 
	 * new object is added.
	 * @param object The object to be added
	 */
	public void addObject(T object) {
		if(cache.size() >= SIZE) { //Checks if cache is full
			cache.removeLast();
		}
		cache.addFirst(object);
	}
	
	/**
	 * Finds the target object in cache, removes it from
	 * its current location, and then adds it to the front
	 * of the cache
	 * @param object The object to be retrieved 
	 * @return The object that was retrieved
	 */
	public T getObject(T object) {
		T retObj = cache.get(cache.indexOf(object));
		cache.remove(object);
		cache.addFirst(object);
		return retObj;
	}
	
	/**
	 * 'Clears cache' by creating a completely new one
	 */
	public void clearCache() {
		cache = new LinkedList<T>();
	}
	
	/**
	 * Determines if an object is in the cache.
	 * @param object The target object
	 * @return True if the object is in the cache, false if not
	 */
	public boolean contains(T object) {
		if(cache.isEmpty()) {
			return false;
		}
		if(cache.contains(object)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the current size of the cache, not the maximum size.
	 * @return The current cache size
	 */
	public int size() {
		return cache.size();
	}
	
	/**
	 * Determines if the cache is full.
	 * @return True if the cache is full, false otherwise
	 */
	public boolean isFull() {
		if(cache.size() >= SIZE) {
			return true;
		}
		return false;
	}
}