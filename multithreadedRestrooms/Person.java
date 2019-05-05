/*
 * Person.java
 * one person that tries to use the restroom
 */

package multithreadedRestrooms;

public class Person implements Runnable{
	
	static int ms_departureIndex = 0; //assigned when people leave
	
	int m_id; //id, changes each time they arrive in the restroom
	public boolean m_gender; // false for male, true for female
	int m_time; // amount of time each person spends in the restroom
	Restroom m_restroom; //shared data that handles the synchronization
	
	Person(){
		
	}
	
	Person(boolean gender, int time, Restroom restroom){
		//m_id = id;
		m_gender = gender;
		m_time = time;
		m_restroom = restroom;
	}
	
	@Override
	public void run() {		
		//m_id = m_restroom.assignID();
		try {
			arrive(m_id, m_gender);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * acquire lock and take when free then use facilities
	 */
	public void arrive(int id, boolean gender) throws InterruptedException {
		id = m_restroom.assignID();
		boolean isDone = false;
		while (!isDone) {
			if(checkRestroom()) {
				m_restroom.getSemaphore().acquire();
				useFacilities(id, gender);
				isDone = true;
			}
		}
	}
	
	/*
	 * check the current gender allowed in the restroom
	 */
	private synchronized boolean checkRestroom() {
		return Restroom.occupancyType == m_gender;
	}
	
	/*
	 * Use the facilities for m_time ms
	 */
	public void useFacilities(int id, boolean gender) {
		if(!gender) {
			System.out.println("ID: " + id + " now using male restroom.");
		}
		else {
			System.out.println("ID: " + id + " now using female restroom.");
		}
		try {
			Thread.sleep(m_time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		depart(id);
	}
	
	/*
	 * leave facilities and release semaphore
	 */
	public synchronized void depart(int id) {
		ms_departureIndex++;
		System.out.println("Departing, ID: " + ms_departureIndex);
		m_restroom.getSemaphore().release();
	}
	
	public void start() {
		new Thread(this).start();
	}
}
