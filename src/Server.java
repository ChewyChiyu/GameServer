import java.util.ArrayList;

public class Server {
	protected static boolean isRunning = true;
	protected static ArrayList<User> players = new ArrayList<User>();
	protected Server(){
		new ServerSender();
		new ServerReader();
	}
}
