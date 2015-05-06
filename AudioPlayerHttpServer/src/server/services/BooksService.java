package server.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import server.HttpMessage;
import server.HttpRequest;
import server.IService;

public class BooksService implements IService {

	private static final String filename = "web/books02.html";

	public void sendHTTP(final Socket clientSocket, HttpRequest request)
			throws IOException, FileNotFoundException {

		HttpMessage message = new HttpMessage();

		message.openHttpAnswer(clientSocket);
		copyFile(filename, message.getOutputStreamWriter());
		message.closeHttpAnswer();
	}

	public void copyFile(String filename, OutputStreamWriter out)
			throws FileNotFoundException, IOException {
		BufferedReader fileReader = new BufferedReader(new FileReader(filename));
		String fileLine = fileReader.readLine();
		while (fileLine != null) {
			if (fileLine.trim().equals("$LIBRERIA_LIBRI$")) {

				out.write("<div style=\"background-color:#aaa;width:400px;margin:30px;\">");
				out.write("<h2 style=\"background-color:#ddd;\">Harry Potter</h2>");
				out.write("<b>J K. Rowling</b> <br/>");
				out.write("Prezzo : 29.99 <br/>");
				out.write("Anno : 2005 <br/>");
				out.write("</div>");

			} else {
				out.write(fileLine + "\n");
			}
			fileLine = fileReader.readLine();
		}
		fileReader.close();
	}
}
