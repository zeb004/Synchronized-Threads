import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class ChocoMOD {


public static void main(String[] args) throws InterruptedException {
	int StartVal=150;

	ChocoBuffer bowl = new ChocoBuffer(StartVal);
	Start primary = new Start(bowl);
	ref refil = new ref(bowl);
	primary.start();
	TimeUnit.SECONDS.sleep(2);
	refil.start();


}
}

class Start extends Thread {
	ChocoBuffer buff;
	boolean end = false;
    Start(ChocoBuffer b)
{
    	buff=b;
 }
    public void run() {
Random rand = new Random();
    	while(end !=true) {
    		try {
				int  n = rand.nextInt(50) + 1;
    	      System.out.println("Withdraw"+" "+n);
              TimeUnit.SECONDS.sleep(1);;
              if(buff.getChoco()-1<0)
             	 {
            	  TimeUnit.SECONDS.sleep(1);
            	  System.out.println("Refilling...");
         }
              buff.consume();
    		} catch (InterruptedException e)
{
			 System.out.println("error 1");

    		}
    }
}
class ref extends Thread {
	ChocoBuffer buff;
	boolean end= false;
    ref(ChocoBuffer b)
    {
    	buff=b;
    }
	public void run() {

		   while(end !=true) {
			   try {
				TimeUnit.SECONDS.sleep(2);
				buff.refill();
			   }
		        catch (InterruptedException e)
{
				System.out.println("error 2");

}


		   		}

	}


	}
}

class ChocoBuffer {
	private int Choco;

public ChocoBuffer(int c) {
	this.Choco= c;
}

synchronized public void consume() {
	if(Choco>0);
	Choco--;
	while(Choco<0) {
		try {
			notifyAll();
			wait();
} catch(InterruptedException e)
{
		System.out.println("error 3");
}
	}
}
synchronized public void refill() throws InterruptedException {
	if(Choco==0) {
		Choco=15;
	}
	while(Choco==15) {
    	notifyAll();
		wait();
}
    }



public int getChoco() {
	return Choco;
}
}


