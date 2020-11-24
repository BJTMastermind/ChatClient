package me.bjtmastermind.chat;

public class Helper {
	
	public static byte[] toBytes(String message, int bufferSize) {
		byte[] buffer = message.getBytes();
        byte[] newBuffer = new byte[bufferSize];
        for(int i = 0; i < Math.min(buffer.length, bufferSize); i++) {
            newBuffer[i] = buffer[i];
        }
        return newBuffer;
    }
}
