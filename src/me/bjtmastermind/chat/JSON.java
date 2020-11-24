package me.bjtmastermind.chat;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSON {	
	@SuppressWarnings("unchecked")
	public static JSONObject toJSON(String label, String value) {
		JSONObject jo = new JSONObject();
		jo.put(label, value);
		return jo;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject toDoubleJSON(String label, String value, String label1, String value1) {
		JSONObject jo = new JSONObject();
		jo.put(label, value);
		jo.put(label1, value1);
		return jo;
	}
	
	@SuppressWarnings("unchecked")
	public static String[] getDoubleJSON(String s) {
		JSONParser jp = new JSONParser();
		JSONObject jo;
		String name, msg;
		try {
			jo = (JSONObject) jp.parse(s);
			name = jo.get("username").toString();
			msg = jo.get("message").toString();
		} catch(ParseException e) {
			jo = new JSONObject();
			jo.put("username", "Unknown");
			jo.put("message", s);
			name = jo.get("username").toString();
			msg = jo.get("message").toString();
		}
		String[] so = {name, msg};
		return so;
	}
}
