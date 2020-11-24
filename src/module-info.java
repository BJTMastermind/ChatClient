/**
 * @author BJTMastermind
 */
module Chat {
	exports me.bjtmastermind.chat;

	requires java.instrument;
	requires java.management;
	requires java.net.http;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires json.simple;
	
	opens me.bjtmastermind.chat;
}