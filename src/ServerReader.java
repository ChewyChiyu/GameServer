
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerReader implements Runnable{
	private Thread serverReaderThread;
	private DatagramSocket ds;
	private byte[] buf;
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
			/*
			 * The standard port sent from the user should be 5 characters long ( will be what separates each player apart)
			 * If the Port is not found within list of players a new player with that port is created
			 * If the server receives a package from said user the boolean connected will be set to true
			 * All players that are not connected will be kicked and removed from the player list
			 * 
			 * 
			 */
			
		    DatagramPacket dp = new DatagramPacket(buf, 1024);  
		    ds.receive(dp);  
		    String str = new String(dp.getData(), 0, dp.getLength()); 
		    int PORT = Integer.parseInt(str.substring(0,5)); 
		    if(!contains(PORT)){ 
		    	Server.players.add(new User(PORT));
		    }
		
		    playerIdle(PORT);
		    	
		    String info = str.substring(5);
		    process(PORT,info);
		    
		    
		    
		    
		    
		    
		}catch(Exception e){ }
	}
	void process(int PORT, String info){
		//singles out individual user by port and does actions to it with the given info
		for(int index = 0; index < Server.players.size(); index++){
			User u = Server.players.get(index);
			if(u.getPORT()==PORT){
				//singled out user
				//each user has its own specific game object
				int newX = Integer.parseInt(info.substring(0,info.indexOf(".")));
				int newY = Integer.parseInt(info.substring(info.indexOf(".")+1));
				//moves game object based on its xVelo and yVelo
				u.getObject().setX(newX);
				u.getObject().setY(newY);
				
				//System.out.println(u.getPORT() + " newX " + newX + " newY " + newY);
			}
		}
		
		
	}
	boolean contains(int PORT){
		for(int index = 0; index < Server.players.size(); index++){
			User u = Server.players.get(index);
			if(u.getPORT()==PORT){
				return true;
			}
		}
		System.out.println("new PORT Found");
		return false;
	}
	void playerIdle(int PORT){
		for(int index = 0; index < Server.players.size(); index++){
			User u = Server.players.get(index);
			if(u.getPORT()==PORT){
				u.isConnected(); //sets player connect to true if packages continue being sent
			}
			if(u.shouldKick()){
				Server.players.remove(u); //removes a player if should kick
				System.out.println("removed player");
			}
		}
	}
	
}	
