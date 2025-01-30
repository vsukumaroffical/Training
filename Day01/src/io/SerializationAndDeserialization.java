//$Id$
package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationAndDeserialization {
	public static void main(String[] args) {
		User user = new User("sukumar", "22/03/2001", "suku@gmail.com", "suku@123");
		user.print();
		FileOutputStream fileOutStream = null;
		ObjectOutputStream objectOutStream = null;
		FileInputStream fileInStream = null;
		ObjectInputStream objectInStream = null;

		try {
			fileOutStream = new FileOutputStream("C:/Users/test/Desktop/file5.txt");
			objectOutStream = new ObjectOutputStream(fileOutStream);

			objectOutStream.writeObject(user);
			objectOutStream.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileOutStream != null)
					fileOutStream.close();

				if (objectOutStream != null)
					objectOutStream.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			fileInStream = new FileInputStream("C:/Users/test/Desktop/file5.txt");
			objectInStream = new ObjectInputStream(fileInStream);
			User u = (User) objectInStream.readObject();
			u.print();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileInStream != null)
					fileInStream.close();

				if (objectInStream != null)
					objectInStream.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}

class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String name;
	String dob;
	String emailId;
	transient String password;

	User(String name, String dob, String emailId, String password) {
		this.name = name;
		this.dob = dob;
		this.emailId = emailId;
		this.password = password;
	}

	public void print() {
		System.out.println(this.name + " " + this.dob + " " + this.emailId + " " + this.password);

	}
}
