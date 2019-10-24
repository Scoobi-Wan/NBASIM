import java.text.DecimalFormat;

public class LeagueStats {
	Player[] allPlayers;
	Player[] pointsAllPlayers;
	public LeagueStats(Team[] teamArr1) {
		allPlayers = new Player[360];
		int apINDEX = 0;
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 12; j++) {
				teamArr1[i].sStats.calcPlayerStats(teamArr1[i].roster[j]);
				allPlayers[apINDEX] = teamArr1[i].roster[j];
				
				apINDEX++;
			}
		}
		//for(int k = 0; k < allPlayers.length; k++) {
			//System.out.println(allPlayers[k].asString());
		//}	
	}
	
	
	public String ppgLeaders(int n) {
		Player[] pointsNLeaders = new Player[n];
		pointsAllPlayers = new Player[360];
		for(int k = 0; k < allPlayers.length; k++) {
			pointsAllPlayers[k] = allPlayers[k];
		}	
		
		
		sortPPG();
		for(int i = 0; i < n; i++) {
			pointsNLeaders[i] = pointsAllPlayers[i];
		}
		return stringPPGLeaders(pointsNLeaders);
	}
	
	public String stringPPGLeaders(Player[] ppgL) {
		String toR = "";
		for(int i = 0; i < ppgL.length; i ++) {
			if (ppgL[i].asString().length() > 15) {
			toR = toR + ppgL[i].asString() + "\t" + ppgL[i].team + "\t\t" + ppgL[i].ppg + "\n";
		} else {
			toR = toR + ppgL[i].asString() + "\t\t" + ppgL[i].team + "\t\t" + ppgL[i].ppg + "\n";

		}
		}return toR;
	}
	
	public void sortPPG() {
		for(int i = 0; i < pointsAllPlayers.length - 1; i++) {
			if ((pointsAllPlayers[i].ppg < pointsAllPlayers[i + 1].ppg)) {
				int j = i;
				while (j < pointsAllPlayers.length - 1 && 
						pointsAllPlayers[j].ppg < pointsAllPlayers[j + 1].ppg) {
					Player temp = pointsAllPlayers[j];
					pointsAllPlayers[j] = pointsAllPlayers[j + 1];
					pointsAllPlayers[j + 1] = temp;
					j++;
				}
				sortPPG();
			} 
	}

	}
	}
