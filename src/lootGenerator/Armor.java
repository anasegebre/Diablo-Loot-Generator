package lootGenerator;

import java.util.Random;

public class Armor {
	private String armor;
	private String minac;
	private String maxac;

	/**
	 * @param s
	 * This is the constructor for Armor
	 */
	public Armor(String s){	
		String[] inputs = s.split("\t");
		armor = inputs[0];
		minac = inputs[1];
		maxac = inputs[2];
	}

	/**
	 * @return armor
	 */
	public String getArmor(){
		return armor;
	}

	/**
	 * @return defense stat of armor
	 */
	public String getDefense(){
		Random rand = new Random();
		Integer ret = rand.nextInt(Integer.parseInt(maxac) - Integer.parseInt(minac));
		ret = ret + Integer.parseInt(minac);
		return ret.toString();
	}
	
}
