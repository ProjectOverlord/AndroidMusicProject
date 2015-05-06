package server;

/**
 * This enum contains all the possible content types for the HTTP response.
 * 
 * @author claudio
 *
 */
public enum ContentType {
	HTML("text/html"),
	XML("text/xml");

	private String text;

	private ContentType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}