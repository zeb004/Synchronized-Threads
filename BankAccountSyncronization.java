import java.util.Random;
import java.util.*;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

class BankAccount {
private int balance;

public int getBalance() {
return balance;
}

public BankAccount(int bal) {
super();
balance = bal;
}

synchronized boolean withdraw(int amt) {
int temp = amt;
try {
Thread.sleep(2000);
} catch(InterruptedException e) {
e.printStackTrace();
}

if(amt>balance) {
System.out.println("Low Balance");
return false;
} else {
balance = balance - temp;
System.out.println("Balance:" + balance);
return true;
}
}

synchronized void deposit(int amt) {
int temp = amt;
try {
Thread.sleep(3000);
} catch (Exception e) {
e.printStackTrace();
}
balance += amt;
System.out.println("Balance:" + balance);
}
}

class WithdrawerThread extends Thread {
BankAccount bank;
int amt;

public WithdrawerThread(BankAccount bank, int amt, int n) {
	for(int i=1;i>0;i--) {
this.bank = bank;
this.amt = amt;
System.out.println("Withdraw of"+" "+amt);
}
}

@Override
public void run() {
	//N Children
for (int i = 0; i < 1; i++) {
bank.withdraw(this.amt);
//System.out.println("Withdraw of"+" "+amt);
}
}
}

class DepositerThread extends Thread {
BankAccount bank;
int amt;

public DepositerThread(BankAccount bank, int amt) {
this.bank = bank;
this.amt = amt;
System.out.println("Deposit of"+" "+amt);
}

@Override
public void run() {
	//2 parents
for (int i = 0; i < 1; i++) {
bank.deposit(this.amt);
//System.out.println("deposit of"+" "+this.amt);
}
}
}

public class BankAccountSyncronization extends JPanel {

public static void main(String[] args) {
	int choice=1;

JFrame frame= new JFrame("Bank Account");
		//frame.setSize(600, 400);
		//frame.setVisible(true);

Scanner keyboard = new Scanner(System.in);
System.out.println("Enter Number of Children:");
int numchild=keyboard.nextInt();
System.out.println("Enter Starting Bank Value:");
int starting=keyboard.nextInt();
BankAccount bank = new BankAccount(starting);
System.out.println("Starting Balance"+" "+starting);
System.out.println("MAX Withdraw Amt:");
int childmax=keyboard.nextInt();
System.out.println("MAX Deposit Amt:");
int parentmax=keyboard.nextInt();
Random make = new Random();
Random take = new Random();


while(choice==1) {
for(int y=0;y<numchild;y++) {
	int take1=take.nextInt(childmax)+1;
WithdrawerThread wit = new WithdrawerThread(bank,take1,numchild);
wit.start();
try {
wit.join();
} catch(Exception e) {}
}
for(int r=2;r>0;r--) {
	int make1=make.nextInt(parentmax)+1;
DepositerThread dep = new DepositerThread(bank,make1);


dep.start();
try {


dep.join();
} catch(Exception e){}
}

System.out.println("Balance of Account = " + bank.getBalance() );
System.out.println("Change Parameters?? 1 for Yes, 2 for no");
int choice2=keyboard.nextInt();
if(choice2==1) {
main(args);
}
System.out.println("Continue?? 1 for Yes, 2 for no");
choice=keyboard.nextInt();
if(choice==2) {
	System.exit(0);
}

}
}
}