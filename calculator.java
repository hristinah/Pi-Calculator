package spo_pi_cal;

import java.math.*;
import java.util.*;
import java.util.concurrent.*;

public class calculator {
	
	public static void main(String[] args) 
	{
		System.out.print("Process has started\n");
		long startTime=System.nanoTime();
		BigDecimal s= new BigDecimal(0);
		BigDecimal f= new BigDecimal(0);
		int n=2; //num threads
		int r=10; //num we want
		int r1=r/n; //rotations per thread
		int w=r%n; // what threads get 1 bonus
		
		
		
	    List<Future<BigDecimal>> futuresList = new ArrayList<>();
		ExecutorService executor = Executors.newFixedThreadPool(n);
		
		for (int i=1;i<=n;i++) {
			Callable<BigDecimal> task;
			if(i<=w)
			{
			task = new thcalculator(r,i,n,r1+1);}
			else 
			{ task = new thcalculator(r,i,n,r1);}
			Future<BigDecimal> future = executor.submit(task);
			futuresList.add(future);
		}
		
		for (Future<BigDecimal> future : futuresList) {
			
			try {
				f = future.get();
			} catch (InterruptedException | ExecutionException e) {
				// ... Exception handling code ...
	        } 
			s=s.add(f);
		}
		
		executor.shutdown();
		System.out.println("The answer is "+s+"\n");
		
		long elpasedTimeSoFar = System.nanoTime()-startTime;
		System.out.print("Process has ended\n");
		System.out.printf("Total execution time for current run was %d \n",elpasedTimeSoFar);
	}

}
