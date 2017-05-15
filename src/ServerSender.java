import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerSender implements Runnable{
	private Thread serverSenderThread;
	private  DatagramSocket ds;
	private  InetAddress ip;
	protected ServerSender(){
		serverSenderThread = new Thread(this);
		configure();
		serverSenderThread.start();
	}
	void configure(){
		try{
			ds = new DatagramSocket();  
			ip = InetAddress.getByName("127.0.0.1");  
		}catch(Exception e) { }
	}
	public void run(){
		while(Server.isRunning){
			try{
				send();
				Thread.sleep(1);
			}catch(Exception e) { }
		}
	}
	void send(){
		try{
			/*
			 * Goes through the list of players and send out packages with the port of each User
			 *  
			 */
			for(int index = 0 ;index < Server.players.size(); index++){
				//User being send the info
				User u = Server.players.get(index);
				//packaging all server server info
				StringBuilder str = new StringBuilder();
				for(int index2 = 0; index2 < Server.players.size(); index2++){
					/*
					 * All users in the player list 
					 */
					User subU = Server.players.get(index2);
					str.append(""+subU.getObject().getX() +"."+ subU.getObject().getY() + "|");

				}
				String str2 = str.toString();
				
				DatagramPacket dp = new DatagramPacket(str2.getBytes(), str2.length(), ip, u.getPORT());  
				ds.send(dp); 
			}
		}catch(Exception e) { }
	}
}
