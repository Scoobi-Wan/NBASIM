/**
 * Author: Paul Rich
 * Program: Game.java
 * Purpose: Creates a game object, which is used to simulate an NBA game.
 */

import java.util.Arrays;
/**
 * 
 * Creates an instance of Game, with possible methods startGame(), and the private 
 * method printFinal() used to print the final box score. printFinal is called automatically
 * at the completion of the game.
 */
public class Game {
	int possesions = 210; //Chosen as NBA avg is usually ~105/team
	Team teamWithBall;
	int homeScore = 0;
	int awayScore = 0;
	GameStats homeStats;
	GameStats awayStats;
	int possSinceSub = 1;
	public Game(Team HomeTeam, Team AwayTeam) {
		teamWithBall = HomeTeam;
		HomeTeam.n = 0;
		AwayTeam.n = 0;
		HomeTeam.subIn();
		AwayTeam.subIn();
		homeStats = new GameStats(HomeTeam);
		awayStats = new GameStats(AwayTeam);
		startGame(HomeTeam, AwayTeam);
		//printFinal(HomeTeam, AwayTeam);
		//System.out.println(HomeTeam.getName().toUpperCase());
		//String hs = homeStats.printStats();
		//System.out.println(hs);
		//System.out.println(AwayTeam.getName().toUpperCase());
		//String as = awayStats.printStats();
		//System.out.println(as);
		homeStats.updateSeasonStats();
		awayStats.updateSeasonStats();
		if(homeStats.score > awayStats.score) {
			HomeTeam.wins++;
			AwayTeam.losses++;
		} else {
			HomeTeam.losses++;
			AwayTeam.wins++;
		}

	}

		private void printFinal(Team homeTeam, Team awayTeam) {
			System.out.print("FINAL: " + homeTeam.getName() + " " + homeStats.getScore() + " - " + 
		awayTeam.getName() + " " + awayStats.getScore());
		if (homeStats.overtime == true) {
			System.out.println(" (" + homeStats.overtimes +"OT)");
		} else {
			System.out.println();
		}
	}

/**
 * 
 * Starts the game, with 210 possesions, as NBA averages around 105 per team per game.
 * Game operates with substitutions every 18 possesions, with rotations used based on teams
 * play style, indicated within the team object. At the end of the game, the function calls
 * printFinal() to print the final box score.
 */
		public void startGame(Team HomeTeam, Team AwayTeam) {
		while (possesions > 0) {
			if (possSinceSub == 19) {
				AwayTeam.subIn();
				HomeTeam.subIn();
				possSinceSub = 1;
			}
			if(teamWithBall==HomeTeam) {
				String pEvent = HomeTeam.possesionEventSelector();
				String[] splitEvent = pEvent.split("-");
				String playerName = splitEvent[0];
				String shotResult = splitEvent[1];
				homeStats.updateStats(playerName, shotResult);
				if (shotResult.equals("Missed Three") || (shotResult.equals("Missed Two"))) {
					Player reboundedBy = AwayTeam.reboundSelector();
					awayStats.updateStats(reboundedBy.asString(), "Rebound");
					//System.out.println(playerName + " " + shotResult + ", Rebounded by " + 
					//reboundedBy.asString());
					possesions--;
					possSinceSub++;
					teamWithBall = AwayTeam;
				} else {
					//System.out.println(playerName + " " + shotResult);
					possesions--;
					possSinceSub++;
					teamWithBall = AwayTeam;
			}
			} else {
				String pEvent = AwayTeam.possesionEventSelector();
				String[] splitEvent = pEvent.split("-");
				String playerName = splitEvent[0];
				String shotResult = splitEvent[1];
				awayStats.updateStats(playerName, shotResult);
				if (shotResult.equals("Missed Three") || (shotResult.equals("Missed Two"))) {
					Player reboundedBy = HomeTeam.reboundSelector();
					homeStats.updateStats(reboundedBy.asString(), "Rebound");
					//System.out.println(playerName + " " + shotResult + ", Rebounded by " + 
					//		reboundedBy.asString());
					possesions--;
					possSinceSub++;
					teamWithBall = HomeTeam;
				} else {
					//System.out.println(playerName + " " + shotResult);
					possesions--;
					possSinceSub++;
					teamWithBall = HomeTeam;
			}
				
			}
			if (homeStats.score == awayStats.score && possesions == 0) {
				HomeTeam.resetRotation();
				AwayTeam.resetRotation();
				possesions = 18;
				homeStats.overtime = true;
				homeStats.overtimes++;
			}
			
		}
		}
	}
	


