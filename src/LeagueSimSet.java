import java.io.IOException;

public class LeagueSimSet {
	League[] sims;
	Team[] teams;
	Team[] EC; 
	Team[] WC; 
	boolean eastSorted = false;
	int ECi = 0;
	int WCi = 0;
	public LeagueSimSet(int n) throws IOException {
		sims = new League[n];
		int i = 0;
		while(i < n) {
			League l1 = new League();
			l1.newSeason("/Users/paul/Desktop/paulrepos/NBASIM/scheduleFULL.csv");
			sims[i] = l1;
			i++;
		}
		EC = sims[0].EasternConference;
		WC = sims[0].WesternConference;
		averageWins(n);
		String topPPG = sims[0].leagueStats1.ppgLeaders(20);
		System.out.println(topPPG);
		
	}
	
	public void averageWins(int n) {
		teams = new Team[30];
		for(int i = 0; i < 30; i++) {
			teams[i] = sims[0].teams[i];
		}
		for (int j = 0; j < 30; j++) {
			for (int i = 0; i < n; i++) {
				teams[j].simWins += sims[i].teams[j].wins;
				teams[j].simLosses += sims[i].teams[j].losses;
		}
			teams[j].simAwins = (double) teams[j].simWins / n;
			teams[j].simAlosses = (double) teams[j].simLosses / n;
		
		}
		sortConferences();
		String standings = printStandings();
		System.out.println(standings);
		
	}
		public void sortConferences() {
			if (eastSorted == false) {
			for(int i = 0; i < 14; i++) {
				int j = i;
				if(j < 14 && EC[j].simAwins< EC[j+1].simAwins) {
					while(j < 14 && EC[j].simAwins < EC[j+1].simAwins) {
						Team temp = EC[j];
						EC[j] = EC[j + 1];
						EC[j + 1] = temp;
						j++;
					} sortConferences();
				}
			} eastSorted = true;
			}
			
			for(int i = 0; i < 14; i++) {
				int j = i;
				if(j < 14 && WC[j].simAwins < WC[j+1].simAwins) {
					while(j < 14 && WC[j].simAwins < WC[j+1].simAwins) {
						Team temp = WC[j];
						WC[j] = WC[j + 1];
						WC[j + 1] = temp;
						j++;
					} sortConferences();
				}	
			}
		}
		
		public String printStandings() {
			String toR = "STANDINGS\n\nEASTERN CONFERENCE\n\n";
			sortConferences();
			for(int i = 0; i < 15; i++) {
				String seed = String.valueOf(i + 1);
				if (EC[i].teamName.length() < 5 && 
						i < 9){
				toR = toR + seed + ". " + EC[i].teamName + "\t\t\t\t" + EC[i].simAwins +
						"\t\t" + EC[i].simAlosses + "\n";
					} else {
				toR = toR + seed + ". " + EC[i].teamName + "\t\t\t" + EC[i].simAwins +
						"\t\t" + EC[i].simAlosses + "\n";
					}
				}
			toR = toR + "\nWESTERN CONFERENCE\n\n";
			for(int i = 0; i < 15; i++) {
				String seed = String.valueOf(i + 1);
				if (WC[i].teamName.length() < 5 && i < 9){
					toR = toR + seed + ". " + WC[i].teamName + "\t\t\t\t" + WC[i].simAwins +
							"\t\t" + WC[i].simAlosses + "\n";
						} else if (WC[i].teamName.length() > 11) {
							toR = toR + seed + ". " + WC[i].teamName + "\t\t" + WC[i].simAwins +
									"\t\t" + WC[i].simAlosses + "\n";
						}
				else {
					toR = toR + seed + ". " + WC[i].teamName + "\t\t\t" + WC[i].simAwins +
							"\t\t" + WC[i].simAlosses + "\n";
						}
			}
			return toR;
		}
		
}

