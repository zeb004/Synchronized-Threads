public class SynchBankTest
		{
		  public static void main(String[] args)
		  {
		   Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
		   int i;
		   for (i = 0; i < NACCOUNTS; i++)
		   {
		     TransferThread t = new TransferThread(b, i,
		      INITIAL_BALANCE);
		     t.setPriority(Thread.NORM_PRIORITY + i % 2);
		     t.start();
		   }
		  }

		  public static final int NACCOUNTS = 10;
		  public static final int INITIAL_BALANCE = 10000;
		}

		/**
		  A bank with a number of bank accounts.
		*/
		class Bank
		{
		  /**
		   Constructs the bank.
		   @param n the number of accounts
		   @param initialBalance the initial balance
		   for each account
		  */
		  public Bank(int n, int initialBalance)
		  {
		   accounts = new int[n];
		   int i;
		   for (i = 0; i < accounts.length; i++)
		     accounts[i] = initialBalance;
		   ntransacts = 0;
		  }

		  /**
		   Transfers money from one account to another.
		   @param from the account to transfer from
		   @param to the account to transfer to
		   @param amount the amount to transfer
		  */
		  public synchronized void transfer(int from, int to, int amount)
		   throws InterruptedException
		  {
		   while (accounts[from] < amount)
		     wait();
		   accounts[from] -= amount;
		   accounts[to] += amount;
		   ntransacts++;
		   notifyAll();
		   if (ntransacts % NTEST == 0) test();
		  }

		  /**
		   Prints a test message to check the integrity
		   of this bank object.	  */
		  public synchronized void test()
		  {
		   int sum = 0;

		   for (int i = 0; i < accounts.length; i++)
		     sum += accounts[i];

		   System.out.println("Transactions:" + ntransacts
		     + " Sum: " + sum);
		  }

		  /**
		   Gets the number of accounts in the bank.
		   @return the number of accounts
		  */
		  public int size()
		  {
		   return accounts.length;
		  }

		  public static final int NTEST = 10000;
		  private final int[] accounts;
		  private long ntransacts = 0;
		}

		/**
		  A thread that transfers money from an account to other
		  accounts in a bank.
		*/
	class TransferThread extends Thread
		{
		  /**
		   Constructs a transfer thread.
		   @param b the bank between whose account money is transferred
		   @param from the account to transfer money from
		   @param max the maximum amount of money in each transfer
		  */
		  public TransferThread(Bank b, int from, int max)
		  {
		   bank = b;
		   fromAccount = from;
		   maxAmount = max;
		  }

		  public void run()
		  {
		   try
		   {
		     while (!interrupted())
		     {
		      int toAccount = (int)(bank.size() * Math.random());
		      int amount = (int)(maxAmount * Math.random());
		      bank.transfer(fromAccount, toAccount, amount);
		      sleep(1);
		     }
		   }
		   catch(InterruptedException e) {}
	  }

		  private Bank bank;
		  private int fromAccount;
		  private int maxAmount;
}