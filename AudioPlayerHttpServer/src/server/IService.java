package server;

import java.io.IOException;
import java.net.Socket;

/**
 * Generic Service.
 * The services may include: sending of html pages, file transfers, 
 * music streaming, login services and so on...
 * The default service is FileService, that sends files
 * (specified with the URI) to the client
 * 
 * @author team
 * 
 */
public interface IService {

	/**
	 * This method must check if the URI for the requested file/service is valid
	 * and then answers the HTTP request sending an HttpMessage (header and
	 * closing included) with the appropriate content. If the URI is not
	 * recognized, it readdresses to the default page.
	 */
	public void sendHTTP(Socket clientSocket, HttpRequest request)
			throws IOException;

}
