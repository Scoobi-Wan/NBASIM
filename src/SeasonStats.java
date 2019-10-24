import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class SeasonStats {
	Map<String, int[]> statsMap;
	Player[] teamRoster;
	int games;
	double ppg;
	double rpg;
	double fgPer;
	double fg3Per;
	Team t1;
	int totalPTS = 0;
	double teampPPG;
	
	
	public SeasonStats(Team team) {
	games = 0;
	t1 = team;
	teamRoster = team.roster;		
	statsMap = new HashMap<String, int[]>();
	for(int i = 0; i < team.rosterSize; i++) {
		int[] dbA = new int[] {0,0,0,0,0}; // FGM, FGA, 3FGM, 3FGA, REB
		statsMap.put(teamRoster[i].asString(), dbA);
		}
	
	}
	
	public void calcPlayerStats(Player p) {
		double pts = ((statsMap.get(p.asString())[0] - statsMap.get(p.asString())[2]) * 2) + (statsMap.get(p.asString())[2] * 3);
		ppg = (float)pts * 10 / games;
		DecimalFormat df = new DecimalFormat("#.#");
		String ppgStr = df.format(ppg);
		ppg = Double.valueOf(ppgStr);
		p.ppg = ppg;
		rpg = (float) 10* statsMap.get(p.asString())[4] / games;
		String rpgStr = df.format(rpg);
		rpg = Double.valueOf(rpgStr);
		p.rpg = rpg;
		df = new DecimalFormat("#.##");
		if(statsMap.get(p.asString())[1] != 0) {
		fgPer = (float) statsMap.get(p.asString())[0] / statsMap.get(p.asString())[1];
		String fgPStr = df.format(fgPer);
		fgPer = Double.valueOf(fgPStr);
		} else {
			fgPer = 0.0;
		}
		if(statsMap.get(p.asString())[3] != 0) {
		fg3Per = (float)statsMap.get(p.asString())[2] / statsMap.get(p.asString())[3];
		String fg3PStr = df.format(fg3Per);
		fg3Per = Double.valueOf(fg3PStr);
		} else {
			fg3Per = 0.0;
		}
		
	}
	
	
	
	public String printStats() {
		
		String top = t1.getName().toUpperCase() + " (" + t1.wins + " - " + t1.losses + ")\n\n";
		top = top + "NAME\t\t\t\tPPG\tRPG\tFG%\t3FG%\n";
		for(int i = 0; i < teamRoster.length; i++) {
			String selected = teamRoster[i].asString();
			calcPlayerStats(teamRoster[i]);
			if (selected.length() > 23) {
				top = top + selected + "\t" + ppg + "\t" + rpg + "\t" + 
						fgPer + "\t" + fg3Per + "\n";
			} else if (selected.length() > 15 && selected.length() < 24) {
				top = top + selected + "\t\t" + ppg + "\t" + rpg + "\t" + 
						fgPer + "\t" + fg3Per + "\n";
			} else if (selected.length() < 8) {
				top = top + selected + "\t\t\t\t" + ppg + "\t" + rpg + "\t" + 
						fgPer + "\t" + fg3Per + "\n";
			}
			
			else {
				top = top + selected + "\t\t\t" + ppg + "\t" + rpg + "\t" + 
						fgPer + "\t" + fg3Per + "\n";
			}		
		}
		return top;

	}
	
	public String LeagueLeadersPoints(int n) {
		// n is used to print the top N PPG leaders
		return "nothun";
	}
		
	
	
}

