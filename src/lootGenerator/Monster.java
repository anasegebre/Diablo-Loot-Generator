package lootGenerator;

import java.util.Random;
import java.util.Scanner;

public class Monster {

	private String monsterName;
	private int level;
	private String type;
	private String treasureClass;

	/**
	 * @param s
	 */
	public Monster(String s){	
		String[] inputs = s.split("\t");
		monsterName = inputs[0];
		type = inputs[1];
		level = Integer.parseInt(inputs[2]);
		treasureClass = inputs[3];
	}

	/**
	 * @return name of monster
	 */
	public String getMonsterName(){
		return monsterName;
	}

	/**
	 * @return treasure class for dropped item
	 */
	public String getTreasureClass(){
		return treasureClass;
	}
}