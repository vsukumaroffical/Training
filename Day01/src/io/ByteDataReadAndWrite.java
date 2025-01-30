package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteDataReadAndWrite {
	public static void main(String[] args) {
		write((byte) 54);
		read();
	}

	private static void write(byte val) {
		FileOutputStream output = null;
		try {
			output = new FileOutputStream("file1.txt");
			output.write(val);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (output != null)
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	private static void read() {
		FileInputStream input = null;

		try {
			FileInputStream in = new FileInputStream("file1.txt");
			int temp;
			while ((temp = in.read()) != -1) {
				System.out.print((char) temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
