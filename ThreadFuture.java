package ThreadCoding;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadFuture {
public static void main(String args[]) {
	ThreadPoolExecutor executor=new ThreadPoolExecutor(2,4,10,TimeUnit.MINUTES,
			new ArrayBlockingQueue<>(2),
			new CustomThreadFactory(),
			new CustomRejectionHandler());
	
	
}
}
class CustomThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread th=new Thread(r);
		th.setPriority(Thread.NORM_PRIORITY);
		th.setDaemon(false);
		return th;
	}
	
}
class CustomRejectionHandler implements RejectedExecutionHandler{

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		// TODO Auto-generated method stub
		System.out.println("Task rejected"+r.toString());
	}
	
}