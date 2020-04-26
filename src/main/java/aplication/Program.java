package aplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.hyperic.sigar.shell.ShellCommandExecException;
import org.hyperic.sigar.shell.ShellCommandUsageException;

public class Program {

	public static void main(String[] args) throws ShellCommandUsageException, ShellCommandExecException{		
		
		SysInfo sys = new SysInfo();
		sys.processCommand(args);

		try {
		
		Socket cliente = new Socket(InetAddress.getLocalHost().getHostAddress(), 12345);
        System.out.println("O cliente se conectou ao servidor!");

        File f = new File("logs\\logCurrent.txt");
		FileInputStream in = new FileInputStream(f);
		
		OutputStream out = cliente.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(out);
		BufferedWriter writer = new BufferedWriter(osw);
		writer.write(f.getName() + "\n");
		writer.flush();
		int tamanho = 4096; // buffer de 4KB  
	    byte[] buffer = new byte[tamanho];  
	    int lidos = -1;  
	    while ((lidos = in.read(buffer, 0, tamanho)) != -1) {  
	        out.write(buffer, 0, lidos);  
	    } 
	    
        
        
        /*Scanner teclado = new Scanner(System.in);
        PrintStream saida = new PrintStream(cliente.getOutputStream());

        while (teclado.hasNextLine()) {
            saida.println(teclado.nextLine());
        }

        saida.close();
        teclado.close();*/
	    in.close();
        cliente.close();
        
        
		}catch(UnknownHostException e) {
			System.out.println(e.getMessage());
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
