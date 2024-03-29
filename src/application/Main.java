package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//instancia o objeto loader para poder manipula-lo antes de carregar
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
		
			//Parent parent = loader.load();
			ScrollPane scrollPane = loader.load();
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			
			mainScene = new Scene(scrollPane);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Sample JavaFX application");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Scene getMainScene() {
		return mainScene;
	}
		
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
