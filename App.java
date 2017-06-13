package cs241_Assignment2;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * An application to generate outputs.
 * @author liang dong
 * CS241_Project2
 */
public class App {

	/**
	 * Prints the average numbers of swaps of 20 sets of 100 randomly integers for two methods.
	 */
	private static void randomIntegers() {
		Set<Integer> set = new HashSet<>(100);
		int n;
		Random r = new Random();
		int sum1 = 0;
		int sum2 = 0;
		for(int j = 0; j < 20; j++) {
			Heap<Integer> heap1 = new Heap<>(100);
			Integer[] integers = new Integer[100];
			set.clear();
			for(int i = 0; i < 100; i++) {
				n = r.nextInt(500) + 1;
				if(set.contains(n)) {
					i--;
				}
				else {
					set.add(n);
					heap1.add(n);
					integers[i] = n;
				}
			}
			sum1 += heap1.getSwaps();	
			Heap<Integer> heap2 = new Heap<>(integers);
			sum2 += heap2.getSwaps();
		}
		System.out.println();
		System.out.println("Average swaps for series of insertions: " + sum1 / 20);
		System.out.println("Average swaps for optimal method: " + sum2 / 20);
		System.out.println();
	}
	
	/**
	 * Prints the average numbers of swaps of inserting 1- 100 integers with using two methods.
	 */
	private static void fixedIntegers() {
		Heap<Integer> heap1 = new Heap<>();
		Integer[] integers = new Integer[100];
		for(int i = 0; i < 100; i++){
			heap1.add(i + 1);
			integers[i] = i + 1;
		}
		
		Heap<Integer> heap2 = new Heap<>(integers);
		Object[] a;
		a = heap1.toArray();
		System.out.println();
		System.out.print("Heap built using series of insertions: ");
		for(int i = 0; i < 10; i++){
			System.out.print(a[i] + ",");
		}
		System.out.println("...");
		System.out.println("Number of swaps: " + heap1.getSwaps());

		for(int i = 0; i < 10; i++){
			heap1.removeMax();
		}
		a = heap1.toArray();
		System.out.print("Heap after 10 removals: ");
		for(int i = 0; i < 10; i++){
			System.out.print(a[i]  + ",");
		}
		System.out.println("...");
		System.out.println();
		a = heap2.toArray();
		System.out.print("Heap built using optimal method: ");
		for(int i = 0; i < 10; i++){
			System.out.print(a[i]  + ",");
		}
		System.out.println("...");
		System.out.println("Number of swaps: " + heap2.getSwaps());
		for(int i = 0; i < 10; i++){
			heap2.removeMax();
		}
		a = heap2.toArray();
		System.out.print("Heap after 10 removals: ");
		for(int i = 0; i < 10; i++){
			System.out.print(a[i]  + ",");
		}
		System.out.println("...");
		System.out.println();

	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String choice;
		System.out.println("===============================================================");
		System.out.print("Please select how to test the program:"
				+ "\n(1) 20 sets of 100 randomly generated integers"
				+ "\n(2) Fixed integer values 1-100"
				+ "\nEnter choice: ");
		choice = sc.next();
		switch(choice) {
		case "1" : 
			randomIntegers();
			break;
		case "2" :
			fixedIntegers();
			break;
			
		}
		sc.close();
	}
}
