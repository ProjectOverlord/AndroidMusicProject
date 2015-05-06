package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;

/**
 * This class reads and saves the lines of the HTTP request given the
 * clientSocket. It provides tools to interact with tos lines, first of all the
 * retrievement of the URI.
 * 
 * @author claudio
 *
 */
public class HttpRequest {

	private String uri;
	private LinkedList<String> requestLines = new LinkedList<String>();

	public HttpRequest(Socket clientSocket) throws IOException {
		readAndSaveRequestLines(clientSocket);
		findUri();
	}

	/**
	 * All the lines of the client's HTTP request are saved in a List to prevent
	 * information loss
	 * 
	 * @param clientSocket
	 *            The socket reserved to the client
	 * @throws IOException
	 *             if an error occurs while reading the HTTP request
	 */
	private void readAndSaveRequestLines(Socket clientSocket)
			throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));

		String line = in.readLine();
		this.requestLines.add(line);

		while (line != null) {
			line = in.readLine();
			this.requestLines.add(line);

			if (line.length() == 0)
				line = null;
		}
	}

	/**
	 * The URI is the second element of the first line of the request.
	 */
	private void findUri() {
		String firstLine = requestLines.getFirst();
		String[] lineElements = firstLine.split(" ");
		String uri = lineElements[1];

		this.uri = uri;
	}

	public String getUri() {
		return uri;
	}

	public LinkedList<String> getRequestLines() {
		return requestLines;
	}

}