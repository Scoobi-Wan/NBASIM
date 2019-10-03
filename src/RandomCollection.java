import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomCollection<E> {
	private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
	private Random random;
	private double total = 0.0;
	
	public RandomCollection() {
		random = new Random();
	}
	
	public RandomCollection(Random rand1) {
		random = rand1;
	}
	
	public RandomCollection<E> add(double weight, E result) {
		if (weight <= 0) return this;
		total += weight;
		map.put(total, result);
		return this;
	}
	
	public E next() {
		double val = random.nextDouble() * total;
		return map.higherEntry(val).getValue();
		
	}
}
