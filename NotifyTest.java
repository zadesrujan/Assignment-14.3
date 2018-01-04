package notifytester;

class Waiter implements Runnable{
	//Reference variable of message class
	private Display txt;
	//Constructor of Waiter class and passing the reference as argument
	public Waiter(Display m){
		txt=m;
		
	}
	//Overriding the run method as we have implemented runnable interface
	@Override
	public void run() {
		//Getting the name of the current running thread
		String name= Thread.currentThread().getName();
		// Applying the Synchronized keyword  so that only one thread can get access at a time
		synchronized (txt) {
			//the try block
			try {
				//Thread 1
				System.out.println(name+"  waiting to get notified at time"+System.currentTimeMillis());
				//putting the thread on wait
				txt.wait();
				//Catch block
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//thread 2
			System.out.println(name+ " waiter Thread is notified at time"+System.currentTimeMillis());
			//process the message now
			System.out.println(name+" proceesed "+ txt.getTxt());
		}
		
	}
}
public class NotifyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//created object of message class and passed the argument
			Display txt= new Display("done it");
				//created object of waiter class
				Waiter waiter= new Waiter(txt);
				//constructor of thread class,passing reference and calling the start method
				new Thread(waiter,"Waiter").start();;
				//created object of waiter1 class
				Waiter waiter1= new Waiter(txt);
				//constructor of thread class,passing reference and calling the start method
				new Thread(waiter1,"Waiter1").start();
				//created object of Notifier class
				Notifier notifier= new Notifier(txt);
				//constructor of thread class,passing reference and calling the start method
				new Thread(notifier,"notifier").start();
				//print line
				System.out.println("All thread started");
				
	}

}
