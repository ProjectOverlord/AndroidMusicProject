package server;

/**
 * This enum contains all the possible content types for the HTTP response.
 * 
 * @author CL
 *
 */
public enum ContentType {
	TXT("text/plain"),
	HTML("text/html"),
	FLAC("audio/flac"),
	WAV("audio/vnd.wave"),
	MP4("audio/mp4"),
	MP3("audio/mp3"),
	GENERIC_BINARY("application/octet-stream");

	private String text;

	private ContentType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}