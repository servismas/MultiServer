import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer {
	ServerSocket listener = null;
	Socket client = null;
	int maxCount = 10; // max number of clients
	int count = 0; // current client number
	int port = 18888;

	void createConnection() {
		try {
			listener = new ServerSocket(port, maxCount);
			while (count <= maxCount) {
				count++;
				client = listener.accept();
				MyListener ml = new MyListener(client);
				Thread t = new Thread(ml);
				t.setDaemon(true);
				t.start();
			}
		} catch (IOException ex) {
			Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
