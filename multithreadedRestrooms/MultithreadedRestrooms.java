/*
 * MultithreadedRestrooms.java
 * driver of threads
 */


package multithreadedRestrooms;

import java.util.Random;
import java.util.Scanner;

public class MultithreadedRestrooms {
	
	static int gs_totalNumberOfPeople = 20; //total number of persons/threads
	static int gs_time = 5000; //time it takes for one person to use the restroom
	
	static Thread[] persons = new Thread[gs_totalNumberOfPeople];
	
	public static void main(String[] args) {
		Restroom restroom;
		
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter 1, 2, 3 for each corresponding choice\n" + 
			"(1) 5 : DELAY(10) : 5 : DELAY(10) : 5 : DELAY(10) : 5\r\n" + 
			"(2) 10 : DELAY(10) : 10\r\n" + 
			"(3) 20");
		switch(reader.nextInt()) {
		case 1:
			restroom = new Restroom(5, 10000);
			initializeAndRunPersons(restroom);
			break;
		case 2:
			restroom = new Restroom(10, 10000);
			initializeAndRunPersons(restroom);
			break;
		case 3:
			restroom = new Restroom(20, 0);
			initializeAndRunPersons(restroom);
			break;
		default:
			System.out.println("Improper output, now terminating.");
		}
		
	}
	private static void initializeAndRunPersons(Restroom restroom) {
		restroom.start();
		for(int i = 0; i < gs_totalNumberOfPeople; i++) {Person person = new Person(assignGender(), gs_time, restroom);
			persons[i] = new Thread(person);
			persons[i].start();
		}

		//join threads to see if all run to completion
		for(Thread p: persons) {
			try {
				p.join();	
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		System.out.println("All Persons Have Used the Restroom.");
		restroom.areAllPersonsDone = true;
	}
	
	private static boolean assignGender() {
		Random rand = new Random();
		
		if(rand.nextInt(100) <= 60) { //0 - 59 female
			return true;
		}
		
		// 60 - 99 male
		return false;
	}
}
/* Example Outputs
Enter 1, 2, 3 for each corresponding choice
(1) 5 : DELAY(10) : 5 : DELAY(10) : 5 : DELAY(10) : 5
(2) 10 : DELAY(10) : 10
(3) 20
1
ID: 3 now using male restroom.
ID: 5 now using male restroom.
ID: 8 now using male restroom.
Departing, ID: 1
Departing, ID: 2
Departing, ID: 3
ID: 9 now using male restroom.
ID: 17 now using male restroom.
ID: 15 now using male restroom.
Now Switching.
Departing, ID: 4
ID: 18 now using male restroom.
Departing, ID: 5
Departing, ID: 6
Departing, ID: 7
ID: 19 now using male restroom.
ID: 20 now using male restroom.
ID: 10 now using female restroom.
Now Switching.
Departing, ID: 8
ID: 7 now using female restroom.
Departing, ID: 9
ID: 11 now using female restroom.
Departing, ID: 10
ID: 13 now using female restroom.
Departing, ID: 11
Departing, ID: 12
ID: 14 now using female restroom.
ID: 16 now using female restroom.
Departing, ID: 13
ID: 1 now using female restroom.
Departing, ID: 14
Departing, ID: 14
ID: 4 now using female restroom.
ID: 12 now using female restroom.
Departing, ID: 15
ID: 2 now using female restroom.
Now Switching.
Departing, ID: 16
Departing, ID: 17
ID: 6 now using female restroom.
Departing, ID: 18
Departing, ID: 19
All Persons Have Used the Restroom.
Now Switching.
Restroom now closing.
/////////////////////////
Enter 1, 2, 3 for each corresponding choice
(1) 5 : DELAY(10) : 5 : DELAY(10) : 5 : DELAY(10) : 5
(2) 10 : DELAY(10) : 10
(3) 20
2
ID: 3 now using male restroom.
ID: 6 now using male restroom.
ID: 13 now using male restroom.
Departing, ID: 1
ID: 15 now using male restroom.
Departing, ID: 2
ID: 17 now using male restroom.
Departing, ID: 3
Departing, ID: 4
Departing, ID: 5
Now Switching.
ID: 1 now using female restroom.
ID: 4 now using female restroom.
ID: 20 now using female restroom.
Departing, ID: 6
Departing, ID: 7
ID: 2 now using female restroom.
ID: 8 now using female restroom.
Departing, ID: 8
ID: 10 now using female restroom.
Departing, ID: 9
Departing, ID: 10
ID: 12 now using female restroom.
ID: 7 now using female restroom.
Departing, ID: 11
ID: 5 now using female restroom.
Departing, ID: 12
ID: 9 now using female restroom.
Departing, ID: 12
ID: 11 now using female restroom.
Departing, ID: 13
ID: 14 now using female restroom.
Now Switching.
Departing, ID: 14
Departing, ID: 15
ID: 16 now using female restroom.
ID: 18 now using female restroom.
Departing, ID: 16
ID: 19 now using female restroom.
Departing, ID: 18
Departing, ID: 18
Departing, ID: 19
All Persons Have Used the Restroom.
Now Switching.
Restroom now closing.
////////////////////////////////
Enter 1, 2, 3 for each corresponding choice
(1) 5 : DELAY(10) : 5 : DELAY(10) : 5 : DELAY(10) : 5
(2) 10 : DELAY(10) : 10
(3) 20
3
ID: 3 now using male restroom.
ID: 4 now using male restroom.
ID: 6 now using male restroom.
Departing, ID: 1
ID: 12 now using male restroom.
Departing, ID: 2
Departing, ID: 3
ID: 15 now using male restroom.
ID: 16 now using male restroom.
Departing, ID: 4
ID: 17 now using male restroom.
Departing, ID: 5
Departing, ID: 6
Departing, ID: 7
Now Switching.
ID: 19 now using female restroom.
ID: 18 now using female restroom.
ID: 2 now using female restroom.
Departing, ID: 8
Departing, ID: 9
ID: 9 now using female restroom.
ID: 7 now using female restroom.
Departing, ID: 10
ID: 14 now using female restroom.
Departing, ID: 11
Departing, ID: 12
Departing, ID: 13
ID: 20 now using female restroom.
ID: 1 now using female restroom.
ID: 5 now using female restroom.
Departing, ID: 15
Departing, ID: 16
Departing, ID: 15
ID: 13 now using female restroom.
ID: 8 now using female restroom.
ID: 11 now using female restroom.
Now Switching.
Departing, ID: 17
Departing, ID: 18
Departing, ID: 19
ID: 10 now using female restroom.
Departing, ID: 20
All Persons Have Used the Restroom.
Now Switching.
Restroom now closing.
  
 */
