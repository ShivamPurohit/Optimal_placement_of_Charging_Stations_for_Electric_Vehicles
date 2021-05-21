package Code;

import java.util.*;
import java.io.*;


class Prob
{
	public static class Info implements Comparable<Info>
	{
		int dest;
		double dist, prob;

		public Info(int dest, double dist, double prob)
		{
			this.dest = dest;
			this.dist = dist;
			this.prob = prob;
		}

		public String toString()
		{
			return this.dest+"\t"+this.dist+"\t"+this.prob;
		}

		@Override
    	public int compareTo(Info other)
    	{
    		double temp = this.prob*100.0 - other.prob*100.0;
    		if(temp > 0.0)
    			return -1;
    		else if(temp < 0.0)
    			return 1;

    		return 0;
    	}
	}


	// GLOBAL
	static ArrayList<Integer> start = new ArrayList<>();
	static HashSet<Integer> processed = new HashSet<>();
	static HashSet<Integer> prefferedNodes = new HashSet<>();
	static ArrayList<ArrayList<Info>> arr = new ArrayList<>();
	

	public static void main(String args[])
	{
		
		for(int i = 1; i <= 24; i++)
			start.add(i);
		Collections.shuffle(start);

		@SuppressWarnings("unchecked")
		List<Integer>[] outer = new List[24];
		Arrays.setAll(outer, element -> new ArrayList<>());

		try
		{
			File data = new File("data.txt");
			Scanner sc = new Scanner(data);

			while (sc.hasNextLine())
			{
				int s = sc.nextInt();
				int d = sc.nextInt();

				outer[s-1].add(d);	// containing only edges


				if(s > arr.size())
					arr.add(new ArrayList<Info>());

				arr.get(s-1).add(new Info(d, sc.nextDouble(), sc.nextDouble()));
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error");
		}

		for(int j = 0; j < 50; j++)
		{
			start.clear();
			for(int i = 1; i <= 24; i++)
				start.add(i);
			Collections.shuffle(start);
			// display(outer);
			System.out.println();
			algo();
			System.out.println("\n"+prefferedNodes+"\n");
			processed.clear();
		}
		
	}

	public static void display(List<Integer>[] outer)
	{
		// int counter = 0;

		// for(ArrayList<Info> p : arr)
		// {
		// 	for(Info q : arr.get(counter))
		// 		System.out.println((counter+1)+"\t"+q.toString());
		// 	counter++;
		// }

		// System.out.println();

		// for(int t : start)
		// 	System.out.print(t+" ");

		// System.out.println();

		// for(int i = 0; i < outer.length; i++)
		// 	System.out.println(outer[i]);

		// System.out.println();
	}

	public static int removeRandom()
	{
		int ra = 0;
		do{
			ra = start.remove(0);
		}while(processed.contains(ra));
		
		return ra;
	}

	public static void algo()
	{
		Info d;
		while(processed.size() != 24)
		{
			int start_node = removeRandom();
			int drive = 0;
			System.out.print("Random"+start_node+" ");

			while(!processed.contains(start_node))
			{
				processed.add(start_node);
				d = next_node(start_node);
				if(prefferedNodes.contains(start_node))
					drive = 0;
				if(drive + d.dist >= 16.09)
				{
					prefferedNodes.add(start_node);
					drive = 0;
				}
				drive += d.dist;
				start_node = d.dest;
				if(start_node == 0)
					break;
				System.out.print(start_node+" ");
			}
		}

	}

	public static Info next_node(int start_node)
	{
		Info c = new Info(0, 0.0, 0.0);
		double max_prob = 0.0;

		Collections.sort(arr.get(start_node-1));
		// for(Info b : arr.get(start_node-1))
		// 	System.out.println(b.toString());

		// System.out.println();

		for(Info b : arr.get(start_node-1))
		{
			if(processed.contains(b.dest))
				continue;
			else{
				c = b;
				break;
			}
		}
		return c;
	}
}