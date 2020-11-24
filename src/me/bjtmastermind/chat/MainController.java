package me.bjtmastermind.chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.simple.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MainController implements Runnable {
	static String LOCALIP = "localhost";
	static int LOCALPORT = 6000;
	static int MSG_SIZE = 100;
	static String name;
	@FXML 
	TextArea chatArea;
	static Socket client;
	static TextField textArea;
	static DataOutputStream dos;
	
	public MainController() throws UnknownHostException, IOException {
		client = new Socket(LOCALIP, LOCALPORT);
	}
	
	public static void setName(String name) {
		MainController.name = name;
	}
	
	public void sendMessage(ActionEvent event) throws IOException {
		textArea = ((TextField) event.getSource());
		byte[] b;
		dos = new DataOutputStream(client.getOutputStream());
		
		String input = textArea.getText();
		
		if(!input.isEmpty()) {
			JSONObject jo = JSON.toDoubleJSON("username", name, "message", input);
			
			b = Helper.toBytes(jo.toJSONString(), MSG_SIZE);
			
			dos.write(b);
			dos.flush();
			
			textArea.setText("");
		}        
	}

	public void run() {
		try {
			InputStream is = client.getInputStream();
			byte[] buffer = new byte[MSG_SIZE];
			int read = 0;
			String[] so; 
			while((read = is.read(buffer)) != -1) {
				so = JSON.getDoubleJSON(new String(buffer).trim());
				String name = so[0];
				String msg = so[1];				
				chatArea.appendText(name + ": " + msg + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
