package me.bjtmastermind.chat;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		// Load FXML Scene
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Chat.fxml"));
		Parent layout = loader.load();
		Thread rx = new Thread((MainController) loader.getController());
		rx.start();
		Scene mainScene = new Scene(layout);
		primaryStage.setScene(mainScene);
		
		// Create Window
		primaryStage.setTitle("My Chat - The Best Chat App You'll Ever Own!");
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
	}
}
