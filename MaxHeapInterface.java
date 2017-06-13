package cs241_Assignment2;

/**
 * An interface for a max heap.
 * @author liang dong
 * CS241_Project2
 */
public interface MaxHeapInterface<T extends Comparable<? super T>> {	
	public void add(T newEntry);
	public T removeMax();
	public boolean isEmpty();
	public int getSize();
	public void clear();
}	
