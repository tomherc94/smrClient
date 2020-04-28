package aplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import org.hyperic.sigar.shell.ShellCommandExecException;
import org.hyperic.sigar.shell.ShellCommandUsageException;

public class ThreadSendLog extends Thread {

	private String[] args;
	private Integer timeout;
	private Socket client;

	public ThreadSendLog() {

	}

	public ThreadSendLog(String[] args, Integer timeout) {

		this.args = args;
		this.timeout = timeout;
	}

	@Override
	public void run() {
		// Socket client = null;
		System.out.println("O cliente se conectou ao servidor!");

		try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
			client = new Socket(InetAddress.getLocalHost().getHostAddress(), 12345);
			do {
				SysInfo sys = new SysInfo();
				sys.processCommand(args);

				

				File f = new File("logs\\logCurrent.csv");
				FileInputStream in = new FileInputStream(f);
				
				
				OutputStream out = client.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(out);
				BufferedWriter writer = new BufferedWriter(osw);
				writer.write(f.getName() + "\n");
				writer.flush();
				int tamanho = 1024; // buffer de 4KB
				byte[] buffer = new byte[tamanho];
				int lidos = -1;
				//while ((lidos = in.read(buffer, 0, tamanho)) != -1) {
				lidos = in.read(buffer, 0, tamanho);
				out.write(buffer, 0, lidos);
				//}
				Thread.sleep(timeout);
				in.close();
				//out.close();
				//client.close();
			}while (client.isConnected());
			this.client.close();
		} catch (ShellCommandUsageException e) {
			System.out.println(e.getMessage());
		} catch (ShellCommandExecException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

	}

}
