package lootGenerator;

import java.util.Random;

public class Suffix {
	private String name;
	private String mod1code;
	private String mod1min;
	private String mod1max;
	
	/**
	 * @param s
	 */
	public Suffix(String s){
		String[] inputs = s.split("\t");
		name = inputs[0];
		mod1code = inputs[1];
		mod1min = inputs[2];
		mod1max = inputs[3];
	}
	
	/**
	 * @return suffix name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return mod1code for the suffixes
	 */
	public String getMod1code() {
		return mod1code;
	}
	
	/**
	 * @return suffix stat
	 */
	public String getStat() {
		Random rand = new Random();
		Integer ret = rand.nextInt(Integer.parseInt(mod1max) - Integer.parseInt(mod1min));
		ret = ret + Integer.parseInt(mod1min);
		return ret.toString();
	}
	

}