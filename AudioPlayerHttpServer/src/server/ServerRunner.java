package server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import server.services.FileService;

/**
 * This class is an implementation of the Runnable interface.
 * The run() method can be used to start a thread capable of listening for
 * HTTP requests and answer them.
 * 
 * @author claudio
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
			 * Il thread resta in attesa di una richiesta HTTP. Quando arriva,
			 * "sblocca" questa istruzione. A questo punto vengono salvate tutte
			 * le linee della richiesta Http.
			 */
			HttpRequest request = new HttpRequest(clientSocket);

			/* A titolo di DEBUG stampiamo l'URI richiesto dal client */
			System.err.println("URI richiesto:\t" + request.getUri());

			/*
			 * Il server deve essere generico, dunque deve dipendere solo dalla
			 * classe FileService, che è il default. Questo è il design Pattern
			 * STRATEGY.
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
