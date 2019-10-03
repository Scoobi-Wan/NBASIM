import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class LakersSunsTest {

	@Test
	void test1() throws IOException{ 

		League nba1 = new League();
		nba1.readTeamFile("/Users/paul/Desktop/paulrepos/NBASIM/TEAMSHEET - Sheet1.csv");
		
		nba1.newSeason("/Users/paul/Desktop/paulrepos/NBASIM/schedule.csv");
	}

}
