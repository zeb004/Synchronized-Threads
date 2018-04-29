import java.util.*;
import java.io.*;
import java.lang.*;

class ChocoBuffer{
	private int Choco;

public ChocoBuffer(int c)
{
	this.Choco= c;
}

synchronized public void eat(){
	if(Choco>0);
	Choco--;
	while(Choco==0){
		try{notifyAll();wait();} catch(InterruptedException e){
}
	}
}
synchronized public void refill(){
	if(Choco==0)
	{
		Choco=15;
	}
	while(Choco==15){
    	try{{notifyAll();}wait();}catch(InterruptedException e){
}
    }

}

public int getChoco(){
	return Choco;
}
}
class Staff extends Thread {
	ChocoBuffer buff;
	boolean stop = false;
    Staff(ChocoBuffer b)
    {
    	buff=b;
    }
    public void run() {
    	
    	while(stop !=true) {
    		try{
    	      System.out.println("NOM NOM");            
              Thread.sleep(1000);
              if(buff.getChoco()-1==0)
              {
            	  Thread.sleep(250);
            	  System.out.println("Refilling...");
              }
              buff.eat();
    		}catch (InterruptedException e) {}
    		}
    }
} 
class TA extends Thread{
	ChocoBuffer buff;
	boolean stop= false;
    TA(ChocoBuffer b)
    {
    	buff=b;
    }
	public void run(){
		   
		   while(stop !=true){
			   try{
				Thread.sleep(1000);
				buff.refill();
			   }
		        catch (InterruptedException e) {}
		       
		   		}
		
	}
	
	
	}
public class Choco{
	

public static void main(String[] args)
{
	int cand=15;
	CandyBuffer candys = new CandyBuffer(cand);
	Staff glut = new Staff(candys);
	TA slave = new TA(candys);
	try{
		glut.start();
		Thread.sleep(cand*2000);
		slave.start();
	}catch (InterruptedException e) {

}
	

}
}
