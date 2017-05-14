
public class Server {
	protected static boolean isRunning = true;
	protected static int num = 0;
	protected Server(){
		new ServerSender();
		new ServerReader();
	}
}
