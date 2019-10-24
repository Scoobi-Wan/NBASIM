import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Team {

	int rosterSize;
	Player[] roster;
	int wins;
	int losses;
	String teamName;
	int simWins;
	int simLosses;
	double simAwins;
	double simAlosses;
	char conference;
	SeasonStats sStats = new SeasonStats(this);
	int[][][] possRotations = new int[][][] {
			{ { 1, 2, 3, 4, 5 }, { 1, 2, 3, 4, 5 }, { 1, 2, 8, 9, 5 }, { 6, 7, 8, 9, 10 }, { 1, 2, 3, 11, 10 },
					{ 1, 2, 3, 4, 5 }, { 1, 2, 3, 4, 5 }, { 1, 2, 8, 3, 5 }, { 7, 2, 3, 12, 10 }, { 6, 7, 8, 9, 10 },
					{ 7, 2, 8, 11, 5 }, { 1, 2, 3, 4, 5 } },
			{ { 1, 2, 3, 4, 5 }, { 3, 2, 6, 4, 5 }, { 6, 7, 8, 9, 10 }, { 6, 2, 3, 4, 5 }, { 1, 2, 3, 4, 5 },
					{ 1, 2, 3, 4, 5 }, { 6, 2, 3, 9, 5 }, { 6, 7, 8, 9, 10 }, { 1, 2, 3, 6, 4 }, { 6, 7, 3, 4, 10 },
					{ 1, 2, 4, 9, 10 }, { 6, 2, 3, 4, 5 } },
			{ { 1, 2, 3, 4, 5 }, { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 1, 2, 3, 4, 10 }, { 6, 7, 8, 9, 5 },
					{ 1, 2, 3, 4, 5 }, { 1, 2, 3, 7, 10 }, { 6, 2, 8, 9, 10 }, { 1, 7, 8, 9, 10 }, { 1, 6, 3, 4, 5 },
					{ 1, 2, 3, 4, 5 }, { 1, 2, 3, 4, 5 } },
			{ { 1, 2, 3, 4, 5 }, { 1, 2, 6, 4, 10 }, { 6, 7, 8, 9, 10 }, { 6, 2, 7, 4, 5 }, { 1, 2, 3, 4, 5 },
					{ 1, 2, 3, 4, 5 }, { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 8, 9, 10 }, { 1, 2, 3, 4, 5 },
					{ 1, 2, 8, 9, 10 }, { 1, 2, 3, 4, 5 } },
			{ { 1, 2, 3, 4, 5 }, { 1, 2, 6, 7, 10 }, { 1, 2, 8, 9, 5 }, { 6, 2, 8, 9, 10 }, { 1, 6, 3, 4, 5 },
					{ 1, 2, 3, 9, 5 }, { 6, 2, 3, 4, 5 }, { 1, 2, 8, 9, 10 }, { 1, 2, 8, 9, 10 }, { 1, 2, 7, 4, 5 },
					{ 1, 2, 3, 4, 5 }, { 1, 2, 3, 4, 5 } },
			{ { 1, 2, 3, 4, 5 }, { 1, 6, 3, 4, 5 }, { 1, 6, 8, 4, 10 }, { 6, 2, 8, 9, 10 }, { 12, 2, 3, 9, 10 },
					{ 1, 2, 3, 4, 5 }, { 1, 2, 3, 4, 5 }, { 6, 7, 3, 9, 5 }, { 6, 7, 8, 9, 10 }, { 1, 2, 8, 4, 10 },
					{ 1, 6, 3, 4, 10 }, { 1, 2, 3, 4, 5 } },
			{ { 1, 2, 3, 4, 5 }, { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 6, 7, 9, 4, 5 }, { 1, 2, 3, 4, 5 },
					{ 1, 2, 3, 4, 5 }, { 1, 2, 3, 4, 5 }, { 1, 6, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 1, 2, 3, 4, 10 },
					{ 1, 2, 3, 4, 5 }, { 1, 2, 3, 4, 5 } },
			{ { 1, 2, 3, 4, 5 }, { 1, 2, 3, 4, 5 }, { 1, 7, 8, 4, 5 }, { 6, 7, 8, 9, 10 }, { 2, 3, 4, 11, 10 },
					{ 1, 2, 3, 4, 5 }, { 1, 2, 3, 4, 5 }, { 1, 2, 8, 9, 10 }, { 6, 7, 12, 9, 10 }, { 6, 7, 3, 4, 5 },
					{ 1, 2, 3, 4, 5 }, { 1, 2, 3, 4, 5 } } };

	int subs;
	Player[] onTheFloor;
	int n = 0;
	int[][] rotation;

	public Team(String city, String name, String ID, int rot) {

		String cityName = city;
		teamName = name;
		String teamID = ID;

		roster = new Player[12];
		rosterSize = 0;
		wins = 0;
		losses = 0;
		rotation = possRotations[rot];

	}

	/**
	 * 
	 * @param player object
	 * @return boolean of true if player successfully added, false if not added.
	 */

	public boolean addPlayerToTeam(Player player) {
		if (rosterSize < 12) {
			roster[rosterSize] = player;
			rosterSize++;
			return true;
		} else {
			return false;
		}

	}

	public void createPlayer() {
	}
	
	public void resetRotation() {
		n = 0;
	}

	public String getName() {
		return teamName;
	}

	public String rosterToString() {
		String toString = "";
		for (int i = 0; i < rosterSize; i++) {
			toString = toString + roster[i].asString() + ", ";
		}
		return toString;
	}

	/**
	 * for(int i = 0; i < onTheFloor.length; i++) { totalUsage +=
	 * onTheFloor[i].getUsage(); } for (int k = 0; k < onTheFloor.length; k++) {
	 * onTheFloorUsages[k] = ((onTheFloor[k].getUsage()) / totalUsage) +
	 * (secUsagePass / totalUsage); secUsagePass += onTheFloor[k].getUsage(); }
	 * 
	 * 
	 * String asStr = Arrays.toString(onTheFloorUsages); System.out.println(asStr);
	 * double shotSelector = rand.nextDouble(); for(int j = 0; j <
	 * onTheFloorUsages.length; j++) { if(onTheFloorUsages[j] > shotSelector) {
	 * String result = onTheFloor[j].shot();
	 * System.out.println(onTheFloor[j].asString() + " - " + result);
	 * 
	 * } } return "done for now";
	 * 
	 */
	public String possesionEventSelector() {
		double totalUsage = 0.0;
		double secUsagePass = 0.0;
		Random rand = new Random();
		double[] onTheFloorUsages = new double[5];
		RandomCollection<Player> rc1 = new RandomCollection<>();
		for (int i = 0; i < onTheFloor.length; i++) {
			double w = onTheFloor[i].getUsage() * 100;
			rc1.add(w, onTheFloor[i]);
		}

		Player toShoot = rc1.next();
		String shotResult = toShoot.asString() + "-" + toShoot.shot();
		return shotResult;

	}

	public Player reboundSelector() {
		RandomCollection<Player> rebCol = new RandomCollection<>();
		for (int i = 0; i < onTheFloor.length; i++) {
			double w = onTheFloor[i].getRebound() * 100;
			rebCol.add(w, onTheFloor[i]);
		}

		Player toRebound = rebCol.next();
		return toRebound;

	}

	public void subIn() {
		int[] currRotation = rotation[n];
		Player[] temp = new Player[5];
		n++;
		for (int i = 0; i < 5; i++) {
			temp[i] = roster[currRotation[i] - 1];
		}
		onTheFloor = temp;

	}

}
