import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarQueue {
	private ArrayList<Integer> directions;
	private Lock accessLock;
	
	public CarQueue() {
		accessLock = new ReentrantLock();
		directions = new ArrayList<Integer>();
		directions.add(0);
		directions.add(1);
		directions.add(2);
		directions.add(3);
		directions.add(2);
		directions.add(2);
	}
	
	public void addToQueue() {
		class QueueHandler implements Runnable{
			
			@Override
			public void run() {
				Random rand = new Random();
				
				try {
					for(int i = 0; i < 41; i++) {
						directions.add(i, rand.nextInt(4));
					}
					Thread.sleep(1000);
				}
				catch(InterruptedException e) {
					System.err.println("INTERRUPTED");
					return;
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		Thread thread = new Thread(new QueueHandler());
		thread.start();
	}
	
	public int deleteQueue() {
		int n = -1;
		accessLock.lock();
		if(!directions.isEmpty()) n = directions.remove(0);
		accessLock.unlock();
		return n;
	}
}
