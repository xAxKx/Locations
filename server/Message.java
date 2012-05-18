package locations.server;

import java.io.Serializable;

/**
 * Sõnumite klass
 *
 */
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String contents;
	public MessageType type;
	
	public Message(MessageType type) {
		this(type, "");
	}

	public Message(MessageType type, String contents) {
		this.type = type;
		this.contents = contents;
	}
	
	@Override
	public String toString() {
		return "Message type: " + type + ", contents: " + contents;
	}
}
