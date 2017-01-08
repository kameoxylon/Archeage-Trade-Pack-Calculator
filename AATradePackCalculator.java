//Yitzak Hernandez
//Archeage Trade Pack Calculator

import java.util.*;
import java.io.*;

class Pack
{
	String packName;
	String type;
	String gildaStar;
	LinkedList<String> material1 = new LinkedList<String>();
	LinkedList<String> material2 = new LinkedList<String>();

	public String getName() 
	{
		return packName;
	}

	public String getType()
	{
		return type;
	}

	public String getGildaStar() 
	{
		return gildaStar;
	}

	public String getMaterial1(int i)
	{
		return material1.get(i);
	}

	public String getMaterial2(int i)
	{
		return material2.get(i);
	}
	
}


public class AATradePackCalculator 
{

	public static void main(String [] args) throws FileNotFoundException 
	{
		HashMap<String, Pack> packs = new HashMap<String, Pack>();
		HashMap<String, Integer> regions = new HashMap<String, Integer>();
		String buffer;

		Scanner info = new Scanner(new File("tradepackinfo.txt"));

		while(info.hasNext())
		{

			Integer totalPacks = info.nextInt();
			buffer = info.nextLine();
			String regionName = info.nextLine();
			regions.put(regionName, totalPacks);

			for (int i = 0; i < totalPacks; i++)
			{	
				Pack pack = new Pack();

				pack.packName = info.nextLine();
				pack.type = info.nextLine();
				pack.gildaStar = info.nextLine();

				buffer = info.nextLine();
				pack.material1.addFirst(buffer);

				buffer = info.nextLine();
				pack.material1.addLast(buffer);

				buffer = info.nextLine();
				pack.material2.addFirst(buffer);

				buffer = info.nextLine();
				pack.material2.addLast(buffer);

				packs.put(pack.packName, pack);
			}
		}

		System.out.println("What would you like to do? [1] Cheapest pack, [2] How much a pack will cost")

	}
}