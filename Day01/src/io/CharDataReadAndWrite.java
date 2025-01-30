//$Id$
package io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharDataReadAndWrite {

	public static void main(String[] args) throws IOException {
		write("welcome to zoho");
		read();
	}

	private static void read() throws IOException {
		FileReader reader = null;

		try {
			reader = new FileReader("file2.txt");
			int temp;
			while ((temp = reader.read()) != -1) {
				System.out.print((char) temp);
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	private static void write(String content) throws IOException {
		FileWriter writer = null;
		try {
			writer = new FileWriter("file2.txt");
			writer.write(content);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				writer.close();
		}
	}

}
