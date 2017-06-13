package cs241_Assignment2;

/**
 * A Max Heap
 * @author liang dong
 * CS241_Project2
 */
public class Heap <T extends Comparable<? super T>> 
								implements MaxHeapInterface<T> {
	private T[] heap;
	private int lastIndex;
	private static final int DEFAULT_CAPACITY = 100;
	private static final int MAX_CAPACITY = 10001;
	private boolean initialized = false;
	private int swaps;
	
	/**
	 * Creats a max heap with a given capacity.
	 * @param capacity A given capacity
	 */
	public Heap(int capacity) {		
		if(capacity > MAX_CAPACITY) {
			capacity = MAX_CAPACITY;
		}
		@SuppressWarnings("unchecked")
		T[] temp = (T[])new Comparable[capacity + 1];
		heap = temp;
		lastIndex = 0;
		initialized = true;
		swaps = 0;
	}
	
	/**
	 * Creats a max heap with 100 space.
	 */
	public Heap() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Creats a max heap with an array of values.
	 */
	public Heap(T[] entries) {
		this(entries.length);
		for(int i = 0; i < entries.length; i++) {
			heap[i + 1] = entries[i];
			lastIndex++;
		}
		swaps = 0;
		int root = lastIndex / 2;
		for(int i = root; i > 0; i--) {
			reheap(i);
		}
	}
	
	
	private boolean isInitialized() {
		return initialized;
	}
	
	public int getSwaps() {
		return swaps;
	}
	
	/**
	 * Double the capacity, which must be not greater than the max caacity.
	 * @throws HeapIsFullException If the heap has already reached the max caacity.
	 */
	private void doubleSize() {
		int newCapacity;
		if(heap.length >= MAX_CAPACITY) {
			throw new HeapIsFullException();
		}
		if(heap.length * 2 - 1 > MAX_CAPACITY) {
			newCapacity = MAX_CAPACITY;
		}
		else {
			newCapacity = heap.length * 2 - 1;
		}
		@SuppressWarnings("unchecked")
		T[] temp = (T[])new Comparable[newCapacity];
		for(int i = 1; i < heap.length; i++) {
			temp[i] = heap[i];
		}
		heap = temp;
	}
	
	/**
	 * Adds a new entry into heap.
	 * @throws InitializationException The heap is not well initialized
	 */
	public void add(T newEntry) {
		if(isInitialized()) {
			if(lastIndex == heap.length - 1) {
				doubleSize();
			}
			lastIndex++;
			heap[lastIndex] = newEntry;
			int child = lastIndex;
			int parent = lastIndex / 2;
			boolean done = false;
			while(!done && parent > 0) {
				if(heap[child].compareTo(heap[parent]) > 0) {
					T temp = heap[child];
					heap[child] = heap[parent];
					heap[parent] = temp;
					swaps++;
					child = parent;
					parent = child / 2;
				}
				else {
					done = true;
				}
			}
		}
		else {
			throw new InitializationException();
		}
	}


	/**
	 * Maintains the Max heap property of a subtree with root parent.
	 * @param parent A root of a subtree.
	 */
	private void reheap(int parent) {
		int largestIndex = parent * 2;
		while(largestIndex <= lastIndex) {
			if(largestIndex + 1 <= lastIndex
					&& heap[largestIndex].compareTo(heap[largestIndex + 1]) < 0) {
				largestIndex++;
			}		
			
			if(heap[parent].compareTo(heap[largestIndex]) < 0) {
				swaps++;
				T temp = heap[parent];
				heap[parent] = heap[largestIndex];
				heap[largestIndex] = temp;
				parent = largestIndex;
				largestIndex = parent * 2; // assume the child has the largest value
			}
			else {
				break;
			}
		}
	}
	
	/**
	 * Removes and return the largest value.
	 * @return The largest value
	 */
	public T removeMax() {
		if(isInitialized()) {
			T temp = heap[1];
			heap[1] = heap[lastIndex];
			heap[lastIndex] = null;
			lastIndex--;
			reheap(1);
			return temp;
		}
		else {
			return null;
		}
	}

	public boolean isEmpty() {
		return lastIndex < 1;
	}

	@Override
	public int getSize() {
		return lastIndex;
	}

	@Override
	public void clear() {
		if(isInitialized()) {
			while(!isEmpty()) {
				removeMax();
			}
		}
	}
	
	/**
	 * Returns the heap as an array.
	 * @return The heap
	 */
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] newArray = (T[]) new Comparable[lastIndex];
		for(int i = 0; i < newArray.length; i++) {
			newArray[i] = heap[i + 1];
		}
		return newArray;
	}
	
	public T getMax() {
		return heap[1];
	}

}
