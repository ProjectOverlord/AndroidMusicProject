package server.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import server.ContentType;
import server.HttpMessage;
import server.HttpRequest;
import server.IService;

/**
 * Among all the possible IServices, FileService is the one that handles the
 * copy of a file to the OutputStream. This is the DEFAULT service and sends
 * files to the client as specified with the URI.
 * 
 * @author team
 *
 */
public class FileService implements IService {

	public void sendHTTP(Socket clientSocket, HttpRequest request)
			throws IOException {

		String filename = checkURI(request.getUri());
		HttpMessage message = new HttpMessage();

		// This isn't very nice, but it works.
		if (filename.endsWith("mp3")) {
			message.setContentType(ContentType.MP3);
		} else
			if (filename.endsWith("txt"))
				message.setContentType(ContentType.TXT);
		

		// Answers the HTTP request sending the requested file
		message.openHttpAnswer(clientSocket);
		
		if (filename.endsWith("txt")) {
			copyTextFile(filename, message.getOutputStreamWriter());
		}
		else
			copyBinaryFile(filename, clientSocket.getOutputStream());
		message.closeHttpAnswer();
	}

	/**
	 * Copies the requested file on the output stream. 
	 * To be used for text files.
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
	private void copyTextFile(String filename, OutputStreamWriter out)
			throws FileNotFoundException, IOException {

		System.err.println("FILENAME: "+filename);

		BufferedReader fileReader = new BufferedReader(new FileReader(filename));
		String fileLine = fileReader.readLine();
		System.err.println("FILELINE: "+fileLine);

		while (fileLine != null) {
			out.write(fileLine + "\n");
			fileLine = fileReader.readLine();
		}
		fileReader.close();
	}

	/**
	 * Copies the requested file on the output stream. 
	 * To be used for binary files.
	 * 
	 * @param filename
	 *            The name of the requested file
	 * @param stream
	 *            The output stream
	 * @throws FileNotFoundException
	 *             When the FileReader cannot find a file corresponding to
	 *             filename.
	 * @throws IOException
	 *             When something goes wrong while reading from file or writing
	 *             to the OutputStream.
	 * 
	 */
	private void copyBinaryFile(String filename, OutputStream stream)
			throws IOException, FileNotFoundException {

		InputStream inputStream = new FileInputStream(filename);

		byte[] bytesAlreadyRead = new byte[102400];
		int bread = inputStream.read(bytesAlreadyRead);
		while (bread != -1) {
			stream.write(bytesAlreadyRead);
			bread = inputStream.read(bytesAlreadyRead);
		}
		stream.close();
		inputStream.close();

	}

	/**
	 * Checks if the file is in its folder, otherwise returns error.html
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