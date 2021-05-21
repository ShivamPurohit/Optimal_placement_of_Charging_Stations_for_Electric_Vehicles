package Code;


import java.io.*;
import java.util.*;

class Midsem
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

	// Driver Code
	private static int start, destination;
	private static int drive_range;
	private static int arr[] = new int[100];
	private static int counter = 0;
	private static int dist;


	public static void main(String[] args) 
	{ 
		int[][] admx = { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, 
						{ 4, 0, 8, 0, 0, 0, 0, 11, 0 }, 
						{ 0, 8, 0, 7, 0, 4, 0, 0, 2 }, 
						{ 0, 0, 7, 0, 9, 0, 14, 0, 0 }, 
						{ 0, 0, 0, 9, 0, 10, 0, 0, 0 }, 
						{ 0, 0, 4, 0, 10, 0, 2, 0, 0 }, 
						{ 0, 0, 0, 14, 0, 2, 0, 1, 6 }, 
						{ 8, 11, 0, 0, 0, 0, 1, 0, 7 }, 
						{ 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

		System.out.println("City\t\t\t| Distance");
		System.out.println("___________________________________");
		for(int i = 0; i < 9; i++)
		{
			for(int j = i+1; j < 9; j++)
			{
				if(i != j && admx[i][j] != 0)
				{
					char a = (char)(i+65);
					char b = (char)(j+65);
					System.out.println("Node "+a+" to Node "+b+"\t| "+admx[i][j]);
				}
			}
		}
		System.out.println();

		try{
			input();
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.exit(1);
		}

		dijkstra(admx, start);

		System.out.println();
		int numberOfStations = dist/drive_range;
		System.out.println("\nNumber of required stations :"+ANSI_CYAN+numberOfStations+ANSI_RESET);

		ArrayList<Integer> prefferedNodes = new ArrayList<>();


		int tc = 0;
		for(int m = 0; m < numberOfStations; m++)
		{
			int vt = 0;
			while(vt <= drive_range)
			{
				if(admx[arr[tc]][arr[tc+1]]+vt <= drive_range)
				{
					tc++;
					vt += admx[arr[tc-1]][arr[tc]];
				}
				else
				{
					prefferedNodes.add(arr[tc]);
					break;
				}
			}
		}

		System.out.print("Charging station to be placed at : ");
		if(numberOfStations == 0)
			System.out.println(ANSI_RED+"None"+ANSI_RESET);
		else
			for(int temp : prefferedNodes)
				System.out.print(ANSI_CYAN+temp+" "+ANSI_RESET);

		System.out.println();
	}//end of main



	// The program is for adjacency matrix representation of the graph. 
	private static final int NO_PARENT = -1; 
	private static void dijkstra(int[][] matrix, int startVertex)
	{ 
		int n = matrix[0].length; //number of nodes
		int[] shortestDistances = new int[n];	//shortest distance from src
		boolean[] added = new boolean[n];	//true if vertex included in shortest path tree 

		// Initialize all distances as INFINITE and added[] as false 
		for (int vertexIndex = 0; vertexIndex < n; vertexIndex++) 
		{
			shortestDistances[vertexIndex] = Integer.MAX_VALUE; 
			added[vertexIndex] = false; 
		}
		
		shortestDistances[startVertex] = 0; 

		int[] parents = new int[n];	//Parent array to store shortest path tree
		parents[startVertex] = NO_PARENT;

		// Find shortest path for all vertices 
		for (int i = 1; i < n; i++) 
		{ 
			int nearestVertex = -1; 
			int shortestDistance = Integer.MAX_VALUE; 
			for (int vertex = 0; vertex < n; vertex++) 
			{ 
				if (!added[vertex] && shortestDistances[vertex] < shortestDistance)
				{ 
					nearestVertex = vertex;
					shortestDistance = shortestDistances[vertex];
				} 
			}

			added[nearestVertex] = true;	// Mark the picked vertex as processed 

			// Update dist value of the
			// adjacent vertices of the picked vertex.
			for (int vertex = 0; vertex < n; vertex++) 
			{ 
				int edgeDistance = matrix[nearestVertex][vertex]; 
				
				if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertex]))
				{
					parents[vertex] = nearestVertex;
					shortestDistances[vertex] = shortestDistance + edgeDistance; 
				}
			}
		}
		printSolution(startVertex, destination, shortestDistances, parents); 
	} 

	//print the start/end nodes and distance
	private static void printSolution(int startVertex, int dest, int[] distances, int[] parents) 
	{
		int n = distances.length; 
		System.out.print("\nVertex\t\tDistance\t\tPath\n"); 

		int vertexIndex = dest;
		System.out.print(startVertex + " -> "+ vertexIndex + "\t\t"); 
		System.out.print(distances[vertexIndex] + "\t\t\t");
		dist = distances[vertexIndex];

		printPath(vertexIndex, parents); 
	}

	//print shortest path from source to currentVertex using parents array 
	private static void printPath(int currentVertex, int[] parents) 
	{
		
		// Base case : Source node has been processed 
		if (currentVertex == NO_PARENT) 
		{ 
			return; 
		} 
		printPath(parents[currentVertex], parents); 
		System.out.print(currentVertex + " ");
		arr[counter++] = currentVertex;
	}

	private static void input() throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		do{
			System.out.println("Enter start and end between 0-9 ");
			System.out.print("Start : ");
			start = Integer.parseInt(br.readLine());
			System.out.print("Destination : ");
			destination = Integer.parseInt(br.readLine());

		}while(start < 0 || destination <0 || start > 9 || destination > 9);
		

		int model;
		do
		{
			System.out.print("\nChoose your car:\n1. MG ZS EV\t\t\t2. TATA Nexon EV\n3. TATA Tigor EV\t\t4. Tesla Model 3\nYour choice: ");
			model = Integer.parseInt(br.readLine());

			switch(model)
			{
				case 1	: drive_range = 20;
				break;

				case 2 : drive_range = 19;
				break;

				case 3 : drive_range = 16;
				break;

				case 4 : drive_range = 14;
				break;

				default : System.out.println("Wrong Input");
				break;
			}
		}while(model > 4 || model < 1);
	}
}