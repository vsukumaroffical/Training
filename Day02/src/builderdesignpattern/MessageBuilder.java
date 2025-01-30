//$Id$
package builderdesignpattern;

public class MessageBuilder {
	
	private int messageId;
	private String from;
	private String to;
	private String content;
	private String timeStamp;
	
	public MessageBuilder setMessageId(int messageId) {
		this.messageId = messageId;
		return this;
	}
	public MessageBuilder setFrom(String from) {
		this.from = from;
		return this;
	}
	public MessageBuilder setTo(String to) {
		this.to = to;
		return this;
	}
	public MessageBuilder setContent(String content) {
		this.content = content;
		return this;
	}
	public MessageBuilder setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}
	
	public Message getMessage() {
		return new Message(messageId,from,to,content,timeStamp);
	}
}
