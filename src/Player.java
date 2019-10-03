
/**
 * Author: Paul Rich
 * Program: Player.java
 * Purpose: Creates a player object, with usage, 2pt, 3pt, 2ptvs3pt, reb and assist attributes
 * 
 */

import java.util.Random;

/**
 * Creates a player instance, with instance variables matching aforementioned
 * categories. Usage, 2pt, 3pt, 2ptvs3pt, rebound, assist. These are the main
 * ratings used in simulation algorithm.
 */
public class Player {

	String firstName;
	String lastName;
	int playerID;
	double usage;
	double twoPtVsThreePt;
	double threePt;
	double twoPt;
	double rebound;
	double assist;

	public Player(String[] playerData) {
		playerID = Integer.valueOf(playerData[1]);
		firstName = playerData[2];
		lastName = playerData[3];
		twoPt = Double.valueOf(playerData[4]);
		threePt = Double.valueOf(playerData[5]);
		twoPtVsThreePt = Double.valueOf(playerData[6]);
		assist = Double.valueOf(playerData[7]);
		rebound = Double.valueOf(playerData[8]);
		usage = Double.valueOf(playerData[9]);
	}

	/**
	 * 
	 * @returns a String of the players first and last name, sep by a space.
	 */
	public String asString() {
		String toR = firstName + " " + lastName;
		return toR;
	}

	/**
	 * 
	 * @returns the playersID, which is used to map to the rotation array
	 */
	public int getID() {
		return playerID;
	}

	/**
	 * 
	 * @returns the players usage rating
	 */
	public double getUsage() {
		return usage;
	}

	/**
	 * 
	 * @returns the player's rebounding rating
	 */
	public double getRebound() {
		return rebound;
	}

	/**
	 * 
	 * @returns a string of the result of the player's shot. Possible returns are:
	 *          "Made Three" "Missed Three" "Made Two" "Missed Two"
	 */
	public String shot() {
		Random rand = new Random();
		double randShot2v3 = rand.nextDouble();
		if (randShot2v3 > twoPtVsThreePt) {
			double threeRand = rand.nextDouble();
			if (threeRand > threePt) {
				return "Missed Three";
			} else {
				return "Made Three";
			}
		} else {
			double twoRand = rand.nextDouble();
			if (twoRand > twoPt) {
				return "Missed Two";
			} else {
				return "Made Two";
			}
		}

	}

}
