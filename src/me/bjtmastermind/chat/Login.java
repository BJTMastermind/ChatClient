package me.bjtmastermind.chat;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Application {
	@FXML
	TextField nameArea;
	@FXML
	Button continueBtn;
	@FXML
	Label errorText;
	static Stage primaryStage;
	static Scene mainScene;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Login.primaryStage = primaryStage;
		// Load FXML Scene
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Chat.fxml"));
		Parent layout = loader.load();
		Parent layout1 = loader1.load();
		Scene loginScene = new Scene(layout);
		mainScene = new Scene(layout1);
		Thread rx = new Thread((MainController) loader1.getController());
		rx.start();
		Platform.setImplicitExit(false);
		primaryStage.setScene(loginScene);
				
		// Create Window
		primaryStage.setTitle("My Chat - Login Screen");
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
	}
	
	public void login(ActionEvent event) {
		if(nameArea.getText().length() != 0) {
			continueBtn = ((Button) event.getSource());
			String name = nameArea.getText();
			MainController.setName(name);
			primaryStage.setScene(mainScene);
			primaryStage.show();
			primaryStage.setTitle("My Chat - The Best Chat App You'll Ever Own!");
		} else {
			errorText.setText("You Must Enter A Username To Contiune");
		}
	}
	
	public void checkLen(KeyEvent event) {
		int maxLen = 16;
		nameArea = ((TextField) event.getSource());
		nameArea.setOnKeyTyped(k -> {
			if(nameArea.getText().length() > maxLen) {
				int pos = nameArea.getCaretPosition();
				nameArea.setText(nameArea.getText(0, maxLen));
				nameArea.positionCaret(pos);
			}
		});
	}
}
