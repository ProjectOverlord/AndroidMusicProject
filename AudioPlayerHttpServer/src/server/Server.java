package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * This is the central model class for the server.
 * To launch the server in any other class, there must be a call to the launch() method.
 * 
 * @author claudio
 *
 */
public class Server {

	private HashMap<String, IService> services = new HashMap<String, IService>();
	private int port;

	public Server(int port) {
		this.port = port;
	}

	/**
	 * This is the method effectively launching the server. It uses both the
	 * port number (to start the service) and the services map, to correctly
	 * answer the client
	 * 
	 * @author claudio
	 */
	public void launch() {
		try {
			startThreadFromSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void startThreadFromSocket() throws IOException {

		ServerSocket socket = new ServerSocket(port);
		Socket clientSocket;
		while (true) {
			clientSocket = socket.accept();
			Runnable runnable = new ServerRunner(clientSocket, services);
			Thread thread = new Thread(runnable);
			thread.start();
			// socket.close();
			// TODO: Come mai la socket non va chiusa? Perché interromperei le
			// il thread. Probabilmente bisogna chiuderla nel thread.
		}

	}

	/**
	 * Adds another service to the services map
	 * 
	 * @param name
	 * @param service
	 * 
	 * @author claudio
	 */
	public void addService(String name, IService service) {
		services.put(name, service);
	}
}
