package Code;


import java.io.*;
import java.util.*;

class Endsem
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

	private static int drive_range;
	private static int counter = 0;
	private static int dist;
	private static int flag = 1;
	private static final int NO_PARENT = -1; 


	// Driver Code 
 	public static void main(String[] args)
	{

		int[][] adm = { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, 
						{ 4, 0, 8, 0, 0, 0, 0, 11, 0 }, 
						{ 0, 8, 0, 7, 0, 4, 0, 0, 2 }, 
						{ 0, 0, 7, 0, 9, 0, 14, 0, 0 },
						{ 0, 0, 0, 9, 0, 10, 0, 0, 0 }, 
						{ 0, 0, 4, 0, 10, 0, 2, 0, 0 }, 
						{ 0, 0, 0, 14, 0, 2, 0, 1, 6 }, 
						{ 8, 11, 0, 0, 0, 0, 1, 0, 7 }, 
						{ 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

		System.out.println("Node\t\t\t| Distance");
		System.out.println("___________________________________");
		for(int i = 0; i < 9; i++)
		{
			for(int j = i+1; j < 9; j++)
			{
				if(i != j && adm[i][j] != 0)
				{
					char a = (char)(i+65);
					char b = (char)(j+65);
					System.out.println("Node "+a+" to Node "+b+"\t| "+adm[i][j]);
				}
			}
		}
		System.out.println();


		System.out.print("Vertex\t\tDistance\t\tPath");
		
		for(int i = 0; i < 9; i++)
		{
			dijkstra(adm, i);
		}
		
	}//end of main



	// The program is for adjacency matrix representation of the graph.
	private static void dijkstra(int[][] matrix, int startVertex)
	{ 
		int nVertices = matrix[0].length; 

		int[] shortestDistances = new int[nVertices]; // shortest distance from src

		boolean[] added = new boolean[nVertices]; // true if vertex included in shortest path tree

		// Initialize all distances as INFINITE and added[] as false 
		for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) 
		{
			shortestDistances[vertexIndex] = Integer.MAX_VALUE; 
			added[vertexIndex] = false; 
		}
		
		shortestDistances[startVertex] = 0;
		 
		int[] parents = new int[nVertices]; // Parent array to store shortest path tree
		parents[startVertex] = NO_PARENT; 

		// Find shortest path for all vertices 
		for (int i = 1; i < nVertices; i++) 
		{ 
			int nearestVertex = -1; 
			int shortestDistance = Integer.MAX_VALUE; 
			for (int vertex = 0; vertex < nVertices; vertex++) 
			{ 
				if (!added[vertex] && shortestDistances[vertex] < shortestDistance)
				{ 
					nearestVertex = vertex;
					shortestDistance = shortestDistances[vertex];
				} 
			}

			// Mark the picked vertex as processed 
			added[nearestVertex] = true; 

			// Update dist value of the 
			// adjacent vertices of the picked vertex. 
			for (int vertex = 0; vertex < nVertices; vertex++) 
			{ 
				int edgeDistance = matrix[nearestVertex][vertex]; 
				
				if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertex]))
				{
					parents[vertex] = nearestVertex;
					shortestDistances[vertex] = shortestDistance + edgeDistance; 
				}
			}
		}
		printSolution(startVertex, shortestDistances, parents, matrix); 
	} 

	//print the constructed distances array and shortest paths 
	private static void printSolution(int startVertex, int[] distances, int[] parents, int adm[][]) 
	{
		int nVertices = distances.length;  
		
		for (int vertexIndex = startVertex+1; vertexIndex < nVertices; vertexIndex++) 
		{ 
			if (distances[vertexIndex] > 14) 
			{
				//System.out.println();
				System.out.print("\n" + startVertex + " -> "+ vertexIndex + "\t\t"); 
 
				System.out.print(distances[vertexIndex] + "\t\t");
				dist = distances[vertexIndex];
				
				int[] arr2 = new int[10];
				arr2 = printPath(vertexIndex, parents);
				algo(adm, arr2);
			}
		}
		//System.out.println();
	}

	//print shortest path from source to currentVertex using parents array 
	private static int[] printPath(int currentVertex, int[] parents) 
	{
		// Base case : Source node has been processed
		int[] arr = new int[10];
		counter = 0;
		printpath2(currentVertex, parents, arr);
		return arr;
	}


	public static void printpath2(int currentVertex, int[] parents, int[] arr)
	{
		if (currentVertex == NO_PARENT) 
		{ 
			return; 
		} 
		printpath2(parents[currentVertex], parents, arr);
		System.out.print(currentVertex + " ");
		arr[counter++] = currentVertex;
	} 
	

	public static void algo(int adm[][], int[] arr)
	{
		System.out.println();
		int numberofstations = dist/14;
		System.out.println("Number of required stations :"+numberofstations);

		ArrayList<Integer> prefferedNodes = new ArrayList<>();


		//System.out.println("distance between first and second node : "+adm[arr[0]][arr[1]]);
		// System.out.print("arr : ");
		// for(int i = 0; i < arr.length; i++)
		// 	System.out.print(arr[i]+" ");
		//System.out.println();
		int tc = 0;
		for(int m = 0; m < numberofstations; m++)
		{
			int vt = 0;
			while(vt <= /*drive_range*/14)
			{
				if(adm[arr[tc]][arr[tc+1]]+vt <= /*drive_range*/14)
				{
					tc++;
					vt += adm[arr[tc-1]][arr[tc]];
				}
				else
				{
					prefferedNodes.add(arr[tc]);
					break;
				}
			}
		}

		System.out.print("Charging station to be placed at : ");
		for(int temp : prefferedNodes)
			System.out.print(ANSI_CYAN+temp+" "+ANSI_RESET);

		System.out.println();
	}
}