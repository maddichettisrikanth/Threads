package ThreadCoding;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample {

	public static void main(String args[]) {
		
		ForkJoinPool pool =ForkJoinPool.commonPool();
		Future<Integer> futureObj=pool.submit(new ComputeSumTask(0, 100));
		try {
			System.out.println(futureObj.get());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

class ComputeSumTask extends RecursiveTask<Integer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int start;
	int end;
	ComputeSumTask(int start,int end){
		this.start=start;
		this.end=end;
	}
	@Override
	protected Integer compute() {
		// TODO Auto-generated method stub
		if(end-start<=4) {
			int totalsum=0;
			for(int i=start;i<=end;i+=1) {
				totalsum+=i;
			}
			return totalsum;
		}
		else {
			//split the task
			int mid=(start+end)/2;
			ComputeSumTask leftTask=new ComputeSumTask(start, mid);
			ComputeSumTask rightTask=new ComputeSumTask(mid+1,end);
			//fork the subtask for parallel execution
			leftTask.fork();
			rightTask.fork();
			//combine the result of subTasks
			int leftResult=leftTask.join();
			int rightResult=rightTask.join();
			//combine result
			return leftResult+rightResult;
		}
	}
}