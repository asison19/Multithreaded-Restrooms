Program where multiple opposite gendered people/threads have to share one resource/bathroom.

This program has 3 java files being MultithreadedRestrooms, Person, and
Restroom. MultithreadedRestrooms is where main is located and is the driver that
creaters and starts/runs the Person and Restroom threads. 20 Persons and 1
Restroom. I used a semaphore of 3 permits for the Persons to synchronize with each
other. When each person thread arrives, they check if they have gone to the bathroom
already and if they haven’t they check if they can go inside the bathroom. If the
bathroom is for the opposite gender then they keep waiting until it switches. If it is for
their gender they try to acquire the lock, because only 3 people can be in the bathroom.
Once they are in the bathroom they sleep the specified 5 seconds. They then depart the
restroom and release 1 permit of the semaphore.

I put three options for the 3 delay options. The Bathroom thread waits for all
threads to leave the bathroom by waiting for all of the semaphore permits to be in their
possession before it starts the delay. Once the delay is done, it releases the permits in
its possession so the rest of the Person threads can do what they need to.

The Restroom thread sleeps for about 18 seconds before switching the gender
for the restroom. There is only 1 restroom so it has to switch the gender allowed every
18 seconds. The gender checking of the restroom uses synchronized keyword monitor
to ensure the right gender goes in.