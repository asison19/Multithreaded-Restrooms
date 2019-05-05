package multithreadedRestrooms;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock; 

public class Restroom implements Runnable{
	
	static int gs_time = 5000; //amount of time to sleep in restroom in ms
	static int gs_limit = 3; //amount of people total allowed in one restroom
	boolean g_gender; //global gender
	private int g_ID = 0; //to assign persons their ID
	int g_simultaneousPeople; //how many people can be in the bathroom before delaying
	int g_delay; //how long to sleep during the delay
	public boolean areAllPersonsDone = false; //check if all people are done
	private static final Semaphore m_semaphore = new Semaphore(gs_limit); //semaphore for synchronization
	static int gs_amountUsed = 0; //how many people have used the restroom
	static boolean occupancyType = false; //for whether the bathroom is for males or females
	
	public Restroom(int simultaneousPeople, int delay) {
		g_simultaneousPeople = simultaneousPeople;
		g_delay = delay;

	}
	
	public int assignID() {
		g_ID++;
		return g_ID;
	}
	
	public void run() {
	while(!areAllPersonsDone) {
				try {
					Thread.sleep(18000); // 8 seconds and it doesn't, 20 is longest seconds and it switches properly
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				switchGender();
			}
	System.out.println("Restroom now closing.");
	}

	public void start() {
		new Thread(this).start();	
	}
	
	public Semaphore getSemaphore() {
		if(g_ID % g_simultaneousPeople + 1 == g_simultaneousPeople)
			delay();
		gs_amountUsed++;
		return m_semaphore;
	}

	public void delay() {
		try {
			m_semaphore.acquire(gs_limit);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(g_delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m_semaphore.release(gs_limit);
	}

	public synchronized void switchGender() {
		System.out.println("Now Switching.");
		occupancyType = !occupancyType;	
	}
	
}
