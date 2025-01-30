//$Id$
package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SearchFile {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number(1-8)");
		int selectedLine = scanner.nextInt();

		FileReader fileReader = null;
		BufferedReader bufferReader = null;
		
		String line = "";
		int count = 0;
		try {
			fileReader = new FileReader("file4.txt");
			bufferReader = new BufferedReader(fileReader);

			while ((line = bufferReader.readLine()) != null) {
				if (count == selectedLine - 1) {
					System.err.println(line);
					break;
				} else {
					count++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (bufferReader != null) {
				try {
					bufferReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			scanner.close();
		}
	}
}
