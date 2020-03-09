import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class nbaAPP extends Application {
	
	private Label prompt = new Label("Scoobi's Basketball Simulator");
	private Button simSeason = new Button("Sim Season");
	private GridPane pane1 = new GridPane();
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		LeagueSimSet nba1 = new LeagueSimSet(1);
		pane1.add(prompt, 1, 1);
		Scene scene1 = new Scene(pane1, 300, 300);
		primaryStage.setScene(scene1);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
