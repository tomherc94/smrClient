package aplication;

import model.services.ThreadSendLog;

public class Program {
	
	public static void main(String[] args) {

		System.out.println(args);
		
		ThreadSendLog sendLog = new ThreadSendLog(args, 10000L, "127.0.0.1");
		sendLog.start();
		
		

	}

}
