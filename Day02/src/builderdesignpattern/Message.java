//$Id$
package builderdesignpattern;

public class Message {
	private int messageId;
	private String from;
	private String to;
	private String content;
	private String timeStamp;
	
	public Message(int messageId,String from,String to,String content,String timeStamp) {
		this.messageId = messageId;
		this.from = from;
		this.to = to;
		this.content = content;
		this.timeStamp = timeStamp;
	}
	
	
	public void  display() {
		System.out.println("Message Id : "+this.messageId);
		System.out.println("From       : "+this.from);
		System.out.println("To         : "+this.to);
		System.out.println("Content    : "+this.content);
		System.out.println("Time Stamp : "+this.timeStamp);
		
	}
}
