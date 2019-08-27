package lootGenerator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class LootGenerator {

	/**
	 * @param file
	 * @return a random Monster
	 * @throws FileNotFoundException
	 */
	public static Monster pickMonster(File file) throws FileNotFoundException {
		Random rand = new Random();
		Scanner in = new Scanner(file);
		Map<Integer, Monster> map = new HashMap<Integer, Monster>();
		for(int i = 0;in.hasNext(); i++) {
			Monster monster = new Monster(in.nextLine());
			map.put(i, monster);
		}
		int size = map.size();
		Monster randMonster = map.get(rand.nextInt(size));
		in.close();
		return randMonster;
	}

	/**
	 * @param file
	 * @return the list of treasure classes
	 * @throws FileNotFoundException
	 */
	public static List<TreasureClass> treasureClassScanner(File file) throws FileNotFoundException{
		Scanner in = new Scanner(file);
		List<TreasureClass> treasureClassList = new ArrayList<TreasureClass>();
		while(in.hasNext()){
			treasureClassList.add(new TreasureClass(in.nextLine()));
		}
		in.close();
		return treasureClassList;
	}

	/**
	 * @param treasureClassList
	 * @param monster
	 * @return the treasure dropped
	 */
	public static TreasureClass fetchTreasureClass(List<TreasureClass> treasureClassList, Monster monster){
		String treasure =  monster.getTreasureClass();
		Iterator<TreasureClass> classIter = treasureClassList.iterator();
		TreasureClass retTreasure;
		while(classIter.hasNext()){
			retTreasure = classIter.next();
			if(retTreasure.getTypeOfTreasure().equals(treasure)){
				return retTreasure;
			}
		}
		return null;
	}

	/**
	 * @param file
	 * @return the list of armor
	 * @throws FileNotFoundException
	 */
	public static List<Armor> armorScanner(File file) throws FileNotFoundException{
		Scanner in = new Scanner(file);
		List<Armor> ret = new ArrayList<Armor>();
		while(in.hasNext()){
			ret.add(new Armor(in.nextLine()));
		}
		in.close();
		return ret;
	}

	/**
	 * @param armor
	 * @param treasureClassList
	 * @param treasure
	 * @return returns base armor item
	 */
	public static Armor generateBaseItem(List<Armor> armor, List<TreasureClass> treasureClassList, TreasureClass treasure){
		String item = treasure.getRandomItem();
		Iterator<Armor> armorIter = armor.iterator();
		Armor retArmor;
		while(armorIter.hasNext()){
			retArmor = armorIter.next();
			if(retArmor.getArmor().equals(item)){

				return retArmor;
			}
		}
		Iterator<TreasureClass> armorIter1 = treasureClassList.iterator();
		TreasureClass cur;
		while(armorIter1.hasNext()){
			cur = armorIter1.next();
			if(cur.getTypeOfTreasure().equals(item)){
				return generateBaseItem(armor, treasureClassList, cur);
			}
		}
		return null;
	}

	/**
	 * @param file
	 * @return list of prefixes
	 * @throws FileNotFoundException
	 */
	public static List<Prefix> prefixScanner(File file) throws FileNotFoundException{
		Scanner in = new Scanner(file);
		List<Prefix> ret = new ArrayList<Prefix>();
		while(in.hasNext()){
			ret.add(new Prefix(in.nextLine()));
		}
		in.close();
		return ret;
	}

	/**
	 * @param file
	 * @return list of suffixes
	 * @throws FileNotFoundException
	 */
	public static List<Suffix> suffixScanner(File file) throws FileNotFoundException{
		Scanner in = new Scanner(file);
		List<Suffix> ret = new ArrayList<Suffix>();
		while(in.hasNext()){
			ret.add(new Suffix(in.nextLine()));
		}
		in.close();
		return ret;
	}

	/**
	 * @param prefixList
	 * @return item prefix
	 */
	public static Prefix generatePrefix(List<Prefix> prefixList) {
		Iterator<Prefix> prefixIter = prefixList.iterator();
		Prefix prefix = null;
		Random rand = new Random();
		int r = rand.nextInt(prefixList.size());
		while(prefixIter.hasNext() && r != -1){
			prefix = prefixIter.next();
			r--;
		}
		return prefix;
	}	

	/**
	 * @param suffixList
	 * @return item suffix
	 */
	public static Suffix generateSuffix(List<Suffix> suffixList) {
		Iterator<Suffix> suffixIter = suffixList.iterator();
		Suffix suffix = null;
		Random rand = new Random();
		int r = rand.nextInt(suffixList.size());
		while(suffixIter.hasNext() && r != -1){
			suffix = suffixIter.next();
			r--;
		}
		return suffix;
	}

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		//Loop keeps going as long as user wants to run program
		boolean continu = true;
		while (continu == true){

			//These declarations, calls, and print statements run the actual gameplay by generating items, stats, etc.
			File monsters = new File("dataSets/data/large/monstats.txt");
			Monster m = pickMonster(monsters);
			System.out.println("Fighting" + " " + m.getMonsterName());
			System.out.println("You have slain: " +  m.getMonsterName());
			System.out.println(m.getMonsterName() + " has dropped:");
			File treasureList = new File("dataSets/data/large/TreasureClassEx.txt");
			List<TreasureClass> list = treasureClassScanner(treasureList);
			TreasureClass t = fetchTreasureClass(list, m);
			File armorList = new File("dataSets/data/large/armor.txt");
			File plist = new File("dataSets/data/large/MagicPrefix.txt");
			List<Prefix> prefixl = prefixScanner(plist);
			Prefix prefix = generatePrefix(prefixl);
			File slist = new File("dataSets/data/large/MagicSuffix.txt");
			List<Suffix> suffixl = suffixScanner(slist);
			Suffix suffix = generateSuffix(suffixl);
			List<Armor> l = armorScanner(armorList);
			Armor armor = generateBaseItem(l, list, t);
			Random rand1 = new Random();
			Random rand2 = new Random();
			int pre = rand1.nextInt(2);
			int suf = rand2.nextInt(2);
			if (pre == 0) {

				System.out.print(prefix.getName() + " ");
			} 
			System.out.print(armor.getArmor());
			if (suf == 0) {
				System.out.print(" " + suffix.getName());

			} 
			System.out.println("");

			System.out.println("Defense: " + armor.getDefense());
			if (pre == 0) {
				System.out.println(prefix.getStat() + " " + prefix.getMod1code());
			}
			if (suf == 0) {
				System.out.println(suffix.getStat() + " " + suffix.getMod1code());
			}

			//here we handle the user's input of whether they want to continue or not
			System.out.println("Do you want to continue? y/n");
			boolean cont2 = false;
			do{ 
				Scanner yesno = new Scanner(System.in);
				String answer = yesno.nextLine();
				//int a = continues(answer);
				if(answer.equalsIgnoreCase("y")){
					cont2 = false;
				}
				else if(answer.equalsIgnoreCase("n")){
					cont2 = false;
					continu = false;
				}
				else {
					cont2 = true;
					System.out.println("wrong syntax, try again");
				}
			} while(cont2 == true);
		}
	}
}