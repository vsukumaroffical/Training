package io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataClone {
	public static void main(String[] args) {
		FileReader reader = null;
		String sentence = "";
		try {
			reader = new FileReader("file2.txt");

			int temp;
			while ((temp = reader.read()) != -1) {
				sentence += (char) temp;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		FileWriter writer = null;
		try {
			writer = new FileWriter("file3.txt");
			writer.write(sentence);
			writer.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}
}
