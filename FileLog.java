package com.turkcell.socket;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileLog {
	
	private String path = "C:\\turkcell\\log.txt";
	private File file = new File(path);
	
	public void logWrite(String message) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true))) {
			bufferedWriter.write(createdDate() + " " + message);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String createdDate() {
		Locale locale = new Locale("tr", "TR");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss", locale);
		return dateFormat.format(new Date());
	}
}