import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerSender implements Runnable{
	protected Thread serverSenderThread;
	protected  DatagramSocket ds;
	protected  InetAddress ip;
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
			String str = ""+ Server.num;
			for(int index = 32700; index <= 61000; index++){
			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, index);  
			ds.send(dp); 
			}
		}catch(Exception e) { }
	}
}
