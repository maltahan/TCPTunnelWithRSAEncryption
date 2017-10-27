
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

public class TCPServer {

	private static ServerSocket welcomeSocket;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		byte[] DecryptedBytes = new byte[1024];
		welcomeSocket = new ServerSocket(6788);

		while (true) {
			Socket connectionSocket = welcomeSocket.accept();

			ObjectInputStream is = new ObjectInputStream(connectionSocket.getInputStream());

			ClientServerInformation info = (ClientServerInformation) is.readObject();

			if (!is.equals(null)) {

				RSA rsa = new RSA(info.e, info.phi, info.N);
				DecryptedBytes = rsa.decrypt(info.encrypted);
				System.out.println("The Recived Encrypted message is \t " + info.encrypted);
				System.out.println("The message has been decrypted to \t (" + new String(DecryptedBytes) + ")");
				try {
					//Send the decrypted fdata to the endpoint
					URL url = new URL("http://localhost:8040/myEndPointApp/rest/recive/post");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "text/html");
					DataOutputStream os = new DataOutputStream(conn.getOutputStream());
					os.write(DecryptedBytes);
					System.out.println("The message has been send to the destionation endpoint http://localhost:8040/myEndPointApp/rest/recive");
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					String output;
					System.out.println("Output from EndPoint Server .... ");
					while ((output = br.readLine()) != null) {
						System.out.println(output);
					}

					conn.disconnect();

				} catch (MalformedURLException e) {

					e.printStackTrace();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}

		}
	}

}
