
/**
 * Author: Paul Rich
 * Program: GameStats.java
 * Description: GameStats is used to log the stats for individual players during the course
 * of a simulation. The GameStats are accessed at the end of the game by the Game class's 
 * printFinal method from within startGame(). 
 */

import java.util.HashMap;
import java.util.Map;

public class GameStats {

	Map<String, int[]> statsMap;
	int score = 0;
	boolean overtime = false;
	int overtimes = 0;
	Player[] teamRoster;
	Team team;

	public GameStats(Team t) {
		teamRoster = t.roster;
		team = t;
		statsMap = new HashMap<String, int[]>();
		for (int i = 0; i < team.rosterSize; i++) {
			int[] dbA = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
			statsMap.put(team.roster[i].asString(), dbA);
		}
	}

	public void updateStats(String playerName, String result) {

		if (result.equals("Missed Three")) {
			statsMap.get(playerName)[1] += 1;
			statsMap.get(playerName)[3] += 1;
		}

		if (result.equals("Made Three")) {
			statsMap.get(playerName)[0] += 1;
			statsMap.get(playerName)[1] += 1;
			statsMap.get(playerName)[2] += 1;
			statsMap.get(playerName)[3] += 1;
			score += 3;
		}

		if (result.equals("Missed Two")) {
			statsMap.get(playerName)[1] += 1;
		}

		if (result.equals("Made Two")) {
			statsMap.get(playerName)[0] += 1;
			statsMap.get(playerName)[1] += 1;
			score += 2;
		}

		if (result.equals("Rebound")) {
			statsMap.get(playerName)[4] += 1;
		}

		if (result.equals("Made FT")) {
			statsMap.get(playerName)[6] += 1;
			statsMap.get(playerName)[7] += 1;
			score += 1;
		}

		if (result.equals("Missed FT")) {
			statsMap.get(playerName)[7] += 1;
		}
	}

	public int getScore() {
		return score;
	}

	public String printStats() {
		String toR = "NAME\t\t\t\tPTS\tFGM\tFGA\t3FGM\t3FGA\tREB\tFTA\tFTM\n";
		for (int i = 0; i < teamRoster.length; i++) {
			String selected = teamRoster[i].asString();
			double pts = ((statsMap.get(selected)[0] - statsMap.get(selected)[2]) * 2)
					+ (statsMap.get(selected)[2] * 3);
			if (selected.length() > 23) {
				toR = toR + selected + "\t" + pts + "\t" + statsMap.get(selected)[0] + "\t" + statsMap.get(selected)[1]
						+ "\t" + statsMap.get(selected)[2] + "\t" + statsMap.get(selected)[3] + "\t"
						+ statsMap.get(selected)[4] + "\n";
			} else if (selected.length() > 15 && selected.length() < 24) {
				toR = toR + selected + "\t\t" + pts + "\t" + statsMap.get(selected)[0] + "\t"
						+ statsMap.get(selected)[1] + "\t" + statsMap.get(selected)[2] + "\t"
						+ statsMap.get(selected)[3] + "\t" + statsMap.get(selected)[4] + "\n";
			} else if (selected.length() < 8) {
				toR = toR + selected + "\t\t\t\t" + pts + "\t" + statsMap.get(selected)[0] + "\t"
						+ statsMap.get(selected)[1] + "\t" + statsMap.get(selected)[2] + "\t"
						+ statsMap.get(selected)[3] + "\t" + statsMap.get(selected)[4] + "\n";
			}

			else {
				toR = toR + selected + "\t\t\t" + pts + "\t" + statsMap.get(selected)[0] + "\t"
						+ statsMap.get(selected)[1] + "\t" + statsMap.get(selected)[2] + "\t"
						+ statsMap.get(selected)[3] + "\t" + statsMap.get(selected)[4] + "\n";
			}
		}

		return toR;
	}

	public void updateSeasonStats() {
		for (int i = 0; i < 12; i++) {
			String selected = teamRoster[i].asString();
			team.sStats.statsMap.get(selected)[0] += statsMap.get(selected)[0];
			team.sStats.statsMap.get(selected)[1] += statsMap.get(selected)[1];
			team.sStats.statsMap.get(selected)[2] += statsMap.get(selected)[2];
			team.sStats.statsMap.get(selected)[3] += statsMap.get(selected)[3];
			team.sStats.statsMap.get(selected)[4] += statsMap.get(selected)[4];
			team.sStats.games++;

		}
	}

}
