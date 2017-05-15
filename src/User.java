
public class User {
	private int PORT;
	private boolean connected;
	private boolean shouldKick;
	private GameObject o;
	protected User(int PORT){
		this.PORT = PORT;
		connected = true;
		shouldKick = false;
		o = new GameObject(500,500); // initial pos of 500 500
		/*
		 * Internal thread kick system
		 * If the user is connected this thread will set the connection to false, leaving 1 second
		 * for the user to send a package to renew the connection. If there is no renewal of the connection 
		 * the user removes itself from the server player list
		 */
		Thread internal = new Thread(new Runnable(){
			public void run(){
				while(Server.isRunning){
					 	
						connected = false;
						try{
							Thread.sleep(1000); 
						}catch(Exception e) { }
						
						if(!connected){
							shouldKick = true;
						}
					
				}
			}
		});
		internal.start();		
		
	}
	GameObject getObject(){
		return o;
	}
	void isConnected(){
		connected = true;
	}
	boolean shouldKick(){
		return shouldKick;
	}
	int getPORT(){
		return PORT;
	}
}
