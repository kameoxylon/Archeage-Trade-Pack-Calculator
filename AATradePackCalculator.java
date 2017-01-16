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

	//0 = Price, 1 = Name
	public String getMaterial1(int i)
	{
		return material1.get(i);
	}

	//0 = Price, 1 = Name
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
		HashMap<String, Integer> materialNamesPrices = new HashMap<String, Integer>();
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

				//Quantity
				buffer = info.nextLine();
				pack.material1.addFirst(buffer);

				//Name
				buffer = info.nextLine();
				pack.material1.addLast(buffer);
				materialNamesPrices.put(buffer, 0);

				//Quantity
				buffer = info.nextLine();
				pack.material2.addFirst(buffer);

				//Name
				buffer = info.nextLine();
				pack.material2.addLast(buffer);
				materialNamesPrices.put(buffer, 0);


				packs.put(pack.packName, pack);
			}
		}

		//iterate through hashset and add prices to each corresponding

		System.out.println(packs.get("tigerspine seasoned meat").getMaterial1(0));


		System.out.println("What would you like to do? [1] Cheapest pack, [2] How much a pack will cost");
		Scanner userInput = new Scanner(System.in);
		int input = userInput.nextInt();

		if (input == 1)
		{
			System.out.println();
			System.out.println("Please input the current price for each item.");
			System.out.println("(ex. 2g 0s 30c = 20030, 0g 20s 0c = 2000, 1g 0s 0c = 10000");

			System.out.println();
			System.out.println("What is the price for...");
			for (String matName : materialNamesPrices.keySet())
			{
				System.out.print(matName + ": ");
				input = userInput.nextInt();
				materialNamesPrices.put(matName, input);
			}
			
			System.out.println();
			int lowestPrice = 99999999;
			String lowestPackName = " ";
			for (String packName : packs.keySet())
			{
				int price = packPrice(packs.get(packName), materialNamesPrices);
				System.out.println(packName + ": " + price);

				if (price < lowestPrice)
				{
					lowestPrice = price;
					lowestPackName = packName;
				}
			}

			System.out.println();
			System.out.println("Lowest pack is: " + lowestPackName + " -> " + lowestPrice);
			
		}
		else if (input == 2)
		{
			System.out.println("Please input the current price for each item.");
			System.out.println("(ex. 2g 0s 30c = 20030, 0g 20s 0c = 2000, 1g 0s 0c = 10000");

		}
		else
		{
			System.out.println("Choice not found. Please pick from one of the choices listed above.");
		}

	}

	public static int packPrice (Pack pack, HashMap materialPrices){
	
		String mat1Amt = pack.getMaterial1(0);
		String mat2Amt = pack.getMaterial2(0);

		String material1 = pack.getMaterial1(1);
		String material2 = pack.getMaterial2(1);

		int total = Integer.parseInt(mat1Amt) * (int)materialPrices.get(material1) + Integer.parseInt(mat2Amt) * (int)materialPrices.get(material2);
		return total;
		
	}

}