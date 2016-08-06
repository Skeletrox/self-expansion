/*
   Command Line Hand Cricket. I've coded this so many times that my logic changes with every version of this. 
   -------------------------------------------------------------------------------------------------
   To those unfamiliar with Hand Cricket [i.e. everyone who wasn't a middle class Indian schoolboy], the rules are as follows:
   1. A toss occurs in the beginning of the match. Usually performed using rock-paper-scissors, but here it uses a proper toss [heads or tails]. Now the winner can choose to bat or bowl first.
   2. The rules are very simple. Using hand signs, the batsmen and bowlers "score" between 1 and 10. Although normal cricket allows a maximum of 6 runs per ball, schoolboys' obsession with high scores has led to
   some unique matches where you could even score a thousand runs per ball, but we shall have the universally accepted limit of 10 runs per ball. Since we cannot use hand signs while playing on a terminal,
   a user shall enter the runs he wishes to score. The score is limited to 10, so even if you type 371 you'll end up scoring only 1. The computer chooses a random integer, using the Random.nextInt() function.
   3. If the batsman and the bowler choose the same number to score, the batsman becomes out. Else, the runs scored is added to the score. 
   4. If the person batting second surpasses the score of the person batting first, he wins!
   -------------------------------------------------------------------------------------------------
   Stuff that needs to be fixed:
   1. Handling exceptions because some users can be dicks.
   2. Use getter and setter methods to encapsulate the data within the Player class
   3. Maybe improve formatting?
   -------------------------------------------------------------------------------------------------
   Stuff that I wish to add:
   1. Upgrade this to a GUI version, preferably with C#. GUI is very easy to render in C#!
   2. Allow test-match like multiple innings.
   3. Enable single machine multiplayer, and maybe upgrade that to teams.
   4. Enable multiplayer [with teams] over a network.

*/
import java.util.*;

class Player
{
	public int score=0;
	public boolean isbatfirst=true;
}
/*
   This is the main class of the program. It contains all the member entities and member functions required to run the game.
*/

public class handcricket
{
	static Random rand;				//Because Math.random() loves to make life hell :)
	static Scanner sc;
	static Player man, comp;
	static int target=0, temp, temp2;
	static boolean toss, chc, flag;
	static void yourbat()				//This segment of code is called twice and I could not care to type it again so I made it a function.
	{
		System.out.print ("\nEnter runs to score between 1 and 10:\t");
		temp = sc.nextInt();
		temp%=10;
		if (temp==0)
			temp = 10;
		temp2 = rand.nextInt(10) + 1;
		System.out.println ("Computer bowled " + temp2);
		if (temp==(temp2))
		{
			System.out.println ("Out!\n--------------------------\n");
			flag=true;
		}
		else
		{
			man.score+=temp;
			System.out.println ("\nYour score is " + man.score);
		}
	}
	static void yourbowl()				//Same issue as above, and here, the computer bats.
	{
		System.out.print ("\nEnter runs to bowl between 1 and 10\t");
		temp = sc.nextInt();
		temp%=10;
		if (temp==0)
			temp = 10;
		temp2 = rand.nextInt(10) + 1;
		System.out.println ("Computer scored " + temp2);
		if (temp==(temp2))
		{
			System.out.println ("Out!\n---------------------------\n");
			flag=true;
		}
		else
		{
			comp.score+=temp2;
			System.out.println ("\nComputer's score is " + comp.score);
		}
	}
	public static void main (String[] args)
	{
		sc = new Scanner(System.in);
		man = new Player();
		comp = new Player();
		rand = new Random();
		System.out.println ("\nWelcome to Command Line Hand Cricket\n");
		while (true)
		{
			System.out.println ("Enter 1 to choose heads and 0 for tails");
			temp = sc.nextInt();
			if (temp==1)
			{
				chc = true;
				break;
			}
			else if (temp==0)
			{
				chc = false;
				break;
			}
			else
				System.out.println ("Enter proper choice please\n");
		}
		toss = (rand.nextInt(2)==1?true:false);
		if (chc ^ toss)						//XOR returns true if L != R and so if it's true the toss result was not your choice.
		{
			System.out.println ("You lost the toss!");
			System.out.print ("Computer has decided to... ");
			if (rand.nextInt(2)==1)
			{
				chc = true;
				comp.isbatfirst = true;
				man.isbatfirst = false;
				//Although having to assign false to a default boolean might seem redundant, I had a minor issue while testing so the assignment is just a precautionary measure.
			}
			else
			{
				chc = false;
				man.isbatfirst = true;
				comp.isbatfirst = false;
			}
			System.out.print((chc?"Bat":"Bowl") + "\n");
		}
		else
		{
			while (true)
			{
				System.out.println ("You won the toss! Press 1 to bat, 0 to bowl:");
				temp = sc.nextInt();
				if (temp==1)
				{
					System.out.println ("\nYou chose to bat!");
					man.isbatfirst = true;
					comp.isbatfirst=false;
					break;
				}
				else if (temp==0)
				{
					System.out.println ("\nYou chose to bowl!");
					comp.isbatfirst = true;
					man.isbatfirst=true;
					break;
				}
				else
					System.out.println ("\nEnter proper choice please");
			}
		}
		//The below parts handle the actual match, where you either bat first or bowl first, depending on which boolean is set, and which isn't.
		if (man.isbatfirst)
		{
			System.out.println("You are going to bat first!");
			while (!(flag))
				yourbat();
			target = man.score+1;
			System.out.println ("\n-----------------------\nTarget is " + target + "\n--------------------------\n");
			flag=false;
			while (!(flag))
			{
				yourbowl();
				if (comp.score > target)
					flag = true;
			}
			if (comp.score > target)
				System.out.println ("You lose!");
			else if (comp.score == target)
				System.out.println ("Tie match!");
			else
				System.out.println ("You win!");
		}
		else
		{
			System.out.println ("Computer is going to bat first!");
			while (!(flag))
				yourbowl();
			target = comp.score+1;
			System.out.println ("\n-----------------------\nTarget is " + target + "\n--------------------------\n");
			flag=false;
			while(!(flag))
			{
				yourbat();
				if (man.score > target)
					flag = true;
			}
			if (man.score > target)
				System.out.println ("You win!");
			else if (man.score == target)
				System.out.println ("Tie match!");
			else
				System.out.println ("You lose!");
		}
		System.out.println ("-----------------------------------\nThank you for taking the time to play Command Line Hand Cricket!");
	}
}
