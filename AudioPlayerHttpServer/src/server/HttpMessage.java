package server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * This class is the one in charge of opening and closing the HTTP messages.
 * Calls of 'openHttpAnswer' must always be followed by calls to
 * 'closeHttpAnswer'
 * 
 * @author claudio
 * 
 */
public class HttpMessage {

	private ContentType contentType = ContentType.HTML; // Default content
	private OutputStreamWriter out;

	/**
	 * Prints the header of the HTTP answer on the OutputStream defined by the
	 * clientSocket. The contentType varies accordingly to the client's request
	 * and avilability of the type in the enumeration
	 * 
	 * @param clientSocket
	 *            the socket reserved for the client connection
	 * @throws IOException
	 *             when unable to write on the OutputStream
	 */
	public void openHttpAnswer(Socket clientSocket) throws IOException {

		this.out = new OutputStreamWriter(clientSocket.getOutputStream(),
				Charset.forName("UTF-8").newEncoder());
		out.write("HTTP/1.1 200 OK\n");
		out.write("Date: " + (new Date()).toString() + "\n");
		out.write("Content-Type: " + contentType.getText()
				+ "; charset=utf-8\n");
		out.write("\n");

	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	public void closeHttpAnswer() throws IOException {
		out.write("\n");
		out.close();
	}

	public OutputStreamWriter getOutputStreamWriter() {
		return out;
	}

}
