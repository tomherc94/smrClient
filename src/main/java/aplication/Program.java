package aplication;

public class Program {

	public static void main(String[] args) {

		ThreadSendLog sendLog = new ThreadSendLog(args, 10000);
		sendLog.start();

	}

}
