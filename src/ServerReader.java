
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerReader implements Runnable{
	Thread serverReaderThread;
	DatagramSocket ds;
	byte[] buf;
	protected ServerReader(){
		serverReaderThread = new Thread(this);
		serverReaderThread.start();
		configure();
	}
	void configure(){
		try{
		   	ds = new DatagramSocket(2000);  
		    buf = new byte[1024];  
		}catch(Exception e){ }
	}
	public void run(){
		while(Server.isRunning){
			try{
				readFromClient();
				Thread.sleep(1);
			}catch(Exception e) { }
		}
	}
	void readFromClient(){
		try{
		    DatagramPacket dp = new DatagramPacket(buf, 1024);  
		    ds.receive(dp);  
		    String str = new String(dp.getData(), 0, dp.getLength());  
		    //System.out.println(str); 
		    Server.num ++;
		}catch(Exception e){ }
	}
}	
