package Code;


import java.util.Scanner;
import java.io.*;
import Code.Midsem;
import Code.Endsem;
import Code.Prob;

public class Menu
{
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	//background
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int choice = 0;

		while(choice != 4)
		{
			System.out.println(ANSI_RED+"\n\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+ANSI_RESET);
			System.out.println(ANSI_YELLOW+"       /   Optimal Placement of Charging stations for Electric Vehicles   /"+ANSI_RESET);
			System.out.println(ANSI_RED+"       ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+ANSI_RESET);
			System.out.println(ANSI_CYAN+"\t1. Module for given path\n\t2. Module for given map\\graph"+"\n\t3. Module for probabilistic path approach"+"\n\t4. Quit\n"+ANSI_RESET);
			System.out.print(ANSI_GREEN+"\t   Choose your option: "+ANSI_RESET);
			
			choice = sc.nextInt();

			switch(choice)
			{
				case 1:
					//Midsem obj1 = new Midsem();
					Midsem.main(null);
					//System.out.println("Object1 created");
					break;

				case 2:
					//Endsem obj2 = new Endsem();
					Endsem.main(null);
					break;

				case 3:
					//Prob obj3 = new Prob();
					Prob.main(null);
					break;

				case 4:
					break;

				default:
					System.out.println("That wasn't a choice!");
					break;
			}
		}
	}
}