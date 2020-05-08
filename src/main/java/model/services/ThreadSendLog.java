package model.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.hyperic.sigar.shell.ShellCommandExecException;
import org.hyperic.sigar.shell.ShellCommandUsageException;

import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;

public class ThreadSendLog extends Thread {

	private String[] args;
	private Long timeout;
	private Socket client;
	private String ipServidor;

	public ThreadSendLog() {

	}

	public ThreadSendLog(String[] args, Long timeout, String ipServidor) {

		this.args = args;
		this.timeout = timeout;
		this.ipServidor = ipServidor;
	}

	@Override
	public void run() {
		// Socket client = null;
		

		try {
			client = new Socket(this.ipServidor, 12345);
			System.out.println("O cliente se conectou ao servidor!");
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
			//Alerts.showAlert("IOException", null, "Erro ao conectar com o servidor", AlertType.ERROR);
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

	}

}
