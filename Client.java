package com.turkcell.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	// değişkenler
	private static String receiveMessage;// mesaj almak
	private static String sendMessage; // mesa j göndersin
	
	// twoWayClient
	private static void twoWayClient() {
		FileLog fileLog = new FileLog();
		try {
			Socket socket = new Socket("localhost", 4545);
			
			// Client veri gönderecek
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter printWriter = new PrintWriter(outputStream, true);
			
			// serverdan gelen veriyi almak
			InputStream inputStream = socket.getInputStream();
			BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream));
			System.out.println("Client: Lütfen mesaj yazınız ");
			
			while (true) {
				// clientten veri almak
				sendMessage = bufferedReader.readLine();
				printWriter.println(sendMessage);
				printWriter.flush();
				
				if ((receiveMessage = bufferedReader2.readLine()) != null) {
					System.out.println("SERVER: " + receiveMessage);
					fileLog.logWrite("SERVER: " + receiveMessage);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// PSVM
	public static void main(String[] args) {
		twoWayClient();
	}
}
