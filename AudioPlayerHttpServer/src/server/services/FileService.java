package server.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import server.ContentType;
import server.HttpMessage;
import server.HttpRequest;
import server.IService;

/**
 * Among all the possible IServices, FileService is the one that handle the copy
 * of a file to the OutputStream.
 * 
 * @author claudio
 *
 */
public class FileService implements IService {

	public void sendHTTP(Socket clientSocket, HttpRequest request)
			throws IOException, FileNotFoundException {

		String filename = checkURI(request.getUri());
		HttpMessage message = new HttpMessage();

		// TODO: This clearly isn't a versatile way to handle the various
		// formats. Must generalize for many (interface..?)
		if (filename.endsWith("xml")) {
			message.setContentType(ContentType.XML);
		}

		// Answers the HTTP request sending the requested file
		message.openHttpAnswer(clientSocket);
		copyFile(filename, message.getOutputStreamWriter());
		message.closeHttpAnswer();
	}

	/**
	 * Copies the requested file on the output stream.
	 * 
	 * @param filename
	 *            The name of the requested file
	 * @param out
	 *            The OutputStreamWriter
	 * @throws FileNotFoundException
	 *             When the FileReader cannot find a file corresponding to
	 *             filename
	 * @throws IOException
	 *             When something goes wrong while reading from file or writing
	 *             to the OutputStream
	 */
	private void copyFile(String filename, OutputStreamWriter out)
			throws FileNotFoundException, IOException {

		BufferedReader fileReader = new BufferedReader(new FileReader(filename));
		String fileLine = fileReader.readLine();
		while (fileLine != null) {
			out.write(fileLine + "\n");
			fileLine = fileReader.readLine();
		}
		fileReader.close();
	}

	/**
	 * Controlla se il file è presente nella cartella del file, altrimenti
	 * restituisce error.html
	 * 
	 * @param uri
	 *            The URI of the requested file, relative to the "web" folder of
	 *            this project
	 * @return the String filename, the path of the file including "web/"
	 */
	private String checkURI(String uri) {
		String filename = "web" + uri;
		File file = new File(filename);
		if (!file.exists()) {
			filename = "web/error.html";
		}
		return filename;
	}
}
