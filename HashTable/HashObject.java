/**
 * An object to be inserted into an array using the hashing method.
 * @author Trevor Smith
 *
 * @param <T>
 */
public class HashObject<T> {

	private int duplicates = 0; 	 //keeps track of how many duplicate objects are in the array
	private int probes = 0; 		 //keeps track of how many probes it took to insert this object into the array
	private boolean deleted = false; //if this object was removed from the array
	private final T key; 			 //element being tracked by this object
	
	/**
	 * Constructor to build a HashObject
	 * @param object The object to be used as the key of the HashObject
	 */
	public HashObject(T object) {
		key = object;
	}
	
	
	/**
	 * Returns the number of probes for the object to be inserted
	 * @return The number of probes for the object to be inserted
	 */
	public int getProbes() {
		return probes;
	}
	
	/**
	 * Deletes a duplicate and set deleted to true if there are no duplicates
	 */
	public void delete() {
		if(duplicates > 0) {
			duplicates--;
		} else {
			deleted = true;
		}
	}
	
	/**
	 * Increments the number of duplicates
	 */
	public void incrementDups() {
		duplicates++;
	}
	
	/**
	 * Returns the number of duplicates in the list
	 * @return The number of duplicates in the list
	 */
	public int getDups() {
		return duplicates;
	}
	
	/**
	 * Increments the number of probes
	 */
	public void incrementProbes() {
		probes++;
	}
	
	/**
	 * Return the object's key used in the constructor
	 * @return The object's key
	 */
	public T getKey() {
		return key;
	}
	
	/**
	 * Return true if the object has been deleted from the list 
	 * @return True if the object has been deleted from the list
	 */
	public boolean isDeleted() {
		return deleted;
	}
	
	/**
	 * Returns true if the object given is equal to this object
	 * @param object A HashObject to be compared
	 * @return True if the object given and this object are equal
	 */
	public boolean equals(HashObject<?> object) {
		Class<?> class1 = key.getClass();
        Class<?> class2 = object.getKey().getClass();
        
        if ((class2 == Long.class || class2 == Integer.class) && (class1 == Integer.class || class1 == Long.class)) {
            return ((Number) key).longValue() == ((Number) object.getKey()).longValue();
        }
        if ((class1 == String.class) && (class2 == String.class)) {
            return key.toString().equals(object.getKey().toString());
        }
        return false;	
	}
	
	@Override
	public String toString() {
		return key + " " + duplicates + " " + probes;
	}
}