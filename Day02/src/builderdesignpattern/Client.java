//$Id$
package builderdesignpattern;

public class Client {
	public static void main(String[] args) {
		Message message = new MessageBuilder().setMessageId(1).setFrom("sukumar").setTo("sharan").setContent("Hi").getMessage();
		message.display();
	}
}
