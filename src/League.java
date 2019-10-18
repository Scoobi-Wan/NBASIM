
/**
 * Author: Paul Rich
 * Program: League.java, part of the NBA suite
 * Purpose: Creates an new league, which holds teams, and can be used to run a season.
 * 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * Creates an instance of League(), which holds team objects. At initialization,
 * teams is empty, teams are added through the addTeam() method.
 * readTeamFile(filename) opens a CSV file and parses it for player information.
 * Players are added to their respective teams. This file must be the correctly
 * formated TEAMSHEET CSV file, or it will not work.
 *
 */
public class League {

	int leagueSize;
	Team[] teams;
	String[][] schedule;
	@SuppressWarnings("serial")
	Map<String, Team> teamIDs;
	Team[] EasternConference;
	Team[] WesternConference;

	public League() throws IOException {

		leagueSize = 0;
		setTeams();
	}

	/**
	 * 
	 * Adds a team object to the Array of teams in the league. LeagueSize is
	 * incremented by one to reflect the added team.
	 */
	public boolean addTeam(Team teamToAdd) {
		if (leagueSize < 30) {
			teams[leagueSize] = teamToAdd;
			leagueSize++;
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * @param filename, a String of the file location
	 * @throws IOException if file does not exist, or path is incorrect.
	 * 
	 *                     Reads in the TEAMSHEET file via the filename String,
	 *                     which is the location of the TEAMSHEET file.
	 */
	public void readTeamFile(String filename) throws IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader(filename));
		String row;
		String currKey = "";
		while ((row = csvReader.readLine()) != null) {
			String[] data = row.split(",");
			if ((data[0].isEmpty())) {
				Player currPlayer = new Player(data);
				teamIDs.get(currKey).addPlayerToTeam(currPlayer);
			} else {
				if (teamIDs.containsKey(data[0])) {
					currKey = data[0];
				}

			}
		}

		csvReader.close();

	}

	/**
	 * Prints a list of the current teams in the league.
	 */
	public void printTeams() {
		for (int i = 0; i < leagueSize; i++) {
			System.out.println(teams[i].getName());
		}
	}
	
	
	public void setSeasonStats() {
		Set<String> ks = teamIDs.keySet();
		Object[] keySetArr = ks.toArray();
		for(int i= 0; i < 30; i++) {
			teamIDs.get(keySetArr[i]).sStats = new SeasonStats(teamIDs.get(keySetArr[i]));
		}
	}

	/**
	 * 
	 * Sets all standard 30 NBA teams to be in the league.
	 */
	public void setTeams() throws IOException {

		Team suns = new Team("Phoenix", "Suns", "PHX", 0);
		Team lakers = new Team("Los Angeles", "Lakers", "LAL", 1);
		Team kings = new Team("Sacramento", "Kings", "SAC", 2);
		Team warriors = new Team("Golden State", "Warriors", "GSW", 2);
		Team jazz = new Team("Utah", "Jazz", "UTH", 3);
		Team clippers = new Team("Los Angeles", "Clippers", "LAC", 1);
		Team blazers = new Team("Portland", "Blazers", "POR", 4);
		Team rockets = new Team("Houston", "Rockets", "HOU", 4);
		Team mavericks = new Team("Dallas", "Mavericks", "DAL", 2);
		Team spurs = new Team("San Antonio", "Spurs", "SAS", 2);
		Team pelicans = new Team("New Orleans", "Pelicans", "NOP", 2);
		Team grizzlies = new Team("Memphis", "Grizzlies", "MEM", 3);
		Team nuggets = new Team("Denver", "Nuggets", "DEN", 4);
		Team timberwolves = new Team("Minnesota", "Timberwolves", "MIN", 0);
		Team thunder = new Team("Oklahoma City", "Thunder", "OKC", 3);
		Team celtics = new Team("Boston", "Celtics", "BOS", 2);
		Team cavaliers = new Team("Cleveland", "Cavaliers", "CLE", 3);
		Team raptors = new Team("Toronto", "Raptors", "TOR", 6);
		Team heat = new Team("Miami", "Heat", "MIA", 7);
		Team hornets = new Team("Charlotte", "Hornets", "CHA", 3);
		Team magic = new Team("Orlando", "Magic", "ORL", 7);
		Team knicks = new Team("New York", "Knicks", "NYK", 3);
		Team nets = new Team("Brooklyn", "Nets", "BKN", 5);
		Team hawks = new Team("Atlanta", "Hawks", "ATL", 2);
		Team wizards = new Team("Washington", "Wizards", "WAS", 3);
		Team sixers = new Team("Philadelphia", "76ers", "PHI", 3);
		Team bulls = new Team("Chicago", "Bulls", "CHI", 3);
		Team bucks = new Team("Milwaukee", "Bucks", "MIL", 7);
		Team pacers = new Team("Indiana", "Pacers", "IND", 3);
		Team pistons = new Team("Detroit", "Pistons", "$DET", 6);
		
		EasternConference = new Team[] {celtics, cavaliers, raptors, heat, hornets,
				magic, knicks, nets, hawks, wizards, sixers, bulls, bucks, pacers, pistons};
		WesternConference = new Team[] {suns, lakers, kings, warriors, jazz, clippers,
				blazers, rockets, mavericks, spurs, pelicans, grizzlies, nuggets, timberwolves, thunder};

		teamIDs = new HashMap<String, Team>() {
			{
				put("PHX", suns);
				put("LAL", lakers);
				put("SAC", kings);
				put("LAC", clippers);
				put("UTH", jazz);
				put("SAS", spurs);
				put("DAL", mavericks);
				put("GSW", warriors);
				put("POR", blazers);
				put("OKC", thunder);
				put("HOU", rockets);
				put("MEM", grizzlies);
				put("DEN", nuggets);
				put("MIN", timberwolves);
				put("NOP", pelicans);
				put("BOS", celtics);
				put("BKN", nets);
				put("NYK", knicks);
				put("PHI", sixers);
				put("TOR", raptors);
				put("CHI", bulls);
				put("CLE", cavaliers);
				put("DET", pistons);
				put("IND", pacers);
				put("MIL", bucks);
				put("ATL", hawks);
				put("CHA", hornets);
				put("MIA", heat);
				put("ORL", magic);
				put("WAS", wizards);
			}
		};
		readTeamFile("/Users/paul/Desktop/paulrepos/NBASIM/TEAMSHEET - Sheet1.csv");
	}

	/**
	 * 
	 * @param filename, the name of a CSV file containing the list of games in the
	 *                  season.
	 * @throws IOException if filename does not exist, or is incorrect.
	 * 
	 *                     Creates a new season based on the games listed in the
	 *                     file. Games are listed in the format line[0] == homeTeam,
	 *                     line[1] == awayTeam.
	 */
	public void newSeason(String filename) throws IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader(filename));
		String row;
		setSeasonStats();
		while ((row = csvReader.readLine()) != null) {
			String[] data = row.split(",");
			Game currGame = new Game(teamIDs.get(data[1]), teamIDs.get(data[0]));

		}
		csvReader.close();
		String standings = printStandings();
		System.out.println(standings);
	}
	
	public String printStandings() {
		String toR = "STANDINGS\n\nEASTERN CONFERENCE\n\n";
		sortConferences();
		for(int i = 0; i < 15; i++) {
			String seed = String.valueOf(i + 1);
			if (EasternConference[i].teamName.length() < 5 && 
					i < 9){
			toR = toR + seed + ". " + EasternConference[i].teamName + "\t\t\t\t" + EasternConference[i].wins +
					"\t\t" + EasternConference[i].losses + "\n";
				} else {
			toR = toR + seed + ". " + EasternConference[i].teamName + "\t\t\t" + EasternConference[i].wins +
					"\t\t" + EasternConference[i].losses + "\n";
				}
			}
		toR = toR + "\nWESTERN CONFERENCE\n\n";
		for(int i = 0; i < 15; i++) {
			String seed = String.valueOf(i + 1);
			if (WesternConference[i].teamName.length() < 5 && i < 9){
				toR = toR + seed + ". " + WesternConference[i].teamName + "\t\t\t\t" + WesternConference[i].wins +
						"\t\t" + WesternConference[i].losses + "\n";
					} else if (WesternConference[i].teamName.length() > 11) {
						toR = toR + seed + ". " + WesternConference[i].teamName + "\t\t" + WesternConference[i].wins +
								"\t\t" + WesternConference[i].losses + "\n";
					}
			else {
				toR = toR + seed + ". " + WesternConference[i].teamName + "\t\t\t" + WesternConference[i].wins +
						"\t\t" + WesternConference[i].losses + "\n";
					}
		}
		return toR;
	}
	boolean eastSorted = false;
	public void sortConferences() {
		if (eastSorted == false) {
		for(int i = 0; i < 14; i++) {
			int j = i;
			if(j < 14 && EasternConference[j].wins < EasternConference[j+1].wins) {
				while(j < 14 && EasternConference[j].wins < EasternConference[j+1].wins) {
					Team temp = EasternConference[j];
					EasternConference[j] = EasternConference[j + 1];
					EasternConference[j + 1] = temp;
					j++;
				} sortConferences();
			}
		} eastSorted = true;
		}
		
		for(int i = 0; i < 14; i++) {
			int j = i;
			if(j < 14 && WesternConference[j].wins < WesternConference[j+1].wins) {
				while(j < 14 && WesternConference[j].wins < WesternConference[j+1].wins) {
					Team temp = WesternConference[j];
					WesternConference[j] = WesternConference[j + 1];
					WesternConference[j + 1] = temp;
					j++;
				} sortConferences();
			}	
		}
	}

}
