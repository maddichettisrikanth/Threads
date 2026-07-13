package ThreadCoding;

import java.util.concurrent.locks.ReentrantLock;

public class Thread1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReentrantLock lock=new ReentrantLock();

		SharedResources s=new SharedResources();
		Thread t1=new Thread(()->s.produce(lock));

		SharedResources s1=new SharedResources();
		Thread t2=new Thread(()->s1.produce(lock));
		
		t1.start();
		t2.start();
	}
}

 class SharedResources{
	boolean isAvailable=false;
	ReentrantLock lock=new ReentrantLock();
	
	void produce(ReentrantLock lock) {
		try {
			lock.lock();
			System.out.println("Lock acquired by thread "+Thread.currentThread().getName());
			isAvailable=true;
			Thread.sleep(2000);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			lock.unlock();
			System.out.println("lock released");
		}
	}
}
