package spo_pi_cal;

import java.math.*;
import java.util.*;
import java.util.concurrent.Callable;

public class thcalculator implements Callable<BigDecimal>{
	int goal;
	int number; // what thread are we
	int addition; //num of threads
	int rotations;
	int current;
	
	public  thcalculator (int g,int n,int a,int r)
	{
		goal=g;
		number=n;
		addition=a;
		rotations=r;
		current=n;
	}
	
	public BigDecimal call() throws Exception
	{
		BigDecimal mult=BigDecimal.valueOf(1123+(current)*21460);
		BigDecimal mult1=BigDecimal.valueOf(21460);
		mult1=mult1.multiply(BigDecimal.valueOf(addition));
		int current4=1;
		BigDecimal sum=BigDecimal.valueOf(0);
		BigDecimal pro=BigDecimal.valueOf(1);
		System.out.printf("Thread %d has started \n",number);
		long startTime=System.nanoTime();
		for(int i=0;i<rotations;i++)
		{
			BigDecimal pro1=BigDecimal.valueOf(1);
			System.out.printf("Thread %d : %d  adding %f \n",number,current,pro);  	
		if(i==0)
		{	
		 for(int j=1;j<=current;j++)	
		  {
			 for(int k=0;k<4;k++)
				{
				  System.out.printf("Thread %d : %d  adding  4  %d     %f \n",number,current,current4,pro1);  
					pro1=pro1.multiply(BigDecimal.valueOf(current4));
					pro1=pro1.divide(BigDecimal.valueOf(j*4),goal, RoundingMode.HALF_UP);
					current4++;	
				}
			pro1=pro1.divide(BigDecimal.valueOf(-882*882),goal, RoundingMode.HALF_UP);
		  }
		}
		else
		{
			mult=mult.add(mult1);
			for(int d=0;d<addition;d++)	
			  {
				current++;
				for(int k=0;k<4;k++)
				{
					System.out.printf("Thread %d : %d  adding  4  %d      %f \n",number,current,current4,pro1);  
					pro1=pro1.multiply(BigDecimal.valueOf(current4));
					pro1=pro1.divide(BigDecimal.valueOf(current*4),goal, RoundingMode.HALF_UP);
					current4++;	
				}
				pro1=pro1.divide(BigDecimal.valueOf(-882*882),goal, RoundingMode.HALF_UP);
		
			  }
		}
		pro=pro.multiply(pro1);
		BigDecimal pro2=pro.multiply(mult); 
		sum=sum.add(pro2);
		
		System.out.printf("Thread %d : %d \n",number,current);
		}
		long elpasedTimeSoFar = System.nanoTime()-startTime;
		System.out.printf("Thread %d has ended with %f \n",number,sum);
		System.out.printf("Thread %d execution time was %d \n",number,elpasedTimeSoFar);
		return sum;
	}

}
