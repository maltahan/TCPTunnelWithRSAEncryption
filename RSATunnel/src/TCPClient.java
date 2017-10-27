import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPClient {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		while (true) {
			RSA rsa = new RSA();
			// Reciving the message from the browser.
			String clientSentenceFromBrowser;
			ServerSocket welcomeSocket = new ServerSocket(8050);
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			clientSentenceFromBrowser = inFromClient.readLine();
			System.out.println("Message Received: " + clientSentenceFromBrowser);
			welcomeSocket.close();
			// Send the message to the Server.
			Socket clientSocket = new Socket("localhost", 6788);
			ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
			byte[] encrypted = rsa.encrypt(clientSentenceFromBrowser.getBytes());
			System.out.println("The message Has been encrypted to ("+encrypted+ ") and send to the server");
			//Initilaze the object to be send to the server
			ClientServerInformation info = new ClientServerInformation(encrypted, RSA.e, RSA.N, RSA.phi);
			outToServer.writeObject(info);
			clientSocket.close();
		}

	}
}
