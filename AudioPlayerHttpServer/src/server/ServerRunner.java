package server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import server.services.FileService;

/**
 * This class is an implementation of the Runnable interface.
 * The run() method is used to start a thread capable of listening for
 * HTTP requests and answer them.
 * 
 * @author team
 *
 */
public class ServerRunner implements Runnable {

	private Socket clientSocket = null;
	private HashMap<String, IService> services;

	public ServerRunner(Socket clientSocket, HashMap<String, IService> services) {
		if (clientSocket != null)
			this.clientSocket = clientSocket;
		else
			System.err.println("clientSocket NULL! C'è un problema.");

		if (services != null)
			this.services = services;
		else
			System.err.println("services punta a NULL.");
	}

	@Override
	public void run() {

		try {

			/*
			 * The thread keeps waiting for an HTTP request. When this arrives,
			 * it "unlocks" this instruction. At this point all the lines
			 * from the HTTP request are saved.
			 */
			HttpRequest request = new HttpRequest(clientSocket);

			/* A titolo di DEBUG stampiamo l'URI richiesto dal client */
			System.err.println("URI richiesto:\t" + request.getUri());

			/*
			 * According to the Strategy DP, the server 
			 * has to be as generic as possible, so it depends
			 * only from FileService, which is the default.
			 */
			IService service = services.get(request.getUri());
			if (service == null) { // DEFAULT
				service = new FileService();
			}

			service.sendHTTP(clientSocket, request);

			clientSocket.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
