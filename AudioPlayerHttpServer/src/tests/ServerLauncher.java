package tests;

import server.Server;

/**
 * This class is a test for the server model,
 * as well as the launcher.
 * 
 * Original code of the whole project by Alessandro Martinelli.
 * 
 * @author team
 *
 */
public class ServerLauncher {

	public static final int PORT = 8080;

	public static void main(String[] args) {
		Server server = new Server(PORT);
		server.launch();
	}
}
