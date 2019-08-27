package lootGenerator;
import java.util.Random;

public class TreasureClass {
	private String typeOfTreasure;
	private String[] treasures;
	
	/**
	 * @param s
	 */
	public TreasureClass(String s){
		String[] inputs = s.split("\t");
		typeOfTreasure = inputs[0];
		treasures = new String[3];
		for(int i = 0; i < 3; i++){
		treasures[i] = inputs[i+1];
		}
	}
	
	/**
	 * @return type of treasure drop
	 */
	public String getTypeOfTreasure(){
		return typeOfTreasure;
	}
	
	/**
	 * @return random item
	 */
	public String getRandomItem(){
		Random rand = new Random();
		return treasures[rand.nextInt(3)];
	}

}
